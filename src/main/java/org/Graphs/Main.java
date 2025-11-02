package org.Graphs;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Parser.InputData input = Parser.readInput("inputLarge.json");

        Graph g = Parser.toGraph(input);
        allMetrics  metrics= new allMetrics();

        Kosaraju kos = new Kosaraju(g,metrics);
        kos.findSCC();
        int n = g.vertices;
        int[][] w = new int[n][n];

        for (Parser.EdgeInput e : input.edges) {
            w[e.u][e.v] = e.w;
        }
        int source = input.source;

        metrics.reset();

        Graph condensed = kos.condencedGraph();
        kahn k = new kahn(condensed,metrics);
        k.results();

        DAGPATHs.longestPath(g, source, w, metrics);
        DAGPATHs.shortestPath(g, source, w,metrics);
    }

}