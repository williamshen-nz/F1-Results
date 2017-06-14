package Formula1.Model;

public class QualifyingSession extends Session {
    public void addResult(Driver driver, Team team, int position, int laps, String q1, String q2, String q3) {
        this.addResult(new QualifyingResult(driver, team, position, laps, q1, q2, q3));
    }
}
