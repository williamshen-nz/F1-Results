package Formula1.Model;

import Helpers.JSON;

public abstract class Result {
    private Driver driver;
    private int position;
    private int laps;

    public Result() {
    }

    public Result(Driver driver, int position, int laps) {
        this.driver = driver;
        this.position = position;
        this.laps = laps;
    }

    public Driver getDriver() {
        return driver;
    }

    public int getPosition() {
        return position;
    }

    public int getLaps() {
        return laps;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
