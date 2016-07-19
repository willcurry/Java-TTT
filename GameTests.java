import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GameTests {
    @Test
    public void moveIsRegistered() {
        Game game = new Game();
        Board board = new Board("---------", 0);
        Board newBoard = game.playerMakesMove('x', 2, board, 1);
        assertThat(newBoard.getState(), is("--x------"));
    }
    @Test
    public void canFindWinnerXrow() {
        Game game = new Game();
        Board board = new Board("xxx------", 3);
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void canFindWinnerOrow() {
        Game game = new Game();
        Board board = new Board("ooo------", 3);
        assertThat(board.checkForWin('o'), is(true));
    }

    @Test
    public void canFindWinnerOcolum() {
        Game game = new Game();
        Board board = new Board("oxooxoox-", 8);
        assertThat(board.checkForWin('o'), is(true));
    }


    @Test
    public void canFindWinnerXcolum() {
        Game game = new Game();
        Board board = new Board("oxooxoox-", 8);
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void canFindWinnerDiagional() {
        Game game = new Game();
        Board board = new Board("x---x---x", 8);
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void posUnavaliable() {
        Game game = new Game();
        Board board = new Board("xxx------", 3);
        Board newBoard = game.playerMakesMove('o', 2, board, 3);
        assertThat(newBoard.getState(), is("xxx------"));
    }

    @Test
    public void movesAreRegistered() {
        Game game = new Game();
        Board board = new Board("xxx------", 3);
        Board newBoard = game.playerMakesMove('o', 5, board, 3);
        assertThat(newBoard.getMove(), is(4));
    }

    @Test
    public void checkCantGoTwice() {
        Game game = new Game();
        Board board = new Board("xox------", 3);
        Board newBoard = game.playerMakesMove('o', 3, board, 3);
        Board newNewBoard = game.playerMakesMove('o', 4, newBoard, 4);
        assertThat(newNewBoard.getState(), is("xoxo-----"));
    }

    @Test
    public void checkTurnWorks() {
        Game game = new Game();
        Board board = new Board("xox------", 3);
        Board newBoard = game.playerMakesMove('o', 3, board, 3);
        Board newNewBoard = game.playerMakesMove('o', 4, newBoard, 4);
        assertThat(newNewBoard.getState(), is("xoxo-----"));
    }

    @Test
    public void checkForDraw() {
        Game game = new Game();
        Board board = new Board("xoxoxoxo-", 8);
        Board newBoard = game.playerMakesMove('x', 8, board, 8);
        assertEquals(null, newBoard);
    }
}
