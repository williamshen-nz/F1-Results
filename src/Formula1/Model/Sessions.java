package Formula1.Model;

import Helpers.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class Sessions {
    private Session practice1;
    private Session practice2;
    private Session practice3;
    private Session qualifying;
    private Session race;

    public Sessions() {
    }

    @JsonInclude(NON_NULL)
    public Session getPractice1() {
        return practice1;
    }

    public void setPractice1(Session practice1) {
        this.practice1 = practice1;
    }

    @JsonInclude(NON_NULL)
    public Session getPractice2() {
        return practice2;
    }

    public void setPractice2(Session practice2) {
        this.practice2 = practice2;
    }

    @JsonInclude(NON_NULL)
    public Session getPractice3() {
        return practice3;
    }

    public void setPractice3(Session practice3) {
        this.practice3 = practice3;
    }

    @JsonInclude(NON_NULL)
    public Session getQualifying() {
        return qualifying;
    }

    public void setQualifying(Session qualifying) {
        this.qualifying = qualifying;
    }

    @JsonInclude(NON_NULL)
    public Session getRace() {
        return race;
    }

    public void setRace(Session race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }
}
