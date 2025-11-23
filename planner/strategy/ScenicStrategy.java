package planner.strategy;

import planner.map.MapManager;
import planner.map.POI;
import planner.map.Road;
import planner.map.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScenicStrategy implements RouteStrategy {

    @Override
    public Route planRoute(POI start, POI end, MapManager mapManager) {
        List<POI> path = new ArrayList<>();
        path.add(start);

        Set<POI> visited = new HashSet<>();
        visited.add(start);

        POI current = start;
        int totalDistance = 0, totalTime = 0;

        while (!current.equals(end)) {
            POI localCurrent = current;

            List<Road> outgoing = mapManager.getRoads().stream()
                    .filter(r -> r.getStart().equals(localCurrent) || r.getEnd().equals(localCurrent))
                    .map(r -> {
                        POI neighbor = r.getStart().equals(localCurrent) ? r.getEnd() : r.getStart();
                        return new Road(localCurrent, neighbor, r.getLength(), r.getTime());
                    })
                    .filter(r -> !visited.contains(r.getEnd()))
                    .toList();

            Road next = outgoing.stream()
                    .filter(r -> r.getEnd().equals(end) || r.getEnd().isScenic())
                    .findFirst()
                    .orElse(outgoing.stream().findFirst().orElse(null));

            if (next == null) break;

            totalDistance += next.getLength();
            totalTime += next.getTime();
            current = next.getEnd();
            path.add(current);
            visited.add(current);
        }

        if (end != current) {
            throw new RuntimeException("no route found from " + start.getName() + " to " + end.getName());
        }

        return new Route(path, totalDistance, totalTime);
    }

    @Override
    public String getName() {
        return "Scenic Route Strategy";
    }
}
