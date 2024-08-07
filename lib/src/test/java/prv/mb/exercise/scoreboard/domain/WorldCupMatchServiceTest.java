package prv.mb.exercise.scoreboard.domain;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import prv.mb.exercise.scoreboard.infrastructure.MatchRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;

@Listeners(MockitoTestNGListener.class)
public class WorldCupMatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    private WorldCupMatchService service;

    @BeforeMethod
    public void setUp() {
        this.service = new WorldCupMatchService(matchRepository);
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
        Assert.assertEquals(match.toString(), "Mexico 0 - Canada 0");
    }

    @Test
    public void shouldUpdateMatchWhenScoreIsNonNegative() {
        //given
        Match match = new Match(new Team("Mexico"), new Team("Canada"));

        //when
        Match updatedMatch = this.service.updateMatch(match, 1, 3);
        //then
        Assert.assertEquals(updatedMatch.toString(), "Mexico 1 - Canada 3");
    }

    @Test
    public void shouldFinishMatchWhenOneExist() {
        //given
        Match match = mock(Match.class);
        //when
        this.service.finishMatch(match);
        //then
        verify(matchRepository, times(1)).remove(match);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Match Id is required")
    public void shouldThrowExceptionWhenMatchIdNotExists() {
        //given
        String matchId = null;

        //when
        this.service.getMatchById(matchId);

        //then
        //exception is thrown
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Match has not been found for the following Id: mexico-canada-match")
    public void shouldThrowExceptionWhenMatchNotExists() {
        //given
        String matchId = "mexico-canada-match";
        Match notExistingMatch = null;
        when(matchRepository.get(matchId)).thenReturn(notExistingMatch);

        //when
        this.service.getMatchById(matchId);

        //then
        //exception is thrown
    }

    @Test
    public void shouldGetMatchesSummarySortedByTotalScoreDescAndTimestampDesc() {
        //given
        List<Match> matches = new ArrayList<Match>();
        matches.add(assembleMatch("Mexico", 0, "Canada", 5, matches.size()));
        matches.add(assembleMatch("Spain", 10, "Brazil", 2, matches.size()));
        matches.add(assembleMatch("Germany", 2, "France", 2, matches.size()));
        matches.add(assembleMatch("Uruguay", 6, "Italy", 6, matches.size()));
        matches.add(assembleMatch("Argentina", 3, "Australia", 1, matches.size()));
        when(matchRepository.getAllMatches()).thenReturn(matches);

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

    private Match assembleMatch(String homeTeamCountry, Integer homeScore, String awayTeamCountry, Integer awayScore, int index) {
        Match match = new Match(new Team(homeTeamCountry), new Team(awayTeamCountry));
        match.updateScore(homeScore, awayScore);

        //used only in unit tests to be certain that each match has different millis set
        match.setTimestamp(match.getTimestamp() + index);
        return match;
    }
}