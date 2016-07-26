import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTests {
    @Test
    public void playerMoveIsRegistered() throws IOException {
        InputStream stream = new ByteArrayInputStream("2".getBytes());
        Board board = new Board("---------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("-x-------"));
    }

    @Test
    public void multiplePlayerMovesAreRegistered() throws IOException {
        InputStream stream = new ByteArrayInputStream("2\n3".getBytes());
        Board board = new Board("---------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("-x-------"));
        assertThat(game.playerMakesMove().getState(), is("-xo------"));
    }

    @Test
    public void doesNotOverwriteMove() {
        InputStream stream = new ByteArrayInputStream("2\n2".getBytes());
        Board board = new Board("---------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("-x-------"));
        assertThat(game.playerMakesMove().getState(), is("-x-------"));
    }


    @Test
    public void gameKnowsWhosTurnItIs() {
        InputStream stream = new ByteArrayInputStream("2".getBytes());
        Board board = new Board("o--------");
        Game game = new Game(stream, board);
        assertThat(game.playerMakesMove().getState(), is("ox-------"));
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
        Board board = new Board("xx-------");
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
}
