package graphs;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

/**
 * SearchGraph
 */
public class SearchGraph {
    Graph graph;
    int s;
    boolean[] marked;

    public SearchGraph(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        marked = new boolean[graph.V()];

        LinkedList<Integer> ls = new LinkedList<>();
        ls.add(s);
        while (!ls.isEmpty()) {
            int g = ls.removeLast();
            if (!marked[g]) {
                marked[g] = true;
                System.out.println(g);
                for (int w : graph.adj(g)) {
                    if (!marked[w]) {
                        ls.add(w);
                    }
                }
            }
        }
    }

    boolean marked(int v) {
        return marked[v];
    }

    public static class Tester {
        @Test
        public void t1() {

            Graph g = new Graph(6);
            g.addEdge(0, 5);
            g.addEdge(2, 4);
            g.addEdge(2, 3);
            g.addEdge(1, 2);
            g.addEdge(0, 1);
            g.addEdge(3, 4);
            g.addEdge(3, 5);
            g.addEdge(0, 2);

            SearchGraph sg = new SearchGraph(g, 0);
            assertTrue(sg.marked(1));
            assertTrue(sg.marked(2));
            assertTrue(sg.marked(3));
            assertTrue(sg.marked(4));
            assertTrue(sg.marked(5));

        }
    }
}
