package Formula1.Model;

import Helpers.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

public class Session {
    private ArrayList<Result> results;
    private ArrayList<String> notes;

    public Session() {
        this.results = new ArrayList<>(20);
        this.notes = new ArrayList<>(1);
    }

    @JsonInclude(NON_EMPTY)
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

    public void addNote(String note) { notes.add(note); }

    @JsonInclude(NON_EMPTY)
    public ArrayList<String> getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
