package prv.mb.exercise.scoreboard.domain;

// for this exercise I provide a simple temporary service with set of hardcoded teams,
// in production we can get all valid teams via REST endpoint or other appointed repository, e.g. database
class TemporaryTeamService implements TeamService {

    @Override
    public Team getByCountry(String teamCountry, TeamType teamType) {
        return null;
    }
}
