package Formula1.Views;

import Formula1.Analysis.RaceStatistics;
import Formula1.Analysis.ResultNotFoundException;
import Formula1.Model.*;
import Helpers.JSON;

import java.util.ArrayList;

public class DriverViews {
    public static String getSummary(Driver driver, Season season) throws ResultNotFoundException {
        DriverResult driverResult = new DriverResult(season.getYear(), driver);
        for (Race race : season.getRaces()) {
            RaceResult raceResult = (RaceResult) race.getSessions().getRace().getResult(driver);
            if (raceResult == null) continue;
            CustomResult customResult = new CustomResult(raceResult.getPosition(), raceResult.getLaps(),
                    ((RaceResult) RaceStatistics.getWinner(race)).getTime(), raceResult.getTime(), raceResult.getPoints());
            CustomRace customRace = new CustomRace(race.getName(), race.getEndDate(), customResult);
            driverResult.addRace(customRace);
        }
        return driverResult.toString();
    }

    private static class DriverResult {
        private int year;
        private Driver driver;
        private ArrayList<CustomRace> races;

        public DriverResult(int year, Driver driver) {
            this.year = year;
            this.driver = driver;
            this.races = new ArrayList<>(20);
        }

        public void addRace(CustomRace race) {
            races.add(race);
        }

        public int getYear() {
            return year;
        }

        public Driver getDriver() {
            return driver;
        }

        public ArrayList<CustomRace> getRaces() {
            return races;
        }

        @Override
        public String toString() {
            return JSON.stringify(this);
        }
    }

    private static class CustomRace {
        private String name;
        private String date;
        private CustomResult result;

        public CustomRace(String name, String date, CustomResult result) {
            this.name = name;
            this.date = date;
            this.result = result;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public CustomResult getResult() {
            return result;
        }

        @Override
        public String toString() {
            return JSON.stringify(this);
        }
    }

    private static class CustomResult {
        private int position;
        private int laps;
        private String winningTime;
        private String time;
        private int points;

        public CustomResult(int position, int laps, String winningTime, String time, int points) {
            this.position = position;
            this.laps = laps;
            this.winningTime = winningTime;
            this.time = time;
            this.points = points;
        }

        public Object getPosition() {
            return position == -1 ? "NC" : position;
        }

        public int getLaps() {
            return laps;
        }

        public String getwinningTime() {
            return winningTime;
        }

        public String getTime() {
            return time;
        }

        public int getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return JSON.stringify(this);
        }
    }
}
