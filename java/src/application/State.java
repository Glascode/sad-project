package application;

import java.util.HashSet;
import java.util.TreeSet;

public class State {

    private int numberOfMachines;
    private int numberOfInfectedMachines;
    private String player;
    private Network network;

    public State(Network network) {
        this.network = network;
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
    public TreeSet<Integer> getAttacks() {
        TreeSet<Integer> infectableMachines = new TreeSet<>();
        for (Node machine : network.getNodeSet()) {
            if (machine.isInfected()) {
                for (Node neighbour : network.getNeighbourNodes(machine)) {
                    if (!neighbour.isInfected()) {
                        infectableMachines.add(neighbour.getId());
                    }
                }
            }
        }
        return infectableMachines;
    }

    public void playAttack(int machine) {
        network.getNode(machine).infect();
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

    public TreeSet<Edge> getDefenses() {
        TreeSet<Edge> infectableEdges = new TreeSet<>();
        for (Edge edge : network.getEdgeSet()) {
            if (edge.isInfectable()) {
                infectableEdges.add(edge);
            }
        }
        return infectableEdges;
    }

    public void playDefense(Edge edge) {
        network.removeEdge(edge);
    }

    public boolean isFinished() {
        return getAttacks().isEmpty();
    }

    public String getPlayer() {
        return player;
    }

}
