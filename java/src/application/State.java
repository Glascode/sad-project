package application;

import java.util.HashSet;
import java.util.TreeSet;

public class State {

    private int numberOfMachines;
    private int numberOfInfectedMachines;
    private String player;
    private Network network;

    public State() {
        network = new Network();

        network.addNode(1);
        network.addNode(2);
        network.addNode(3);
        network.addNode(4);
        network.addNode(5);
        network.addNode(6);

        network.addEdge(1, 2);
        network.addEdge(1, 5);
        network.addEdge(2, 3);
        network.addEdge(2, 5);
        network.addEdge(3, 4);
        network.addEdge(4, 5);
        network.addEdge(4, 6);

        /* Infect the machine (1) */
        network.getNode(1).infect();
    }

    /**
     * Returns the number of safe edges.
     *
     * @return The number of safe edges
     */
    public int getValue() {
        int numberOfSafeEdges = 0;
        for (Edge edge : network.getEdgeSet()) {

            /* If both nodes of the edge are not infected */
            if (edge.getType().equals("healthy")) {
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
    public TreeSet<Node> getAttacks() {
        TreeSet<Node> infectableMachines = new TreeSet<>();
        for (Node machine : network.getNodeSet()) {
            if (machine.isInfected()) {
                for (Node neighbour : network.getNeighbourNodes(machine)) {
                    if (!neighbour.isInfected()) {
                        infectableMachines.add(machine);
                    }
                }
//                Iterator<Node> machineItr = machine.getNeighborNodeIterator();
//                while (machineItr.hasNext()) {
//                    Node nextMachine = machineItr.next();
//                    if (nextMachine.getAttribute("ui.class") != "infected") {
//                        infectableMachines.add(nextMachine);
//                    }
//                }
            }
        }
        return infectableMachines;
    }

    public void playAttack(int move) {
        network.getNode(move).infect();
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
