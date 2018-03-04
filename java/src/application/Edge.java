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
        return node1.getName() + "-" + node2.getName();
    }

    /**
     * Returns the type of this edge.
     *
     * @return The type of this edge.
     */
    public String getType() {
        return type;
    }

    /**
     * Infects this edge in setting its type to "infected".
     */
    public void infect() {
        type = "infected";
    }

    /**
     * Returns {@code true} if this edge is infectable.
     *
     * @return {@code true} if this edge is infectable; {@code false} otherwise.
     */
    public boolean isInfectable() {
        return type.equals("infectable");
    }

    /**
     * Returns {@code true} if this edge is infected.
     *
     * @return {@code true} if this edge is infected; {@code false} otherwise.
     */
    public boolean isInfected() {
        return type.equals("infected");
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The object to be compared.
     * @return {@code true} if this object is the same as the obj argument;
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {

        /* If the object is compared with itself then return true */
        if (this == obj) {
            return true;
        }

        /*
         * Check if obj is an instance of Node or not.
         * "null instanceof Node" also returns false.
         */
        if (!(obj instanceof Edge)) {
            return false;
        }

        /* Typecast obj to Node so that we can compare data members */
        Edge edge = (Edge) obj;

        /* Compare the data members and return accordingly */
        return (((edge.getNode1().getId() == this.getNode1().getId())
                && (edge.getNode2().getId() == this.getNode2().getId()))
                || ((edge.getNode1().getId() == this.getNode2().getId())
                && (edge.getNode2().getId() == this.getNode1().getId())));
    }

    /**
     * Returns the hash code value for this edge.
     *
     * @return The integer hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getNode1().getId() + getNode2().getId();
        return result;
    }

    /**
     * Returns the representation of this edge.
     *
     * @return The representation of this edge.
     */
    public String toString() {
        return "(" + node1 + "-" + node2 + ")";
    }
}
