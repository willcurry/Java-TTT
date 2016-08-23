import org.junit.Test;
import java.util.ArrayList;

import static java.util.Arrays.asList;
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
    public void cannotFindWinnerMiddleRow() {
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
    public void cannotFindWinnerLeftColumn() {
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
    public void checkForWinCanFindWinner3x3() {
        Board board = new Board("x---x---x");
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void checkForWinCanFindWinner4x4() {
        Board board = new Board("xxxx------------");
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void posUnavaliable3x3() {
        Board board = new Board("xxx------");
        Board newBoard = board.playerMakesMove('o', 2);
        assertThat(newBoard.getState(), is("xxx------"));
    }

    @Test
    public void posUnavaliable4x4() {
        Board board = new Board("xxx-------------");
        Board newBoard = board.playerMakesMove('o', 2);
        assertThat(newBoard.getState(), is("xxx-------------"));
    }

    @Test
    public void checkForDraw() {
        Board board = new Board("xoxoxoxox");
        assertThat(board.checkForDraw(), is(true));
    }

    @Test
    public void canGetAllAvailablePositions3x3() {
        Board board = new Board("---x-----");
        ArrayList<Integer> positions = board.availablePositions();
        ArrayList<Integer> expected = new ArrayList<Integer>(asList(1, 2, 3, 5, 6, 7, 8, 9));
        assertThat(positions, is(expected));
    }

    @Test
    public void canGetAllAvailablePositions4x4() {
        Board board = new Board("-xx-------------");
        ArrayList<Integer> positions = board.availablePositions();
        ArrayList<Integer> expected = new ArrayList<Integer>(asList(1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
        assertThat(positions, is(expected));
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
