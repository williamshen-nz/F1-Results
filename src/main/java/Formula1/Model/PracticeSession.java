package Formula1.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = PracticeSession.class)
public class PracticeSession extends Session {
    public PracticeSession() {
    }

    public void addResult(Driver driver, Team team, int position, int laps, String time, String gap) {
        this.addResult(new PracticeResult(driver, team, position, laps, time, gap));
    }
}
