package prv.mb.exercise.scoreboard.domain;

public interface TeamService {

    Team getByCountry(String teamCountry, TeamType teamType);
}
