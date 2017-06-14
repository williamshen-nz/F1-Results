package Formula1.Model;

public class RaceSession extends Session {
    public void addResult(Driver driver, Team team, int position, int laps, String time, int points, int gridPosition, FastestLap fastestLap) {
        this.addResult(new RaceResult(driver, team, position, laps, time, points, gridPosition, fastestLap));
    }
}
