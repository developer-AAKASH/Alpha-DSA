package GraphApnaCollege;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class GraphInClassFour {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge (final int source, final int destination, final int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static void createGraph (final ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

//        graph[0].add (new Edge(0, 1, 2));
//        graph[0].add (new Edge(0, 2, 4));
//
//        graph[1].add (new Edge(1, 2, -4));
//
//        graph[2].add (new Edge(2, 3, 2));
//
//        graph[3].add (new Edge(3, 4, 4));
//
//        graph[4].add (new Edge(4, 1, -1));

        // MST
        graph[0].add (new Edge(0, 1, 10));
        graph[0].add (new Edge(0, 2, 15));
        graph[0].add (new Edge(0, 3, 30));

        graph[1].add (new Edge(1, 0, 10));
        graph[1].add (new Edge(1, 3, 40));

        graph[2].add (new Edge(2, 0, 15));
        graph[2].add (new Edge(2, 3, 50));

        graph[3].add (new Edge(3, 1, 40));
        graph[3].add (new Edge(3, 2, 50));
    }
    static void createGraphWithEdges (final ArrayList<Edge> graph) {
        graph.add (new Edge(0, 1, 2));
        graph.add (new Edge(0, 2, 4));
        graph.add (new Edge(1, 2, -4));
        graph.add (new Edge(2, 3, 2));
        graph.add (new Edge(3, 4, 4));
        graph.add (new Edge(4, 1, -1));
    }

    // Using Bell-Man Ford's Algorithm...
    public static void shortestPathFromAllVertices (final ArrayList<Edge>[] graph, final int source) {
        final int vertices = graph.length;
        int distances[] = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            if (i != source) {
                distances[i] = Integer.MAX_VALUE;
            }
        }

        // Bell-Man Algorithm start...
        for (int v = 0; v < vertices - 1; v++) {
            for (int edges = 0; edges < vertices; edges++) {
                for (int edge = 0; edge < graph[edges].size(); edge++) {
                    final Edge edg = graph[edges].get(edge);

                    final int u = edg.source, vv = edg.destination, weight = edg.weight;

                    // Relaxation...
                    if (distances[u] != Integer.MAX_VALUE && (distances[u] + weight) < distances[vv]) {
                        distances[vv] = distances[u] + weight;
                    }
                }
            }
        }

        for (int itr = 0; itr < vertices; itr++) {
            System.out.print(distances[itr] + " ");
        }

        System.out.println();

    }
    public static void shortestPathFromAllVerticesEDGES (final ArrayList<Edge> graph, final int source, final int vertices) {
        int distances[] = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            if (i != source) {
                distances[i] = Integer.MAX_VALUE;
            }
        }

        // Bell-Man Algorithm start...
        for (int vertex = 0; vertex < vertices - 1; vertex++) {
            for (int edge = 0; edge < vertices; edge++) {
                final Edge edg = graph.get(edge);

                final int u = edg.source, v = edg.destination, weight = edg.weight;

                // Relaxation...
                if (distances[u] != Integer.MAX_VALUE && (distances[u] + weight) < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }

        for (int itr = 0; itr < vertices; itr++) {
            System.out.print(distances[itr] + " ");
        }

        System.out.println();

    }

    // MST
    static class Pair implements Comparable<Pair> {
        int vertex;
        int cost;

        public Pair (final int vertex, final int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        public int compareTo (final Pair p2) {
            return this.cost - p2.cost; // asscending...
        }
    }
    public static void prims (final ArrayList<Edge> graph[]) {
        boolean visited[] = new boolean[graph.length];
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(new Pair(0, 0));

        int minimumCost = 0; // MST cost / Total Minimum weight

        while (!priorityQueue.isEmpty()) {
            final Pair currentPair = priorityQueue.remove();

            if (!visited[currentPair.vertex]) {
                visited[currentPair.vertex] = true;

                minimumCost += currentPair.cost;

                for (int itr = 0; itr < graph.length; itr++) {
                    final Edge edge = graph[currentPair.vertex].get(itr);

                    priorityQueue.add(new Pair(edge.destination, edge.weight));
                }
            }
        }

        System.out.println(minimumCost);



    }
    public static void main (String[] args) {
        final int vertices = 5;
        ArrayList<Edge>[] graph = new ArrayList[vertices];
        ArrayList<Edge> graphE = new ArrayList<>();

        createGraph (graph);

//        createGraphWithEdges(graphE);

//        shortestPathFromAllVertices (graph, 0);

//        shortestPathFromAllVerticesEDGES(graphE, 0, vertices);

        prims(graph);
    }
}
