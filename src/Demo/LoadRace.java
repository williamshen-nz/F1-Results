package Demo;

import Formula1.Model.Drivers;
import Formula1.Model.Race;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadRace {
    public static Race load(Drivers drivers, String location) throws Exception {
        Race race;
        BufferedReader in = new BufferedReader(new FileReader(location + "meta.txt"));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            race = new Race(curr[0], curr[1], curr[2], curr[3]);
        } while ((str = in.readLine()) != null);
        in.close();
        race.setSessions(LoadSessions.load(drivers, location));
        return race;
    }
}
