package GUI;

import application.Edge;
import application.Network;
import application.Node;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class GraphicalNetwork {

    private Network network;
    private Graph graph;

    public GraphicalNetwork(Network network) {
        this.network = network;

        graph = new SingleGraph("Network");

        /* Add nodes to graph */
        for (Node node : network.getNodeSet()) {
            graph.addNode(node.getName()).setAttribute("ui.label", node.getName());
            graph.getNode(node.getName()).addAttribute("ui.class", node.getType());
        }

        /* Add edges to graph */
        for (Edge edge : network.getEdgeSet()) {
            String node1Name = edge.getNode1().getName();
            String node2Name = edge.getNode2().getName();
            graph.addEdge(edge.getName(), node1Name, node2Name);
            graph.getEdge(edge.getName()).addAttribute("ui.class", edge.getType());
        }

        /* Style renderer */
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        /* Style sheet */
        graph.addAttribute("ui.stylesheet", "url('src/application/style.css')");
    }

    public void updateGraph() {
        for (Node node : network.getNodeSet()) {
            graph.getNode(node.getName()).addAttribute("ui.class", node.getType());
        }
        for (Edge edge : network.getEdgeSet()) {
            graph.getEdge(edge.getName()).addAttribute("ui.class", edge.getType());
        }
    }

    public void display() {
        graph.display();
    }

}
