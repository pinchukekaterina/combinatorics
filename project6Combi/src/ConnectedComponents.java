import java.util.*;

public class ConnectedComponents {
    public static List<Set<Integer>> findConnectedComponents(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        List<Set<Integer>> components = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Set<Integer> component = new HashSet<>();
                dfs(graph, i, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    private static void dfs(int[][] graph, int node, boolean[] visited, Set<Integer> component) {
        visited[node] = true;
        component.add(node);

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, component);
            }
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {1, 2},
                {0, 2},
                {0, 1, 3},
                {2}
        };

        List<Set<Integer>> components = findConnectedComponents(graph);
        System.out.println("Connected components:");
        for (Set<Integer> component : components) {
            System.out.println(component);
        }
    }
}
