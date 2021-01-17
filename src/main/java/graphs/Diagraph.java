package graphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Diagraph
 */
public class Diagraph {

    private final int V;
    int E;
    List<Integer>[] edges;

    Diagraph(int V) {
        this.V = V;
        edges = new List[V];
        for (int v = 0; v < V; v++) {
            edges[v] = new ArrayList<Integer>();
        }

    }

    int V() {
        return V;
    }

    int E() {
        return E;
    }

    void addEdge(int v, int w) {
        edges[v].add(w);
        E++;
    }

    List<Integer> adj(int v) {
        return edges[v];
    }

    Diagraph reverse() {
        Diagraph reversedDiagraph = new Diagraph(V);

        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reversedDiagraph.addEdge(w, v);
            }
        }

        return reversedDiagraph;
    }

    public static class Tester {
        @Test
        public void tester() {
            Diagraph d = new Diagraph(5);
            d.addEdge(0, 1);
            d.addEdge(1, 2);
        }
    }
}
