package prv.mb.exercise.scoreboard.application;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WorldCupScoreBoardServiceTest {

    private WorldCupScoreBoardService service;

    @BeforeMethod
    public void setUp() throws Exception {
        this.service = new WorldCupScoreBoardService();
    }

    @Test
    public void shouldStartNewMatchByCallingExpectedServices() {
        //given
        String homeTeamCountry = "Mexico";
        String awayTeamCountry = "Canada";
        String expectedMatchId = "mexico-canada-match";

        //when
        String matchId = this.service.startNewMatch(homeTeamCountry, awayTeamCountry);

        //then
        Assert.assertEquals(matchId, expectedMatchId);

    }

    @Test
    public void shouldUpdateScoreByCallingExpectedServices() {
        //given
        String matchId = "mexico-canada-match";
        Integer homeTeamScore = 1;
        Integer awayTeamScore = 2;

        //when
        this.service.updateScore(matchId, homeTeamScore, awayTeamScore);

        //then
        Assert.fail();
    }

    @Test
    public void shouldFinishMatchByCallingExpectedServices() {
        //given
        String matchId = "mexico-canada-match";

        //when
        this.service.finishMatch(matchId);

        //then
        Assert.fail();
    }

    @Test
    public void shouldGetMatchesSummaryByCallingExpectedServices() {
        //given
        //not required

        //when
        List<String> matchesSummary = this.service.getMatchesSummary();

        //then
        Assert.assertTrue(matchesSummary.size() > 0);
    }
}
