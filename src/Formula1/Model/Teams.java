package Formula1.Model;

import Helpers.JSON;

import java.util.ArrayList;

public class Teams {
    private ArrayList<Team> teams;

    public Teams() {
        teams = new ArrayList<>(10);
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public Team getTeam(String abbreviation) {
        for (Team team : teams)
            if (team.getAbbreviation().equals(abbreviation)) return team;
        return null;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
