package prv.mb.exercise.scoreboard.domain;

import java.util.List;

public interface MatchService {

    Match startNewMatch(Team homeTeam, Team awayTeam);

    Match updateMatch(Match match, Integer homeTeamScore, Integer awayTeamScore);

    void finishMatch(Match match);

    List<String> getMatchesSummary();

    Match getMatchById(String matchId);
}
