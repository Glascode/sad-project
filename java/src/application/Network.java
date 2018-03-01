package application;

import java.util.TreeSet;

public class Network extends TreeSet {

    private TreeSet<Node> nodes;
    private TreeSet<Edge> edges;

    public Network() {
        nodes = new TreeSet<>();
        edges = new TreeSet<>();
    }

    public void addNode(String node) {
        nodes.add(new Node(node));
    }

    public void addEdge(String node1, String node2) {
        edges.add(new Edge(new Node(node1), new Node(node2)));
    }

    public TreeSet<Node> getNodes() {
        return nodes;
    }

    public TreeSet<Edge> getEdges() {
        return edges;
    }

    public TreeSet<Node> getNeighborNodes(Node node) {
        TreeSet<Node> neighborNodes = new TreeSet<>();
        for (Edge e : edges) {
            if (e.getNode1().equals(node)) {
                neighborNodes.add(e.getNode2());
            } else if (e.getNode2().equals(node)) {
                neighborNodes.add(e.getNode1());
            }
        }
        return neighborNodes;
    }
}
