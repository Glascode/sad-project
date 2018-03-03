package application;

import AI.MinMax;
import IO.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Game {

    private Network network;
    private int aDepth;
    private int dDepth;
    private Output o = new Output();

    /**
     * Constructs a Game with m machines, a number of infected machines, the
     * probability p for two machines to be linked, the attacker depth
     * reasoning, the defender depth reasoning and the usage or not of an
     * alpha-beta pruning.
     *
     * @param m                Number of machines to be created in the network
     * @param nInfected        Number of machines to be infected
     * @param p                The probability for two machines to be linked
     * @param aDepth           The attacker depth reasoning
     * @param dDepth           The defender depth reasoning
     * @param alphaBetaPruning Usage or not of an alpha-beta pruning
     */
    public Game(int m, int nInfected, double p, int aDepth, int dDepth,
                boolean alphaBetaPruning) {
        network = new Network();
        this.aDepth = aDepth;
        this.dDepth = dDepth;

        /* Handle exceptions */
        if (m == 0) {
            o.printError("Cannot define 0 machine!");
            System.exit(1);
        }
        if (p < 0 || p > 1) {
            o.printError("Probability p must be a decimal in ]0, 1[!");
            System.exit(1);
        }
        if (nInfected >= m) {
            o.printError("Cannot infect all machines!");
            System.exit(1);
        }

        /* Generate nodes */
        for (int id = 1; id <= m; id++) {
            network.addNode(id);
        }

        /* Infect machine(s) */
        Random random = new Random();
        for (int i = 1; i <= nInfected; i++) {
            int randomId = random.nextInt(network.getNodeSet().size()) + 1;

            /* Verify that the machine to infect is not already infected */
            while (network.getNode(randomId).isInfected()) {
                randomId = random.nextInt(network.getNodeSet().size()) + 1;
            }
            network.getNode(randomId).infect();
        }

        /* Number of possible edges */
        long n = (factorial(m) / (factorial(2) * factorial(m - 2)));

        System.out.println("Number of possible edges: " + n);
        System.out.println("Binomial: " + binomial((int) n, 0.7, 10));


        int numberOfEdgesToBeGenerated = (int) Math.round(p * n);
        ArrayList<Integer> edgesToBeGenerated = new ArrayList<>();
        for (int i = 0; i < numberOfEdgesToBeGenerated; i++) {
            int randomNumber = random.nextInt((int) n);
            while (edgesToBeGenerated.contains(randomNumber)) {
                randomNumber = random.nextInt((int) n);
            }
            edgesToBeGenerated.add(randomNumber);
        }
        System.out.println("Number of edges to be generated: " + numberOfEdgesToBeGenerated);

        /* Edges generation */
        System.out.println("Generating edges...");
        int counter = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = i + 1; j <= m; j++) {
                if (edgesToBeGenerated.contains(counter)) {
                    network.addEdge(i, j);
                }
                counter++;
            }
        }

        System.out.println("Number of edges: " + network.getEdgeSet().size());

//        network.addEdge(1, 2);
//        network.addEdge(1, 5);
//        network.addEdge(2, 3);
//        network.addEdge(2, 5);
//        network.addEdge(3, 4);
//        network.addEdge(4, 5);
//        network.addEdge(4, 6);
    }

    /**
     * Reads in n, p, and k and returns (n choose k) * p^k * (1-p)^(n-k).
     *
     * @param n The total number of edges
     * @param p The probability for an edge to exist
     * @param k The number of edges amongst all edges
     * @return The probability for n edges to exist
     */
    public double binomial(int n, double p, int k) {
        return (factorial(n) / (factorial(k) * factorial(n - k)))
                * (Math.pow(p, k))
                * Math.pow(1 - p, n - k);
    }

    public static long factorial(int n) {
        if (n > 1) {
            return n * factorial(n - 1);
        } else {
            return 1;
        }
    }

    /**
     * Makes a pause in the program.
     */
    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the game until it ends.
     */
    public void run() {
        State state = new State(network);
        o.printNetwork(network);
        System.out.println("Infectable machines: " + state.getAttacks());
        System.out.println("Defenses: " + state.getDefenses());
        sleep();
        state.playAttack(5);
        o.printNetwork(network);
        sleep();

        HashSet<Edge> tempSet = new HashSet<>();
        tempSet.add(new Edge(new Node(1), new Node(2)));
        System.out.println("Play defense: [1--2]");
        state.playDefense(tempSet);
        o.printNetwork(network);
        sleep();

        tempSet = new HashSet<>();
        tempSet.add(new Edge(new Node(2), new Node(5)));
        System.out.println("Play defense: [2--5]");
        state.playDefense(tempSet);
        o.printNetwork(network);
        sleep();

        tempSet = new HashSet<>();
        tempSet.add(new Edge(new Node(4), new Node(5)));
        System.out.println("Play defense: [4--5]");
        state.playDefense(tempSet);
        o.printNetwork(network);

        MinMax minMax = new MinMax(state);
        double move;
        while (!state.isFinished()) {
            move = minMax.minMax(dDepth);
            state.playDefense(move);
            state.changePlayer();
            move = minMax.minMax(aDepth);
            state.playAttack((int) move);
            state.changePlayer();
        }
    }

}
