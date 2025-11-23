package planner.map;

public class POI {
    String id;
    String name;
    boolean isScenic;

    public POI(String id, String name, boolean isPretty) {
        this.id = id;
        this.name = name;
        this.isScenic = isPretty;
    }

    public boolean isScenic() {
        return isScenic;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "POI{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isScenic=" + isScenic +
                '}';
    }
}
