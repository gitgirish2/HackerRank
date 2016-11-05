import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by gakshintala on 12/3/15.
 */
public class SnakesLadders {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int testCases = scn.nextInt();
        for (int i = 0; i < testCases; i++) {
            Map<Integer, Integer> snakeOrLad = new HashMap<>();
            boolean[] visited = new boolean[101];
            boolean pathFound = false;

            int ladders = scn.nextInt();
            for (int l = 0; l < ladders; l++)
                snakeOrLad.put(scn.nextInt(), scn.nextInt());
            int snakes = scn.nextInt();
            for (int s = 0; s < snakes; s++)
                snakeOrLad.put(scn.nextInt(), scn.nextInt());

            Queue<Node> boardQueue = new LinkedList<>();
            boardQueue.add(new Node(1, 0));
            visited[1] = true;

            while (!boardQueue.isEmpty()) {
                Node square = boardQueue.poll();
                if (square.vertex == 100) {
                    pathFound = true;
                    System.out.println(square.stepsFromSource);
                    break;
                }

                for (int v = 1; v <= 6 && square.vertex + v <= 100; v++) {
                    // Always check the map, if the current vertex holds a snake or a ladder
                    Integer referencedVertex = snakeOrLad.get(square.vertex + v);
                    int connectedVertex = (referencedVertex != null && referencedVertex <= 100) ? referencedVertex : square.vertex + v;
                    if (!visited[connectedVertex]) {
                        boardQueue.add(new Node(connectedVertex, square.stepsFromSource + 1));
                        visited[connectedVertex] = true;
                    }
                }
            }
            if (!pathFound) System.out.println("-1");
        }
    }
}

class Node {
    int vertex;
    int stepsFromSource;

    Node(int vertex, int stepsFromSource) {
        this.vertex = vertex;
        this.stepsFromSource = stepsFromSource;
    }
}
