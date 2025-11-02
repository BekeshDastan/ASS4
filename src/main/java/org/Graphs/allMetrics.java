package org.Graphs;

public class allMetrics {
    private long startTime;
    private long Time;

    public int dfsVisits = 0;
    public int dfsEdges = 0;

    public int pushCount = 0;
    public int popCount = 0;

    public int relaxations = 0;

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        Time = System.nanoTime() - startTime;
    }

    public long getTime() {
        return Time;
    }

    public void incrementDFSVisit() {
        dfsVisits++;
    }

    public void incrementDFSEdge() {
        dfsEdges++;
    }

    public void incrementPush() {
        pushCount++;
    }

    public void incrementPop() {
        popCount++;
    }

    public void incrementRelaxation() {
        relaxations++;
    }

    public void reset() {
        dfsVisits = 0;
        dfsEdges = 0;
        pushCount = 0;
        popCount = 0;
        relaxations = 0;
    }

    public String toString() {
        return String.format(
                "Time: %.3f ms, DFS Visits: %d, DFS Edges: %d, Push: %d, Pop: %d, Relaxations: %d",
                Time / 1_000_000.0, dfsVisits, dfsEdges, pushCount, popCount, relaxations
        );
    }
}
