package org.Graphs;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;


public class Kosaraju {
    Graph graph;
    boolean[] visited;
    Stack<Integer> stack;
    public List<List<Integer>> sccList;
    Graph tranposedGraph;
    AllMetrics metrics;


    public Kosaraju(Graph g, AllMetrics metrics){
        this.metrics = metrics;
        this.graph = g;
        visited = new boolean[g.vertices];
        stack = new Stack<>();
        sccList = new ArrayList<>();
    }

    public void findSCC(){
        long start = System.nanoTime();
        DFS dfs = new DFS(graph,metrics);
        for (int i=0;i<graph.vertices;i++){
            if(!dfs.visited[i]){
                dfs.dfs1(i,stack);
            }
        }

        transposedGraph();

        while (!stack.isEmpty()) {
            int v = stack.pop();
            metrics.incrementPop();
            if (!dfs.visited2[v]) {
                List<Integer> component = new ArrayList<>();
                dfs.dfs2(v, tranposedGraph, component);
                sccList.add(component);
            }
        }
        long end = System.nanoTime();
        double elapsedMs = (end - start) / 1_000_000.0;

        System.out.println("Kosaraju results:");
        System.out.println("SCC list: " + sccList);
        System.out.println("DFS visits: " + metrics.dfsVisits);
        System.out.println("DFS edges: " + metrics.dfsEdges);
        System.out.println("Stack pushes: " + metrics.pushCount);
        System.out.println("Stack pops: " + metrics.popCount);
        System.out.printf("Time: %.3f ms\n", elapsedMs);
    }


    public void transposedGraph(){
        Graph transposed = new Graph(graph.vertices);
        for (int u=0;u<graph.vertices;u++){
            for (int v :  graph.adjList.get(u)){
                transposed.adjList.get(v).add(u);
            }
        }
        this.tranposedGraph=transposed;
    }

    public Graph condencedGraph(){
        int n = sccList.size();
        Graph condensed = new Graph(n);
        int[] compId= new int[graph.vertices];
        for (int i=0;i< sccList.size();i++){
            for (int v : sccList.get(i)){
                compId[v]=i;
            }
        }

        for (int u=0;u<graph.vertices;u++){
            for (int v : graph.adjList.get(u)){
                if (compId[v]!=compId[u]){
                    condensed.addEdge(compId[u],compId[v]);

                }
            }

        }
        return condensed;
    }

}
