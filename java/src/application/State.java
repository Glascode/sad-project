package application;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.HashSet;
import java.util.Iterator;

public class State {

    private int numberOfMachines;
    private int numberOfInfectedMachines;
    private String player;
    private Graph network = new SingleGraph("Network");

    public State() {

        network.addNode("1").setAttribute("ui.label", "1");
        network.addNode("2").setAttribute("ui.label", "2");
        network.addNode("3").setAttribute("ui.label", "3");
        network.addNode("4").setAttribute("ui.label", "4");
        network.addNode("5").setAttribute("ui.label", "5");
        network.addNode("6").setAttribute("ui.label", "6");

        network.addEdge("1-2", "1", "2");
        network.addEdge("1-5", "1", "5");
        network.addEdge("2-3", "2", "3");
        network.addEdge("2-5", "2", "5");
        network.addEdge("3-4", "3", "4");
        network.addEdge("4-6", "4", "6");
        network.addEdge("4-5", "4", "5");

        /* Infect the first (1) machine */
        network.getNode("1").addAttribute("ui.class", "infected");

        /* Style renderer */
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        /* Style sheet */
        network.addAttribute("ui.stylesheet", "url('src/application/style.css')");

        network.display();
    }

    /**
     * Returns the number of safe edges.
     *
     * @return The number of safe edges
     */
    public int getValue() {
        int numberOfSafeEdges = 0;
        for (Edge edge : network.getEachEdge()) {

            /* If both nodes of the edge are not infected */
            if (edge.getNode0().getAttribute("ui.class") != "infected"
                    && edge.getNode1().getAttribute("ui.class") != "infected") {
                numberOfSafeEdges++;
            }
        }
        return numberOfSafeEdges;
    }

    /**
     * Returns a set of infectable machines.
     *
     * @return The set of infectable machines
     */
    public HashSet<Node> getAttacks() {
        HashSet<Node> infectableMachines = new HashSet<>();
        for (Node machine : network) {
            if (machine.getAttribute("ui.class") == "infected") {
                Iterator<Node> machineItr = machine.getNeighborNodeIterator();
                while (machineItr.hasNext()) {
                    Node nextMachine = machineItr.next();
                    if (nextMachine.getAttribute("ui.class") != "infected") {
                        infectableMachines.add(nextMachine);
                    }
                }
            }
        }
        return infectableMachines;
    }

    public void playAttack(String move) {
        network.getNode(move).addAttribute("ui.class", "infected");
        for (Edge edge : network.getEachEdge()) {
            if (edge.getNode0().getAttribute("ui.class") == "infected"
                    && edge.getNode1().getAttribute("ui.class") == "infected") {
                edge.setAttribute("ui.class", "infected");
            }
        }
    }

    public HashSet<Edge> getDefenses() {
        HashSet<Edge> infectableEdges = new HashSet<>();
        for (Edge edge : network.getEachEdge()) {
            if (edge.getNode0().getAttribute("ui.class") == "infected"
                    && edge.getNode1().getAttribute("ui.class") == "infected") {
                edge.setAttribute("ui.class", "infected");
            }
        }
        return infectableEdges;
    }

    public void playDefense(String move) {
        network.removeEdge(move);
    }

}
