package Formula1.Model;

import Helpers.JSON;

public class GridPosition {
    private int position;
    private Driver driver;

    public GridPosition(int position, Driver driver) {
        this.position = position;
        this.driver = driver;
    }

    public int getPosition() {
        return position;
    }

    public Driver getDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
