package org.Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class Kahn {
    int[] inDegree;
    int[] topOrder;
    AllMetrics metrics;
    int index = 0;
    boolean hasCycle=false;
    double Time;

    public Kahn(Graph G, AllMetrics metrics) {
        long start = System.nanoTime();
        this.metrics = metrics;
        inDegree = new int[G.vertices];
        topOrder = new int[G.vertices];
        for (int u = 0; u < G.vertices; u++) {
            for (int v : G.adjList.get(u)) {
                inDegree[v]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < G.vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                metrics.incrementPush();

            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            metrics.incrementPop();
            topOrder[index++] = u;

            for (int v : G.adjList.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }

            }
        }

        if (index < G.vertices) {
            hasCycle = true;
        }
        long end = System.nanoTime();
        Time = (end - start) / 1_000_000.0;
    }

    public void results(){

        System.out.println("Topological Sort (Kahn) Results");
        if (hasCycle) {
            System.out.println("Graph has a cycle. Topological sort not possible.");
        } else {
            System.out.print("Topological order: ");
            for (int i = 0; i < index; i++) {
                System.out.print(topOrder[i] + " ");
            }
            System.out.println();
        }

        System.out.println("Queue pushes: " + metrics.pushCount);
        System.out.println("Queue pops: " + metrics.popCount);
        System.out.printf("Time: %.3f ms\n", Time);

    }

    public String getTopOrder(int i){
        if (hasCycle) {
            return "Graph has a cycle. Topological sort not possible.";
        }
        return Integer.toString(topOrder[i]);
    }

    public boolean hasCycle(){
        return hasCycle;
    }

}
