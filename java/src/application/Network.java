package application;

import java.util.HashSet;
import java.util.TreeSet;

public class Network {

    private TreeSet<Node> nodeSet;
    private HashSet<Edge> edgeSet;

    public Network() {
        nodeSet = new TreeSet<>();
        edgeSet = new HashSet<>();
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

    public void removeEdge(Edge edge) {
        edgeSet.remove(edge);
    }

    public TreeSet<Node> getNodeSet() {
        return nodeSet;
    }

    public HashSet<Edge> getEdgeSet() {
        return edgeSet;
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
        for (Node node : nodeSet) {
            formattedNetwork.append(node + " => ");
            for (Edge edge : edgeSet) {
                if (edge.getNode1().equals(node)) {
                    formattedNetwork.append(edge.getNode2());
                    formattedNetwork.append(", ");
                } else if (edge.getNode2().equals(node)) {
                    formattedNetwork.append(edge.getNode1());
                    formattedNetwork.append(", ");
                }
            }
            formattedNetwork.append("\n");
        }
        return formattedNetwork.toString();
    }

    public void printNetwork() {
        System.out.println(formatNetwork());
    }

}
