package Graphs;
import org.Graphs.allMetrics;
import org.Graphs.Graph;
import org.Graphs.kahn;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Kahn_test {

    @Test
    public void testTopologicalOrder() {
        allMetrics metrics = new allMetrics();
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        kahn topo = new kahn(g,metrics);

        assertFalse(topo.hasCycle());
        int count = 0;
        for (int i = 0; i < g.vertices; i++) {
            int v = Integer.parseInt(topo.getTopOrder(i));
            assertTrue(v >= 0 && v < g.vertices);
            count++;
        }
        assertEquals(g.vertices, count);
    }

    @Test
    public void testCycleDetection() {
        allMetrics metrics = new allMetrics();
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);

        kahn topo = new kahn(g,metrics);
        assertTrue(topo.hasCycle());
        assertEquals("Graph has a cycle. Topological sort not possible.", topo.getTopOrder(0));
    }
}
