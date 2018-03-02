package application;

public class Node {

    private int id;
    private String type;

    public Node(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Node(int id) {
        this(id, "healthy");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Integer.toString(id);
    }

    public String getType() {
        return type;
    }

    public void infect() {
        type = "infected";
    }

    public boolean isInfected() {
        return type.equals("infected");
    }

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

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        return result;
    }
}
