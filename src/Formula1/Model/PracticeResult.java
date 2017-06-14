package Formula1.Model;

public class PracticeResult extends Result {
    private String time;
    private String gap;

    public PracticeResult(Driver driver, Team team, int position, int laps, String time, String gap) {
        super(driver, team, position, laps);
        this.time = time;
        this.gap = gap;
    }

    public String getTime() {
        return time;
    }

    public String getGap() {
        return gap;
    }
}
