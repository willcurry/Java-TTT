import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TTTTests {
    @Test
    public void boardCreation()  {
        TTT ttt = new TTT();
        assertThat(ttt.createBoard(), is("---------"));
    }

    @Test
    public void playerMove() {
        TTT ttt = new TTT();
        ttt.createBoard();
        assertThat(ttt.playerMove('x', 2), is("x2"));
    }

    @Test
    public void checkMoveUpdate() {
        TTT ttt = new TTT();
        ttt.createBoard();
        assertThat(ttt.updateBoard('x', 2), is("-x-------"));
    }

    @Test
    public void checkLastTurn() {
        TTT ttt = new TTT();
        ttt.createBoard();
        ttt.playerMove('x', 2);
        assertThat(ttt.isTurn('x'), is(false));
    }

    @Test
    public void playerMoveNotYourTurn() {
        TTT ttt = new TTT();
        ttt.createBoard();
        ttt.playerMove('x', 4);
        assertThat(ttt.playerMove('x', 6), is("It is not your turn x!"));
    }

    @Test
    public void checkWinnerIsChecked() {
        TTT ttt = new TTT();
        ttt.createBoard();
        ttt.playerMove('x', 1);
        ttt.playerMove('o', 8);
        ttt.playerMove('x', 2);
        ttt.playerMove('o', 9);
        assertThat(ttt.updateBoard('x', 3), is("x has won"));
    }

    @Test
    public void checkWinnerIsFound() {
        TTT ttt = new TTT();
        ttt.createBoard();
        assertThat(ttt.foundWinner('x'), is("x won in 0 moves!"));
    }

    @Test
    public void checkDrawHappens() {
        TTT ttt = new TTT();
        ttt.createBoard();
        ttt.playerMove('x', 1);
        ttt.playerMove('o', 2);
        ttt.playerMove('x', 3);
        ttt.playerMove('o', 4);
        ttt.playerMove('x', 5);
        ttt.playerMove('o', 6);
        ttt.playerMove('x', 7);
        ttt.playerMove('o', 8);
        ttt.playerMove('x', 9);
        assertThat(ttt.updateBoard('o', 5), is("Draw"));
    }

    @Test
    public void checkAvaliablePos() {
        TTT ttt = new TTT();
        ttt.createBoard();
        ttt.playerMove('x', 1);
        assertThat(ttt.posAvaliable(0), is(false));
    }

}
