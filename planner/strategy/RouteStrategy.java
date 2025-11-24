package planner.strategy;

import planner.map.MapManager;
import planner.map.POI;
import planner.map.Route;

public interface RouteStrategy {
    Route planRoute(POI start, POI end, MapManager mapManager);

    String getName();
}
