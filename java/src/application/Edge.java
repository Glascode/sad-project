package application;

public class Edge {

    private Node node1;
    private Node node2;
    private String type;

    /**
     * Constructs an Edge with two nodes.
     *
     * @param node1 The first node of the edge
     * @param node2 The second node of the edge
     */
    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
        if ((node1.isInfected() && !node2.isInfected())
                || (!node1.isInfected() && node2.isInfected())) {
            type = "infectable";
        } else if (node1.getType().equals("infected")
                && node2.getType().equals("infected")) {
            type = "infected";
        } else {
            type = "healthy";
        }
    }

    /**
     * Returns the first node of this edge.
     *
     * @return The first node of this edge
     */
    public Node getNode1() {
        return node1;
    }

    /**
     * Returns the second node of this edge.
     *
     * @return The second node of this edge
     */
    public Node getNode2() {
        return node2;
    }

    /**
     * Returns the name of this edge.
     *
     * @return The name of this edge.
     */
    public String getName() {
        return node1 + "-" + node2;
    }

    public String getType() {
        return type;
    }

    public void infect() {
        type = "infected";
    }

    public boolean isInfectable() {
        return type.equals("infectable");
    }

    public boolean isInfected() {
        return type.equals("infected");
    }

    public String toString() {
        return "[" + node1 + "--" + node2 + "]";
    }
}
