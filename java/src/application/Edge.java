package application;

public class Edge {

    private Node node1;
    private Node node2;
    private String type;

    public Edge(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
        if ((node1.isInfected() && !node2.isInfected())
                || (!node1.isInfected() && node2.isInfected()))
        {
            type = "infectable";
        } else if (node1.getType().equals("infected") && node2.equals("infected")) {
            type = "infected";
        } else {
            type = "healthy";
        }
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public String getName() {
        return node1 + "-" + node2;
    }

    public String getType() {
        return type;
    }

    public boolean isInfected() {
        return type.equals("infected");
    }

    public String toString() {
        return "[" + node1 + "--" + node2 + "]";
    }
}
