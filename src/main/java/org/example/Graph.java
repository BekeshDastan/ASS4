package org.example;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class Graph {
    int vertices;
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
