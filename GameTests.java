import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GameTests {
    @Test
    public void playerMoveIsRegistered() {
        Game game = new Game(System.in);
        Board board = new Board("---------");
        assertThat(game.playerMakesMove(board, 2).getState(), is("-x-------"));
    }
}
