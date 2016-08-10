import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class ConsoleGameTests {

    private Writer writer;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
        writer = new PrintWriter(output);
    }

    @Test
    public void boardIsDisplayedCorrectly() {
        InputStream stream = new ByteArrayInputStream("9".getBytes());
        Board board = new Board("xxooxxox-");
        ConsoleGame consoleGame = new ConsoleGame(writer, stream);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        consoleGame.drawBoard(board);
        assertThat(output.toString(), containsString("|xxo|\n|oxx|\n|ox-|"));
    }

    @Test
    public void displaysWhosTurnItIsCorrectly() {
        InputStream stream = new ByteArrayInputStream("1".getBytes());
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(stream));
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame(writer, stream);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        game.playerMakesMove();
        assertThat(output.toString(), containsString("Its your turn o!"));
    }

    @Test
    public void displaysStartOfGameInfoAtTheStartOfAGame() {
        InputStream stream = new ByteArrayInputStream("1".getBytes());
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(stream));
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame(writer, stream);
        Game game = new Game(stream, board, consoleGame);
        consoleGame.drawNewGame(game);
        assertThat(output.toString(), containsString("game is starting.."));
        assertThat(output.toString(), containsString("Where would you like to go?"));
    }

    @Test
    public void displaysAllGameModesCorrectly() {
        InputStream stream = new ByteArrayInputStream("1".getBytes());
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(stream));
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame(writer, stream);
        Game game = new Game(stream, board, consoleGame);
        consoleGame.displayAllGameModes();
        for (Gamemodes gm : Gamemodes.values()) {
            assertThat(output.toString(), containsString(gm.toString()));
        }
    }

    @Test
    public void displaysWhoWonWhenGameEndsFromWin() {
        InputStream stream = new ByteArrayInputStream("9".getBytes());
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(stream));
        Board board = new Board("xxooxxox-");
        ConsoleGame consoleGame = new ConsoleGame(writer, stream);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        game.playerMakesMove();
        Player player = new ComputerPlayer('x');
        consoleGame.gameIsOver(board,player);
        assertThat(output.toString(), containsString("x has won the game!"));
    }

    @Test
    public void displaysDrawWhenGameEndsFromDraw() {
        InputStream stream = new ByteArrayInputStream("9".getBytes());
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(stream));
        Board board = new Board("lllllllll");
        ConsoleGame consoleGame = new ConsoleGame(writer, stream);
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        Player player = new ComputerPlayer('x');
        consoleGame.gameIsOver(board,player);
        assertThat(output.toString(), containsString("Draw!"));
    }
}
