package Demo;

import Formula1.Analysis.SeasonStatistics;
import Formula1.Model.Drivers;
import Formula1.Model.Race;
import Formula1.Model.Season;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Demo2017 {
    public static void main(String[] args) {
        try {
            // Default values
            String path = "data/2017/";
            String[] races = {"australia", "china", "bahrain", "russia", "spain", "monaco", "canada"};

            // Create season and read in the driver list
            System.out.print("Loading 2017 season data... ");
            Season season = new Season(2017);
            System.out.println("DONE");
            System.out.print("Loading drivers... ");
            Drivers drivers = LoadDrivers.load(path + "drivers.txt");
            System.out.println("DONE");

            // Add each of the races
            for (String name : races) {
                System.out.print("Loading " + name.toUpperCase() + "... ");
                Race race = LoadRace.load(drivers, path + name + "/");
                season.addRace(race);
            }

            // Get current championship points
            System.out.print("Calculating driver rankings... ");
            Object ranking = SeasonStatistics.getPoints(season);
            System.out.println("DONE. Saving driver rankings... ");
            String location = "data/2017/driverRankings.json";
            save(ranking.toString(), location);
            System.out.println("JSON successfully written to `" + location + "`!");

            // Write result
            location = "data/2017/results.json";
            System.out.println("Writing complete season data...");
            save(season.toString(), location);
            System.out.println("JSON successfully written to `" + location + "`!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void save(String json, String location) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(location));
        writer.write(json);
        writer.close();
    }
}
