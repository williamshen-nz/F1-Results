package Formula1.Model;

import Helpers.JSON;

public class FastestLap extends Lap {
    private int lap;
    private String timeOfDay;
    private double averageSpeed;

    public FastestLap(String time, int lap, String timeOfDay, double averageSpeed) {
        super(time);
        this.lap = lap;
        this.timeOfDay = timeOfDay;
        this.averageSpeed = averageSpeed;
    }

    public int getLap() {
        return lap;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
