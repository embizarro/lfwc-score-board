package prv.mb.exercise.scoreboard.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prv.mb.exercise.scoreboard.domain.Match;

import java.util.ArrayList;
import java.util.List;

class InMemoryMatchRepository implements MatchRepository {
    private final Logger LOG = LoggerFactory.getLogger(InMemoryMatchRepository.class);

    List<Match> matches;

    public InMemoryMatchRepository() {
        this.matches = new ArrayList<>();
    }

    @Override
    public Match add(Match match) {
        matches.add(match);
        LOG.info("Added new match to the board. {} matches in total at the moment.", matches.size());
        return match;
    }

    @Override
    public Match get(String matchId) {
        return matches.stream()
                .filter(existingMatch -> existingMatch.getId().equals(matchId))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No match found for provided match id: " + matchId));

    }

    @Override
    public void remove(Match matchToRemove) {
        matches.removeIf(match -> match.getId().equals(matchToRemove.getId()));
        LOG.info("Match has been removed from the board. {} matches in total at the moment.", matches.size());
    }

    @Override
    public List<Match> getAllMatches() {
        return matches;
    }

    //for testing purposes only
    void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
