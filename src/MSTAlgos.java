import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by gakshintala on 12/16/15.
 */
public class MSTAlgos {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int vertexCount = scn.nextInt();
        int edgeCount = scn.nextInt();

        EdgeWeightedGraph_MST edgeWeightedGraph = readGraph(vertexCount, edgeCount, scn);

        System.out.println(krusalsAlgo(edgeWeightedGraph, vertexCount, edgeCount));
        //int startVertex = scn.nextInt();
        //System.out.println(primsLazyAlgo(edgeWeightedGraph,vertexCount,edgeCount,startVertex));
    }

    private static int primsLazyAlgo(EdgeWeightedGraph_MST edgeWeightedGraph, int vertexCount, int edgeCount, int startVertex) {
        PriorityQueue<Edge_MST> edgePQ = new PriorityQueue<>(edgeWeightedGraph.getAllConnectedEdges(startVertex));
        boolean[] visited = new boolean[vertexCount + 1];
        addEdgesWithUnvisitedVerticesToPQ(edgeWeightedGraph, edgePQ, startVertex, visited);
        List<Edge_MST> mst = new ArrayList<>();
        while (!edgePQ.isEmpty()) {
            Edge_MST e = edgePQ.poll();
            int v = e.either();
            int w = e.other(v);
            if (!visited[v] || !visited[w]) {
                mst.add(e);
                if (!visited[v]) addEdgesWithUnvisitedVerticesToPQ(edgeWeightedGraph, edgePQ, v, visited);
                if (!visited[w]) addEdgesWithUnvisitedVerticesToPQ(edgeWeightedGraph, edgePQ, w, visited);
            }
        }
        return sumOfEdgeWeights(mst);
    }

    private static void addEdgesWithUnvisitedVerticesToPQ(EdgeWeightedGraph_MST edgeWeightedGraph, PriorityQueue<Edge_MST> edgePQ, int v, boolean[] visited) {
        visited[v] = true;
        for (Edge_MST e : edgeWeightedGraph.getAllConnectedEdges(v))
            if (!visited[e.other(v)]) edgePQ.add(e);
    }

    private static int krusalsAlgo(EdgeWeightedGraph_MST edgeWeightedGraph, int vertexCount, int edgeCount) {
        PriorityQueue<Edge_MST> edgePQ = new PriorityQueue<>(edgeWeightedGraph.getAllEdges());
        List<Edge_MST> mst = new ArrayList<>();
        QuickUnion union = new QuickUnion(vertexCount);
        // Pick the smallest edge from PQ
        while (!edgePQ.isEmpty() && mst.size() < vertexCount - 1) {
            Edge_MST e = edgePQ.poll();
            int v = e.either();
            int w = e.other(v);
            // Check if v,w are connected before using Quick Union, to detect if there is a cycle
            if (!union.connected(v, w)) { // To check if it creates a cycle
                union.union(v, w);
                mst.add(e);
            }
        }
        return sumOfEdgeWeights(mst);
    }

    private static int sumOfEdgeWeights(List<Edge_MST> mst) {
        int sum = 0;
        for (Edge_MST e : mst) sum += e.weight();
        return sum;
    }

    private static EdgeWeightedGraph_MST readGraph(int vertexCount, int edgeCount, Scanner scn) {
        EdgeWeightedGraph_MST edg = new EdgeWeightedGraph_MST(vertexCount);
        while (edgeCount-- > 0)
            edg.addEdge(new Edge_MST(scn.nextInt(), scn.nextInt(), scn.nextInt()));
        return edg;
    }
}

class EdgeWeightedGraph_MST {
    private int N;
    private LinkedList<Edge_MST>[] adj;
    private HashSet<Edge_MST> edges = new HashSet<>();

    EdgeWeightedGraph_MST(int n) {
        this.N = n;
        this.adj = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++)
            adj[i] = new LinkedList<>();
    }

    public int getVertexCount() {
        return this.N;
    }

    public void addEdge(Edge_MST e) {
        edges.add(e);
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }

    public LinkedList<Edge_MST> getAllConnectedEdges(int v) {
        return adj[v];
    }

    public Set<Edge_MST> getAllEdges() {
        return edges;
    }
}

class Edge_MST implements Comparable<Edge_MST> {
    private final int v, w;
    private final int weight;

    Edge_MST(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == this.v) return this.w;
        return v;
    }

    public int weight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge_MST that) {
        return Integer.compare(this.weight, that.weight);
    }
}

class QuickUnion {
    int[] id;

    QuickUnion(int n) {
        id = new int[n + 1];
        for (int i = 1; i <= n; i++) id[i] = i;
    }

    private int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        id[root(p)] = root(q);
    }
}
