package io;

import application.Network;

public class Output {

    private String ERR_PREFIX = "Error > ";
    private String INF_PREFIX = "Info > ";

    /**
     * Output text colours.
     */
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    /**
     * Prints the network.
     *
     * @param network The network to be printed
     */
    public void printNetwork(Network network) {
        System.out.println(network);
    }

    /**
     * Formats an info string.
     *
     * @param info The info string to be formatted.
     * @return The formatted info
     */
    private String formatInfo(String info) {
        return ANSI_YELLOW + info + ANSI_RESET;
    }

    /**
     * Prints an info string.
     *
     * @param info The info string to be printed.
     */
    public void printInfo(String info) {
        System.out.println(formatInfo(INF_PREFIX + info));
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
