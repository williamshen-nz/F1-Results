package Formula1.Views;

import Formula1.Model.*;
import Helpers.JSON;

import java.util.ArrayList;
import java.util.HashMap;

public class TeamViews {
    public static String getDetailed(Team team, Season season) {
        TeamResult teamResult = new TeamResult(season.getYear(), team);
        for (Race race : season.getRaces()) {
            CustomRace customRace = new DetailedCustomRace(race.getName(), race.getEndDate());
            for (Result result : race.getSessions().getRace().getResults()) {
                if (result.getTeam().equals(team))
                    ((DetailedCustomRace) customRace).put(result.getDriver().getName(), ((RaceResult) result).getPoints());
            }
            teamResult.addRace(customRace);
        }
        return teamResult.toString();
    }

    public static String getSummary(Team team, Season season) {
        TeamResult teamResult = new TeamResult(season.getYear(), team);
        for (Race race : season.getRaces()) {
            int points = 0;
            for (Result result : race.getSessions().getRace().getResults()) {
                if (result.getTeam().equals(team))
                    points += ((RaceResult) result).getPoints();
            }
            CustomRace customRace = new SimpleCustomRace(race.getName(), race.getEndDate(), points);
            teamResult.addRace(customRace);
        }
        return teamResult.toString();
    }

    private static class TeamResult {
        private int year;
        private Team team;
        private ArrayList<CustomRace> races;

        public TeamResult(int year, Team driver) {
            this.year = year;
            this.team = driver;
            this.races = new ArrayList<>(20);
        }

        public void addRace(CustomRace race) {
            races.add(race);
        }

        public int getYear() {
            return year;
        }

        public Team getTeam() {
            return team;
        }

        public ArrayList<CustomRace> getRaces() {
            return races;
        }

        @Override
        public String toString() {
            return JSON.stringify(this);
        }
    }

    private static abstract class CustomRace {
        private String name;
        private String date;

        public CustomRace(String name, String date) {
            this.name = name;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        @Override
        public String toString() {
            return JSON.stringify(this);
        }
    }

    private static class SimpleCustomRace extends CustomRace {
        private int points;

        public SimpleCustomRace(String name, String date, int points) {
            super(name, date);
            this.points = points;
        }

        public int getPoints() {
            return points;
        }
    }

    private static class DetailedCustomRace extends CustomRace {
        private HashMap<String, Integer> points;

        public DetailedCustomRace(String name, String date) {
            super(name, date);
            this.points = new HashMap<>(2);
        }

        public void put(String name, int points) {
            this.points.put(name, points);
        }

        public HashMap<String, Integer> getPoints() {
            return points;
        }
    }
}
