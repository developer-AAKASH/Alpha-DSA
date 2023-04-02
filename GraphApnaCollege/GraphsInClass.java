package GraphApnaCollege;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphsInClass {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge (final int source, final int destination) {
            this.source = source;
            this.destination = destination;
        }
    }
    static void createGraph (final ArrayList<Edge> graph[]) { // true
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // True
//        graph[0].add(new Edge(0, 2));
//        graph[1].add(new Edge(1, 0));
//        graph[2].add(new Edge(2, 3));
//        graph[3].add(new Edge(3, 0));

        // False
//        graph[0].add(new Edge(0, 1));
//        graph[0].add(new Edge(0, 2));
//        graph[1].add(new Edge(1, 3));
//        graph[2].add(new Edge(2, 3));

        // Topological Sort
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 1));

        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));

        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));
    }
    // Cycle Detecting for Directed Graph --> Start
    public static boolean isCycleForDirectedGraphUtil (final ArrayList<Edge>[] graph, final int current, final boolean[] visited, final boolean track[]) {
        visited[current] = true;
        track[current] = true;

        for (int i = 0; i < graph[current].size(); i++) {
            final Edge edge = graph[current].get(i);

            if (track[edge.destination]) { // cycle
                return true;
            }

            if (!visited[edge.destination] && isCycleForDirectedGraphUtil(graph, edge.destination, visited, track)) {
                return true;
//                if (isCycleUtil(graph, edge.destination, visited, track)) {
//                    return true;
//                }
            }
        }

        track[current] = false;

        return false;
    }
    public static boolean isCycleForDirectedGraph (final ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        boolean[] track = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                if (isCycleForDirectedGraphUtil (graph, i, visited, track)) {
                    return true;
                }
            }
        }

        return false;
    }
    // Cycle Detecting for Directed Graph --> End

    // Cycle Detecting for Undirected Graph --- Start
    public static boolean isCycleUtill (final ArrayList<Edge>[] graph, final boolean[] visited, final int currentNode, final int parent) {
        visited[currentNode] = true;

        for (int i = 0; i < graph[currentNode].size(); i++) {
            final Edge edge = graph[currentNode].get(i);

            // case 3
            if (!visited[edge.destination]) {
                if (isCycleUtill(graph, visited, edge.destination, currentNode)) {
                    return true;
                }
            }

            // case 1
            else if (visited[edge.destination] && edge.destination != parent) {
                return true;
            }

            // case - 2 --> Nothing...

        }

        return false;
    }
    public static boolean isCycle (final ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                if (isCycleUtill (graph, visited, i, -1)) {
                    return true;
                }
            }
        }

        return false;
    }
    // Cycle Detecting for Undirected Graph -- End

    // Topological Sort --> Start
    public static void dfsHelper (final ArrayList<Edge>[] graph, final int currentNode, final boolean[] visited, final Stack<Integer> stack) {
        visited[currentNode] = true;

        for (int i = 0; i < graph[currentNode].size(); i++) {
            final Edge edge = graph[currentNode].get(i);

            if (!visited[edge.destination]) {
                dfsHelper(graph, edge.destination, visited, stack);
            }
        }

        stack.push(currentNode);
    }
    public static void topologicalSort (final ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                dfsHelper(graph, i, visited, stack);
            }
        }

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
    // Topological Sort --> End.

    // Bipartite Graph --> Start
    // If graph doesnt have cycle than they are bipartite for sure...
    public static boolean isBipartiteGraph (final ArrayList<Edge>[] graph) {
        int colors[] = new int[graph.length];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = -1; // No color
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == -1) { // BFS
                queue.add(i);
                colors[i] = 0; // Yellow

                while (!queue.isEmpty()) {
                    final int current = queue.remove();

                    for (int j = 0; j < graph[current].size(); j++) {
                        Edge edge = graph[current].get(j); // edge.destinatio is neighboue

                        if (colors[edge.destination] == -1) {
                            final int nextColor = colors[current] == 0 ? 1 : 0;

                            colors[edge.destination] = nextColor;

                            queue.add(edge.destination);
                        } else if (colors[edge.destination] == colors[current]) {
                            return false; // Not Bipartite
                        }
                    }
                }
            }
        }

        return true;
    }
    // Bipartite Graph --> End
    public static void main(String[] args) {

    }
}
