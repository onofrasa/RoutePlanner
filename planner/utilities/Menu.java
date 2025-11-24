package planner.utilities;

import planner.map.POI;
import planner.strategy.FastStrategy;
import planner.strategy.RouteStrategy;
import planner.strategy.ScenicStrategy;
import planner.strategy.ShortStrategy;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final List<POI> poiList;
    private final Scanner keyboard = new Scanner(System.in);

    public Menu(List<POI> poiList) {
        this.poiList = poiList;
    }

    public Integer chooseStartPoint() {
        System.out.println("Choose your START POINT by selecting an id from the following: ");
        poiList.forEach(System.out::println);
        Integer startOption = Integer.valueOf(keyboard.nextLine());
        if (startOption <= 0 || startOption > poiList.size()) {
            System.out.println("Invalid Start Point ID. Please try again.");
            return null;
        }
        return startOption;
    }

    public Integer chooseDestination(Integer startOption) {
        System.out.println("Choose your DESTINATION by selecting an id from the following: ");
        poiList.stream()
                .filter(poi -> !poi.getId().equals(startOption.toString()))
                .forEach(System.out::println);

        Integer destinationOption = Integer.valueOf(keyboard.nextLine());

        if (destinationOption <= 0 || destinationOption > poiList.size()) {
            System.out.println("Invalid Destination ID. Please try again.");
            return null;
        }
        if (startOption.equals(destinationOption)) {
            System.out.println("Destination can't be the same as start point, please select a different Destination");
            return null;
        }
        return destinationOption;
    }

    public RouteStrategy chooseStrategy() {
        System.out.println("""
                 Choose the ROUTE STRATEGY:
                 1 Shortest Route
                 2 Fastest Route
                 3 Scenic Route
                """);
        Integer strategyOption = Integer.valueOf(keyboard.nextLine());
        if (strategyOption <= 0 || strategyOption > 3) {
            System.out.println("Invalid Strategy ID. Please try again.");
            return null;
        }
        return switch (strategyOption) {
            case 1 -> new ShortStrategy();
            case 2 -> new FastStrategy();
            case 3 -> new ScenicStrategy();
            default -> throw new IllegalArgumentException("Invalid strategy ID");
        };
    }

    public Integer continueProgram() {
        System.out.println("""
                 Do you want to create another route?
                 1 Continue
                 0 Exit
                """);
        return Integer.valueOf(keyboard.nextLine());
    }
}
