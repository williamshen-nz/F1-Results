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

    @Override
    public String toString() {
        return JSON.toString(this);
    }
}
