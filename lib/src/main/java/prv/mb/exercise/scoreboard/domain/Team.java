package prv.mb.exercise.scoreboard.domain;

public class Team {
    private final String country;

    Team(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getId() {
        return this.country.toLowerCase();
    }
}
