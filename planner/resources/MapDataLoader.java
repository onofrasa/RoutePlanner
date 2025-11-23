package planner.resources;

import planner.map.MapManager;
import planner.map.POI;
import planner.map.Road;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MapDataLoader {

    public static void loadData() {
        MapManager mapManager = MapManager.getInstance();
        List<POI> pointOfInterests = getPoiListFromFile();
        mapManager.setPointOfInterests(pointOfInterests);

        List<Road> roads = getRoadListFromFile(pointOfInterests);
        mapManager.setRoads(roads);
    }

    private static List<POI> getPoiListFromFile() {
        File poiFile = new File("src/planner/resources/poi.csv");

        List<POI> pointOfInterests = new ArrayList<>();
        try {
            String line;
            BufferedReader reader = Files.newBufferedReader(poiFile.toPath());
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                boolean isScenic = Boolean.parseBoolean(parts[2]);
                POI poi = new POI(id, name, isScenic);
                pointOfInterests.add(poi);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load poi data: " + e.getMessage());
        }
        return pointOfInterests;
    }

    private static List<Road> getRoadListFromFile(List<POI> pointOfInterests) {
        File roadsFile = new File("src/planner/resources/roads.csv");

        List<Road> roads = new ArrayList<>();
        try {
            String line;
            BufferedReader reader = Files.newBufferedReader(roadsFile.toPath());
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                POI poiStart = pointOfInterests.stream()
                        .filter(poi -> poi.getId().equals(parts[0]))
                        .findFirst()
                        .orElse(null);

                POI poiEnd = pointOfInterests.stream()
                        .filter(poi -> poi.getId().equals(parts[1]))
                        .findFirst()
                        .orElse(null);

                if (poiStart == null || poiEnd == null) {
                    System.err.println("Skipping road: invalid POI reference "
                            + parts[0] + " -> " + parts[1]);
                    continue;
                }

                int length = Integer.parseInt(parts[2]);
                int time = Integer.parseInt(parts[3]);
                Road road = new Road(poiStart, poiEnd, length, time);
                roads.add(road);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load roads data: " + e.getMessage());
        }
        return roads;
    }

}
