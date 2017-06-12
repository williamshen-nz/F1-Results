package Formula1.Analysis;

import Formula1.Model.*;
import Helpers.JSON;

import java.util.*;

public class SeasonStatistics {
    public static HashMap<ArrayList<Driver>, Integer> mostPolePositions(Season season) throws ResultNotFoundException {
        HashMap<Driver, Integer> polePositions = new HashMap<>(20);
        for (Race race : season.getRaces()) {
            GridPosition pole = RaceStatistics.getPolePosition(race);
            Integer currentPoles = polePositions.get(pole.getDriver());
            if (currentPoles == null)
                polePositions.put(pole.getDriver(), 1);
            else
                polePositions.put(pole.getDriver(), currentPoles + 1);
        }

        ArrayList<Driver> mostPoles = new ArrayList<>();
        int numPoles = 0;
        for (Driver driver : polePositions.keySet()) {
            if (polePositions.get(driver) > numPoles) {
                numPoles = polePositions.get(driver);
                mostPoles.clear();
                mostPoles.add(driver);
            } else if (polePositions.get(driver) == numPoles) {
                mostPoles.add(driver);
            }
        }

        HashMap<ArrayList<Driver>, Integer> res = new HashMap<>(mostPoles.size());
        res.put(mostPoles, numPoles);
        return res;
    }

    public static ArrayList<DriverPosition> getDriverPoints(Season season) {
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

        ArrayList<DriverPosition> rankings = new ArrayList<>(20);
        for (Map.Entry<Driver, Integer> entry : points.entrySet()) {
            rankings.add(new DriverPosition(entry.getKey(), entry.getValue()));
            Collections.sort(rankings);
        }
        return rankings;
    }

    public static int getPoints(Driver driver, Season season) {
        int res = 0;
        for (Race race : season.getRaces()) {
            for (Result result: race.getSessions().getRace().getResults()) {
                if (result.getDriver().equals(driver))
                    res += ((RaceResult) result).getPoints();
            }
        }
        return res;
    }

    public static int getPoints(Team team, Season season) {
        int res = 0;
        for (Race race : season.getRaces()) {
            for (Result result: race.getSessions().getRace().getResults()) {
                if (result.getDriver().getTeam().equals(team))
                    res += ((RaceResult) result).getPoints();
            }
        }
        return res;
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

    private static class DriverPosition implements Comparable<DriverPosition> {
        private Driver driver;
        private int points;

        public DriverPosition() {
        }

        public DriverPosition(Driver driver, int points) {
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
        public int compareTo(DriverPosition o) {
            return o.points - this.points;
        }

        @Override
        public String toString() {
            return JSON.stringify(this);
        }
    }
}
