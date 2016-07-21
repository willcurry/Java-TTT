import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BoardTests {
    @Test
    public void boardIsCreated() {
        Board board = new Board("---------", 0);
        assertThat(board.getState(), is("---------"));
    }

    @Test
    public void moveIsRegistered() {
        Board board = new Board("---------", 0);
        Board newBoard = board.playerMakesMove('x', 2, 1);
        assertThat(newBoard.getState(), is("--x------"));
    }
    @Test
    public void canFindWinnerXrow() {
        Board board = new Board("---xxx---", 3);
        assertThat(board.checkForWin('x', 4), is(true));
    }

    @Test
    public void canFindWinnerOrow() {
        Board board = new Board("ooo------", 3);
        assertThat(board.checkForWin('o', 1), is(true));
    }

    @Test
    public void canFindWinnerOcolum() {
        Board board = new Board("oxxoxxox-", 8);
        assertThat(board.checkForWin('o', 0), is(true));
    }


    @Test
    public void canFindWinnerXcolum() {
        Board board = new Board("oxooxoox-", 8);
        assertThat(board.checkForWin('x', 1), is(true));
    }

    @Test
    public void canFindWinnerDiagional() {
        Board board = new Board("x---x---x", 8);
        assertThat(board.checkForWin('x', 0), is(true));
    }

    @Test
    public void posUnavaliable() {
        Board board = new Board("xxx------", 3);
        Board newBoard = board.playerMakesMove('o', 2, 3);
        assertThat(newBoard.getState(), is("xxx------"));
    }

    @Test
    public void movesAreUpdated() {
        Board board = new Board("xxx------", 3);
        Board newBoard = board.playerMakesMove('x', 5, 3);
        assertThat(newBoard.getMove(), is(4));
    }

    @Test
    public void checkTurnWorks() {
        Board board = new Board("xox------", 3);
        Board newBoard = board.playerMakesMove('o', 3, 3);
        assertThat(newBoard.checkTurn('o'), is(false));
    }

    @Test
    public void checkForDraw() {
        Board board = new Board("xoxoxoxo-", 8);
        Board newBoard = board.playerMakesMove('x', 8, 8);
        assertEquals(null, newBoard);
    }
}
