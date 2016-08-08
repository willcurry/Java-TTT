import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleGameTests {
    @Test
    public void boardIsDisplayedCorrectly() {
        InputStream stream = new ByteArrayInputStream("9".getBytes());
        Board board = new Board("xxooxxox-");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(stream, board, consoleGame);
        game.assignPlayers();
        assertThat(consoleGame.drawBoard(board), is("|" + board.getState().substring(0, 3) + "|\n|" + board.getState().substring(3, 6) + "|\n|" + board.getState().substring(6, 9) + "|\n"));
    }
}
