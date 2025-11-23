package planner.map;

public class Road {
    int length;
    int time;
    POI start;
    POI end;

    public Road(POI start, POI end, int length, int time) {
        this.length = length;
        this.time = time;
        this.start = start;
        this.end = end;
    }

    public int getLength() {
        return length;
    }

    public int getTime() {
        return time;
    }

    public POI getStart() {
        return start;
    }

    public POI getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Road{" +
                "start=" + start.getName() +
                ", end=" + end.getName() +
                ", length=" + length +
                ", time=" + time +
                '}';
    }
}
