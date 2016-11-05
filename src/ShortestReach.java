import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by gakshintala on 12/4/15.
 */
public class ShortestReach {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int testCases = scn.nextInt();
        for (int c = 0; c < testCases; c++) {
            int vertexCount = scn.nextInt();
            int edgeCount = scn.nextInt();
            Graph_SR graph = readGraph(scn, vertexCount, edgeCount);

            boolean[] visited = new boolean[vertexCount + 1];
            Queue<Integer> queue = new LinkedList<>();
            Map<Integer, Integer> distanceFromSource = new TreeMap<>();

            int source = scn.nextInt();
            queue.add(source);
            visited[source] = true;
            distanceFromSource.put(source, 0);

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                int distanceFromSourceViaNeighbour = distanceFromSource.get(vertex) + 6;
                for (int connectedVertex : graph.getAllConnectedVertices(vertex)) {
                    if (distanceFromSource.get(connectedVertex) == null || distanceFromSource.get(connectedVertex) > distanceFromSourceViaNeighbour) {
                        distanceFromSource.put(connectedVertex, distanceFromSourceViaNeighbour);
                        if (!visited[connectedVertex]) {
                            queue.add(connectedVertex);
                            visited[connectedVertex] = true;
                        }
                    }
                }
            }

            for (int i = 1; i <= vertexCount; i++) {
                if (i == source) continue;
                Integer d = distanceFromSource.get(i);
                int distance = d != null ? d : -1;
                System.out.print(distance + " ");
            }
            System.out.println();
        }
    }

    private static Graph_SR readGraph(Scanner scn, int vertexCount, int edgeCount) {
        Graph_SR graph = new Graph_SR(vertexCount);
        for (int i = 0; i < edgeCount; i++) {
            graph.addEdge(scn.nextInt(), scn.nextInt());
        }
        return graph;
    }


}

class Graph_SR {
    private List<Integer>[] adj;

    Graph_SR(int vertexCount) {
        adj = new LinkedList[vertexCount + 1];

        for (int i = 1; i <= vertexCount; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int a, int b) {
        adj[a].add(b);
        adj[b].add(a);
    }

    public Iterable<Integer> getAllConnectedVertices(int vertex) {
        return adj[vertex];
    }
}
