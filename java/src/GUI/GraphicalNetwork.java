package GUI;

import application.Edge;
import application.Network;
import application.Node;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import javax.swing.text.html.CSS;
import java.io.InputStreamReader;
import java.util.HashSet;

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
        //graph.addAttribute("ui.stylesheet", "url('/resources/style.css')");
        String styleSheet = "graph { fill-color: #1E232B; } "
                + "node { fill-color: #6eaba6, white; text-alignment: under; "
                + "text-offset: 0px, 4px; text-color: white; text-size: 20; } "
                + "node.infected { fill-color: #9d0000; }"
                + "edge { fill-color: #52807c; } "
                + "edge.infected { fill-color: #740000; } ";
        graph.addAttribute("ui.stylesheet", styleSheet);

        /* Rendering quality */
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
    }

    /**
     * Plays the defense in removing edges in the edge set.
     *
     * @param edgeSet The edge set of the edges to be removed
     */
    public void playDefense(HashSet<Edge> edgeSet) {
        for (Edge edge : edgeSet) {
            graph.removeEdge(edge.getName());
        }
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
