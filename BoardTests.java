import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

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
    public void canFindWinnerXRow() {
        Board board = new Board("------xxx");
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void canNotFindWinnerMiddleRow() {
        Board board = new Board("---oox---");
        assertThat(board.checkForWin('x'), is(false));
    }

    @Test
    public void canFindWinnerORow() {
        Board board = new Board("ooo------");
        assertThat(board.checkForWin('o'), is(true));
    }

    @Test
    public void canFindWinnerOColumn() {
        Board board = new Board("o--o--o--");
        assertThat(board.checkForWin('o'), is(true));
    }

    @Test
    public void canNotFindWinnerLeftColumn() {
        Board board = new Board("x--o--o--");
        assertThat(board.checkForWin('o'), is(false));
    }

    @Test
    public void canFindWinnerXColumn() {
        Board board = new Board(
                "x--" +
                "x--" +
                "x--");
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void canFindWinnerRightDiagonal() {
        Board board = new Board("x---x---x");
        assertThat(board.checkForWin('x'), is(true));
    }

    @Test
    public void canFindWinnerLeftDiagonal() {
        Board board = new Board("--x-x-x--");
        assertThat(board.checkForWin('x'), is(true));
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
    public void posUnavailable3x3() {
        Board board = new Board("xxx------");
        Board newBoard = board.playerMakesMove('o', 2);
        assertThat(newBoard.getState(), is("xxx------"));
    }

    @Test
    public void posUnavailable4x4() {
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
        ArrayList<Integer> expected = new ArrayList<Integer>(asList(1, 2, 3, 5, 6, 7, 8, 9));
        assertThat(board.availablePositions(), is(expected));
    }

    @Test
    public void canGetAllAvailablePositions4x4() {
        Board board = new Board("-xx-------------");
        ArrayList<Integer> expected = new ArrayList<Integer>(asList(1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
        assertThat(board.availablePositions(), is(expected));
    }

    @Test
    public void whenBoredLengthIsFourThreeCannotWin() {
        Board board = new Board(
                        "xxx-" +
                        "----" +
                        "----" +
                        "----");
        assertThat(board.checkForWin('x'), is(false));
    }
}
