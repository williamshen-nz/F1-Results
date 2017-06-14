package Formula1.Model;

import Helpers.JSON;

public class Lap {
    private String time;

    public Lap(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
