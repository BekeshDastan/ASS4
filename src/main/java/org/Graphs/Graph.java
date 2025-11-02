package org.Graphs;
import java.util.ArrayList;
import java.util.List;


public class Graph {
    public int vertices;
    List<List<Integer>> adjList;

    public Graph ( int n){
        vertices = n;
        adjList = new ArrayList<>();

        for (int i=0; i<n;i++){
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to){
        adjList.get(from).add(to);
    }

}
