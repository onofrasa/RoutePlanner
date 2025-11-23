package planner.map;

import java.util.Objects;

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

    public String getFullName() {
        String scenicSymbol = isScenic ? "✨" : "";
        return scenicSymbol + name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        POI poi = (POI) o;
        return isScenic == poi.isScenic && Objects.equals(id, poi.id) && Objects.equals(name, poi.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isScenic);
    }
}
