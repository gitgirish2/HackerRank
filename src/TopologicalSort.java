import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by gakshintala on 1/10/16.
 */
public class TopologicalSort {

    private static List<Integer> reversePostOrder = new ArrayList<>();
    private static boolean[] visited;
    Digraph_TS graph;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int vertexCount = scn.nextInt();
        int edgeCount = scn.nextInt();
        Digraph_TS graph = new Digraph_TS(vertexCount);
        while (edgeCount-- > 0) graph.addEdge(scn.nextInt(), scn.nextInt());

        visited = new boolean[vertexCount + 1];

        for (int i = 1; i <= vertexCount; i++) if (!visited[i]) dfs(graph, i);
        System.out.println(reversePostOrder);
    }

    private static void dfs(Digraph_TS graph, int vertex) {
        visited[vertex] = true;

        for (int adj : graph.getConnectingNodes(vertex)) {
            if (!visited[adj]) {
                dfs(graph, adj);
            }
        }
        reversePostOrder.add(vertex);
    }
}


class Digraph_TS {
    private int n;
    private List<Integer>[] adj;

    Digraph_TS(int n) {
        this.n = n;
        this.adj = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
    }

    public List<Integer> getConnectingNodes(int source) {
        return adj[source];
    }
}
