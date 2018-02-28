package application;

public class Game {

    private int numberOfMachines;

    public Game(int numberOfMachines) {
        this.numberOfMachines = numberOfMachines;
    }

    /*public Game(int numberOfMachines, int numberOfInfectedMachines,
                     int edgesProbability, int attackerDepth, int defenderDepth,
                     boolean alphaBetaPruning) {
        this.numberOfMachines = numberOfMachines;
        state = new State(3, 1);
    }*/

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        State state = new State();
        sleep();
        state.playAttack("5");
        sleep();
        state.playDefense("1-2");
        sleep();
        state.playDefense("2-5");
        sleep();
        state.playDefense("4-5");
    }

}
