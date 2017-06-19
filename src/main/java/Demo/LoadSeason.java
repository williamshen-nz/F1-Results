package Demo;

import Formula1.Model.Season;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadSeason {
    static Season season;

    public static Season loadAndGetSeason(String year) {
        try {
            // Default values
            String path = "data/" + year + "/";
            String[] races = loadRaces(path + "schedule.txt");

            // Create season and read in the driver and team list
            System.out.println("Loading " + year + " season data... ");
            season = new Season(2017);
            System.out.print("- loading teams... ");
            LoadTeams.load(path + "teams.txt");
            System.out.print("- loading drivers... ");
            LoadDrivers.load(path + "drivers.txt");

            // Add each of the races
            int roundNumber = 1;
            for (String name : races) {
                System.out.print("- loading " + name.toUpperCase() + "... ");
                LoadRace.load(path + name + "/", roundNumber++);
            }
            System.out.println("SUCCESSFULLY LOADED " + year + " SEASON DATA!");
            return season;
        } catch (Exception e) {
            e.printStackTrace();
            return season;
        }
    }

    private static String[] loadRaces(String location) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        return str.split(",");
    }
}
