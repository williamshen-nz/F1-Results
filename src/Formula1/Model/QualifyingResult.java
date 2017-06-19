package Formula1.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = QualifyingResult.class)
public class QualifyingResult extends Result {
    private String q1;
    private String q2;
    private String q3;

    public QualifyingResult() {
    }

    public QualifyingResult(Driver driver, Team team, int position, int laps, String q1, String q2, String q3) {
        super(driver, team, position, laps);
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }

    public String getQ1() {
        return q1;
    }

    public String getQ2() {
        return q2;
    }

    public String getQ3() {
        return q3;
    }
}
