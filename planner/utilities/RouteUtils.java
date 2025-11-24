package planner.utilities;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import planner.map.MapManager;
import planner.map.POI;
import planner.map.Road;

import java.util.List;

public class RouteUtils {

    public static Graph<POI, DefaultWeightedEdge> buildGraph(MapManager mapManager, String weightType) {
        Graph<POI, DefaultWeightedEdge> graph =
                new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        for (POI poi : mapManager.getPointOfInterests()) {
            graph.addVertex(poi);
        }

        for (Road road : mapManager.getRoads()) {
            double weight = "time".equalsIgnoreCase(weightType) ? road.getTime() : road.getLength();

            DefaultWeightedEdge edge1 = graph.addEdge(road.getStart(), road.getEnd());
            graph.setEdgeWeight(edge1, weight);

            DefaultWeightedEdge edge2 = graph.addEdge(road.getEnd(), road.getStart());
            graph.setEdgeWeight(edge2, weight);
        }

        return graph;
    }

    public static GraphPath<POI, DefaultWeightedEdge> findPath(Graph<POI, DefaultWeightedEdge> graph,
                                                               POI start, POI end) {
        GraphPath<POI, DefaultWeightedEdge> path = DijkstraShortestPath.findPathBetween(graph, start, end);
        if (path == null) {
            throw new RuntimeException("no route found from " + start.getName() + " to " + end.getName());
        }
        return path;
    }

    public static double calculateSecondaryMetric(List<POI> poiPath, MapManager mapManager, String metricType) {
        double total = 0;
        for (int i = 0; i < poiPath.size() - 1; i++) {
            POI from = poiPath.get(i);
            POI to = poiPath.get(i + 1);
            Road road = mapManager.getRoads().stream()
                    .filter(r -> (r.getStart().equals(from) && r.getEnd().equals(to)) ||
                            (r.getStart().equals(to) && r.getEnd().equals(from)))
                    .findFirst()
                    .orElse(null);
            if (road != null) {
                total += "time".equalsIgnoreCase(metricType) ? road.getTime() : road.getLength();
            }
        }
        return total;
    }
}
