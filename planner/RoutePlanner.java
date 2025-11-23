package planner;

import planner.map.MapManager;
import planner.map.POI;
import planner.strategy.RouteStrategy;

public class RoutePlanner {
    public void planRoute(RouteStrategy strategy, String start, String end) {
        MapManager mapManager = MapManager.getInstance();
        String strategyName = strategy.getName();
        POI startPoi, endPoi;
        try {
            startPoi = getPoi(start);
            endPoi = getPoi(end);
        } catch (RuntimeException e) {
            System.out.println("Unfortunately, cannot plan " + strategyName + " from " + start + " to " + end + ", because " + e.getMessage());
            return;
        }

        try {
            System.out.println(strategyName + ": " + strategy.planRoute(startPoi, endPoi, mapManager).getRouteDescription());
        } catch (RuntimeException e) {
            System.out.println("Unfortunately, cannot plan " + strategyName + " from " + start + " to " + end + ", because " + e.getMessage());
        }
    }

    private static POI getPoi(String poiName) {
        MapManager mapManager = MapManager.getInstance();
        return mapManager.getPointOfInterests().stream()
                .filter(poi -> poi.getName().equalsIgnoreCase(poiName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(poiName + " was not found"));
    }
}
