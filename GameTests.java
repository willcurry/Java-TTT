import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTests {
    @Test
    public void playerMoveIsRegistered() {
        InputStream stream = new ByteArrayInputStream("2".getBytes());
        Board board = new Board("---------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("-o-------"));
    }

    @Test
    public void multiplePlayerMovesAreRegistered() {
        InputStream stream = new ByteArrayInputStream("2\n3".getBytes());
        Board board = new Board("---------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("-o-------"));
        assertThat(game.playerMakesMove().getState(), is("-ox------"));
    }

    @Test
    public void doesNotOverwriteMove() {
        InputStream stream = new ByteArrayInputStream("2\n2".getBytes());
        Board board = new Board("---------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("-o-------"));
        assertThat(game.playerMakesMove().getState(), is("-o-------"));
    }


    @Test
    public void gameKnowsWhoseTurnItIs() {
        InputStream stream = new ByteArrayInputStream("2".getBytes());
        Board board = new Board("x--------");
        Game game = new Game(stream, board);
        game.playerMakesMove();
        assertThat(game.getTurn(), is('x'));
    }

    @Test
    public void playerCannotGoInUnavaliablePositions() {
        InputStream stream = new ByteArrayInputStream("1".getBytes());
        Board board = new Board("o--------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("o--------"));
    }

    @Test
    public void newGameStartsAfterWin() {
        InputStream stream = new ByteArrayInputStream("3".getBytes());
        Board board = new Board("oo-------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("---------"));
    }

    @Test
    public void newGameStartsAfterDraw() {
        InputStream stream = new ByteArrayInputStream("9".getBytes());
        Board board = new Board("xxooxxox-");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("---------"));
    }

    @Test
    public void boardIsDisplayedCorrectly() {
        InputStream stream = new ByteArrayInputStream("9".getBytes());
        Board board = new Board("xxooxxox-");
        Game game = new Game(stream, board);
        assertThat(game.showBoard(board), is("|" + board.getState().substring(0, 3) + "|\n|" + board.getState().substring(3, 6) + "|\n|" + board.getState().substring(6, 9) + "|"));
    }

    @Test
    public void cannotGoInAPositionLowerThen0() {
        InputStream stream = new ByteArrayInputStream("-1".getBytes());
        Board board = new Board("---------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("---------"));
    }

    @Test
    public void cannotGoInHigherThenMaxLength() {
        InputStream stream = new ByteArrayInputStream("10".getBytes());
        Board board = new Board("---------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("---------"));
    }
}
