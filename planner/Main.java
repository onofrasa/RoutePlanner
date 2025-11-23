package planner;

import planner.resources.MapDataLoader;
import planner.strategy.FastStrategy;
import planner.strategy.ScenicStrategy;
import planner.strategy.ShortStrategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Loading map data");
        MapDataLoader.loadData();

        System.out.println("Planning routes");
        RoutePlanner planner = new RoutePlanner();
        planner.planRoute(new ShortStrategy(), "Center", "Airport");
        planner.planRoute(new FastStrategy(), "Center", "Airport");
        planner.planRoute(new ScenicStrategy(), "Center", "Airport");

        planner.planRoute(new ScenicStrategy(), "Home", "Airport");

        planner.planRoute(new ShortStrategy(), "Airport", "Center");
        planner.planRoute(new FastStrategy(), "Airport", "Center");
        planner.planRoute(new ScenicStrategy(), "Airport", "Center");
    }

}