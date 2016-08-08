import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTests {
    @Test
    public void playerMoveIsRegistered() {
        InputStream stream = new ByteArrayInputStream("2".getBytes());
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("-x-------"));
    }

    @Test
    public void multiplePlayerMovesAreRegistered() {
        InputStream stream = new ByteArrayInputStream("2\n3".getBytes());
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("-x-------"));
        assertThat(game.playerMakesMove().getState(), is("-xo------"));
    }

    @Test
    public void doesNotOverwriteMove() {
        InputStream stream = new ByteArrayInputStream("2\n2".getBytes());
        Board board = new Board("-x-------");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        game.playerMakesMove();
        assertThat(game.playerMakesMove().getState(), is("-x-------"));
    }


    @Test
    public void gameKnowsWhoseTurnItIs() {
        InputStream stream = new ByteArrayInputStream("2".getBytes());
        Board board = new Board("x--------");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("xx-------"));
    }

    @Test
    public void playerCannotGoInUnavaliablePositions() {
        InputStream stream = new ByteArrayInputStream("1".getBytes());
        Board board = new Board("o--------");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("o--------"));
    }

    @Test
    public void boardStateDoesntChangeAfterWin() {
        InputStream stream = new ByteArrayInputStream("3".getBytes());
        Board board = new Board("xx-------");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("xxx------"));
    }

    @Test
    public void boardStateDoesntChangeAfterDraw() {
        InputStream stream = new ByteArrayInputStream("9".getBytes());
        Board board = new Board("xxoxoxxo-");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("xxoxoxxox"));
    }

    @Test
    public void boardIsDisplayedCorrectly() {
        InputStream stream = new ByteArrayInputStream("9".getBytes());
        Board board = new Board("xxooxxox-");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(consoleGame.drawBoard(board), is("|" + board.getState().substring(0, 3) + "|\n|" + board.getState().substring(3, 6) + "|\n|" + board.getState().substring(6, 9) + "|"));
    }

    @Test
    public void cannotGoInAPositionLowerThen0() {
        InputStream stream = new ByteArrayInputStream("-1".getBytes());
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("---------"));
    }

    @Test
    public void cannotGoInHigherThenMaxLength() {
        InputStream stream = new ByteArrayInputStream("11".getBytes());
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(game.playerMakesMove().getState(), is("---------"));
    }
}
