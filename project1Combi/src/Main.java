import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Класс для представления ребра
class Edge {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

// Класс для представления графа
class Graph {
    // Список смежности
    private Map<Integer, Set<Integer>> adjacencyList;
    // Список пучков дуг
    private Map<Integer, Set<Edge>> edgeBundles;

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.edgeBundles = new HashMap<>();
    }

    // Добавление ребра
    public void addEdge(int source, int destination, int weight) {
        // Обновление списка смежности
        if (!adjacencyList.containsKey(source)) {
            adjacencyList.put(source, new HashSet<>());
        }
        adjacencyList.get(source).add(destination);

        // Обновление списка пучков дуг
        if (!edgeBundles.containsKey(source)) {
            edgeBundles.put(source, new HashSet<>());
        }
        edgeBundles.get(source).add(new Edge(source, destination, weight));
    }

    // Удаление ребра
    public void removeEdge(int source, int destination) {
        // Удаление из списка смежности
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }

        // Удаление из списка пучков дуг
        if (edgeBundles.containsKey(source)) {
            Set<Edge> edges = edgeBundles.get(source);
            edges.removeIf(e -> e.destination == destination);
        }
    }

    // Поиск ребра
    public Edge findEdge(int source, int destination) {
        if (edgeBundles.containsKey(source)) {
            for (Edge edge : edgeBundles.get(source)) {
                if (edge.destination == destination) {
                    return edge;
                }
            }
        }
        return null;
    }

    // Добавление вершины
    public void addVertex(int vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
            edgeBundles.put(vertex, new HashSet<>());
        }
    }

    // Удаление вершины
    public void removeVertex(int vertex) {
        // Удаление из списка смежности
        adjacencyList.remove(vertex);

        // Удаление из списка пучков дуг
        edgeBundles.remove(vertex);

        // Удаление ребер, связанных с вершиной
        for (Set<Integer> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        for (Set<Edge> edges : edgeBundles.values()) {
            edges.removeIf(e -> e.source == vertex || e.destination == vertex);
        }
    }

    // Поиск вершины
    public boolean containsVertex(int vertex) {
        return adjacencyList.containsKey(vertex);
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Добавление ребер
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 10);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 4, 7);

        // Поиск ребра
        Edge edge = graph.findEdge(1, 2);
        System.out.println("Edge from 1 to 2 with weight " + edge.weight); // Вывод: Edge from 1 to 2 with weight 5

        // Удаление ребра
        graph.removeEdge(1, 3);

        // Добавление вершины
        graph.addVertex(5);

        // Удаление вершины
        graph.removeVertex(2);

        // Проверка наличия вершины
        System.out.println("Graph contains vertex 3: " + graph.containsVertex(3)); // Вывод: Graph contains vertex 3: true
    }
}
