import java.util.*;

public class FordFulkerson {
    private static class Edge {
        int from, to, capacity, flow;

        Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }
    }

    public static int maxFlow(int n, int s, int t, List<Edge> edges) {
        int totalFlow = 0;
        while (true) {
            int[] parent = new int[n];
            Arrays.fill(parent, -1);
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(s);
            parent[s] = s;

            while (!queue.isEmpty() && parent[t] == -1) {
                int u = queue.poll();
                for (Edge e : edges) {
                    if (e.from == u && e.flow < e.capacity && parent[e.to] == -1) {
                        parent[e.to] = u;
                        queue.offer(e.to);
                    }
                }
            }

            if (parent[t] == -1) {
                break;
            }

            int flow = Integer.MAX_VALUE;
            for (int i = t; i != s; i = parent[i]) {
                for (Edge e : edges) {
                    if (e.to == i && e.from == parent[i]) {
                        flow = Math.min(flow, e.capacity - e.flow);
                        break;
                    }
                }
            }

            for (int i = t; i != s; i = parent[i]) {
                for (Edge e : edges) {
                    if (e.to == i && e.from == parent[i]) {
                        e.flow += flow;
                    } else if (e.from == i && e.to == parent[i]) {
                        e.flow -= flow;
                    }
                }
            }

            totalFlow += flow;
        }
        return totalFlow;
    }

    public static void main(String[] args) {
        int n = 6, s = 0, t = 5;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 16));
        edges.add(new Edge(0, 2, 13));
        edges.add(new Edge(1, 2, 10));
        edges.add(new Edge(1, 3, 12));
        edges.add(new Edge(2, 1, 4));
        edges.add(new Edge(2, 4, 14));
        edges.add(new Edge(3, 2, 9));
        edges.add(new Edge(3, 4, 20));
        edges.add(new Edge(3, 5, 7));
        edges.add(new Edge(4, 5, 4));

        int maxFlow = maxFlow(n, s, t, edges);
        System.out.println("Maximum flow: " + maxFlow);
    }
}
