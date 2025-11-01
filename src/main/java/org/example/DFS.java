package org.example;
import java.util.Stack;
import java.util.List;


public class DFS {
    boolean[] visited;
    boolean[] visited2;
    Graph graph;


    public DFS(Graph g){
        this.graph=g;
        visited=new boolean[g.vertices];
        visited2=new boolean[g.vertices];
    }

    public void dfs1(int v, Stack<Integer> stack){
        visited[v]=true;
        for (int u : graph.adjList.get(v)){
            if (!visited[u]){
                dfs1(u,stack);
            }
        }
        stack.push(v);
    }

    public void dfs2(int v, Graph g, List<Integer> component ){
        visited2[v] = true;
        component.add(v);
        for (int u : g.adjList.get(v)) {
            if (!visited2[u]) {
                dfs2(u, g, component);
            }
        }
    }

}
