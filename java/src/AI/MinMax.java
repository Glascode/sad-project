package AI;

import application.Edge;
import application.State;
import util.Tuple;

import java.util.ArrayList;
import java.util.HashSet;

public class MinMax {

    private Tuple tuple;
    private ArrayList list;
    private State state;

    public MinMax(State state) {
        this.state = state;
        tuple = new Tuple();
        list = new ArrayList();
    }

    public ArrayList minMax(State state, int depth) {
        if (depth == 0 || state.isFinished()) {
            list.add(state.getValue());
            list.add(state.getValue());
            //tuple.setTuple(state.getValue(), null);
            return list;
        }
        if (state.getPlayer().equals("defender")) {
            double max = Double.NEGATIVE_INFINITY;
            HashSet<Edge> move = null;
            for (HashSet<Edge> move2 : state.getDefense()) {
                state.playDefense(move2);
                int value = (int) minMax(state, depth - 1).get0();
                if (value > max) {
                    max = value;
                    move = move2;
                }
                //return max, move;
                tuple.setTuple(max, move);
                return tuple;
            }
        }
        if (state.getPlayer().equals("attacker")) {
            double min = Double.POSITIVE_INFINITY;
            int move = 0;
            for (int move2 : state.getAttacks()) {
                state.playAttack(move2);
                int value = (int) minMax(state, depth - 1).get0();
                if (value < min) {
                    min = value;
                    move = move2;
                }
                //return min, move;
                tuple.setTuple(min, move);
                return tuple;
            }
        }
        // Missing return statement:
        tuple.setTuple(state.getValue(), null);
        return tuple;
    }
}
