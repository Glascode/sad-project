package application;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class State {

    private int numberOfMachines;
    private int numberOfInfectedMachines;
    private String player;
    private Graph graph = new SingleGraph("Network");

    public State() {

        TreeSet<String> network = new TreeSet<>();

        graph.addNode("1").setAttribute("ui.label", "1");
        graph.addNode("2").setAttribute("ui.label", "2");
        graph.addNode("3").setAttribute("ui.label", "3");
        graph.addNode("4").setAttribute("ui.label", "4");
        graph.addNode("5").setAttribute("ui.label", "5");
        graph.addNode("6").setAttribute("ui.label", "6");

        graph.addEdge("1-2", "1", "2");
        graph.addEdge("1-5", "1", "5");
        graph.addEdge("2-3", "2", "3");
        graph.addEdge("2-5", "2", "5");
        graph.addEdge("3-4", "3", "4");
        graph.addEdge("4-6", "4", "6");
        graph.addEdge("4-5", "4", "5");

        /* Infect the first (1) machine */
        graph.getNode("1").addAttribute("ui.class", "infected");

        /* Style renderer */
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        /* Style sheet */
        graph.addAttribute("ui.stylesheet", "url('src/application/style.css')");

        graph.display();
    }

    /**
     * Returns the number of safe edges.
     *
     * @return The number of safe edges
     */
    public int getValue() {
        int numberOfSafeEdges = 0;
        for (Edge edge : graph.getEachEdge()) {

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
        for (Node machine : graph) {
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
        graph.getNode(move).addAttribute("ui.class", "infected");
        for (Edge edge : graph.getEachEdge()) {
            if (edge.getNode0().getAttribute("ui.class") == "infected"
                    && edge.getNode1().getAttribute("ui.class") == "infected") {
                edge.setAttribute("ui.class", "infected");
            }
        }
    }

    public static HashSet<String> getPowerset(int a[], int n, HashSet<String> ps) {
        if (n < 0) {
            return null;
        }
        if (n == 0) {
            if (ps == null)
                ps = new HashSet<>();
            ps.add(" ");
            return ps;
        }
        ps = getPowerset(a, n - 1, ps);
        HashSet<String> tmp = new HashSet<>();
        for (String s : ps) {
            if (s.equals(" "))
                tmp.add("" + a[n - 1]);
            else
                tmp.add(s + a[n - 1]);
        }
        ps.addAll(tmp);
        return ps;
    }

    public HashSet<Edge> getDefenses() {
        HashSet<Edge> infectableEdges = new HashSet<>();
        for (Edge edge : graph.getEachEdge()) {
            if (edge.getNode0().getAttribute("ui.class") == "infected"
                    && edge.getNode1().getAttribute("ui.class") == "infected") {
                edge.setAttribute("ui.class", "infected");
            }
        }
        return infectableEdges;
    }

    public void playDefense(String move) {
        graph.removeEdge(move);
    }

    public boolean isFinished() {
        return getAttacks().isEmpty();
    }

    public String getPlayer() {
        return player;
    }

}
