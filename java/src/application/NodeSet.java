package application;

import java.util.Iterator;
import java.util.TreeSet;

public class NodeSet extends TreeSet<Node> {

    public NodeSet() {
        super();
    }

    public Node getNode(int id) {
        for (Node node : this) {
            if (node.equals(new Node(id))) {
                return node;
            }
        }
        return null;
    }
}
