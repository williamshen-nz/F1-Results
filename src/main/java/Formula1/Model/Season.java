package Formula1.Model;

import Helpers.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;

public class Season {
    private int year;
    private ArrayList<Team> teams;
    private ArrayList<Driver> drivers;
    private ArrayList<Race> races;

    public Season() {
        teams = new ArrayList<>(10);
        drivers = new ArrayList<>(20);
        races = new ArrayList<>(20);
    }

    public Season(int year, Race... races) {
        this();
        this.year = year;
        addRaces(races);
    }

    public int getYear() {
        return year;
    }

    @JsonIgnore
    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void addTeams(Team... teams) {
        Collections.addAll(this.teams, teams);
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Race> getRaces() {
        return races;
    }

    public void addRaces(Race... races) {
        Collections.addAll(this.races, races);
    }

    public void setRaces(ArrayList<Race> races) {
        this.races = races;
    }

    @JsonIgnore
    public ArrayList<Driver> getDrivers() { return drivers; }

    public void addDrivers(Driver... drivers) {
        Collections.addAll(this.drivers, drivers);
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public Team getTeam(String match) {
        for (Team team : teams) {
            if (team.getEntrant().toLowerCase().equals(match.toLowerCase())) return team;
            if (team.getConstructor().toLowerCase().equals(match.toLowerCase())) return team;
            if (team.getAbbreviation().toLowerCase().equals(match.toLowerCase())) return team;
        }
        return null;
    }

    public Driver getDriver(String name) {
        // Not very reliable, there may be drivers with same first names, last names, etc.
        for (Driver driver : drivers)
            if (driver.getName().toLowerCase().contains(name.toLowerCase())) return driver;
        return null;
    }

    public Driver getDriver(int number) {
        // Guaranteed to function properly, drivers cannot have the same permanent number in a single season
        for (Driver driver : drivers)
            if (driver.getNumber() == number) return driver;
        return null;
    }

    public Race getRace(String match) {
        for (Race race : races) {
            if (race.getName().toLowerCase().contains(match.toLowerCase())) return race;
            if (race.getLocation().toLowerCase().contains(match.toLowerCase())) return race;
        }
        return null;
    }

    public Race getRace(int round) {
        for (Race race : races)
            if (race.getRound() == round) return race;
        return null;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
