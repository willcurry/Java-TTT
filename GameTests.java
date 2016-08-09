import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTests {

    private Writer writer;

    @Before
    public void setUp() throws Exception {
        writer = new PrintWriter(System.out);
    }

    @Test
    public void playerMoveIsRegistered() {
        InputStream stream = new ByteArrayInputStream("2".getBytes());
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame(writer);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("-x-------"));
    }

    @Test
    public void multiplePlayerMovesAreRegistered() {
        InputStream stream = new ByteArrayInputStream("2\n3".getBytes());
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame(writer);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("-x-------"));
        assertThat(game.playerMakesMove().getState(), is("-xo------"));
    }

    @Test
    public void doesNotOverwriteMove() {
        InputStream stream = new ByteArrayInputStream("2\n2".getBytes());
        Board board = new Board("-x-------");
        ConsoleGame consoleGame = new ConsoleGame(writer);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        game.playerMakesMove();
        assertThat(game.playerMakesMove().getState(), is("-x-------"));
    }


    @Test
    public void gameKnowsWhoseTurnItIs() {
        InputStream stream = new ByteArrayInputStream("2".getBytes());
        Board board = new Board("x--------");
        ConsoleGame consoleGame = new ConsoleGame(writer);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("xx-------"));
    }

    @Test
    public void playerCannotGoInUnavaliablePositions() {
        InputStream stream = new ByteArrayInputStream("1".getBytes());
        Board board = new Board("o--------");
        ConsoleGame consoleGame = new ConsoleGame(writer);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("o--------"));
    }

    @Test
    public void noFurtherMovesAcceptedAfterGameIsOver() {
        InputStream stream = new ByteArrayInputStream("3\n4".getBytes());
        Board board = new Board("xx-------");
        ConsoleGame consoleGame = new ConsoleGame(writer);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("xxx------"));
        assertThat(game.playerMakesMove().getState(), is("xxx------"));
    }

    @Test
    public void positionBelowLowerBoundIsNotAccepted() {
        InputStream stream = new ByteArrayInputStream("-1".getBytes());
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame(writer);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("---------"));
    }

    @Test
    public void positionGreaterThenBoardSizeIsNotAccepted() {
        InputStream stream = new ByteArrayInputStream("11".getBytes());
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame(writer);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("---------"));
    }
}
