package Demo;

import Formula1.Model.Driver;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadDrivers {
    public static void load(String location) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            Driver driver = new Driver(curr[0], Integer.parseInt(curr[3]), curr[1]);
            Demo2017.season.addDrivers(driver);
        } while ((str = in.readLine()) != null);
        in.close();
    }
}
