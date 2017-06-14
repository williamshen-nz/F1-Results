package Formula1.Model;

public class RaceResult extends Result {
    private String time;
    private int points;
    private int gridPosition;
    private FastestLap fastestLap;

    public RaceResult(Driver driver, Team team, int position, int laps, String time,
                      int points, int gridPosition, FastestLap fastestLap) {
        super(driver, team, position, laps);
        this.time = time;
        this.points = points;
        this.gridPosition = gridPosition;
        this.fastestLap = fastestLap;
    }

    public String getTime() {
        return time;
    }

    public int getPoints() {
        return points;
    }

    public int getGridPosition() {
        return gridPosition;
    }

    public FastestLap getFastestLap() {
        return fastestLap;
    }
}
