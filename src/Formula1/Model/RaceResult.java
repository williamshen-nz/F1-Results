package Formula1.Model;

public class RaceResult extends Result {
    private String time;
    private int points;
    private FastestLap fastestLap;

    public RaceResult(Driver driver, Team team, int position, int laps, String time, int points, FastestLap fastestLap) {
        super(driver, team, position, laps);
        this.time = time;
        this.points = points;
        this.fastestLap = fastestLap;
    }

    public String getTime() {
        return time;
    }

    public int getPoints() {
        return points;
    }

    public FastestLap getFastestLap() {
        return fastestLap;
    }
}
