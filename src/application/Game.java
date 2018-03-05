package application;

import gui.GraphicalNetwork;
import io.*;

import java.util.HashSet;

public class Game {

    private Network network;
    private State state;
    private int aDepth;
    private int dDepth;

    private Input i = new Input();
    private Output o = new Output();
    private GraphicalNetwork gNet;

    /**
     * Constructs a Game with m machines, a number of infected machines, the
     * probability p for two machines to be linked, the attacker depth
     * reasoning, the defender depth reasoning and the usage or not of an
     * alpha-beta pruning.
     *
     * @param m                Number of machines to be created in the network
     * @param mInfected        Number of machines to be infected
     * @param p                The probability for two machines to be linked
     * @param aDepth           The attacker depth of reasoning
     * @param dDepth           The defender depth of reasoning
     * @param alphaBetaPruning Usage or not of an alpha-beta pruning
     */
    public Game(int m, int mInfected, double p, int aDepth, int dDepth,
                boolean alphaBetaPruning) {
        network = new Network(m, mInfected, p);
        network.generateNetwork();

        state = new State(network);
        gNet = new GraphicalNetwork(network);

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
        gNet.playDefense(edgeSet);
    }

    /**
     * Runs the game until it ends.
     */
    public void run() {
        //inMax minMax = new MinMax((State) state.clone());
        gNet = new GraphicalNetwork(network);
        gNet.display();
        o.printNetwork(network);

        int att;
        HashSet<Edge> def;
        while (!state.isFinished()) {
            att = i.askAttack(state.getAttacks());
            //att = (int) minMax.minMax((State) state.clone(), aDepth).get1();
            state.playAttack(att);
            gNet.updateGraph();
            o.printNetwork(network);
            state.changePlayer();
            sleep();
            def = i.askDefense(network.getEdgeSet());
            //def = (HashSet<Edge>) minMax.minMax((State) state.clone(), dDepth).get1();
            playDefense(def);
            gNet.updateGraph();
            o.printNetwork(network);
            sleep();
        }

        System.out.println("The game is over!");
        i.getQuit();
        System.exit(0);
    }

}
