package graphs;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Paths
 */
public class Paths {
    Graph graph;
    int s;
    int[] path;

    public Paths(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        path = new int[graph.V()];
        Arrays.setAll(path, i -> -1);

        class WithParent {
            final int parent;
            final int node;

            public WithParent(int parent, int node) {
                this.parent = parent;
                this.node = node;
            }
        }
        LinkedList<WithParent> ls = new LinkedList<>();
        ls.add(new WithParent(s, s));

        while (!ls.isEmpty()) {
            WithParent g = ls.removeLast();
            path[g.node] = g.parent;
            for (int w : graph.adj(g.node)) {
                if (path[w] == -1) {
                    ls.add(new WithParent(g.node, w));
                }
            }
        }
    }

    public List<Integer> pathTo(int v) {
        LinkedList<Integer> list = new LinkedList<>();
        for (; v != path[v]; v = path[v]) {
            list.addFirst(v);
        }
        list.addFirst(path[v]);
        return list;
    }

    public boolean hasPathTo(int v) {
        return path[v] != -1;
    }

    public static class Tester {
        @Test
        public void tester() {
            Graph g = new Graph(4);
            g.addEdge(0, 1);
            g.addEdge(1, 2);
            g.addEdge(2, 3);

            Paths p = new Paths(g, 0);
            assertTrue(p.hasPathTo(3));
            assertEquals(Arrays.asList(0, 1, 2, 3), p.pathTo(3));
        }

        @Test
        public void test2() {
            Graph g = new Graph(4);
            g.addEdge(0, 1);
            g.addEdge(0, 2);
            g.addEdge(2, 3);


            Paths p = new Paths(g, 0);
            assertTrue(p.hasPathTo(3));
            assertEquals(Arrays.asList(0, 2, 3), p.pathTo(3));
        }
    }
}
