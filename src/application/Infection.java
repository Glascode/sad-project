package application;

import IO.*;

public class Infection {

    public static void main(String[] args) {

        Game game;
        Input i = new Input();
        Output o = new Output();

        if (args.length == 0) {

            /* Number of machines */
            int m = i.askMachines("");

            /* Number of machines to initially infect */
            int mInfected = i.askMachines("infected");
            while (mInfected >= m) {
                o.printError("Cannot infect all the machines!");
                mInfected = i.askMachines("infected");
            }

            /* Probability for an edge to exist */
            float p = i.askProbability();

            /* Depth of reasoning of the attacker */
            int aDepth = i.askDepth("attacker");

            /* Depth of reasoning of the defender */
            int dDepth = i.askDepth("defender");

            /* Usage or not of an alpha-beta pruning */
            boolean alphaBeta = i.askAlphaBeta();

            /* Create a new game */
            game = new Game(m, mInfected, p, aDepth, dDepth, alphaBeta);
            game.run();
        } else if (args.length == 6) {
            String errorMessage = "Illegal value. A machine must be a positive "
                    + "integer!";
            if (i.isConformInteger(args[0], errorMessage)
                    && i.isConformInteger(args[1], errorMessage)
                    && i.isConformProbability(args[2])
                    && i.isConformInteger(args[3], "Illegal value. "
                    + "A depth of reasoning must be a positive integer!")
                    && i.isConformInteger(args[4], "Illegal value. "
                    + "A depth of reasoning must be a positive integer!")
                    && (args[5].equals("true") || args[5].equals("false"))) {

                /* Create a new game with the arguments */
                game = new Game(Integer.valueOf(args[0]), Integer.valueOf(args[1]),
                        Float.valueOf(args[2]), Integer.valueOf(args[3]),
                        Integer.valueOf(args[4]), Boolean.valueOf(args[5]));
                game.run();
            }
        } else {
            o.printError("Invalid arguments.");
            o.printUsage("m m_infected p a_depth d_depth alpha_beta\n"
                    + "  m          The number of machines to be created in the network\n"
                    + "  m_infected The number of machines to be infected\n"
                    + "  p          The probability for two machines to be linked\n"
                    + "  a_depth    The attacker depth of reasoning\n"
                    + "  d_depth    The defender depth of reasoning\n"
                    + "  alpha_beta Usage or not of an alpha-beta pruning\n");
        }
        //game = new Game(6, 1, 0.4, 2, 2, false);
        //game.run();
    }

}
