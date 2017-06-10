package Formula1.Model;

import Helpers.JSON;

public class Race {
    private String name;
    private String location;
    private String startDate;
    private String endDate;
    private Sessions sessions;

    public Race() {
    }

    public Race(String name, String location, String startDate, String endDate) {
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Sessions getSessions() {
        return sessions;
    }

    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return JSON.toString(this);
    }
}
