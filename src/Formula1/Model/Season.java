package Formula1.Model;

import Helpers.JSON;

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

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void addTeams(Team... teams) {
        Collections.addAll(this.teams, teams);
    }

    public ArrayList<Race> getRaces() {
        return races;
    }

    public void addRaces(Race... races) {
        Collections.addAll(this.races, races);
    }

    public ArrayList<Driver> getDrivers() { return drivers; }

    public void addDrivers(Driver... drivers) {
        Collections.addAll(this.drivers, drivers);
    }

    public Team getTeam(String match) {
        for (Team team : teams) {
            if (team.getEntrant().equals(match)) return team;
            if (team.getConstructor().equals(match)) return team;
            if (team.getAbbreviation().equals(match)) return team;
        }
        return null;
    }

    public Driver getDriver(String name) {
        // Not very reliable, there may be drivers with same first names, last names, etc.
        for (Driver driver : drivers)
            if (driver.getName().contains(name)) return driver;
        return null;
    }

    public Driver getDriver(int number) {
        // Guaranteed to function properly, drivers cannot have the same permanent number in a single season
        for (Driver driver : drivers)
            if (driver.getNumber() == number) return driver;
        return null;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
