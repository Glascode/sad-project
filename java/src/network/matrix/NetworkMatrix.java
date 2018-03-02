package network.matrix;

public class NetworkMatrix {

    private Integer[][] networkMatrix;

    public NetworkMatrix(int nMachines) {
        networkMatrix = new Integer[nMachines][nMachines];
        for (int i = 0; i < networkMatrix.length; i++) {
            for (int j = 0; j < networkMatrix.length; j++) {
                networkMatrix[i][j] = 0;
            }
        }
    }

    public void addEdge(int node1, int node2) {
        networkMatrix[node1 - 1][node2 - 1] = 1;
        networkMatrix[node2 - 1][node1 - 1] = 1;
    }

    public String formatNetwork() {
        StringBuilder formattedNetwork = new StringBuilder();
        for (int i = 0; i < networkMatrix.length; i++) {
            formattedNetwork.append("Node " + (i + 1) + " => ");
            for (int j = 0; j < networkMatrix.length; j++) {
                if (networkMatrix[i][j] == 1) {
                    formattedNetwork.append((j+ 1) + " ");
                }
            }
            formattedNetwork.append("\n");
        }
        return formattedNetwork.toString();
    }

    public void printNetwork() {
        System.out.println(formatNetwork());
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < networkMatrix.length; i++) {
            for (int j = 0; j < networkMatrix.length; j++) {
                result.append(networkMatrix[i][j] + " ");
            }
            result.append("\n");
        }
        return result.toString();
    }

}
