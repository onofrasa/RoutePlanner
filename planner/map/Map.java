package planner.map;

import java.util.List;

public class Map {

    private static final Map instance = new Map();

    private Map() {
    }

    public static Map getInstance() {
        return instance;
    }

    private List<POI> pointOfInterests;
    private List<Road> roads;

    public List<POI> getPointOfInterests() {
        return pointOfInterests;
    }

    public void setPointOfInterests(List<POI> pointOfInterests) {
        this.pointOfInterests = pointOfInterests;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }
}
