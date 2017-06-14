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
