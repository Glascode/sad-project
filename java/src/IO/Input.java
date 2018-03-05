package IO;

import application.Edge;
import application.Node;

import java.util.HashSet;
import java.util.Scanner;

public class Input {

    private Output o = new Output();

    /**
     * Returns {@code true} if the input is a positive integer value.
     *
     * @param input The input to be parsed
     * @return {@code true} if the input is conform; {@code false} otherwise
     */
    public boolean isConformInteger(String input, String errorMessage) {
        int value;
        try {
            value = Integer.valueOf(input);
        } catch (NumberFormatException e) {
            o.printError(errorMessage);
            return false;
        }
        if (value <= 0) {
            o.printError(errorMessage);
            return false;
        }
        return true;
    }

    /**
     * Asks an attack to the user.
     *
     * @return The raw user attack
     */
    private String getAttack() {
        Scanner input = new Scanner(System.in);
        System.out.print("Id of the machine to attack > ");
        return input.nextLine();
    }

    /**
     * Asks the user to issue an attack.
     *
     * @return The conform issued attack
     */
    public int askAttack() {
        String move = getAttack();
        String errorMessage = "Illegal value. A machine must be a "
                + "positive integer!";
        while (!isConformInteger(move, errorMessage)) {
            move = getAttack();
        }
        return Integer.valueOf(move);
    }

    /**
     * Returns {@code true} if the defense move is conform.
     *
     * @param move The move to be parsed
     * @return {@code true} if the move is conform; {@code false} otherwise
     */
    private boolean isConformDefense(String move, HashSet<Edge> edgeSet) {
        if ((!move.contains(",") && move.length() > 3) || move.length() < 3) {
            o.printError("Unaccepted command. An defense must be for instance "
                    + "'1-2,1-3'.");
            return false;
        }
        String[] elements = move.split(",");
        for (String element : elements) {
            if (element.length() != 3) {
                o.printError("Unaccepted command. An edge must be for instance "
                        + "'1-2'.");
                return false;
            }

            /* Element length is 3 here */
            if (!Character.isDigit(element.charAt(0))
                    || (element.charAt(1) != '-')
                    || !Character.isDigit(element.charAt(2))) {
                o.printError("Illegal characters. An edge must be for instance "
                        + "'1-2'.");
                return false;
            }

            /* Element is correct here */
            int node1 = Character.getNumericValue(element.charAt(0));
            int node2 = Character.getNumericValue(element.charAt(2));
            if (!edgeSet.contains(new Edge(new Node(node1), new Node(node2)))) {
                o.printError("Edge " + element + " not found.");
                return false;
            }
        }
        return true;
    }

    /**
     * Asks a defense to the user.
     *
     * @return The raw user attack
     */
    private String getDefense() {
        Scanner input = new Scanner(System.in);
        System.out.print("Edge(s) to defend > ");
        return input.nextLine();
    }

    /**
     * Format the defense move.
     *
     * @param move The conform move to be formatted
     * @return The formatted defense move
     */
    private HashSet<Edge> formatDefense(String move) {
        HashSet<Edge> formattedDefense = new HashSet<>();
        String[] moves = move.split(",");
        for (String e : moves) {
            int node1 = Character.getNumericValue(e.charAt(0));
            int node2 = Character.getNumericValue(e.charAt(2));
            if (node1 > node2) {

                /* Swap the nodes */
                node1 = node1 + node2;
                node2 = node1 - node2;
                node1 = node1 - node2;
            }
            Edge edge = new Edge(new Node(node1), new Node(node2));
            formattedDefense.add(edge);
        }
        return formattedDefense;
    }

    /**
     * Asks the user to issue a defense.
     *
     * @return The conform issued defense
     */
    public HashSet<Edge> askDefense(HashSet<Edge> edgeSet) {
        String move = getDefense();
        while (!isConformDefense(move, edgeSet)) {
            move = getDefense();
        }
        return formatDefense(move);
    }

    /**
     * Asks a number of machines to the user.
     *
     * @return The raw user input
     */
    private String getMachines() {
        Scanner input = new Scanner(System.in);
        System.out.print("Number of machines > ");
        return input.nextLine();
    }

    /**
     * Asks a number of machines to be infected to the user.
     *
     * @return The raw user input
     */
    private String getInfectedMachines() {
        Scanner input = new Scanner(System.in);
        System.out.print("Number of machines to initially infect > ");
        return input.nextLine();
    }

    /**
     * Asks the user a number of machines or infected machines.
     *
     * @return The conform issued number of machines
     */
    public int askMachines(String type) {
        String n;
        String errorMessage = "Illegal value. A machine must be a "
                + "positive integer!";
        if (type.equals("infected")) {
            n = getInfectedMachines();
            while (!isConformInteger(n, errorMessage)) {
                n = getInfectedMachines();
            }
        } else {
            n = getMachines();
            while (!isConformInteger(n, errorMessage)) {
                n = getMachines();
            }
        }
        return Integer.valueOf(n);
    }

    /**
     * Returns {@code true} if the probability input is conform.
     *
     * @param input The input to be parsed
     * @return {@code true} if the input is conform; {@code false} otherwise
     */
    public boolean isConformProbability(String input) {
        float value;
        try {
            value = Float.valueOf(input);
        } catch (NumberFormatException e) {
            o.printError("Illegal characters. Probability must be a decimal in ]0, 1[!");
            return false;
        }
        if (value <= 0 || value >= 1) {
            o.printError("Illegal value. Probability must be a decimal in ]0, 1[!");
            return false;
        }
        return true;
    }

    /**
     * Asks a percentage to the user.
     *
     * @return The raw user input
     */
    private String getProbability() {
        Scanner input = new Scanner(System.in);
        System.out.print("Probability for an edge to exist > ");
        return input.nextLine();
    }

    /**
     * Asks the user a probability for an edge to exist.
     *
     * @return The conform issued probability
     */
    public float askProbability() {
        String p = getProbability();
        while (!isConformProbability(p)) {
            p = getProbability();
        }
        return Float.valueOf(p);
    }

    /**
     * Asks a depth of reasoning to the user.
     *
     * @return The raw user input
     */
    private String getDepth(String player) {
        Scanner input = new Scanner(System.in);
        System.out.print("Depth of reasoning of the " + player + " > ");
        return input.nextLine();
    }

    /**
     * Asks the user a depth of reasoning.
     *
     * @return The conform issued probability
     */
    public int askDepth(String player) {
        String d = getDepth(player);
        String errorMessage = "Illegal value. The depth fo reasoning must be a "
                + "positive integer!";
        while (!isConformInteger(d, errorMessage)) {
            d = getDepth(player);
        }
        return Integer.valueOf(d);
    }

    /**
     * Asks a depth of reasoning to the user.
     *
     * @return The raw user input
     */
    private String getAlphaBeta() {
        Scanner input = new Scanner(System.in);
        System.out.print("Usage of an alpha-beta pruning (true or false) > ");
        return input.nextLine();
    }

    /**
     * Asks the user a depth of reasoning.
     *
     * @return The conform issued probability
     */
    public boolean askAlphaBeta() {
        String alphaBeta = getAlphaBeta();
        while (!(alphaBeta.equals("true") || alphaBeta.equals("false"))) {
            o.printError("Illegal characters. Please enter 'true' or 'false'");
            alphaBeta = getAlphaBeta();
        }
        return Boolean.valueOf(alphaBeta);
    }

    public String getQuit() {
        Scanner input = new Scanner(System.in);
        System.out.print("Press any key to quit > ");
        return input.nextLine();
    }

}
