package prv.mb.exercise.scoreboard.domain;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

// for this exercise I provide a simple temporary service with set of hardcoded teams,
// in production we can get all valid teams via REST endpoint or other appointed repository, e.g. database
public class TemporaryTeamService implements TeamService {

    private static final Set<Team> TEAMS = Set
            .of("Mexico", "Canada", "Spain", "Brazil", "Germany", "France", "Uruguay", "Italy", "Argentina", "Australia")
            .stream()
            .map(Team::new)
            .collect(Collectors.toSet());


    @Override
    public Team getByCountry(String teamCountry, TeamType teamType) {
        String lowercaseType = teamType.toString().toLowerCase();
        validateBefore(teamCountry, lowercaseType);

        Team team = this.getFromAvailableTeamsByCountry(teamCountry);

        validateAfter(teamCountry, team, lowercaseType);
        return team;
    }

    void validateAfter(String teamCountry, Team team, String lowercaseType) {
        if (isNull(team)) {
            throw new IllegalArgumentException("Not supported " + lowercaseType + " team / country: " + teamCountry);
        }
    }

    void validateBefore(String teamCountry, String lowercaseType) {
        if (isNull(teamCountry)) {
            throw new IllegalArgumentException("Missing " + lowercaseType + " team country.");
        }
    }

    Team getFromAvailableTeamsByCountry(String country) {
        return TEAMS.stream().filter(team -> team.getCountry().equals(country)).findAny().orElse(null);
    }
}
