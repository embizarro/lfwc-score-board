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
        return null;
    }

    @Override
    public Match get(String matchId) {
        return null;

    }

    @Override
    public void remove(Match matchToRemove) {
    }

    @Override
    public List<Match> getAllMatches() {
        return matches;
    }

}
