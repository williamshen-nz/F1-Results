package Formula1.Analysis;

import Formula1.Model.*;
import Helpers.JSON;

import java.util.*;

public class SeasonStatistics {
    public static ArrayList<TeamPosition> getDriverPoints(Season season) {
        HashMap<Driver, Integer> points = new LinkedHashMap<>(20);
        for (Race race : season.getRaces()) {
            for (Result result : race.getSessions().getRace().getResults()) {
                Integer currentPoints = points.get(result.getDriver());
                if (currentPoints == null)
                    points.put(result.getDriver(), ((RaceResult) result).getPoints());
                else
                    points.put(result.getDriver(), currentPoints + ((RaceResult) result).getPoints());
            }
        }

        ArrayList<TeamPosition> rankings = new ArrayList<>(20);
        for (Map.Entry<Driver, Integer> entry : points.entrySet()) {
            rankings.add(new TeamPosition(entry.getKey(), entry.getValue()));
            Collections.sort(rankings);
        }
        return rankings;
    }

    public static ArrayList<ConstructorPosition> getConstructorPoints(Season season) {
        HashMap<Team, Integer> points = new LinkedHashMap<>(10);
        for (Race race : season.getRaces()) {
            for (Result result : race.getSessions().getRace().getResults()) {
                Team team = result.getDriver().getTeam();
                Integer currentPoints = points.get(team);
                if (currentPoints == null)
                    points.put(team, ((RaceResult) result).getPoints());
                else
                    points.put(team, currentPoints + ((RaceResult) result).getPoints());
            }
        }

        ArrayList<ConstructorPosition> rankings = new ArrayList<>(20);
        for (Map.Entry<Team, Integer> entry : points.entrySet()) {
            rankings.add(new ConstructorPosition(entry.getKey(), entry.getValue()));
            Collections.sort(rankings);
        }
        return rankings;
    }


    private static class ConstructorPosition implements Comparable<ConstructorPosition> {
        private Team team;
        private int points;

        public ConstructorPosition() {
        }

        public ConstructorPosition(Team team, int points) {
            this.team = team;
            this.points = points;
        }

        public Team getTeam() {
            return team;
        }

        public int getPoints() {
            return points;
        }

        @Override
        public int compareTo(ConstructorPosition o) {
            return o.points - this.points;
        }

        @Override
        public String toString() {
            return JSON.stringify(this);
        }
    }

    private static class TeamPosition implements Comparable<TeamPosition> {
        private Driver driver;
        private int points;

        public TeamPosition() {
        }

        public TeamPosition(Driver driver, int points) {
            this.driver = driver;
            this.points = points;
        }

        public Driver getDriver() {
            return driver;
        }

        public int getPoints() {
            return points;
        }

        @Override
        public int compareTo(TeamPosition o) {
            return o.points - this.points;
        }

        @Override
        public String toString() {
            return JSON.stringify(this);
        }
    }
}
