package prv.mb.exercise.scoreboard.application;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import prv.mb.exercise.scoreboard.domain.MatchService;
import prv.mb.exercise.scoreboard.domain.TeamService;
import prv.mb.exercise.scoreboard.domain.TemporaryTeamService;
import prv.mb.exercise.scoreboard.domain.WorldCupMatchService;
import prv.mb.exercise.scoreboard.infrastructure.InMemoryMatchRepository;
import prv.mb.exercise.scoreboard.infrastructure.MatchRepository;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ITScoreBoardServiceTest {
    private ScoreBoardService service;

    @BeforeMethod
    public void setUp() {
        MatchRepository matchRepository = new InMemoryMatchRepository();

        TeamService teamService = new TemporaryTeamService();
        MatchService matchService = new WorldCupMatchService(matchRepository);
        this.service = new WorldCupScoreBoardService(teamService, matchService);
    }

    /**
     * Test Scenario 1:
     * Create two matches.
     * Update first.
     * Update first again.
     * Finish second.
     */
    @Test
    public void shouldSupportScenario1() throws InterruptedException {

        //create two matches
        String mexicoCanadaMatchId = this.service.startNewMatch("Mexico", "Canada");
        waitFor1ms();
        String spainBrazilMatchId = this.service.startNewMatch("Spain", "Brazil");
        assertCreatedMatches(mexicoCanadaMatchId, spainBrazilMatchId);

        //update first match
        this.service.updateScore(mexicoCanadaMatchId, 1, 2);
        assertFirstUpdate();

        //update first match again
        this.service.updateScore(mexicoCanadaMatchId, 5, 4);
        assertSecondUpdate();

        //finish first match
        this.service.finishMatch(spainBrazilMatchId);
        assertFinish();
    }

    /**
     * Test Scenario 2:
     * Create set of matches.
     * Update set of matches.
     * Review summary
     */
    @Test
    public void shouldSupportScenario2() throws InterruptedException {
        //create matches
        String mexicoCanadaMatchId = this.service.startNewMatch("Mexico", "Canada");
        waitFor1ms();
        String spainBrazilMatchId = this.service.startNewMatch("Spain", "Brazil");
        waitFor1ms();
        String germanyFranceMatchId = this.service.startNewMatch("Germany", "France");
        waitFor1ms();
        String uruguayItalyMatchId = this.service.startNewMatch("Uruguay", "Italy");
        waitFor1ms();
        String argentinaAustraliaMatchId = this.service.startNewMatch("Argentina", "Australia");
        assertCreatedSetOfMatches();

        //update matches
        this.service.updateScore(mexicoCanadaMatchId, 0, 5);
        this.service.updateScore(spainBrazilMatchId, 10, 2);
        this.service.updateScore(germanyFranceMatchId, 2, 2);
        this.service.updateScore(uruguayItalyMatchId, 6, 6);
        this.service.updateScore(argentinaAustraliaMatchId, 3, 1);
        assertUpdateSetOfMatches();
    }

    private void assertUpdateSetOfMatches() {
        List<String> matchesSummary = this.service.getMatchesSummary();
        assertEquals(matchesSummary.size(), 5);
        Iterator<String> matchesIterator = matchesSummary.iterator();
        assertEquals(matchesIterator.next(), "Uruguay 6 - Italy 6");
        assertEquals(matchesIterator.next(), "Spain 10 - Brazil 2");
        assertEquals(matchesIterator.next(), "Mexico 0 - Canada 5");
        assertEquals(matchesIterator.next(), "Argentina 3 - Australia 1");
        assertEquals(matchesIterator.next(), "Germany 2 - France 2");
    }

    private void assertCreatedSetOfMatches() {
        List<String> matchesSummary = this.service.getMatchesSummary();
        assertEquals(matchesSummary.size(), 5);
        Iterator<String> matchesIterator = matchesSummary.iterator();
        assertEquals(matchesIterator.next(), "Argentina 0 - Australia 0");
        assertEquals(matchesIterator.next(), "Uruguay 0 - Italy 0");
        assertEquals(matchesIterator.next(), "Germany 0 - France 0");
        assertEquals(matchesIterator.next(), "Spain 0 - Brazil 0");
        assertEquals(matchesIterator.next(), "Mexico 0 - Canada 0");
    }

    private void assertFinish() {
        List<String> matchesSummary = this.service.getMatchesSummary();
        assertEquals(matchesSummary.size(), 1);
        Iterator<String> matchesIterator = matchesSummary.iterator();
        assertEquals(matchesIterator.next(), "Mexico 5 - Canada 4");
    }

    private void assertSecondUpdate() {
        List<String> matchesSummary = this.service.getMatchesSummary();
        assertEquals(matchesSummary.size(), 2);
        Iterator<String> matchesIterator = matchesSummary.iterator();
        assertEquals(matchesIterator.next(), "Mexico 5 - Canada 4");
        assertEquals(matchesIterator.next(), "Spain 0 - Brazil 0");
    }

    private void assertFirstUpdate() {
        List<String> matchesSummary = this.service.getMatchesSummary();
        assertEquals(matchesSummary.size(), 2);
        Iterator<String> matchesIterator = matchesSummary.iterator();
        assertEquals(matchesIterator.next(), "Mexico 1 - Canada 2");
        assertEquals(matchesIterator.next(), "Spain 0 - Brazil 0");
    }

    private void assertCreatedMatches(String mexicoCanadaMatchId, String spainBrazilMatchId) {
        assertEquals(mexicoCanadaMatchId, "mexico-canada-match");
        assertEquals(spainBrazilMatchId, "spain-brazil-match");

        List<String> matchesSummaryAfterCreate = this.service.getMatchesSummary();
        assertEquals(matchesSummaryAfterCreate.size(), 2);
        Iterator<String> matchesIteratorAfterCreate = matchesSummaryAfterCreate.iterator();
        assertEquals(matchesIteratorAfterCreate.next(), "Spain 0 - Brazil 0");
        assertEquals(matchesIteratorAfterCreate.next(), "Mexico 0 - Canada 0");
    }

    private void waitFor1ms() throws InterruptedException {
        Thread.sleep(1);
    }
}
