package Formula1.Model;

public class Team {
    private String entrant;
    private String constructor;
    private String country;
    private String abbreviation;

    public Team() {
    }

    public Team(String entrant, String constructor, String country, String abbreviation) {
        this.entrant = entrant;
        this.constructor = constructor;
        this.country = country;
        this.abbreviation = abbreviation;
    }

    public String getEntrant() {
        return entrant;
    }

    public String getConstructor() {
        return constructor;
    }

    public String getCountry() {
        return country;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
