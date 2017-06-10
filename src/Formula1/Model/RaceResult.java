package Formula1.Model;

public class RaceResult extends Result {
    private String time;
    private int points;

    public RaceResult(Driver driver, int position, int laps, String time, int points) {
        super(driver, position, laps);
        this.time = time;
        this.points = points;
    }

    public String getTime() {
        return time;
    }

    public int getPoints() {
        return points;
    }
}
