package Formula1.Model;

public class PracticeResult extends Result {
    private String time;
    private String gap;

    public PracticeResult(Driver driver, int position, int laps, String time, String gap) {
        super(driver, position, laps);
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
