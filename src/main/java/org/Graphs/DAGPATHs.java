package org.Graphs;
import java.util.*;

public class DAGPATHs {

    public static void shortestPath(Graph g, int source, int[][] weight,  allMetrics metrics) {
        long start = System.nanoTime();

        int n = g.vertices;
        int[] dist = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[source] = 0;

        kahn topo = new kahn(g,metrics);
        if (topo.hasCycle) throw new RuntimeException("Graph has a cycle!");

        for (int i = 0; i < n; i++) {
            int u = Integer.parseInt(topo.getTopOrder(i));
            if (dist[u] != Integer.MAX_VALUE) {
                for (int v : g.adjList.get(u)) {
                    metrics.incrementRelaxation();
                    if (dist[v] > dist[u] + weight[u][v]) {
                        dist[v] = dist[u] + weight[u][v];
                        parent[v] = u;
                    }
                }
            }
        }

        long end = System.nanoTime();
        double Time = (end - start) / 1_000_000.0;

        System.out.println("DAG Shortest Path Result ");
        System.out.println("Shortest distances from " + source + ": " + Arrays.toString(dist));
        int target = n - 1;
        List<Integer> path = new ArrayList<>();
        for (int cur = target; cur != -1; cur = parent[cur]) path.add(cur);
        Collections.reverse(path);
        System.out.println("One shortest path to " + target + ": " + path);
        System.out.println("Queue pushes: " + metrics.pushCount);
        System.out.println("Queue pops: " + metrics.popCount);
        System.out.println("Relaxations: " + metrics.relaxations);
        System.out.printf("Time: %.3f ms\n", Time);

    }

    public static void longestPath(Graph g, int source, int[][] weight,  allMetrics metrics) {
        long start = System.nanoTime();
        int n = g.vertices;
        int[] dist = new int[n];
        int[] parent = new int[n];

        Arrays.fill(dist, Integer.MIN_VALUE);
        Arrays.fill(parent, -1);
        dist[source] = 0;

        kahn topo = new kahn(g, metrics);
        if (topo.hasCycle) throw new RuntimeException("Graph has a cycle!");

        for (int i = 0; i < n; i++) {
            int u = Integer.parseInt(topo.getTopOrder(i));
            if (dist[u] != Integer.MIN_VALUE) {
                for (int v : g.adjList.get(u)) {
                    metrics.incrementRelaxation();
                    if (dist[v] < dist[u] + weight[u][v]) {
                        dist[v] = dist[u] + weight[u][v];
                        parent[v] = u;
                    }
                }
            }
        }

        long end = System.nanoTime();
        double Time = (end - start) / 1_000_000.0;

        System.out.println("DAG Longest Path Results ");
        System.out.println("Longest distances from " + source + ": " + Arrays.toString(dist));

        int target = n - 1;
        List<Integer> path = new ArrayList<>();
        for (int cur = target; cur != -1; cur = parent[cur]) path.add(cur);
        Collections.reverse(path);
        System.out.println("One longest path to " + target + ": " + path);

        System.out.println("Queue pushes: " + metrics.pushCount);
        System.out.println("Queue pops: " + metrics.popCount);
        System.out.println("Relaxations: " + metrics.relaxations);
        System.out.printf(" Time: %.3f ms\n", Time);
    }


    public static int[] getShortestDistances(Graph g, int source, int[][] weight, allMetrics metrics) {
        int n = g.vertices;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        kahn topo = new kahn(g, metrics);
        if (topo.hasCycle) throw new RuntimeException("Graph has a cycle!");

        for (int i = 0; i < n; i++) {
            int u = Integer.parseInt(topo.getTopOrder(i));
            if (dist[u] != Integer.MAX_VALUE) {
                for (int v : g.adjList.get(u)) {
                    if (dist[v] > dist[u] + weight[u][v]) {
                        dist[v] = dist[u] + weight[u][v];
                    }
                }
            }
        }
        return dist;
    }



    public static int[] getLongestDistances(Graph g, int source, int[][] weight, allMetrics metrics) {
        int n = g.vertices;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[source] = 0;

        kahn topo = new kahn(g, metrics);
        if (topo.hasCycle) throw new RuntimeException("Graph has a cycle!");

        for (int i = 0; i < n; i++) {
            int u = Integer.parseInt(topo.getTopOrder(i));
            if (dist[u] != Integer.MIN_VALUE) {
                for (int v : g.adjList.get(u)) {
                    if (dist[v] < dist[u] + weight[u][v]) {
                        dist[v] = dist[u] + weight[u][v];
                    }
                }
            }
        }
        return dist;
    }

}
