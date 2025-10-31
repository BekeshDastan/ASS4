package org.example;


public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(8);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(5, 7);

        kahn k = new kahn(g);

        for (int i=0;i<g.vertices;i++){
                System.out.println(k.getTopOrder(i));
                if (k.getTopOrder(i) == "Graph has a cycle. Topological sort not possible."){
                    return;
                }
        }
    }
}