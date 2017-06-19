package Demo;

import Formula1.Model.Race;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadRace {
    public static void load(String location, int roundNumber) throws Exception {
        Race race;
        BufferedReader in = new BufferedReader(new FileReader(location + "meta.txt"));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            // int round, String name, String location, String startDate, String endDate
            race = new Race(roundNumber, curr[0], curr[1], curr[2], curr[3]);
            race.setSessions(LoadSessions.load(location));
            LoadSeason.season.addRaces(race);
        } while ((str = in.readLine()) != null);
        in.close();
    }
}
