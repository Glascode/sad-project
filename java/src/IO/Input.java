package IO;

import application.Edge;
import application.Node;

import java.util.HashSet;
import java.util.Scanner;

public class Input {

    private Output o = new Output();

    /**
     * Returns {@code true} if the attack move is conform.
     *
     * @param move The move to be parsed
     * @return {@code true} if the move is conform; {@code false} otherwise
     */
    private boolean isConformAttack(String move) {
        if (move.length() != 1) {
            return false;
        }

        /* Move length is 1 here */
        return Character.isDigit(move.charAt(0));
    }

    /**
     * Returns {@code true} if the defense move is conform.
     *
     * @param move The move to be parsed
     * @return {@code true} if the move is conform; {@code false} otherwise
     */
    private boolean isConformDefense(String move) {
        String[] elements = move.split(",");
        for (String element : elements) {
            if (element.length() != 3) {
                return false;
            }

            /* Move length is 3 here */
            if (!Character.isDigit(move.charAt(0))
                    || (move.charAt(1) != '-')
                    || !Character.isDigit(move.charAt(2))) {
                return false;
            }
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
        while (!isConformAttack(move)) {
            move = getAttack();
        }
        return Integer.valueOf(move);
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
        String[] edges = move.split(",");
        for (String e : edges) {
            int node1 = Character.getNumericValue(move.charAt(0));
            int node2 = Character.getNumericValue(move.charAt(2));
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
    public HashSet<Edge> askDefense() {
        String move = getDefense();
        while (!isConformDefense(move)) {
            move = getDefense();
        }
        return formatDefense(move);
    }
}
