package application;

import network.matrix.NetworkMatrix;

public class Infection {

    public static void main(String[] args) {

        Game game = new Game(6, 1, 0.7, 2, 2, false);
        game.run();

    }

}
