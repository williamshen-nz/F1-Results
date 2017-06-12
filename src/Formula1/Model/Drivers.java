package Formula1.Model;

import Helpers.JSON;

import java.util.ArrayList;

/**
 * @author William Shen
 */

public class Drivers {
    private ArrayList<Driver> drivers;

    public Drivers() {
        drivers = new ArrayList<>(20);
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public Driver getDriver(String name) {
        for (Driver driver : drivers)
            if (driver.matchName(name)) return driver;
        return null;
    }

    public Driver getDriver(int number) {
        for (Driver driver : drivers)
            if (driver.getNumber() == number) return driver;
        return null;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
