package prv.mb.exercise.scoreboard.application;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import prv.mb.exercise.scoreboard.domain.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Listeners(MockitoTestNGListener.class)
public class WorldCupScoreBoardServiceTest {

    @Mock
    private TeamService teamService;

    @Mock
    private MatchService matchService;

    private WorldCupScoreBoardService service;

    @BeforeMethod
    public void setUp() throws Exception {
        this.service = new WorldCupScoreBoardService(teamService, matchService);
    }

    @Test
    public void shouldStartNewMatchByCallingExpectedServices() {
        //given
        String homeTeamCountry = "Mexico";
        String awayTeamCountry = "Canada";

        Team mockHomeTeam = mock(Team.class);
        when(teamService.getByCountry(homeTeamCountry, TeamType.HOME)).thenReturn(mockHomeTeam);

        Team mockAwayTeam = mock(Team.class);
        when(teamService.getByCountry(awayTeamCountry, TeamType.AWAY)).thenReturn(mockAwayTeam);

        Match mockMatch = mock(Match.class);
        when(matchService.startNewMatch(mockHomeTeam, mockAwayTeam)).thenReturn(mockMatch);

        //when
        this.service.startNewMatch(homeTeamCountry, awayTeamCountry);

        //then
        verify(teamService, times(2)).getByCountry(anyString(), any(TeamType.class));
        verify(matchService, times(1)).startNewMatch(mockHomeTeam, mockAwayTeam);
    }

    @Test
    public void shouldUpdateScoreByCallingExpectedServices() {
        //given
        String matchId = "mexico-canada-match";
        Integer homeTeamScore = 1;
        Integer awayTeamScore = 2;
        Match mockMatch = mock(Match.class);
        when(matchService.getMatchById(matchId)).thenReturn(mockMatch);

        //when
        this.service.updateScore(matchId, homeTeamScore, awayTeamScore);

        //then
        verify(matchService, times(1)).getMatchById(matchId);
        verify(matchService, times(1)).updateMatch(mockMatch, homeTeamScore, awayTeamScore);
    }

    @Test
    public void shouldFinishMatchByCallingExpectedServices() {
        //given
        String matchId = "mexico-canada-match";
        Match mockMatch = mock(Match.class);
        when(matchService.getMatchById(matchId)).thenReturn(mockMatch);

        //when
        this.service.finishMatch(matchId);

        //then
        verify(matchService, times(1)).getMatchById(matchId);
        verify(matchService, times(1)).finishMatch(mockMatch);
    }

    @Test
    public void shouldGetMatchesSummaryByCallingExpectedServices() {
        //given
        //not required

        //when
        this.service.getMatchesSummary();

        //then
        verify(matchService,times(1)).getMatchesSummary();
    }
}
