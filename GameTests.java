import org.junit.Test;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GameTests {
    @Test
    public void playerMoveIsRegistered() {
        InputStream stream = new ByteArrayInputStream("1".getBytes());
        Game game = new Game(stream);
        Board board = new Board("---------");
        assertThat(game.playerMakesMove(board, 2).getState(), is("-x-------"));
    }

    @Test
    public void gameKnowsWhosTurnItIs() {
        InputStream stream = new ByteArrayInputStream("1".getBytes());
        Game game = new Game(stream);
        Board board = new Board("o--------");
        assertThat(game.playerMakesMove(board, 2).getState(), is("ox-------"));
    }

    @Test
    public void playerCannotGoInUnavaliablePositions() {
        InputStream stream = new ByteArrayInputStream("1".getBytes());
        Game game = new Game(stream);
        Board board = new Board("o--------");
        assertThat(game.playerMakesMove(board, 1).getState(), is("o--------"));
    }
}
