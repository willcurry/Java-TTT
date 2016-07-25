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
        InputStream anyInputStream = new ByteArrayInputStream("1".getBytes());
        Game game = new Game(anyInputStream);
        Board board = new Board("---------");
        assertThat(game.playerMakesMove(board, 2), is("-x-------"));
    }
}
