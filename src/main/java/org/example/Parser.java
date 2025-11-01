package org.example;

import com.google.gson.*;
import java.io.*;
import java.util.List;

public class Parser {

    public static class InputData {
        public boolean directed;
        public int n;
        public List<EdgeInput> edges;
        public int source;
        public String weight_model;
    }

    public static class EdgeInput{
        public int u;
        public int v;
        public int w;
    }

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static InputData readInput (String filename){
        try {
            File file = new File(filename);
            return gson.fromJson(new FileReader(file),InputData.class);
        } catch (Exception e){
            throw new RuntimeException("ERROR" + e.getMessage());
        }
    }

    public static Graph toGraph(InputData input) {
        Graph g = new Graph(input.n);
        for (EdgeInput e : input.edges) {
            g.addEdge(e.u, e.v);
        }
        return g;
    }
}
