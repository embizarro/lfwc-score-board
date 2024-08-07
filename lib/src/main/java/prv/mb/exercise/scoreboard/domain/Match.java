package prv.mb.exercise.scoreboard.domain;

import java.time.Instant;
import java.util.Objects;

public class Match {
    private final Team homeTeam;
    private Integer homeScore;

    private final Team awayTeam;
    private Integer awayScore;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.homeScore = 0;

        this.awayTeam = awayTeam;
        this.awayScore = 0;
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
}
