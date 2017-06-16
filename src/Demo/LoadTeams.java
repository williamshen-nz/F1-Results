package Demo;

import Formula1.Model.Team;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadTeams {
    public static void load(String location) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            Team team = new Team(curr[1], curr[2], curr[0], curr[3]);
            Demo2017.season.addTeams(team);
        } while ((str = in.readLine()) != null);
        in.close();
    }
}
