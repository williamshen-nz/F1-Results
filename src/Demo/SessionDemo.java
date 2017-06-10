package Demo;

import Formula1.Model.Driver;
import Formula1.Model.Drivers;
import Formula1.Model.Session;
import Formula1.Model.Sessions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SessionDemo {
    public static void main(String[] args) throws IOException {

        Drivers drivers = new Drivers();
        BufferedReader in = new BufferedReader(new FileReader("drivers.txt"));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            Driver driver = new Driver(curr[0], Integer.parseInt(curr[3]), curr[2], curr[1]);
            drivers.getDrivers().add(driver);
        } while ((str = in.readLine()) != null);
        in.close();
        System.out.println(drivers);

        Session race = new Session();
        in = new BufferedReader(new FileReader("australiarace.txt"));
        str = in.readLine();
        do {
            String[] curr = str.split(",");
            Driver match = drivers.getDriver(curr[1]);
            // Driver driver, int position, int laps, String time, int points
            race.addResult(match, (curr[0].equals("NC") ? -1 : Integer.parseInt(curr[0])),
                    Integer.parseInt(curr[2]), curr[3], Integer.parseInt(curr[4]));
        } while ((str = in.readLine()) != null);
        in.close();

        System.out.println(race);

        Sessions australia = new Sessions();
        australia.setRace(race);

        System.out.println("\n\n\n");

        System.out.println(australia);


        /*
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(drivers.toString(), Object.class);
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        System.out.println(jsonString);

        System.out.println("Writing...");
        BufferedWriter out = new BufferedWriter(new FileWriter("drivers.json"));
        out.write(jsonString);
        out.close();
        */
    }
}
