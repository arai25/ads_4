import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;
    private Set<V> visited;

    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
        this.visited = new HashSet<>();
    }

    @Override
    public void search(V start) {
        Queue<V> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            V vertex = queue.poll();
            System.out.print(vertex + " ");

            Map<Vertex<V>, Double> neighbors = graph.getNeighbors(vertex);
            if (neighbors != null) {
                for (Vertex<V> neighbor : neighbors.keySet()) {
                    if (!visited.contains(neighbor.getData())) {
                        queue.add(neighbor.getData());
                        visited.add(neighbor.getData());
                    }
                }
            }
        }
    }
}