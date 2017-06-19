package Formula1.Views;

import Formula1.Analysis.RaceStatistics;
import Formula1.Analysis.ResultNotFoundException;
import Formula1.Model.*;
import Helpers.JSON;

import java.util.ArrayList;
import java.util.Collection;

public class DriverViews {
    public static Collection<Race> getResults(Driver driver, Season season) throws ResultNotFoundException {
        // Get a copy of the race results for a Driver in a season
        ArrayList<Race> results = new ArrayList<>(20);
        for (Race race : season.getRaces()) {
            // Make a copy of a Race
            Race copy = new Race(race.getRound(), race.getName(), race.getLocation(), race.getStartDate(), race.getEndDate());
            // Add the race session and result as required
            RaceSession raceSession = new RaceSession();
            Result result = race.getSessions().getRace().getResult(driver);
            if (result == null) continue;
            raceSession.addResult(result);
            Sessions sessions = new Sessions();
            sessions.setRace(raceSession);
            copy.setSessions(sessions);
            results.add(copy);
        }
        return results;
    }

    public static String getSummary(Driver driver, Season season) throws ResultNotFoundException {
        // Get a summary of a driver's performance throughout the season using a custom view model with more useful statistics
        DriverResult driverResult = new DriverResult(season.getYear(), driver);
        for (Race race : season.getRaces()) {
            // Get the relevant race result and extract data for the CustomResult
            RaceResult raceResult = (RaceResult) race.getSessions().getRace().getResult(driver);
            if (raceResult == null) continue;
            CustomResult customResult = new CustomResult(raceResult.getPosition(), raceResult.getLaps(),
                    ((RaceResult) RaceStatistics.getWinner(race)).getTime(), raceResult.getTime(), raceResult.getPoints());
            CustomRace customRace = new CustomRace(race.getName(), race.getEndDate(), customResult);
            driverResult.addRace(customRace);
        }
        // Return the JSON representation
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
