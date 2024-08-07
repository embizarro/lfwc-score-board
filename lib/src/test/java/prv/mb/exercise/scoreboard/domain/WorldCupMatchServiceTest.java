package prv.mb.exercise.scoreboard.domain;

import org.mockito.testng.MockitoTestNGListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.mock;

@Listeners(MockitoTestNGListener.class)
public class WorldCupMatchServiceTest {

    private WorldCupMatchService service;

    @BeforeMethod
    public void setUp() {
        this.service = new WorldCupMatchService();
    }

    @Test
    public void shouldStartNewMatchNoSimilarExists() {
        //given
        String homeTeamCountry = "Mexico";
        String awayTeamCountry = "Canada";
        Team homeTeam = new Team(homeTeamCountry);
        Team awayTeam = new Team(awayTeamCountry);

        //when
        Match match = this.service.startNewMatch(homeTeam, awayTeam);

        //then
        Assert.assertNotNull(match);
    }

    @Test
    public void shouldUpdateMatchWhenScoreIsNonNegative() {
        //given
        Match match = new Match(new Team("Mexico"), new Team("Canada"));

        //when
        Match updatedMatch = this.service.updateMatch(match, 1, 3);
        //then
        Assert.assertNotNull(match);
    }

    @Test
    public void shouldFinishMatchWhenOneExist() {
        //given
        Match match = mock(Match.class);
        //when
        this.service.finishMatch(match);
        //then
        Assert.fail();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenMatchIdNotExists() {
        //given
        String matchId = null;

        //when
        this.service.getMatchById(matchId);

        //then
        //exception is thrown
    }

    @Test
    public void shouldGetMatchesSummarySortedByTotalScoreDescAndTimestampDesc() {
        //given
        //not required

        //when
        List<String> matchesSummary = this.service.getMatchesSummary();

        //then
        SoftAssert softAssert = new SoftAssert();
        Iterator<String> matchesIterator = matchesSummary.iterator();
        softAssert.assertEquals(matchesIterator.next(), "Uruguay 6 - Italy 6");
        softAssert.assertEquals(matchesIterator.next(), "Spain 10 - Brazil 2");
        softAssert.assertEquals(matchesIterator.next(), "Mexico 0 - Canada 5");
        softAssert.assertEquals(matchesIterator.next(), "Argentina 3 - Australia 1");
        softAssert.assertEquals(matchesIterator.next(), "Germany 2 - France 2");
        softAssert.assertAll();
    }

}