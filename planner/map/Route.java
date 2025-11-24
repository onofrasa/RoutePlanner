package planner.map;

import java.util.List;
import java.util.stream.Collectors;

public class Route {
    List<POI> waypoints;
    double totalDistance;
    double totalTime;

    public Route(List<POI> waypoints, double totalLength, double totalTime) {
        this.waypoints = waypoints;
        this.totalDistance = totalLength;
        this.totalTime = totalTime;
    }

    public String getRouteDescription() {
        String waypointsDescription = waypoints.stream().map(POI::getFullName).collect(Collectors.joining(" → "));
        return waypointsDescription + ", Total Distance: " + totalDistance + " km, Total Time: " + totalTime + " min";
    }

}
