package prv.mb.exercise.scoreboard.domain;

import java.time.Instant;
import java.util.Objects;

public class Match {
    private final Team homeTeam;
    private Integer homeScore;

    private final Team awayTeam;
    private Integer awayScore;

    private long timestamp;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.homeScore = 0;

        this.awayTeam = awayTeam;
        this.awayScore = 0;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public void updateScore(Integer homeScore, Integer awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return timestamp == match.timestamp && Objects.equals(homeTeam, match.homeTeam) && Objects.equals(homeScore, match.homeScore) && Objects.equals(awayTeam, match.awayTeam) && Objects.equals(awayScore, match.awayScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, homeScore, awayTeam, awayScore, timestamp);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(homeTeam.getCountry()).append(" ").append(homeScore)
                .append(" - ")
                .append(awayTeam.getCountry()).append(" ").append(awayScore)
                .toString();
    }

    public String getId() {
        return homeTeam.getId() + "-" + awayTeam.getId() + "-match";
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    Integer getTotalScore() {
        return homeScore + awayScore;
    }

    long getTimestamp() {
        return timestamp;
    }

    void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
