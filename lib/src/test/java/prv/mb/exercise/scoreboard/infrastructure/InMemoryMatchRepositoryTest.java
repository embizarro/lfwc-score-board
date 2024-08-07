package prv.mb.exercise.scoreboard.infrastructure;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import prv.mb.exercise.scoreboard.domain.Match;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class InMemoryMatchRepositoryTest {

    private static final String MATCH_ID_1 = "mexico-canada-match";
    private static final String MATCH_ID_2 = "mexico-poland-match";

    private InMemoryMatchRepository repository;

    @BeforeMethod
    public void setUp() throws Exception {
        this.repository = new InMemoryMatchRepository();
    }

    @Test
    public void shouldAddNewMatchIfNotExists() {
        //given
        Match mockedMatch = Mockito.mock(Match.class);

        //when
        this.repository.add(mockedMatch);

        //then
        assertEquals(this.repository.getAllMatches().size(), 1);
    }

    @Test
    public void shouldUpdateMatchIfExists() {
        //given

        //when
        Match result = this.repository.get(MATCH_ID_1);

        //then
        assertEquals(result.getId(), MATCH_ID_1);
    }

    @Test
    public void shouldRemoveMatchIfExists() {
        //given
        Match mockedMatch = assembleMatchWithId(MATCH_ID_1);

        //when
        this.repository.remove(mockedMatch);

        //then
        assertEquals(repository.getAllMatches().size(), 1);
        assertEquals(repository.getAllMatches().iterator().next().getId(), MATCH_ID_2);
    }

    private Match assembleMatchWithId(String matchId) {
        Match mockedMatch = Mockito.mock(Match.class);
        when(mockedMatch.getId()).thenReturn(matchId);
        return mockedMatch;
    }
}