package Formula1.Model;

import Helpers.JSON;

public class Driver {
    private String name;
    private int number;
    private String team;
    private String nationality;

    public Driver() {
    }

    public Driver(String name, int number, String team, String nationality) {
        this.name = name;
        this.number = number;
        this.team = team;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getTeam() {
        return team;
    }

    public String getNationality() {
        return nationality;
    }

    public boolean matchName(String name) {
        return this.name.contains(name);
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
