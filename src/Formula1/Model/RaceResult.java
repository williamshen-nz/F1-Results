package Formula1.Model;

public class RaceResult extends Result {
    private String time;
    private int points;

    public RaceResult(Driver driver, Team team, int position, int laps, String time, int points) {
        super(driver, team, position, laps);
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
