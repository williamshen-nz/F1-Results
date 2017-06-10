package Formula1.Analysis;

import Formula1.Model.*;
import Helpers.JSON;

import java.util.*;

public class SeasonStatistics {
    public static ArrayList<Position> getPoints(Season season) {
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

        ArrayList<Position> rankings = new ArrayList<>(20);
        for (Map.Entry<Driver, Integer> entry : points.entrySet()) {
            rankings.add(new Position(entry.getKey(), entry.getValue()));
            Collections.sort(rankings);
        }
        return rankings;
    }

    private static class Position implements Comparable<Position> {
        private Driver driver;
        private int points;

        public Position() {
        }

        public Position(Driver driver, int points) {
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
        public int compareTo(Position o) {
            return o.points - this.points;
        }

        @Override
        public String toString() {
            return JSON.stringify(this);
        }
    }
}
