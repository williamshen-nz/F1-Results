package Formula1.Model;

import Helpers.JSON;

public class Lap {
    private Driver driver;
    private String time;

    public Lap(Driver driver, String time) {
        this.driver = driver;
        this.time = time;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
