package Demo;

import Formula1.Model.Drivers;
import Formula1.Model.Race;
import Formula1.Model.Teams;

public class LoadRace {
    public static Race load(Drivers drivers, Teams teams, String location, int roundNumber) throws Exception {
        Race race;
        /*
        BufferedReader in = new BufferedReader(new FileReader(location + "meta.txt"));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            // String name, String location, String startDate, String endDate
            race = new Race(curr[0], curr[1], curr[2], curr[3]);
        } while ((str = in.readLine()) != null);
        in.close();*/
        String[] curr = location.split("/");
        race = new Race(roundNumber, curr[curr.length - 1], "", "", "");
        race.setSessions(LoadSessions.load(drivers, teams, location));
        return race;
    }
}
