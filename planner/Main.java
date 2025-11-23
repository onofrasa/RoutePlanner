package planner;

import planner.map.Map;
import planner.map.Route;
import planner.strategy.FastStrategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Route planner started...");
        Map map = Map.getInstance();
        MapDataLoader.loadData();

        map.getPointOfInterests().forEach(System.out::println);
        map.getRoads().forEach(System.out::println);

        RoutePlanner planner = new RoutePlanner();
        Route myRoute = planner.planRoute(new FastStrategy(), map.getPointOfInterests().get(0), map.getPointOfInterests().get(3), map);
        System.out.println("Planned Route: " + myRoute);


    }
}