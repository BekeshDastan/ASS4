package org.example;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Parser.InputData input = Parser.readInput("tasks.json");

        Graph g = Parser.toGraph(input);

        Kosaraju kos = new Kosaraju(g);
        kos.findSCC();
        for (List<Integer> scc : kos.sccList) {
            System.out.println("Component: " + scc + ", size: " + scc.size());
        }

        Graph condensed = kos.condencedGraph();
        kahn k = new kahn(condensed);


        for (int i=0;i< condensed.vertices; i++){
                System.out.println(k.getTopOrder(i));
                if (k.getTopOrder(i) == "Graph has a cycle. Topological sort not possible."){
                    return;
                }
        }
    }
}