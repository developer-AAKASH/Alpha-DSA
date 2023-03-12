package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GraphWithAdjMatrix {
    public static void DFS (final int[][] graph, final int n, final int startingVertax, final boolean[] visited) {
        System.out.println(startingVertax);
        visited[startingVertax] = true;

        for (int i = 0; i < n; i++) {
            if (i == startingVertax) {
                continue;
            }
            if (graph[startingVertax][i] == 1 && !visited[i]) {
                DFS (graph, n, i, visited);
            }
        }
    }
    public static void bfs (final int[][] graph, final int n, final boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            final int currentVertax = queue.remove();
            System.out.println(currentVertax);

            for (int i = 0; i < n; i++) {
                if (graph[currentVertax][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
    public static boolean hasPath (final int[][] graph, final int totalVertices, final int start, final int end, final boolean[] visited) {
        if (graph[start][end] == 1) {
            return true;
        }

        for (int i = 0; i < totalVertices; i++) {
            if (i == end && graph[start][i] == 1) {
                return true;
            }

            if (graph[start][i] == 1 && !visited[i]) {
                visited[i] = true;
                return hasPath(graph, totalVertices, i, end, visited);
            }
        }

        return false;
    }
    public static void dfsForComponents (int[][] graph, final int currentVertex, final int totalVertex, ArrayList<Integer> component, boolean[] visited) {
        visited[currentVertex] = true;
        component.add(currentVertex);

        for (int i = 0; i < totalVertex; i++) {
            if (i != currentVertex && graph[currentVertex][i] == 1 && !visited[i]) {
                dfsForComponents(graph, i, totalVertex, component, visited);
            }
        }
    }
    public static void getComponents (int[][] edges, final int totalVertices) {
        boolean visited[] = new boolean[totalVertices];
        for (int i = 0; i < totalVertices; i++) {
            visited[i] = false;
        }

        ArrayList<ArrayList<Integer>> allComponents = new ArrayList<>();
        for (int i = 0; i < totalVertices; i++) {
            if (!visited[i]) {
                ArrayList<Integer> component = new ArrayList<>();
                dfsForComponents(edges, i, totalVertices, component, visited);
                allComponents.add(component);
            }
        }

        System.out.println("All Components::: ");
        for (int i = 0; i < allComponents.size(); i++) {
            ArrayList<Integer> currentComponent = allComponents.get(i);
            for (int itr = 0; itr < currentComponent.size(); itr++) {
                System.out.print((currentComponent.get(itr) + 1) + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int n, e;
        Scanner scn = new Scanner(System.in);

        System.out.println("");
        n = scn.nextInt();

        System.out.println("");
        e = scn.nextInt();

        int graph[][] = new int[n][n];

        for (int i = 0; i < e; i++) {
            int edge1, edge2;

            edge1 = scn.nextInt();
            edge2 = scn.nextInt();

//            graph[edge1][edge2] = 1;
//            graph[edge2][edge1] = 1;

            graph[edge1 - 1][edge2 - 1] = 1;
            graph[edge2 - 1][edge1 - 1] = 1;
        }

// for has path...
//        int start, end;
//        start = scn.nextInt();
//        end = scn.nextInt();

        boolean visited[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
//        DFS (graph, n, 0, visited);
//        bfs(graph, n, visited);
//        System.out.println(hasPath(graph, n, start, end, visited));
        getComponents(graph, n);
    }
}
