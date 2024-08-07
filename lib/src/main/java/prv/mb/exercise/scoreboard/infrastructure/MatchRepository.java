package prv.mb.exercise.scoreboard.infrastructure;

import prv.mb.exercise.scoreboard.domain.Match;

import java.util.List;

public interface MatchRepository {

    Match add(Match match);

    Match get(String matchId);

    void remove(Match match);

    List<Match> getAllMatches();
}
