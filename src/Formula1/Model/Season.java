package Formula1.Model;

import Helpers.JSON;

import java.util.ArrayList;
import java.util.Collections;

public class Season {
    private int year;
    private ArrayList<Race> races;

    public Season() {
        races = new ArrayList<>();
    }

    public Season(int year, Race... races) {
        this();
        this.year = year;
        Collections.addAll(this.races, races);
    }

    public int getYear() {
        return year;
    }

    public void addRace(Race... races) {
        Collections.addAll(this.races, races);
    }

    public ArrayList<Race> getRaces() {
        return races;
    }

    @Override
    public String toString() {
        return JSON.toString(this);
    }
}
