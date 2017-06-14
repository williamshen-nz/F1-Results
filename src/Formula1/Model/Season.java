package Formula1.Model;

import Helpers.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.HashMap;

public class Season {
    private int year;
    private Teams teams;
    private Drivers drivers;
    private HashMap<Integer, Race> races;
    private int index = 1;

    public Season() {
        races = new HashMap<>();
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
        for (Race race : races)
            this.races.put(index++, race);;
    }

    @JsonIgnore
    public Collection<Race> getRaces() {
        return races.values();
    }

    public HashMap<Integer, Race> getRacesMap() {
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
