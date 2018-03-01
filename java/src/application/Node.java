package application;

public class Node {

    private String name;
    private String state;

    public Node(String name) {
        this.name = name;
    }

    public void infect() {
        state = "infected";
    }

}
