package graphs;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Bfs
 */
public class ShortestPath {

    Graph graph;
    int[] path;

    public ShortestPath(Graph graph) {
        this.graph = graph;
        this.path = new int[graph.V()];
        Arrays.setAll(this.path, i->-1);
    }

    void bfs(int v){
        LinkedList<Integer> q = new LinkedList<>();
        q.add(v);
        this.path[v]=v;
        while(!q.isEmpty()){
            int node = q.removeFirst(); //bfs
            for(int w : graph.adj(node)){
                if (path[w]==-1) {
                    path[w] = node;
                    q.add(w);
                }
            }
        }

    }

    public static class Tester{
        @Test
        public void test1(){
            Graph g = new Graph(4);
            g.addEdge(0,1);
            g.addEdge(1,2);
            g.addEdge(2,3);
            g.addEdge(0,3);

            ShortestPath bfs = new ShortestPath(g);

            bfs.bfs(0);
            System.out.println(Arrays.toString(bfs.path));

        }
    }

}
