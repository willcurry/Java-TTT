import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GameTests {
    @Test
    public void moveIsRegistered() {
        Game game = new Game();
        Board board = new Board("---------", 0);
        Board newBoard = board.playerMakesMove('x', 2, 1);
        assertThat(newBoard.getState(), is("--x------"));
    }
    @Test
    public void canFindWinnerXrow() {
        Game game = new Game();
        Board board = new Board("---xxx---", 3);
        assertThat(board.checkForWin('x', 4), is(true));
    }

    @Test
    public void canFindWinnerOrow() {
        Game game = new Game();
        Board board = new Board("ooo------", 3);
        assertThat(board.checkForWin('o', 1), is(true));
    }

    @Test
    public void canFindWinnerOcolum() {
        Game game = new Game();
        Board board = new Board("oxxoxxox-", 8);
        assertThat(board.checkForWin('o', 0), is(true));
    }


    @Test
    public void canFindWinnerXcolum() {
        Game game = new Game();
        Board board = new Board("oxooxoox-", 8);
        assertThat(board.checkForWin('x', 1), is(true));
    }

    @Test
    public void canFindWinnerDiagional() {
        Game game = new Game();
        Board board = new Board("x---x---x", 8);
        assertThat(board.checkForWin('x', 0), is(true));
    }

    @Test
    public void posUnavaliable() {
        Game game = new Game();
        Board board = new Board("xxx------", 3);
        Board newBoard = board.playerMakesMove('o', 2, 3);
        assertThat(newBoard.getState(), is("xxx------"));
    }

    @Test
    public void movesAreUpdated() {
        Game game = new Game();
        Board board = new Board("xxx------", 3);
        Board newBoard = board.playerMakesMove('x', 5, 3);
        assertThat(newBoard.getMove(), is(4));
    }

    @Test
    public void checkTurnWorks() {
        Game game = new Game();
        Board board = new Board("xox------", 3);
        Board newBoard = board.playerMakesMove('o', 3, 3);
        assertThat(newBoard.checkTurn('o'), is(false));
    }

    @Test
    public void checkForDraw() {
        Game game = new Game();
        Board board = new Board("xoxoxoxo-", 8);
        Board newBoard = board.playerMakesMove('x', 8, 8);
        assertEquals(null, newBoard);
    }
}
