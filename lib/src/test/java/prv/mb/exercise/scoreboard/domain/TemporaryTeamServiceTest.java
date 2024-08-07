package prv.mb.exercise.scoreboard.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TemporaryTeamServiceTest {

    private TemporaryTeamService service;

    @BeforeMethod
    public void setUp() throws Exception {
        this.service = new TemporaryTeamService();
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Missing home team country.")
    public void shouldThrowExceptionWhenTeamCountryNotExists() {
        //given
        String teamCountry = null;
        TeamType homeType = TeamType.HOME;

        //when
        this.service.getByCountry(teamCountry, TeamType.HOME);

        //then
        //exception is thrown
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Not supported away team / country: UnknownTeam")
    public void shouldThrowExceptionWhenTeamNotExists() {
        //given
        String teamCountry = "UnknownTeam";
        Team mockTeam = null;
        TeamType awayType = TeamType.AWAY;

        //when
        this.service.getByCountry(teamCountry, awayType);

        //then
        //exception is thrown
    }
}