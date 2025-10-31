package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class kahn {
    int[] inDegree;
    int[] topOrder;
    int index = 0;
    boolean hasCycle=false;

    public kahn(Graph G) {
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
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
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
    }

    public String getTopOrder(int i){
        if (hasCycle) {
            return "Graph has a cycle. Topological sort not possible.";
        }
        return Integer.toString(topOrder[i]);
    }
}
