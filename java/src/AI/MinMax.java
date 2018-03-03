package AI;

import application.Edge;
import application.State;

import java.util.HashSet;

public class MinMax {

    private State state;

    public MinMax(State state) {
        this.state = state;
    }

    public double minMax(int depth) {
        //ArrayList result = new ArrayList();
        double result;
        if (depth == 0 || state.isFinished()) {
            //result.add(state.getValue());
            //result.add(null);
            //return result;
            return state.getValue();
        }
        if (state.getPlayer().equals("defender")) {
            double max = Double.NEGATIVE_INFINITY;
            HashSet<Edge> move;
            for (HashSet<Edge> move2 : state.getDefenses()) {
                state.playDefense(move2);
                double value = minMax(depth - 1);
                if (value > max) {
                    max = value;
                    move = move2;
                }
                //return max, move;
                return max;
            }
        }
        if (state.getPlayer().equals("attacker")) {
            double min = Double.POSITIVE_INFINITY;
            int move;
            for (int move2 : state.getAttacks()) {
                state.playAttack(move2);
                double value = minMax(depth - 1);
                if (value < min) {
                    min = value;
                    move = move2;
                }
                //return min, move;
                return min;
            }
        }
        // Missing return statement:
        return state.getValue();
    }
}
