package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

public class Network {

    private int m, nInfected;
    private double p;
    private TreeSet<Node> nodeSet;
    private HashSet<Edge> edgeSet;
    private Random random = new Random();

    public Network(int m, int nInfected, double p) {
        this.m = m;
        this.nInfected = nInfected;
        this.p = p;
        nodeSet = new TreeSet<>();
        edgeSet = new HashSet<>();
    }

    /**
     * Generates the network.
     */
    public void generateNetwork() {
        generateNodes();
        infectMachines();
        generateEdges();
    }

    /**
     * Generates the {@code m} nodes.
     */
    private void generateNodes() {
        for (int id = 1; id <= m; id++) {
            addNode(id);
        }
    }

    /**
     * Infects {@code nInfected} machines.
     */
    private void infectMachines() {
        for (int i = 1; i <= nInfected; i++) {
            int randomId = random.nextInt(getNodeSet().size()) + 1;

            /* Verify that the machine to infect is not already infected */
            while (getNode(randomId).isInfected()) {

                /*
                 * If the machine is already infected, then pick another random
                 * integer
                 */
                randomId = random.nextInt(getNodeSet().size()) + 1;
            }
            getNode(randomId).infect();
        }
    }

    /**
     * Randomly generates edges according to the percentage {@code p}.
     */
    private void generateEdges() {
        /* Total number of possible edges */
        long n = (factorial(m) / (factorial(2) * factorial(m - 2)));

        /* Part of edges to be generated according to a percentage p */
        int numberOfEdgesToBeGenerated = (int) Math.round(p * n);

        /* List of identifiers of edges to be generated */
        ArrayList<Integer> edgesToBeGenerated = new ArrayList<>();
        for (int i = 0; i < numberOfEdgesToBeGenerated; i++) {

            /* Pick a random number (random identifier of an edge) */
            int randomNumber = random.nextInt((int) n);
            while (edgesToBeGenerated.contains(randomNumber)) {
                randomNumber = random.nextInt((int) n);
            }

            /* And add it to the list of identifiers of edges to be generated */
            edgesToBeGenerated.add(randomNumber);
        }

        /* Actual edges generation */
        int counter = 0;  // the id of the edge to be tested
        for (int i = 1; i <= m; i++) {
            for (int j = i + 1; j <= m; j++) {
                if (edgesToBeGenerated.contains(counter)) {

                    /* If the edge is an edge to be generated */
                    addEdge(i, j);
                }
                counter++;
            }
        }
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

    /**
     * Returns the factorial of the integer n
     *
     * @param n The integer
     * @return The factorial of the integer n
     */
    public static long factorial(int n) {
        if (n > 1) {
            return n * factorial(n - 1);
        }
        return 1;
    }

    /**
     * Adds a node to this network.
     *
     * @param node The node to be added
     */
    public void addNode(int node) {
        nodeSet.add(new Node(node));
    }

    /**
     * Returns the node object with the id passed in argument.
     *
     * @param id The id of the node to be returned
     * @return The node object with the id passed in argument
     */
    public Node getNode(int id) {
        Node node = new Node(id);
        if (nodeSet.contains(node)) {
            for (Node n : nodeSet) {
                if (n.equals(node))
                    return n;
            }
        }
        return null;
    }

    /**
     * Adds an edge of two nodes to this network.
     *
     * @param node1 The first node of the edge to be added
     * @param node2 The second node of the edge to be added
     */
    public void addEdge(int node1, int node2) {
        edgeSet.add(new Edge(getNode(node1), getNode(node2)));
    }

    /**
     * Removes an edge from this network.
     *
     * @param edge The edge to be removed
     */
    public void removeEdge(Edge edge) {
        edgeSet.remove(edge);
    }

    /**
     * Returns this node set.
     *
     * @return This node set
     */
    public TreeSet<Node> getNodeSet() {
        return nodeSet;
    }

    /**
     * Returns this edge set.
     *
     * @return This edge set
     */
    public HashSet<Edge> getEdgeSet() {
        return edgeSet;
    }

    /**
     * Returns the neighbour(s) node(s) of the node passed in argument.
     *
     * @param node The node to be parsed
     * @return The neighbour(s) node(s) of the node passed in argument
     */
    public TreeSet<Node> getNeighbourNodes(Node node) {
        TreeSet<Node> neighborNodes = new TreeSet<>();
        for (Edge e : edgeSet) {
            if (e.getNode1().equals(node)) {
                neighborNodes.add(e.getNode2());
            } else if (e.getNode2().equals(node)) {
                neighborNodes.add(e.getNode1());
            }
        }
        return neighborNodes;
    }

    /**
     * Formats the network in a string.
     *
     * @return The formatted network
     */
    public String formatNetwork() {
        StringBuilder formattedNetwork = new StringBuilder();
        for (Node node : nodeSet) {
            formattedNetwork.append(node.getRepresentation() + " =>");
            for (Edge edge : edgeSet) {
                if (edge.getNode1().equals(node)) {
                    formattedNetwork.append(" " + edge.getNode2());
                    formattedNetwork.append(",");
                } else if (edge.getNode2().equals(node)) {
                    formattedNetwork.append(" " + edge.getNode1());
                    formattedNetwork.append(",");
                }
            }
            formattedNetwork.append("\n");
        }
        return formattedNetwork.toString();
    }

}
