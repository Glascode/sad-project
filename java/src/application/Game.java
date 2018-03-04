package application;

import AI.MinMax;
import util.Tuple;
import GUI.GraphicalNetwork;
import IO.*;

import java.util.HashSet;

public class Game {

    private Network network;
    private State state;
    private int aDepth;
    private int dDepth;

    private Input i = new Input();
    private Output o = new Output();
    private GraphicalNetwork gui;

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

        network = new Network(m, nInfected, p);
        network.generateNetwork();

        state = new State(network);
        gui = new GraphicalNetwork(network);

        this.aDepth = aDepth;
        this.dDepth = dDepth;
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
     * Makes a pause in the program with a given duration.
     *
     * @param millis The duration in milliseconds
     */
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Notify the state and the graphical network to play a defense.
     *
     * @param edgeSet The edges to be defended.
     */
    public void playDefense(HashSet<Edge> edgeSet) {
        state.playDefense(edgeSet);
        gui.playDefense(edgeSet);
    }

    /**
     * Runs the game until it ends.
     */
    public void run() {
        //inMax minMax = new MinMax((State) state.clone());
        gui = new GraphicalNetwork(network);
        gui.display();
        o.printNetwork(network);

        int att;
        HashSet<Edge> def;
        while (!state.isFinished()) {
            att = i.askAttack();
            //att = (int) minMax.minMax((State) state.clone(), aDepth).get1();
            state.playAttack(att);
            gui.updateGraph();
            o.printNetwork(network);
            state.changePlayer();
            sleep();
            def = i.askDefense();
            //def = (HashSet<Edge>) minMax.minMax((State) state.clone(), dDepth).get1();
            playDefense(def);
            gui.updateGraph();
            o.printNetwork(network);
            sleep();
        }

        System.out.println("The game is over!");
    }

}
