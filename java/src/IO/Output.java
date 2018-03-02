package IO;

import application.Network;

public class Output {

    private String ERR_PREFIX = "Error: ";
    private String INF_PREFIX = "Info: ";

    /**
     * Output text colours.
     */
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    /**
     * Prints the network.
     *
     * @param network The network to be printed
     */
    public void printNetwork(Network network) {
        System.out.println(network.formatNetwork());
    }

    /**
     * Formats an error string.
     *
     * @param error The error string to be formatted.
     * @return The formatted error
     */
    private String formatError(String error) {
        return ANSI_RED + error + ANSI_RESET;
    }

    /**
     * Prints an error string.
     *
     * @param error The error string to be printed.
     */
    public void printError(String error) {
        System.out.println(formatError(ERR_PREFIX + error));
    }

}
