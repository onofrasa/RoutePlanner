package planner.strategy;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import planner.map.MapManager;
import planner.map.POI;
import planner.map.Road;
import planner.map.Route;

import java.util.List;

public class FastStrategy implements RouteStrategy {
    @Override
    public Route planRoute(POI start, POI end, MapManager mapManager) {
        Graph<POI, DefaultWeightedEdge> graph =
                new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        for (POI poi : mapManager.getPointOfInterests()) {
            graph.addVertex(poi);
        }

        for (Road road : mapManager.getRoads()) {
            DefaultWeightedEdge edge = graph.addEdge(road.getStart(), road.getEnd());
            graph.setEdgeWeight(edge, road.getTime());
        }

        GraphPath<POI, DefaultWeightedEdge> fastRoute = DijkstraShortestPath.findPathBetween(graph, start, end);

        List<POI> poiPath = fastRoute.getVertexList();
        double totalLength = 0;
        for (int i = 0; i < poiPath.size() - 1; i++) {
            POI from = poiPath.get(i);
            POI to = poiPath.get(i + 1);
            Road road = mapManager.getRoads().stream()
                    .filter(r -> r.getStart().equals(from) && r.getEnd().equals(to))
                    .findFirst()
                    .orElse(null);
            if (road != null) {
                totalLength += road.getLength();
            }
        }

        return new Route(fastRoute.getVertexList(), totalLength, fastRoute.getWeight());
    }

    @Override
    public String getName() {
        return "Fastest Route Strategy";
    }
}
