package planner;

import planner.map.MapManager;
import planner.resources.MapDataLoader;
import planner.strategy.FastStrategy;
import planner.strategy.ScenicStrategy;
import planner.strategy.ShortStrategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Route planner started...");
        MapManager mapManager = MapManager.getInstance();
        MapDataLoader.loadData();

        RoutePlanner planner = new RoutePlanner();
        planner.planRoute(new ShortStrategy(), "Center", "Airport", mapManager);
        planner.planRoute(new FastStrategy(), "Center", "Airport", mapManager);
        planner.planRoute(new ScenicStrategy(), "Center", "Airport", mapManager);

        planner.planRoute(new ScenicStrategy(), "Home", "Airport", mapManager);

        planner.planRoute(new ShortStrategy(), "Airport", "Center", mapManager);
        planner.planRoute(new FastStrategy(), "Airport", "Center", mapManager);
        planner.planRoute(new ScenicStrategy(), "Airport", "Center", mapManager);
    }

}