package prv.mb.exercise.scoreboard.application;

import prv.mb.exercise.scoreboard.domain.*;

import java.util.List;

class WorldCupScoreBoardService implements ScoreBoardService {

    private final TeamService teamService;
    private final MatchService matchService;

    public WorldCupScoreBoardService(TeamService teamService, MatchService matchService) {
        this.teamService = teamService;
        this.matchService = matchService;
    }

    /**
     * Starts new match with initial score 0:0
     *
     * @param homeTeamCountry
     * @param awayTeamCountry
     * @return matchId, format lower-case [homeTeamCountry]-[awayTeamCountry]-match, e.g. mexico-canada-match
     */
    @Override
    public String startNewMatch(String homeTeamCountry, String awayTeamCountry) {
        Team homeTeam = teamService.getByCountry(homeTeamCountry, TeamType.HOME);
        Team awayTeam = teamService.getByCountry(awayTeamCountry, TeamType.AWAY);

        return matchService.startNewMatch(homeTeam, awayTeam).getId();
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
        Match match = matchService.getMatchById(matchId);
        matchService.updateMatch(match, homeTeamScore, awayTeamScore);
    }

    /**
     * Finishes match provided via ID
     *
     * @param matchId, format lower-case [homeTeamCountry]-[awayTeamCountry]-match, e.g. mexico-canada-match
     */
    @Override
    public void finishMatch(String matchId) {
        Match match = matchService.getMatchById(matchId);
        matchService.finishMatch(match);
    }

    /**
     * Gets the summary of currently in progress matches.
     *
     * @return list of strings representing each match ordered by their total score
     */
    @Override
    public List<String> getMatchesSummary() {
        return matchService.getMatchesSummary();
    }


}
