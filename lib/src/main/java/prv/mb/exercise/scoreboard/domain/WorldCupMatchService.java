package prv.mb.exercise.scoreboard.domain;

import java.util.List;

class WorldCupMatchService implements MatchService {

    @Override
    public Match startNewMatch(Team homeTeam, Team awayTeam) {
        return null;
    }

    @Override
    public Match updateMatch(Match match, Integer homeTeamScore, Integer awayTeamScore) {
        return null;
    }

    @Override
    public void finishMatch(Match match) {
    }

    @Override
    public Match getMatchById(String matchId) {
        return null;
    }

    @Override
    public List<String> getMatchesSummary() {
        return List.of();
    }
}
