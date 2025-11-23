package planner;

import planner.map.Map;
import planner.map.POI;
import planner.map.Route;
import planner.strategy.RouteStrategy;

public class RoutePlanner {
    public Route planRoute(RouteStrategy strategy, POI start, POI end, Map map) {
        return strategy.planRoute(start, end, map);
    }
}
