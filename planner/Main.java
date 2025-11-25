package planner;

import planner.map.MapManager;
import planner.strategy.RouteStrategy;
import planner.utilities.MapDataLoader;
import planner.utilities.Menu;

public class Main {
    public static void main(String[] args) {

        MapManager mapManager = MapManager.getInstance();
        MapDataLoader.loadData();
        Menu menu = new Menu(mapManager.getPointOfInterests());
        RoutePlanner planner = new RoutePlanner();

        Integer continueProgram = 1;
        // loop to allow multiple route planning
        while (!continueProgram.equals(0)) {
            //choose start and destination
            Integer startOption = menu.chooseStartPoint();
            if (startOption == null) continue;

            Integer destinationOption = menu.chooseDestination(startOption);
            if (destinationOption == null) continue;

            //choose route strategy
            RouteStrategy strategyOption = menu.chooseStrategy();
            if (strategyOption == null) continue;

            planner.planRoute(startOption, destinationOption, strategyOption);

            continueProgram = menu.continueProgram();
        }
    }


}