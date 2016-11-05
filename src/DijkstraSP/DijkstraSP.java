package DijkstraSP;

import DS.DirectedEdge;
import DS.EdgeWeightedDigraph;
import DS.Vertex;

import java.util.*;

/**
 * Created by gakshintala on 1/6/16.
 */
public class DijkstraSP {
    private static EdgeWeightedDigraph graph;
    private static DirectedEdge[] edgeTo; // Edge to TO from FROM
    private static PriorityQueue<Vertex> pq;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int testCases = scn.nextInt();
        while (testCases-- > 0) {
            int vertexCount = scn.nextInt();
            int edgeCount = scn.nextInt();

            graph = new EdgeWeightedDigraph(vertexCount);
            edgeTo = new DirectedEdge[vertexCount + 1];

            while (edgeCount-- > 0) {
                graph.addEdge(scn.nextInt(), scn.nextInt(), scn.nextInt());
            }
            int source = scn.nextInt();
            graph = findShortDistancesToAllNodes(source);

            printShortestDistances(source);
            printShortestPaths(source);
        }
    }

    private static EdgeWeightedDigraph findShortDistancesToAllNodes(int source) {
        pq = new PriorityQueue<>();
        graph.getVertices()[source].distFromSource = 0;
        edgeTo[source] = null;
        pq.add(graph.getVertices()[source]);
        while (!pq.isEmpty()) {
            Vertex fromVertex = pq.poll();
            for (DirectedEdge edge : graph.getConnectingEdges(fromVertex.index)) {
                relax(edge);
            }
        }
        return graph;
    }

    private static void relax(DirectedEdge edge) {
        Vertex from = graph.getVertices()[edge.from()];
        Vertex to = graph.getVertices()[edge.to()];
        int distanceFromSourceViaFrom = from.distFromSource + edge.weight();
        if (to.distFromSource == -1 || to.distFromSource > distanceFromSourceViaFrom) {
            to.distFromSource = distanceFromSourceViaFrom;
            edgeTo[to.index] = edge;
            if (!pq.contains(to)) {
                pq.add(to);
            }
        }
    }

    private static void printShortestPathForVertex(int vertexIndex) {
        List<DirectedEdge> path = new ArrayList<>();
        for (DirectedEdge edge = edgeTo[vertexIndex]; edge != null; edge = edgeTo[edge.from()]) {
            path.add(edge);
        }
        Collections.reverse(path);
        System.out.println(path);
    }

    private static void printShortestDistances(int source) {
        int vertexCount = graph.vertexCount();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= vertexCount; i++)
            if (i != source) result.append(graph.getVertices()[i].distFromSource).append(" ");
        System.out.println(result);
    }

    private static void printShortestPaths(int source) {
        int vertexCount = graph.vertexCount();
        for (int i = 1; i <= vertexCount; i++) {
            if (i == source) continue;
            printShortestPathForVertex(i);
        }
    }
}

