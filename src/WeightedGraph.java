import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;

    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(V value) {
        vertices.put(value, new Vertex<>(value));
    }

    public void addEdge(V source, V destination, double weight) {
        Vertex<V> sourceVertex = vertices.get(source);
        Vertex<V> destVertex = vertices.get(destination);

        if (sourceVertex != null && destVertex != null) {
            sourceVertex.addAdjacentVertex(destVertex, weight);
            destVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }

    public boolean hasEdge(V source, V destination) {
        Vertex<V> sourceVertex = vertices.get(source);
        Vertex<V> destVertex = vertices.get(destination);

        return sourceVertex != null && destVertex != null && sourceVertex.getAdjacentVertices().containsKey(destVertex);
    }

    public Map<Vertex<V>, Double> getNeighbors(V vertex) {
        Vertex<V> v = vertices.get(vertex);
        return v != null ? v.getAdjacentVertices() : null;
    }

    public void printGraph() {
        for (V v : vertices.keySet()) {
            System.out.print("Vertex " + v + " connected to ");
            for (Vertex<V> neighbor : vertices.get(v).getAdjacentVertices().keySet()) {
                System.out.print(neighbor.getData() + " ");
            }
            System.out.println();
        }
    }

    public void removeEdge(V source, V destination) {
        Vertex<V> sourceVertex = vertices.get(source);
        Vertex<V> destVertex = vertices.get(destination);

        if (sourceVertex != null && destVertex != null) {
            sourceVertex.getAdjacentVertices().remove(destVertex);
            destVertex.getAdjacentVertices().remove(sourceVertex);
        }
    }
}