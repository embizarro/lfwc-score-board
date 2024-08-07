package prv.mb.exercise.scoreboard.application;

import java.util.List;

class WorldCupScoreBoardService implements ScoreBoardService {

    /**
     * Starts new match with initial score 0:0
     *
     * @param homeTeamCountry
     * @param awayTeamCountry
     * @return matchId, format lower-case [homeTeamCountry]-[awayTeamCountry]-match, e.g. mexico-canada-match
     */
    @Override
    public String startNewMatch(String homeTeamCountry, String awayTeamCountry) {

        return "";
    }

    /**
     * Updates the match scores
     *
     * @param matchId,      format lower-case [homeTeamCountry]-[awayTeamCountry]-match, e.g. mexico-canada-match
     * @param homeTeamScore
     * @param awayTeamScore
     */
    @Override
    public void updateScore(String matchId, Integer homeTeamScore, Integer awayTeamScore) {
    }

    /**
     * Finishes match provided via ID
     *
     * @param matchId, format lower-case [homeTeamCountry]-[awayTeamCountry]-match, e.g. mexico-canada-match
     */
    @Override
    public void finishMatch(String matchId) {
    }

    /**
     * Gets the summary of currently in progress matches.
     *
     * @return list of strings representing each match ordered by their total score
     */
    @Override
    public List<String> getMatchesSummary() {
        return List.of();
    }


}
