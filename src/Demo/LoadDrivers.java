package Demo;

import Formula1.Model.Driver;
import Formula1.Model.Drivers;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoadDrivers {
    public static Drivers load(String location) throws Exception {
        Drivers drivers = new Drivers();
        BufferedReader in = new BufferedReader(new FileReader(location));
        String str = in.readLine();
        do {
            String[] curr = str.split(",");
            Driver driver = new Driver(curr[0], Integer.parseInt(curr[3]), curr[2], curr[1]);
            drivers.getDrivers().add(driver);
        } while ((str = in.readLine()) != null);
        in.close();
        return drivers;
    }
}
