package application;

public class Node implements Comparable {

    private int id;
    private String type;

    /**
     * Constructs a Node with an id and a type.
     *
     * @param id   The id of the node
     * @param type The type of the node
     */
    public Node(int id, String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Constructs a Node with an id and sets his type to "healthy".
     *
     * @param id The id of the node
     */
    public Node(int id) {
        this(id, "healthy");
    }

    /**
     * Returns the raw id of this node.
     *
     * @return The raw id of this node
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the string id (name) of this node.
     *
     * @return The name of this node
     */
    public String getName() {
        return String.valueOf(id);
    }

    /**
     * Returns the type of this node.
     *
     * @return The type of this node
     */
    public String getType() {
        return type;
    }

    /**
     * Infect this node in setting his type to "infected".
     */
    public void infect() {
        type = "infected";
    }

    /**
     * Returns {@code true} if this node is infected.
     *
     * @return {@code true} if this node is infected; {@code false} otherwise.
     */
    public boolean isInfected() {
        return type.equals("infected");
    }

    /**
     * Returns the representation of the node with its type.
     *
     * @return The the representation of the node with its type
     */
    public String getRepresentation() {
        return "[" + getType().toUpperCase() + "] " + getName();
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
        if (!(obj instanceof Node)) {
            return false;
        }

        /* Typecast obj to Node so that we can compare data members */
        Node node = (Node) obj;

        /* Compare the data members and return accordingly */
        return node.getId() == id;
    }

    /**
     * Returns the hash code value for this Node.
     *
     * @return The integer hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        return result;
    }

    /**
     * If this object id is greater than the received object id, then this
     * object is greater than the other.
     *
     * @param obj The object to be compared
     * @return A negative integer, zero, or a positive integer as this object is
     * less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Object obj) {
        Node n = (Node) obj;
        return Integer.compare(id, n.id);
    }

    /**
     * Returns the string representation of this node.
     *
     * @return The string representation of this node
     */
    @Override
    public String toString() {
        return getName();
    }
}
