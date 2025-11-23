package planner.strategy;

import planner.map.MapManager;
import planner.map.POI;
import planner.map.Road;
import planner.map.Route;

import java.util.ArrayList;
import java.util.List;

public class ScenicStrategy implements RouteStrategy {

    @Override
    public Route planRoute(POI start, POI end, MapManager mapManager) {
        List<POI> path = new ArrayList<>();
        path.add(start);

        POI current = start;
        int totalDistance = 0, totalTime = 0;

        while (!current.equals(end)) {
            POI localCurrent = current;
            Road next = mapManager.getRoads().stream()
                    .filter(r -> r.getStart().equals(localCurrent))
                    .filter(r -> r.getEnd().equals(end) || r.getEnd().isScenic()) // prefer scenic or direct to end
                    .findFirst()
                    .orElse(mapManager.getRoads().stream()
                            .filter(r -> r.getStart().equals(localCurrent))
                            .findFirst()
                            .orElse(null));

            if (next == null) break; // dead end

            totalDistance += next.getLength();
            totalTime += next.getTime();
            current = next.getEnd();
            path.add(current);
        }

        return new Route(path, totalDistance, totalTime);

    }

    @Override
    public String getName() {
        return "Scenic Route Strategy";
    }
}
