package planner.strategy;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import planner.map.MapManager;
import planner.map.POI;
import planner.map.Route;
import planner.utilities.RouteUtils;

import java.util.List;

public class ShortStrategy implements RouteStrategy {
    @Override
    public Route planRoute(POI start, POI end, MapManager mapManager) {
        var graph = RouteUtils.buildGraph(mapManager, "length");
        GraphPath<POI, DefaultWeightedEdge> path = RouteUtils.findPath(graph, start, end);

        List<POI> poiPath = path.getVertexList();
        double totalTime = RouteUtils.calculateSecondaryMetric(poiPath, mapManager, "time");

        return new Route(poiPath, path.getWeight(), totalTime);
    }

    @Override
    public String getName() {
        return "Shortest Route Strategy";
    }
}
