package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph
 */
public class Graph {
    private final int V;
    private int E;
    private final List[] adj;

    public Graph(int V) {
        this.V = V;
        E = 0;
        adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public List<Integer> adj(int v){
        return adj[v];
    }
}
