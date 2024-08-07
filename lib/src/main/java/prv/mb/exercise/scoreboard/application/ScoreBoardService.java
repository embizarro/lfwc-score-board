package prv.mb.exercise.scoreboard.application;

import java.util.List;

public interface ScoreBoardService {

    String startNewMatch(String homeTeamCountry, String awayTeamCountry);

    void updateScore(String matchId, Integer homeTeamScore, Integer awayTeamScore);

    void finishMatch(String matchId);

    public List<String> getMatchesSummary();
}
