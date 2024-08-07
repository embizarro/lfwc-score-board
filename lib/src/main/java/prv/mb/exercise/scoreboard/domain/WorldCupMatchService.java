package prv.mb.exercise.scoreboard.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prv.mb.exercise.scoreboard.infrastructure.MatchRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

class WorldCupMatchService implements MatchService {

    private static final Logger LOG = LoggerFactory.getLogger(WorldCupMatchService.class);

    private final MatchRepository matchRepository;

    public WorldCupMatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match startNewMatch(Team homeTeam, Team awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        validateMatchDuplicate(match.getId());

        matchRepository.add(match);

        if (LOG.isDebugEnabled()) {
            LOG.info("New match created: {}", match);
        }
        return match;
    }

    @Override
    public Match updateMatch(Match match, Integer homeTeamScore, Integer awayTeamScore) {
        validateScore(homeTeamScore, TeamType.HOME);
        validateScore(awayTeamScore, TeamType.AWAY);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Match [{}] score has changed from {}:{} to {}:{} ",
                    match.getId(), match.getHomeScore(), match.getAwayScore(), homeTeamScore, awayTeamScore);
        }
        match.updateScore(homeTeamScore, awayTeamScore);
        return match;
    }

    @Override
    public void finishMatch(Match match) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Match [{}] has been removed ", match.getId());
        }
        matchRepository.remove(match);
    }

    @Override
    public Match getMatchById(String matchId) {
        validateIfMatchIdExist(matchId);

        Match match = matchRepository.get(matchId);

        validateIfMatchExists(matchId, match);
        return match;
    }

    @Override
    public List<String> getMatchesSummary() {
        return matchRepository.getAllMatches().stream()
                .sorted(Comparator.comparing(Match::getTotalScore, Comparator.reverseOrder())
                        .thenComparing(Match::getTimestamp, Comparator.reverseOrder()))
                .map(Match::toString)
                .collect(Collectors.toList());
    }

    private void validateIfMatchExists(String matchId, Match match) {
        if (isNull(match)) {
            throw new IllegalArgumentException("Match has not been found for the following Id: " + matchId);
        }
    }

    private void validateIfMatchIdExist(String matchId) {
        if (isNull(matchId)) {
            throw new IllegalArgumentException("Match Id is required");
        }
    }

    void validateMatchDuplicate(String matchId) {
        Match duplicatedMatch = matchRepository.get(matchId);
        if (!isNull(duplicatedMatch)) {
            throw new IllegalArgumentException("Match with id " + matchId + " already exists");
        }
    }

    void validateScore(Integer teamScore, TeamType teamType) {
        if (teamScore < 0) {
            String lowerCase = teamType.toString().toLowerCase();
            throw new IllegalArgumentException("Value of " + lowerCase + " teamScore cannot be less than zero");
        }
    }
}
