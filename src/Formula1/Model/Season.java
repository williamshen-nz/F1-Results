package Formula1.Model;

import Helpers.JSON;

import java.util.ArrayList;
import java.util.Collections;

public class Season {
    private int year;
    private Teams teams;
    private Drivers drivers;
    private ArrayList<Race> races;

    public Season() {
        races = new ArrayList<>();
    }

    public Season(int year, Race... races) {
        this();
        this.year = year;
        addRace(races);
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

    public Drivers getDrivers() {
        return drivers;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setDrivers(Drivers drivers) {
        this.drivers = drivers;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
