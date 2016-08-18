import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTests {
    @Test
    public void boardIsCreated() {
        Board board = new Board("---------");
        assertThat(board.getState(), is("---------"));
    }

    @Test
    public void canFindWinnerXrow() {
        Board board = new Board("------xxx");
        assertThat(board.checkRowsForWin('x'), is(true));
    }

    @Test
    public void canNotFindWinnerMiddleRow() {
        Board board = new Board("---oox---");
        assertThat(board.checkRowsForWin('x'), is(false));
    }

    @Test
    public void canFindWinnerOrow() {
        Board board = new Board("ooo------");
        assertThat(board.checkRowsForWin('o'), is(true));
    }

    @Test
    public void canFindWinnerOcolum() {
        Board board = new Board("o--o--o--");
        assertThat(board.checkColumnsForWin('o'), is(true));
    }

    @Test
    public void canNotFindWinnerLeftColumn() {
        Board board = new Board("x--o--o--");
        assertThat(board.checkColumnsForWin('o'), is(false));
    }

    @Test
    public void canFindWinnerXcolum() {
        Board board = new Board("oxooxoox-");
        assertThat(board.checkColumnsForWin('x'), is(true));
    }

    @Test
    public void canFindWinnerDiagional() {
        Board board = new Board("x---x---x");
        assertThat(board.checkDiagonalsForWin('x'), is(true));
    }

    @Test
    public void posUnavaliable() {
        Board board = new Board("xxx------");
        Board newBoard = board.playerMakesMove('o', 2);
        assertThat(newBoard.getState(), is("xxx------"));
    }

    @Test
    public void checkForDraw() {
        Board board = new Board("xoxoxoxox");
        assertThat(board.checkForDraw(), is(true));
    }

    @Test
    public void canGetAllAvailablePositions() {
        Board board = new Board("---x-----");
        ArrayList<Integer> positions = board.availablePositions();
        for (int i=0; i < positions.size(); i++) {
            int position = positions.get(i);
            assertThat(board.availablePosition(position), is(true));
        }
    }

    @Test
    public void whenBoredLengthIsFourThreeCannotWin() {
        Board board = new Board(
                        "xxx-" +
                        "----" +
                        "----" +
                        "----");
        assertThat(board.checkRowsForWin('x'), is(false));
    }
}
