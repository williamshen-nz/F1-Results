package Formula1.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;

@JsonDeserialize(as = RaceSession.class)
public class RaceSession extends Session {
    private ArrayList<PitStop> pitStops;

    public RaceSession() {
        pitStops = new ArrayList<>(20);
    }

    public ArrayList<PitStop> getPitStops() {
        return pitStops;
    }

    public ArrayList<PitStop> getPitStops(Driver driver) {
        ArrayList<PitStop> driverCopy = new ArrayList<>();
        for (PitStop ps : pitStops)
            if (ps.getDriver().equals(driver)) driverCopy.add(ps);
        return driverCopy;
    }

    public void setPitStops(ArrayList<PitStop> pitStops) {
        this.pitStops = pitStops;
    }

    public void addResult(Driver driver, Team team, int position, int laps, String time, int points, int gridPosition, FastestLap fastestLap) {
        this.addResult(new RaceResult(driver, team, position, laps, time, points, gridPosition, fastestLap));
    }
}
