import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by gakshintala on 12/7/15.
 */
// Is it to delete minimum edges or to make maximum groups?
public class MyEvenTree {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int vertexCount = scn.nextInt();
        int edgeCount = scn.nextInt();

        int[][] edges = new int[edgeCount][2];
        DiGraph_ET graph = new DiGraph_ET(vertexCount);
        for (int i = 0; i < edgeCount; i++) {
            int u = scn.nextInt();
            int v = scn.nextInt();
            graph.addEdge(v, u); // y to add in reverse, can be a problem with input?
            edges[i][0] = u;
            edges[i][1] = v;
        }

        Map<Integer, Integer> children = new HashMap<>();
        for (int i = vertexCount; i > 0; i--) { // y doesn't top-down approach work?
            int childCount = 0;
            if (graph.getAllConnectedVertices(i).iterator().hasNext()) {
                for (int adj : graph.getAllConnectedVertices(i)) {
                    Integer adjChildCount = children.get(adj);
                    if (adjChildCount != null)
                        childCount += adjChildCount;
                    childCount++;
                }
            }
            children.put(i, childCount);
        }

        int edgesRemoveCount = 0;
        // First condition is obvious, that both sides of an edge got even numbers (That is vertices got odd children), sides can be seperated by removing the edge
        // Here we consider one child as parent and other as child. 
        for (int i = 0; i < edgeCount; i++) {
            if ((children.get(edges[i][0]) % 2 != 0 && children.get(edges[i][1]) % 2 != 0) || (children.get(edges[i][1]) - children.get(edges[i][0]) % 2 != 0 && children.get(edges[i][0]) % 2 != 0)) {
                edgesRemoveCount++;
            }
        }
        System.out.println(edgesRemoveCount);
    }
}

class DiGraph_ET {
    List<Integer>[] adj;

    DiGraph_ET(int vertexCount) {
        adj = new LinkedList[vertexCount + 1];

        for (int i = 1; i <= vertexCount; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int a, int b) {
        adj[a].add(b);
    }

    public Iterable<Integer> getAllConnectedVertices(int vertex) {
        return adj[vertex];
    }
}