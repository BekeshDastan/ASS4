package org.example;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;


public class Kosaraju {
    Graph graph;
    boolean[] visited;
    Stack<Integer> stack;
    List<List<Integer>> sccList;
    Graph tranposedGraph;

    public Kosaraju(Graph g ){
        this.graph = g;
        visited = new boolean[g.vertices];
        stack = new Stack<>();
        sccList = new ArrayList<>();
    }

    public void findSCC(){
        DFS dfs = new DFS(graph);

        for (int i=0;i<graph.vertices;i++){
            if(!dfs.visited[i]){
                dfs.dfs1(i,stack);
            }
        }

        transposedGraph();

        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!dfs.visited2[v]) {
                List<Integer> component = new ArrayList<>();
                dfs.dfs2(v, tranposedGraph, component);
                sccList.add(component);
            }
        }
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


}
