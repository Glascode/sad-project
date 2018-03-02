package application;

import network.matrix.NetworkMatrix;

public class InfectionMatrix {

    public static void main(String[] args) throws InterruptedException {

        NetworkMatrix networkMatrix = new NetworkMatrix(6);
        networkMatrix.addEdge(1, 2);
        networkMatrix.addEdge(1, 5);
        networkMatrix.addEdge(2, 3);
        networkMatrix.addEdge(2, 5);
        networkMatrix.addEdge(3, 4);
        networkMatrix.addEdge(4, 6);
        networkMatrix.addEdge(4, 5);
        System.out.println(networkMatrix);
        networkMatrix.printNetwork();

        Game game = new Game(6);
        game.run();

    }

}
