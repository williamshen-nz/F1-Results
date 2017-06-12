package Demo;

import Formula1.Model.Team;
import Formula1.Model.Teams;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadTeams {
    public static Teams load(String location) throws Exception {
        Teams teams = new Teams();
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            Team team = new Team(curr[1], curr[2], curr[0], curr[3]);
            teams.getTeams().add(team);
        } while ((str = in.readLine()) != null);
        in.close();
        return teams;
    }
}
