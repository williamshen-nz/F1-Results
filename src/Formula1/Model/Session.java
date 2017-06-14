package Formula1.Model;

import Helpers.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class Session {
    private ArrayList<Result> results;
    private ArrayList<FastestLap> fastestLaps;
    private StartingGrid startingGrid;

    public Session() {
        this.results = new ArrayList<>(20);
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ArrayList<FastestLap> getFastestLaps() {
        return fastestLaps;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public StartingGrid getStartingGrid() {
        return startingGrid;
    }

    public void setFastestLaps(ArrayList<FastestLap> fastestLaps) {
        this.fastestLaps = fastestLaps;
    }

    public void setStartingGrid(StartingGrid startingGrid) {
        this.startingGrid = startingGrid;
    }

    public void addResult(Driver driver, Team team, int position, int laps, String time, int points) {
        results.add(new RaceResult(driver, team, position, laps, time, points));
    }

    public void addResult(Driver driver, Team team, int position, int laps, String time, String gap) {
        results.add(new PracticeResult(driver, team, position, laps, time, gap));
    }

    public void addResult(Driver driver, Team team, int position, int laps, String q1, String q2, String q3) {
        results.add(new QualifyingResult(driver, team, position, laps, q1, q2, q3));
    }

    public void addResult(Result result) {
        results.add(result);
    }

    public Result getResult(Driver driver) {
        for (Result result : results)
            if (result.getDriver().equals(driver)) return result;
        return null;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
