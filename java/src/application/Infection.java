package application;

import java.util.HashSet;
import java.util.Hashtable;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Infection {

    public static void main(String[] args) {

        Graph graph = new SingleGraph("Healthy graph");
        graph.addNode("1" ).setAttribute("ui.label", "1");
        graph.addNode("2" ).setAttribute("ui.label", "2");;
        graph.addNode("3" ).setAttribute("ui.label", "3");;
        graph.addNode("4" ).setAttribute("ui.label", "4");;
        graph.addNode("5" ).setAttribute("ui.label", "5");;
        graph.addNode("6" ).setAttribute("ui.label", "6");;

        graph.addEdge("1-2", "1", "2");
        graph.addEdge("1-5", "1", "5");

        graph.addEdge("2-3", "2", "3");
        graph.addEdge("2-5", "2", "5");

        graph.addEdge("3-4", "3", "4");

        graph.addEdge("4-6", "4", "6");
        graph.addEdge("4-5", "4", "5");

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph.addAttribute("ui.stylesheet", "url('src/application/style.css')");
        graph.display();


        Hashtable<Integer, HashSet<Integer>> network = new Hashtable<>();

        HashSet<Integer> machine1 = new HashSet<>();
        HashSet<Integer> machine2 = new HashSet<>();
        HashSet<Integer> machine3 = new HashSet<>();
        HashSet<Integer> machine4 = new HashSet<>();
        HashSet<Integer> machine5 = new HashSet<>();
        HashSet<Integer> machine6 = new HashSet<>();

        machine1.add(2);
        machine1.add(5);

        machine2.add(3);
        machine2.add(5);
        machine2.add(1);

        machine3.add(4);
        machine3.add(2);

        machine4.add(6);
        machine4.add(5);
        machine4.add(2);
        machine4.add(3);

        machine5.add(1);
        machine5.add(2);
        machine5.add(4);

        machine6.add(4);

        network.put(1, machine1);
        network.put(2, machine2);
        network.put(3, machine3);
        network.put(4, machine4);
        network.put(5, machine5);

        System.out.println("Network: " + network);
    }

}
