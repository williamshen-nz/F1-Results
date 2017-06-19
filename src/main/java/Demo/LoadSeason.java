package Demo;

import Formula1.Model.Season;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadSeason {
    static Season season;

    public static Season loadAndGetSeason(String year) {
        try {
            // Default values
            System.out.println("Loading " + year + " season data... ");
            long startTime = System.nanoTime();

            // Read schedule
            String path = "data/" + year + "/";
            String[] races = loadRaces(path + "schedule.txt");

            // Create season and read in the driver and team list
            season = new Season(2017);
            System.out.print("- loading teams... ");
            LoadTeams.load(path + "teams.txt");
            System.out.print("Done!\n- loading drivers... ");
            LoadDrivers.load(path + "drivers.txt");
            System.out.println("Done!");

            // Add each of the races
            int roundNumber = 1;
            System.out.print("- loading races: ");
            for (String name : races) {
                System.out.print(name + ", ");
                LoadRace.load(path + name + "/", roundNumber++);
            }

            System.out.println("Done!");
            System.out.println("Success! Loaded in " + (System.nanoTime() - startTime) / 1E9 + "s");
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
