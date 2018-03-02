package application;

public class MinMax {

    public double minMax(State state, int depth) {
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
            Edge move = null;
            for (Edge move2 : state.getDefenses()) {
                state.playDefense(move2.toString());
                double value = minMax(state, depth - 1);//.get(0);
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
            Node move = null;
            for (Node move2 : state.getAttacks()) {
                state.playAttack(move2.toString());
                double value = minMax(state, depth - 1);//.get(0);
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
