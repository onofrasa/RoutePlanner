package planner.strategy;

import planner.map.Map;
import planner.map.POI;
import planner.map.Route;

public class FastStrategy implements RouteStrategy {
    @Override
    public Route planRoute(POI start, POI end, Map map) {
        System.out.println("Planning fast route...");
        return null;
    }
}
