public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        graph.addVertex("Almaty");
        graph.addVertex("Astana");
        graph.addVertex("Shymkent");
        graph.addVertex("Pavlodar");
        graph.addVertex("Oral");

        graph.addEdge("Almaty", "Astana", 1.0);
        graph.addEdge("Almaty", "Shymkent", 2.0);
        graph.addEdge("Astana", "Pavlodar", 3.0);
        graph.addEdge("Astana", "Oral", 4.0);

        System.out.println(graph.hasEdge("Almaty", "Astana"));
        System.out.println(graph.hasEdge("Almaty", "Pavlodar"));

        System.out.println();

        graph.printGraph();
        System.out.println("_____________________________");
        graph.removeEdge("Astana", "Oral");

        graph.printGraph();

        System.out.println("BFS starting from Almaty:");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph);
        bfs.search("Almaty");

        System.out.println("\n\nDijkstra starting from Almaty:");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph);
        dijkstra.search("Almaty");
        System.out.println(dijkstra.getDistances());
    }
}