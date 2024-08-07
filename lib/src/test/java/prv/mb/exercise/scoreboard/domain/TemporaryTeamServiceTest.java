package prv.mb.exercise.scoreboard.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TemporaryTeamServiceTest {

    private TemporaryTeamService service;

    @BeforeMethod
    public void setUp() throws Exception {
        this.service = new TemporaryTeamService();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTeamCountryNotExists() {
        //given
        String teamCountry = null;
        TeamType homeType = TeamType.HOME;

        //when
        this.service.getByCountry(teamCountry, TeamType.HOME);

        //then
        //exception is thrown
    }
}