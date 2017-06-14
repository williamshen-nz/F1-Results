package Formula1.Model;

public class PracticeSession extends Session {
    public void addResult(Driver driver, Team team, int position, int laps, String time, String gap) {
        this.addResult(new PracticeResult(driver, team, position, laps, time, gap));
    }
}
