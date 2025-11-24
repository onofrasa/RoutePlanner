package planner.strategy;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import planner.map.MapManager;
import planner.map.POI;
import planner.map.Route;
import planner.utilities.RouteUtils;

import java.util.List;

public class FastStrategy implements RouteStrategy {
    @Override
    public Route planRoute(POI start, POI end, MapManager mapManager) {
        var graph = RouteUtils.buildGraph(mapManager, "time");
        GraphPath<POI, DefaultWeightedEdge> path = RouteUtils.findPath(graph, start, end);

        List<POI> poiPath = path.getVertexList();
        double totalLength = RouteUtils.calculateSecondaryMetric(poiPath, mapManager, "length");

        return new Route(poiPath, totalLength, path.getWeight());
    }

    @Override
    public String getName() {
        return "Fastest Route Strategy";
    }
}
