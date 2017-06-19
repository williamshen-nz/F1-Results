package Demo;

import Formula1.Model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import static Demo.LoadSession.SessionType.Practice;
import static Demo.LoadSession.SessionType.Qualifying;

public class LoadSession {
    public enum SessionType {
        Practice, Qualifying, Race;
    }

    public static Session load(SessionType type, String location, String base) throws Exception {
        Session session;
        HashMap<Driver, FastestLap> fastestLaps = null;
        HashMap<Driver, Integer> startingGrid = null;

        if (type == Practice)
            session = new PracticeSession();
        else if (type == Qualifying)
            session = new QualifyingSession();
        else {
            session = new RaceSession();
            fastestLaps = loadFastestLaps(base + "fastest-laps.txt");
            startingGrid = loadStartingGrid(session, base + "starting-grid.txt");
            ((RaceSession) session).setPitStops(loadPitStops(base + "pit-stop-summary.txt"));
        }
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            if (str == null) {
                return session;
            }
            String[] curr = str.split(",");
            if (curr.length == 1 && curr[0].equals("")) continue; // ignore blank lines
            else if (curr.length == 1) {
                session.addNote(str);
                continue;
            }
            Driver dMatch = LoadSeason.season.getDriver(Integer.parseInt(curr[1]));  // get driver based on their racing number
            Team tMatch = LoadSeason.season.getTeam(curr[3]);
            switch (type) {
                case Practice:
                    // Driver driver, Team team, int position, int laps, String time, String gap
                    ((PracticeSession) session).addResult(dMatch, tMatch, Integer.parseInt(curr[0]),
                            Integer.parseInt(curr[6]), curr[4], curr[5]);
                    break;
                case Qualifying:
                    // Driver driver, Team team, int position, int laps, String q1, String q2, String q3
                    ((QualifyingSession) session).addResult(dMatch, tMatch, Integer.parseInt(curr[0]),
                            Integer.parseInt(curr[7]), curr[4], curr[5], curr[6]);
                    break;
                case Race:
                    // Driver driver, Team team, int position, int laps, String time, int points
                    Integer grid = startingGrid.get(dMatch);
                    if (grid == null) grid = -1;
                    ((RaceSession) session).addResult(dMatch, tMatch,
                            (curr[0].equals("NC") ? -1 : Integer.parseInt(curr[0])), Integer.parseInt(curr[4]),
                            curr[5], Integer.parseInt(curr[6]), grid, fastestLaps.get(dMatch));
                    break;
            }
        } while ((str = in.readLine()) != null);
        in.close();
        return session;
    }

    private static HashMap<Driver, FastestLap> loadFastestLaps(String location) throws Exception {
        HashMap<Driver, FastestLap> laps = new HashMap<>(20);
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            if (curr.length == 1 && curr[0].equals("")) continue; // ignore blank lines
            // Driver driver, String time, int lap, String timeOfDay, double averageSpeed
            FastestLap lap = new FastestLap(curr[6], Integer.parseInt(curr[4]),
                    curr[5], Double.parseDouble(curr[7]));
            laps.put(LoadSeason.season.getDriver(Integer.parseInt(curr[1])), lap);
        } while ((str = in.readLine()) != null);
        return laps;
    }

    private static HashMap<Driver, Integer> loadStartingGrid(Session session, String location) throws Exception {
        HashMap<Driver, Integer> startingGrid = new HashMap<>();
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            if (curr.length == 1 && curr[0].equals("")) continue; // ignore blank lines
            if (str.contains("Note")) session.addNote(str);
            else startingGrid.put(LoadSeason.season.getDriver(Integer.parseInt(curr[1])), Integer.parseInt(curr[0]));
        } while ((str = in.readLine()) != null);
        return startingGrid;
    }

    private static ArrayList<PitStop> loadPitStops(String location) throws Exception {
        ArrayList<PitStop> pitStops = new ArrayList<>(20);
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            // Driver driver, int stop, int lap, String timeOfDay, double time, double total
            if (curr.length == 1 && curr[0].equals("")) continue; // ignore blank lines
            else pitStops.add(new PitStop(LoadSeason.season.getDriver(Integer.parseInt(curr[1])),
                    Integer.parseInt(curr[0]), Integer.parseInt(curr[4]), curr[5],
                    curr[6], curr[7]));
        } while ((str = in.readLine()) != null);
        return pitStops;
    }

}
