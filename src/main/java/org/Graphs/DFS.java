package org.Graphs;
import java.util.Stack;
import java.util.List;


public class DFS {
    boolean[] visited;
    boolean[] visited2;
    Graph graph;
    AllMetrics metrics;


    public DFS(Graph g,  AllMetrics metrics){
        this.graph=g;
        this.metrics = metrics;
        visited=new boolean[g.vertices];
        visited2=new boolean[g.vertices];
    }

    public void dfs1(int v, Stack<Integer> stack){
        visited[v]=true;
        metrics.incrementDFSVisit();

        for (int u : graph.adjList.get(v)){
            metrics.incrementDFSEdge();
            if (!visited[u]){
                dfs1(u,stack);
            }
        }
        stack.push(v);
        metrics.incrementPush();
    }

    public void dfs2(int v, Graph g, List<Integer> component ){
        visited2[v] = true;
        metrics.incrementDFSVisit();
        component.add(v);
        for (int u : g.adjList.get(v)) {
            metrics.incrementDFSEdge();
            if (!visited2[u]) {
                dfs2(u, g, component);
            }
        }
    }

}
