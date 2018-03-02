package application;

import java.util.TreeSet;

public class Network {

    private NodeSet nodeSet;
    private TreeSet<Node> infectedNodes;
    private TreeSet<Edge> edgeSet;

    public Network() {
        nodeSet = new NodeSet();
        edgeSet = new TreeSet<>();
    }

    public void addNode(int node) {
        nodeSet.add(new Node(node));
    }

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

    public void addEdge(int node1, int node2) {
        edgeSet.add(new Edge(getNode(node1), getNode(node2)));
    }

    public void removeEdge(int node1, int node2) {
        edgeSet.remove(new Edge(getNode(node1), getNode(node2)));
    }

    public TreeSet<Node> getNodeSet() {
        return nodeSet;
    }

    public TreeSet<Node> getInfectedNodes() {
        return infectedNodes;
    }

    public TreeSet<Edge> getEdgeSet() {
        return edgeSet;
    }

    public void infect(Node node) {
        infectedNodes.add(node);
        nodeSet.remove(node);
    }

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

    public String formatNetwork() {
        StringBuilder formattedNetwork = new StringBuilder();
        for (Edge edge : edgeSet) {
            formattedNetwork.append(edge);
        }
        return formattedNetwork.toString();
    }


}
