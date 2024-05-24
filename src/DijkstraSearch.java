import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;
    private Map<V, Double> distances;
    private Map<V, V> previous;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
        this.distances = new HashMap<>();
        this.previous = new HashMap<>();
    }

    @Override
    public void search(V start) {
        PriorityQueue<VertexDistance<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(VertexDistance::getDistance));
        pq.add(new VertexDistance<>(start, 0.0));
        distances.put(start, 0.0);

        while (!pq.isEmpty()) {
            VertexDistance<V> current = pq.poll();
            V currentVertex = current.getVertex();
            double currentDistance = current.getDistance();

            Map<Vertex<V>, Double> neighbors = graph.getNeighbors(currentVertex);
            if (neighbors != null) {
                for (Map.Entry<Vertex<V>, Double> entry : neighbors.entrySet()) {
                    V neighbor = entry.getKey().getData();
                    double weight = entry.getValue();

                    double distance = currentDistance + weight;

                    if (distance < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                        distances.put(neighbor, distance);
                        previous.put(neighbor, currentVertex);
                        pq.add(new VertexDistance<>(neighbor, distance));
                    }
                }
            }
        }
    }

    private static class VertexDistance<V> {
        private V vertex;
        private double distance;

        public VertexDistance(V vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public V getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }
    }

    public Map<V, Double> getDistances() {
        return distances;
    }

    public Map<V, V> getPrevious() {
        return previous;
    }
}