package planner.map;

import java.util.List;

public class MapManager {

    private static final MapManager instance = new MapManager();

    private MapManager() {
    }

    public static MapManager getInstance() {
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
