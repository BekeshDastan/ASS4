package Graphs;

import org.Graphs.Kosaraju;
import org.Graphs.Graph;
import org.Graphs.AllMetrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SCC_test {
    @Test
    public void test() {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(3, 4);
        AllMetrics metrics = new AllMetrics();
        Kosaraju sccFinder = new Kosaraju(g, metrics);
        sccFinder.findSCC();

        assertEquals(3,  sccFinder.sccList.size());

        assertTrue(sccFinder.sccList.stream().anyMatch(c -> c.containsAll(List.of(0,1,2))));
        assertTrue(sccFinder.sccList.stream().anyMatch(c -> c.containsAll(List.of(3))));
        assertTrue(sccFinder.sccList.stream().anyMatch(c -> c.containsAll(List.of(4))));
    }

    @Test
    public void Condenced(){
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(3, 4);

        AllMetrics metrics = new AllMetrics();
        Kosaraju sccFinder = new Kosaraju(g,metrics);
        sccFinder.findSCC();
        Graph condensed = sccFinder.condencedGraph();
        assertEquals(3, condensed.vertices);
    }
}
