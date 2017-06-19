package Formula1.Model;

import Helpers.JSON;

public class PitStop {
    private Driver driver;
    private int stops;
    private int lap;
    private String timeOfDay;
    private String time;
    private String total;

    public PitStop() {
    }

    public PitStop(Driver driver, int stops, int lap, String timeOfDay, String time, String total) {
        this.driver = driver;
        this.stops = stops;
        this.lap = lap;
        this.timeOfDay = timeOfDay;
        this.time = time;
        this.total = total;
    }

    public Driver getDriver() {
        return driver;
    }

    public int getStops() {
        return stops;
    }

    public int getLap() {
        return lap;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public String getTime() {
        return time;
    }

    public String getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
