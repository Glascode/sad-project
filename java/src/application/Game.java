package application;

import IO.*;

import java.util.Random;

public class Game {

    private Network network;
    private Output o = new Output();

    public Game(int nMachines, int nInfected, double p) {
        network = new Network();

        /* Handle exceptions */
        if (nMachines == 0) {
            o.printError("Cannot define 0 machine!");
            System.exit(1);
        }
        if (p < 0 || p > 1) {
            o.printError("Variable p must be a decimal between 0 and 1!");
            System.exit(1);
        }
        if (nInfected >= nMachines) {
            o.printError("Cannot infect all machines!");
            System.exit(1);
        }

        /* Generate nodes */
        for (int i = 1; i <= nMachines; i++) {
            network.addNode(i);
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

        /* Generate nodes */
        for (int i = 1; i <= nMachines; i++) {
            for (int j = i + 1; j <= nMachines; j++) {
                network.addEdge(i, j);
            }
        }

//        network.addEdge(1, 2);
//        network.addEdge(1, 5);
//        network.addEdge(2, 3);
//        network.addEdge(2, 5);
//        network.addEdge(3, 4);
//        network.addEdge(4, 5);
//        network.addEdge(4, 6);

        o.printNetwork(network);
    }

    public static int factorial(int n) {
        if (n > 1) {
            return n * factorial(n - 1);
        } else {
            return 1;
        }
    }

    /*
    public Game(int numberOfMachines, int numberOfInfectedMachines,
                     int p, int attackerDepth, int defenderDepth,
                     boolean alphaBetaPruning) {
        this.numberOfMachines = numberOfMachines;
    }
    */

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
        System.out.println("Infectable machines: " + state.getAttacks());
        sleep();
        state.playAttack(5);
        sleep();
        state.playDefense(new Edge(new Node(1), new Node(2)));
        sleep();
        state.playDefense(new Edge(new Node(2), new Node(5)));
        sleep();
        state.playDefense(new Edge(new Node(4), new Node(5)));
    }

}
