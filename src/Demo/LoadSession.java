package Demo;

import Formula1.Model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import static Demo.LoadSession.SessionType.Practice;
import static Demo.LoadSession.SessionType.Qualifying;

public class LoadSession {
    public enum SessionType {
        Practice, Qualifying, Race;
    }

    public static Session load(Drivers drivers, Teams teams, SessionType type, String location, String base) throws Exception {
        Session session;
        HashMap<Driver, FastestLap> fastestLaps = null;
        if (type == Practice)
            session = new PracticeSession();
        else if (type == Qualifying)
            session = new QualifyingSession();
        else {
            fastestLaps = loadFastestLaps(drivers, base + "fastest-laps.txt");
            session = new RaceSession();
        }
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            if (str == null) {
                System.out.print("(none)... ");
                return session;
            }
            String[] curr = str.split(",");
            if (curr.length == 1) continue; // ignore blank lines
            Driver dMatch = drivers.getDriver(Integer.parseInt(curr[1]));  // get driver based on their racing number
            Team tMatch = teams.getTeam(curr[3]);
            switch (type) {
                case Practice:
                    // 2,5,Sebastian Vettel VET,Ferrari,1:24.167,+0.547s,35
                    // Driver driver, Team team, int position, int laps, String time, String gap
                    ((PracticeSession) session).addResult(dMatch, tMatch, Integer.parseInt(curr[0]),
                            Integer.parseInt(curr[6]), curr[4], curr[5]);
                    break;
                case Qualifying:
                    // 1,44,Lewis Hamilton HAM,Mercedes,1:24.191,1:23.251,1:22.188,14
                    // Driver driver, Team team, int position, int laps, String q1, String q2, String q3
                    ((QualifyingSession) session).addResult(dMatch, tMatch, Integer.parseInt(curr[0]),
                            Integer.parseInt(curr[7]), curr[4], curr[5], curr[6]);
                    break;
                case Race:
                    // 1,5,Sebastian Vettel VET,Ferrari,57,1:24:11.672,25
                    // Driver driver, Team team, int position, int laps, String time, int points
                    ((RaceSession) session).addResult(dMatch, tMatch, (curr[0].equals("NC") ? -1 : Integer.parseInt(curr[0])), Integer.parseInt(curr[4]),
                            curr[5], Integer.parseInt(curr[6]), fastestLaps.get(dMatch));
                    break;
            }
        } while ((str = in.readLine()) != null);
        in.close();
        System.out.print("... ");

        return session;
    }

    public static HashMap<Driver, FastestLap> loadFastestLaps(Drivers drivers, String location) throws Exception {
        HashMap<Driver, FastestLap> laps = new HashMap<>(20);
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            if (curr.length == 1) continue; // ignore blank lines
            // 1,44,Lewis Hamilton HAM,Mercedes,64,15:36:15,1:23.593,200.471
            // Driver driver, String time, int lap, String timeOfDay, double averageSpeed
            FastestLap lap = new FastestLap(curr[6], Integer.parseInt(curr[4]),
                    curr[5], Double.parseDouble(curr[7]));
            laps.put(drivers.getDriver(Integer.parseInt(curr[1])), lap);
        } while ((str = in.readLine()) != null);
        return laps;
    }

/*    public static StartingGrid loadStartingGrid(Drivers drivers, String location) throws Exception {
        StartingGrid startingGrid = new StartingGrid();
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            if (curr.length == 1) continue; // ignore blank lines
            if (curr.length != 5) {
                startingGrid.setNotes(String.join(",", curr));
            } else {
                GridPosition gp = new GridPosition(Integer.parseInt(curr[0]),
                        drivers.getDriver(Integer.parseInt(curr[1])));
                startingGrid.add(gp);
            }
        } while((str = in.readLine()) != null);
        return startingGrid;
    }*/
}
