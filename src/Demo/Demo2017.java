package Demo;

import Formula1.Analysis.SeasonStatistics;
import Formula1.Model.Drivers;
import Formula1.Model.Race;
import Formula1.Model.Season;
import Formula1.Model.Teams;
import Formula1.Views.DriverViews;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Demo2017 {
    public static void main(String[] args) {
        try {
            // Default values
            String path = "data/2017/";
            String[] races = loadRaces(path + "schedule.txt");

            // Create season and read in the driver and team list
            System.out.println("Loading 2017 season data... ");
            Season season = new Season(2017);
            System.out.print("- loading teams... ");
            Teams teams = LoadTeams.load(path + "teams.txt");
            season.setTeams(teams);
            System.out.println("DONE");
            System.out.print("- loading drivers... ");
            Drivers drivers = LoadDrivers.load(path + "drivers.txt");
            season.setDrivers(drivers);
            System.out.println("DONE");
            System.out.println("DONE\n");

            // Add each of the races
            System.out.println("Loading 2017 race data...");
            int roundNumber = 1;
            for (String name : races) {
                System.out.print("- loading " + name.toUpperCase() + "... ");
                Race race = LoadRace.load(drivers, teams, path + name + "/", roundNumber++);
                season.addRace(race);
            }
            System.out.println("DONE");

            // Get current driver championship points
            System.out.print("\nCalculating driver rankings... ");
            Object ranking = SeasonStatistics.getDriverPoints(season);
            System.out.print("DONE.\nSaving driver rankings... ");
            String location = "data/2017/driverRankings.json";
            save(ranking.toString(), location);
            System.out.println("JSON successfully written to `" + location + "`!");

            // Get current constructor championship points
            System.out.print("\nCalculating constructor rankings... ");
            ranking = SeasonStatistics.getConstructorPoints(season);
            System.out.print("DONE.\nSaving constructor rankings... ");
            location = "data/2017/constructorRankings.json";
            save(ranking.toString(), location);
            System.out.println("JSON successfully written to `" + location + "`!");

            // Write result
            location = "data/2017/results.json";
            System.out.println("\nWriting complete season data...");
            save(season.toString(), location);
            System.out.println("JSON successfully written to `" + location + "`!");

            // Example helper and view functions
/*            System.out.println(DriverViews.get(drivers.getDriver("Alonso"), season));
            System.out.println();
            System.out.println(DriverViews.getSummary(drivers.getDriver("Alonso"), season));
            System.out.println();
            System.out.println(SeasonStatistics.getPoints(drivers.getDriver("Alonso"), season));
            System.out.println();
            System.out.println(SeasonStatistics.getPoints(teams.getTeam("McLaren"), season));
            System.out.println();
            System.out.println(TeamViews.getSummary(teams.getTeam("Ferrari"), season));
            System.out.println();
            System.out.println(TeamViews.getDetailed(teams.getTeam("Ferrari"), season));
            System.out.println();
            System.out.println(TeamViews.getDetailed(teams.getTeam("McLaren"), season));
            System.out.println();
            System.out.println("MOST POLE POSITIONS:");
            System.out.println(SeasonStatistics.mostPolePositions(season));

            for (Race r : season.getRaces()) {
                System.out.println(r.getName() + ", " + RaceStatistics.getPolePosition(r).getDriver().getName());
            } */
            System.out.println(DriverViews.getResults(drivers.getDriver("Alonso"), season));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void save(String json, String location) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(location));
        writer.write(json);
        writer.close();
    }

    private static String[] loadRaces(String location) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        return str.split(",");
    }
}
