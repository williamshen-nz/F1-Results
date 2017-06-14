package Formula1.Model;

import Helpers.JSON;

public abstract class Result {
    private Driver driver;
    private Team team;
    private int position;
    private int laps;

    public Result() {
    }

    public Result(Driver driver, Team team, int position, int laps) {
        this.driver = driver;
        this.team = team;
        this.position = position;
        this.laps = laps;
    }

    public Driver getDriver() {
        return driver;
    }

    public Team getTeam() {
        return team;
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
