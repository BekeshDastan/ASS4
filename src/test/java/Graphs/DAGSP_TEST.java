package Graphs;

import org.Graphs.DAGPATHs;
import org.Graphs.Graph;
import org.Graphs.AllMetrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DAGSP_TEST {
    @Test
    public void testShortestAndLongestPaths() {
        int n = 5;
        Graph g = new Graph(n);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        int[][] weight = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                weight[i][j] = Integer.MAX_VALUE;

        weight[0][1] = 2;
        weight[0][2] = 1;
        weight[1][3] = 3;
        weight[2][3] = 2;
        weight[3][4] = 4;

        AllMetrics metrics = new AllMetrics();

        DAGPATHs.shortestPath(g, 0, weight, metrics);
        int[] dist = DAGPATHs.getShortestDistances(g, 0, weight, metrics);
        assertEquals(7, dist[4]);
        DAGPATHs.longestPath(g, 0, weight, metrics);
        int[] distLong = DAGPATHs.getLongestDistances(g, 0, weight, metrics);
        assertEquals(9, distLong[4]);

    }
}
