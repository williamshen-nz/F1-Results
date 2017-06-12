package Demo;

import Formula1.Model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static Demo.LoadSession.SessionType.Race;

public class LoadSession {
    public enum SessionType {
        Practice, Qualifying, Race;
    }

    public static Session load(Drivers drivers, SessionType type, String location, String base) throws Exception {
        Session session = new Session();
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            if (str == null) {
                System.out.print("(none)... ");
                return session;
            }
            String[] curr = str.split(",");
            Driver match = drivers.getDriver(Integer.parseInt(curr[1]));  // get driver based on their racing number
            switch (type) {
                case Practice:
                    session.addResult(match, Integer.parseInt(curr[0]), Integer.parseInt(curr[5]), curr[3], curr[4]);
                    break;
                case Qualifying:
                    session.addResult(match, Integer.parseInt(curr[0]), Integer.parseInt(curr[6]), curr[3], curr[4], curr[5]);
                    break;
                case Race:
                    session.addResult(match, (curr[0].equals("NC") ? -1 : Integer.parseInt(curr[0])), Integer.parseInt(curr[3]),
                            curr[4], Integer.parseInt(curr[5]));
                    break;
            }
        } while ((str = in.readLine()) != null);
        in.close();
        System.out.print("... ");

        if (type == Race) {
            session.setStartingGrid(loadStartingGrid(drivers, base + "starting.txt"));
            session.setFastestLaps(loadFastestLaps(drivers, base + "fastest.txt"));
        }

        return session;
    }

    public static ArrayList<FastestLap> loadFastestLaps(Drivers drivers, String location) throws Exception {
        ArrayList<FastestLap> laps = new ArrayList<>(20);
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            // 1,44,Hamilton,64,15:28:21,1:14.551,210.588
            //String time, int lap, String timeOfDay, double averageSpeed
            FastestLap lap = new FastestLap(drivers.getDriver(Integer.parseInt(curr[1])), curr[5],
                    Integer.parseInt(curr[3]), curr[4], Double.parseDouble(curr[6]));
            laps.add(lap);
        } while ((str = in.readLine()) != null);
        return laps;
    }

    public static StartingGrid loadStartingGrid(Drivers drivers, String location) throws Exception {
        StartingGrid startingGrid = new StartingGrid();
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            if (curr.length == 1) {
                startingGrid.setNotes(curr[0]);
            } else {
                GridPosition gp = new GridPosition(Integer.parseInt(curr[0]),
                        drivers.getDriver(Integer.parseInt(curr[1])));
                startingGrid.add(gp);
            }
        } while((str = in.readLine()) != null);
        return startingGrid;
    }
}
