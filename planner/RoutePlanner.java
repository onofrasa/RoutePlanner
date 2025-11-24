package planner;

import planner.map.MapManager;
import planner.map.POI;
import planner.strategy.FastStrategy;
import planner.strategy.RouteStrategy;
import planner.strategy.ScenicStrategy;
import planner.strategy.ShortStrategy;

import java.util.Scanner;

public class RoutePlanner {
    MapManager mapManager = MapManager.getInstance();

    public void planRoute(Integer start, Integer end, RouteStrategy strategy) {

        String strategyName = strategy.getName();
        POI startPoi, endPoi;
        try {
            startPoi = getPoiById(start);
            endPoi = getPoiById(end);
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

    public static POI getPoiById(Integer poiId) {
        MapManager mapManager = MapManager.getInstance();
        return mapManager.getPointOfInterests().stream()
                .filter(poi -> poi.getId().equalsIgnoreCase(poiId.toString()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(poiId + " was not found"));
    }
}
