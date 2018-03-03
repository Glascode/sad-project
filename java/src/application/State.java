package application;

import java.util.*;

public class State {

    private int numberOfMachines;
    private int numberOfInfectedMachines;
    private String player;
    private Network network;

    /**
     * Constructs a state according to a network.
     *
     * @param network A network
     */
    public State(Network network) {
        this.network = network;
        player = "defender";
    }

    /**
     * Returns the number of safe edges in this network.
     *
     * @return The number of safe edges in this network
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
    public HashSet<Integer> getAttacks() {
        HashSet<Integer> infectableMachines = new HashSet<>();
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

    /**
     * Returns the power set of a given set.
     *
     * @param set The given set
     * @param <T> The type of the items in the set
     * @return The power set of the given set
     */
    public static <T> HashSet<HashSet<T>> powerSet(Collection<T> set) {
        HashSet<HashSet<T>> powerSet = new HashSet<>();
        powerSet.add(new HashSet<>()); // add the empty set

        /* for every item in the original set */
        for (T item : set) {
            HashSet<HashSet<T>> newPowerSet = new HashSet<>();
            for (HashSet<T> subset : powerSet) {
                /* copy all of the current power set's subsets */
                newPowerSet.add(subset);

                /* plus the subsets appended with the current item */
                HashSet<T> newSubset = new HashSet<>(subset);
                newSubset.add(item);
                newPowerSet.add(newSubset);
            }

            /* powerset is now powerset of set.subList(0, set.indexOf(item) + 1) */
            powerSet = newPowerSet;
        }
        return powerSet;
    }

    public HashSet<HashSet<Edge>> getDefenses() {
        HashSet<Edge> infectableEdges = new HashSet<>();
        for (Edge edge : network.getEdgeSet()) {
            if (edge.isInfectable()) {
                infectableEdges.add(edge);
            }
        }
        return powerSet(infectableEdges);
    }

    public void playDefense(HashSet<Edge> edgeSet) {
        for (Edge edge : edgeSet) {
            network.removeEdge(edge);
        }
    }

    /**
     * Returns {@code true} if the state is finished (no more attacks).
     *
     * @return {@code true} if the state is finished; {@code false} otherwise
     */
    public boolean isFinished() {
        return getAttacks().isEmpty();
    }

    /**
     * Changes the player.
     */
    public void changePlayer() {
        if (player.equals("defender")) {
            player = "attacker";
        } else {
            player = "defender";
        }
    }

    /**
     * Returns the current player.
     *
     * @return The current player
     */
    public String getPlayer() {
        return player;
    }

}
