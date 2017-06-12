package Formula1.Model;

import Helpers.JSON;

import java.util.ArrayList;

public class Session {
    private ArrayList<Result> results;

    public Session() {
        this.results = new ArrayList<>(20);
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void addResult(Driver driver, int position, int laps, String time, int points) {
        results.add(new RaceResult(driver, position, laps, time, points));
    }

    public void addResult(Driver driver, int position, int laps, String time, String gap) {
        results.add(new PracticeResult(driver, position, laps, time, gap));
    }

    public void addResult(Driver driver, int position, int laps, String q1, String q2, String q3) {
        results.add(new QualifyingResult(driver, position, laps, q1, q2, q3));
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
