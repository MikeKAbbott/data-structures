import java.lang.reflect.Array;
import java.util.*;

public class Graph {

    private ArrayList<Item> nodes;
    private Hashtable<Item, ArrayList<Item>> neighbors;
    private Hashtable<Edge, Integer> weights;

    Graph(
            ArrayList<Item> nodes,
            Hashtable<Item, ArrayList<Item>> neighbors,
            Hashtable<Edge, Integer> weights) {
        this.nodes = nodes;
        this.neighbors = neighbors;
        this.weights = weights;
    }

    // -----

    ArrayList<Item> getNodes() {
        return nodes;
    }

    Hashtable<Item, ArrayList<Item>> getNeighbors() {
        return neighbors;
    }

    Hashtable<Edge, Integer> getWeights() {
        return weights;
    }

    // -----
    // Computes all shortest paths from a given node
    // Nodes are marked with the shortest path to the source

    void allShortestPaths(Item source) {
        for (Item node : nodes) {
            node.reset();
            node.setValue(Integer.MAX_VALUE);
        }
        source.setValue(0);
        WeakHeap heap = new WeakHeap(nodes);
        while (!heap.isEmpty()) {
            Item min = heap.extractMin();
            if(!min.isVisited()){
            min.setVisited(true);
            ArrayList myNeighbors = neighbors.get(min);
            for (int i = 0; i < myNeighbors.size(); i++) {
                Item neighbor = (Item) myNeighbors.get(i);
                Edge edge = new Edge(min, neighbor);
                int dist = min.getValue() + weights.get(edge);
                if (neighbor.getValue() > dist && !neighbor.isVisited()) {
                    heap.updateKey(neighbor.getPosition(), dist);
                    neighbor.setPrevious(min);
                }
            }
            }
        }
    }

    // -----
    // Point-to-point shortest path; stops as soon as it reaches destination

    ArrayList<Edge> shortestPath(Item source, Item dest) {
        for (Item node : nodes) {
            node.reset();
            node.setValue(Integer.MAX_VALUE);
        }
        source.setValue(0);
        WeakHeap heap = new WeakHeap(nodes);
        while (!heap.isEmpty()) {
            Item min = heap.extractMin();
            if (min.equals(dest)) {
                break;
            }
            if (!min.isVisited()) {
                min.setVisited(true);
                ArrayList myNeighbors = getNeighbors().get(min);
                for (int i = 0; i < myNeighbors.size(); i++) {
                    Item neighbor = (Item) myNeighbors.get(i);
                    Edge edge = new Edge(min, neighbor);
                    int dist = min.getValue() + weights.get(edge);
                    if (dist < neighbor.getValue() && !neighbor.isVisited()) {
                        heap.updateKey(neighbor.getPosition(), dist);
                        neighbor.setPrevious(min);
                    }
                }
            }
        }
        return Item.pathToSource(source);
    }


    // -----

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Nodes:%n%s%n", nodes));
        res.append(String.format("Neighbors:%n%s%n", neighbors));
        res.append(String.format("Weights:%n%s%n", weights));
        return new String(res);
    }
}
