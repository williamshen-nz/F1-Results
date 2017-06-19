package Formula1.Analysis;

import Formula1.Model.*;
import Helpers.JSON;

import java.util.*;

public class SeasonStatistics {
    public static HashMap<ArrayList<Driver>, Integer> mostPolePositions(Season season) throws ResultNotFoundException {
        // Create a map of (Driver -> Pole Positions)
        HashMap<Driver, Integer> polePositions = new HashMap<>(20);
        for (Race race : season.getRaces()) {
            Driver poleSitter = RaceStatistics.getPolePosition(race);
            Integer currentPoles = polePositions.get(poleSitter);
            if (currentPoles == null)
                polePositions.put(poleSitter, 1);
            else
                polePositions.put(poleSitter, currentPoles + 1);
        }

        // We need an ArrayList because many Drivers may have had the same number of pole positions
        ArrayList<Driver> mostPoles = new ArrayList<>();
        int numPoles = 0;
        // Go through the map and add values accordingly
        for (Driver driver : polePositions.keySet()) {
            // If we have found a new maximum, clear the old Driver list
            if (polePositions.get(driver) > numPoles) {
                numPoles = polePositions.get(driver);
                mostPoles.clear();
                mostPoles.add(driver);
            } else if (polePositions.get(driver) == numPoles) {
                mostPoles.add(driver);
            }
        }

        // Create the resulting key value map
        HashMap<ArrayList<Driver>, Integer> res = new HashMap<>(mostPoles.size());
        res.put(mostPoles, numPoles);
        return res;
    }

    public static ArrayList<DriverPosition> getDriverPoints(Season season) {
        // Create an ordered map of driver to points and driver to team
        HashMap<Driver, Integer> points = new LinkedHashMap<>(20);
        HashMap<Driver, Team> driverToTeam = new HashMap<>();
        // Get the number of points for the season and add it accordingly
        for (Race race : season.getRaces()) {
            for (Result result : race.getSessions().getRace().getResults()) {
                driverToTeam.put(result.getDriver(), result.getTeam());
                Integer currentPoints = points.get(result.getDriver());
                if (currentPoints == null)
                    points.put(result.getDriver(), ((RaceResult) result).getPoints());
                else
                    points.put(result.getDriver(), currentPoints + ((RaceResult) result).getPoints());
            }
        }

        // Add the driver rankings into a descending points-order sorted list
        ArrayList<DriverPosition> rankings = new ArrayList<>(20);
        for (Map.Entry<Driver, Integer> entry : points.entrySet()) {
            rankings.add(new DriverPosition(entry.getKey(), driverToTeam.get(entry.getKey()), entry.getValue()));
            Collections.sort(rankings);
        }
        return rankings;
    }

    public static int getPoints(Driver driver, Season season) {
        // Get the number of points a driver has in a season
        int res = 0;
        for (Race race : season.getRaces()) {
            for (Result result: race.getSessions().getRace().getResults()) {
                if (result.getDriver().equals(driver))
                    res += ((RaceResult) result).getPoints();
            }
        }
        return res;
    }

    public static ArrayList<ConstructorPosition> getConstructorPoints(Season season) {
        // Create an ordered map of team to points
        HashMap<Team, Integer> points = new LinkedHashMap<>(10);
        // Go through each race result and add points accordingly
        for (Race race : season.getRaces()) {
            for (Result result : race.getSessions().getRace().getResults()) {
                Team team = result.getTeam();
                Integer currentPoints = points.get(team);
                if (currentPoints == null)
                    points.put(team, ((RaceResult) result).getPoints());
                else
                    points.put(team, currentPoints + ((RaceResult) result).getPoints());
            }
        }

        // Add the team rankings into a descending points-order sorted list
        ArrayList<ConstructorPosition> rankings = new ArrayList<>(20);
        for (Map.Entry<Team, Integer> entry : points.entrySet()) {
            rankings.add(new ConstructorPosition(entry.getKey(), entry.getValue()));
            Collections.sort(rankings);
        }
        return rankings;
    }

    public static int getPoints(Team team, Season season) {
        // Get the number of points a team/constructor has in a season
        int res = 0;
        for (Race race : season.getRaces()) {
            for (Result result: race.getSessions().getRace().getResults()) {
                if (result.getTeam().equals(team))
                    res += ((RaceResult) result).getPoints();
            }
        }
        return res;
    }

    private static class ConstructorPosition implements Comparable<ConstructorPosition> {
        // Custom class for a Team's position within the constructor's championship, designed to be JSON compatible
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
        // Custom class for a Driver's position within the championship, designed to be JSON compatible
        private Driver driver;
        private Team team;
        private int points;

        public DriverPosition() {
        }

        public DriverPosition(Driver driver, Team team, int points) {
            this.driver = driver;
            this.team = team;
            this.points = points;
        }

        public Driver getDriver() {
            return driver;
        }

        public Team getTeam() {
            return team;
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
