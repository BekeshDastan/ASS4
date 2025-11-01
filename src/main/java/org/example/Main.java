package org.example;


public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(1, 0);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        Kosaraju kos = new Kosaraju(g);
        kos.findSCC();
        System.out.println(kos.sccList);
        kahn k = new kahn(g);


        for (int i=0;i<g.vertices;i++){
                System.out.println(k.getTopOrder(i));
                if (k.getTopOrder(i) == "Graph has a cycle. Topological sort not possible."){
                    return;
                }
        }
    }
}