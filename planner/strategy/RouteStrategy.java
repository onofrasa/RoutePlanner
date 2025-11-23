package planner.strategy;

import planner.map.Map;
import planner.map.POI;
import planner.map.Route;

public interface RouteStrategy {
    Route planRoute(POI start, POI end, Map map);
}
