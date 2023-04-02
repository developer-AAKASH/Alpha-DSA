package GraphApnaCollege;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphInClassThree {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge (final int source, final int destination) {
            this.source = source;
            this.destination = destination;
        }

        public Edge (final int source, final int destination, final int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    static void createGrph (final ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // For Topological ...
//        graph[2].add(new Edge(2, 3));
//
//        graph[3].add(new Edge(3, 1));
//
//        graph[4].add(new Edge(4, 0));
//        graph[4].add(new Edge(4, 1));
//
//        graph[5].add(new Edge(5, 0));
//        graph[5].add(new Edge(5, 2));

        // Get all paths...
//        graph[0].add(new Edge(0, 3));
//
//        graph[2].add(new Edge(2, 3));
//
//        graph[3].add(new Edge(3, 1));
//
//        graph[4].add(new Edge(4, 0));
//        graph[4].add(new Edge(4, 1));
//
//        graph[5].add(new Edge(5, 0));
//        graph[5].add(new Edge(5, 2));

        // Dijkastras...
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));

        graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    public static void calculatingIndegree (final ArrayList<Edge>[] graph, int[] indegree) {
        for (int i = 0; i < graph.length; i++) {
            final int v = i;

            // Calculating in-degrees for the graph...
            for (int j = 0; j < graph[v].size(); j++) {
                final Edge edge = graph[v].get(j);
                indegree[edge.destination]++;
            }
        }
    }
    public static void topologiocalSortBFS (final ArrayList<Edge>[] graph) {
        int indegree[] = new int[graph.length];
        calculatingIndegree(graph, indegree);

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // BFS
        while (!queue.isEmpty()) {
            final int current = queue.remove();
            System.out.println(current);

            for (int i = 0; i < graph[current].size(); i++) {
                final Edge edge = graph[current].get(i);
                indegree[edge.destination]--;

                if (indegree[edge.destination] == 0) {
                    queue.add(edge.destination);
                }
            }
        }
    }

    public static void allPathsFromSourceToTraget (final ArrayList<Edge>[] graph, final int source, final int destination, String path) {
        if (source == destination) {
            System.out.println(path + " " + destination);

            return;
        }

        for (int i = 0; i < graph[source].size(); i++) {
            final Edge edge = graph[source].get(i);
            allPathsFromSourceToTraget (graph, edge.destination, destination, path + " " + source);
        }
    }

    // Using Dijkstra Algorithem...
    static class Pair implements Comparable<Pair> {
        int nodeN;
        int path;

        public Pair (final int nodeN, final int path) {
            this.nodeN = nodeN;
            this.path = path;
        }

        @Override // not mandatory
        public int compareTo (Pair p2) {
            return this.path - p2.path; // path based sorting for pairs...
        }
    }
    public static void shortestPath (final ArrayList<Edge>[] graph, int source) {
        int distance[] = new int[graph.length]; // distance[i] -> source to i

        for (int i = 0; i < graph.length; i++) {
            if (i != source) {
                distance[i] = Integer.MAX_VALUE;
            }
        }

        boolean[] visited = new boolean[graph.length];
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Pair(source, 0));

        // BFS loop...
        while (!priorityQueue.isEmpty()) {
            final Pair current = priorityQueue.remove();

            if (!visited[current.nodeN]) {
                visited[current.nodeN] = true;
                // Neighbours
                for (int i = 0; i < graph[current.nodeN].size(); i++) {
                    final Edge edge = graph[current.nodeN].get(i);

                    final int u = edge.source, v = edge.destination, weight = edge.weight;

                    // Update distance of source to V...
                    if ((distance[u] + weight) < distance[v]) {
                        distance[v] = distance[u] + weight;

                        priorityQueue.add(new Pair(v, distance[v]));
                    }
                }
            }
        }

        // Print all source to vertices shortest distance...
        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i] + " ");
        }

        System.out.println();

    }
    public static void main (String[] args) {
        final int V = 6;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGrph (graph);

        // LeetCode - 210 -> Course Schedule II
//        topologiocalSortBFS (graph);

//        int source = 5, destination = 1;
//        allPathsFromSourceToTraget (graph, source, destination, "");

        int sourceDjk = 0;
        shortestPath(graph, sourceDjk);
    }
}
