package GraphApnaCollege;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphInClassFive {
    // Using Dijkstra's Algorithm...
    static class Flight { // Edge
        int from; // source
        int to; // destination
        int price; // weight

        public Flight (final int from, final int to, final int price) {
            this.from = from;
            this.to = to;
            this.price = price;
        }
    }
    static class Info {
        int vertex;
        int cost;
        int stops;

        public Info (final int vertex, final int cost, final int stops) {
            this.vertex = vertex;
            this.cost = cost;
            this.stops = stops;
        }
    }
    public static void cheapestFlightsWithinKStops () {
        int n = 4;
        int flights[][] = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        final int source = 0, destination = 3, k = 1;

        ArrayList<Flight> graph[] = new ArrayList[n];

        // We can break the creating graph part in seprate function but because of simplicity we are keeping them in one function seprated
        // by problem...

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < flights.length; i++) {
            final int from = flights[i][0], to = flights[i][1], price = flights[i][2];

            Flight flight = new Flight (from, to, price);

            graph[from].add(flight);
        }

        // seprate function started from here for finding cheapest cities...

        int distances[] = new int[n];

        for (int i = 0; i < n; i++) {
            if (i != source) {
                distances[i] = Integer.MAX_VALUE;
            }
        }

        Queue<Info> queue = new LinkedList<>();
        queue.add(new Info(source, 0, 0));

        while (!queue.isEmpty()) {
            final Info currentInfo = queue.remove();

            if (currentInfo.stops > k) {
                break;
            }

            for (int i = 0; i < graph[currentInfo.vertex].size(); i++) {
                final Flight flight = graph[currentInfo.vertex].get(i);

                final int u = flight.from, v = flight.to, weight = flight.price;
// Using this, one corner case will be missed...
//                if (distances[u] != Integer.MAX_VALUE && (distances[u] + weight) < distances[v] && currentInfo.stops <= k) {
//                    distances[v] = distances[u] + weight;
//                    queue.add(new Info(v, distances[v], currentInfo.stops + 1));
//                }

                if ((currentInfo.cost + weight) < distances[v] && currentInfo.stops <= k) {
                    distances[v] = currentInfo.cost + weight;
                    queue.add(new Info(v, distances[v], currentInfo.stops + 1));
                }
            }
        }

        if (distances[destination] == Integer.MAX_VALUE) {
            System.out.println(-1);
            // return -1;
        } else {
            System.out.println(distances[destination]);
            // return distances[destination];
        }
    }
    // ------------ Chepast Flights Within K Stops ENds...

    // Using Prim's Algorithm...
    static class Edge implements Comparable<Edge> {
        int destination, cost;

        public Edge (final int destination, final int cost) {
            this.destination = destination;
            this.cost = cost;
        }

        public int compareTo (final Edge edge2) {
            return this.cost - edge2.cost; // ascending...
        }
    }
    public static void connectingCitiesWithMinimumCost () {
        final int[][] cities = {{0, 1, 2, 3, 4},
                {1, 0, 5, 0, 7}, {2, 5, 0, 6, 0}, {3, 0, 6, 0, 0}, {4, 7, 0, 0, 0}};

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[cities.length];

        priorityQueue.add(new Edge(0, 0));
        int minimumCost = 0;

        while (!priorityQueue.isEmpty()) {
            final Edge currentEdge = priorityQueue.remove();

            if (!visited[currentEdge.destination]) {
                visited[currentEdge.destination] = true;

                minimumCost += currentEdge.cost;

                for (int i = 0; i < cities[currentEdge.destination].length; i++) {
                    if (cities[currentEdge.destination][i] != 0) {
                        priorityQueue.add(new Edge(i, cities[currentEdge.destination][i]));
                    }
                }
            }
        }

        System.out.println(minimumCost);
        // return minimumCost;


    }
    // ----------- Connecting Cities Within Minimum cost ends...

    public static void main (String[] args) {
//        cheapestFlightsWithinKStops();
        connectingCitiesWithMinimumCost();
    }
}
