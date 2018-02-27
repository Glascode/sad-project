package application;

import java.util.HashSet;
import java.util.Hashtable;

public class State {

    private Hashtable network;
    private int machine;
    private HashSet infectedMachines;

    public void State(Hashtable network, int machine, HashSet infectedMachines) {
        this.network = network;
        this.infectedMachines = infectedMachines;
    }

}
