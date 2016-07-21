import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BoardTests {
    @Test
    public void boardIsCreated() {
        Board board = new Board("---------");
        assertThat(board.getState(), is("---------"));
    }

    @Test
    public void canFindWinnerXrow() {
        Board board = new Board("---xxx---");
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void canFindWinnerOrow() {
        Board board = new Board("ooo------");
        assertThat(board.checkForWin('o'), is(true));
    }

    @Test
    public void canFindWinnerOcolum() {
        Board board = new Board("oxxoxxox-");
        assertThat(board.checkForWin('o'), is(true));
    }


    @Test
    public void canFindWinnerXcolum() {
        Board board = new Board("oxooxoox-");
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void canFindWinnerDiagional() {
        Board board = new Board("x---x---x");
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void posUnavaliable() {
        Board board = new Board("xxx------");
        Board newBoard = board.playerMakesMove('o', 2);
        assertThat(newBoard.getState(), is("xxx------"));
    }

    @Test
    public void checkTurnKnowsWhoNextPlayerIs() {
        Board board = new Board("xox------");
        Board newBoard = board.playerMakesMove('o', 3);
        assertThat(newBoard.checkTurn('o'), is(false));
    }

    @Test
    public void checkForDraw() {
        Board board = new Board("xoxoxoxox");
        assertThat(board.checkForDraw(), is(true));
    }
}
