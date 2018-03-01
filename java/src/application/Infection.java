package application;

public class Infection {

    public static void main(String[] args) throws InterruptedException {

        Network network = new Network();

        network.addNode("1");
        network.addNode("2");
        network.addNode("3");
        network.addNode("4");

        network.addEdge("1", "2");
        network.addEdge("1", "3");
        network.addEdge("2", "4");

        Game game = new Game(6);
        game.run();

    }

}
