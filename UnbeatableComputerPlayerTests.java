import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UnbeatableComputerPlayerTests {
    @Test
    public void blockOpponentWinningOn3x3() {
        Board board = new Board("oo-------");
        UnbeatableComputerPlayer unbeatableComputerPlayer = new UnbeatableComputerPlayer('x');
        Board newBoard = board.playerMakesMove('x', unbeatableComputerPlayer.nextMove(board) - 1);
        assertThat(newBoard.getState(), is("oox------"));
    }

    @Test
    public void blockOpponentWinningOn4x4() {
        Board board = new Board("ooo-------------");
        UnbeatableComputerPlayer unbeatableComputerPlayer = new UnbeatableComputerPlayer('x');
        Board newBoard = board.playerMakesMove('x', unbeatableComputerPlayer.nextMove(board) - 1);
        assertThat(newBoard.getState(), is("ooox------------"));
    }

    @Test
    public void pickWinningPositionOn3x3() {
        Board board = new Board("xx-------");
        UnbeatableComputerPlayer unbeatableComputerPlayer = new UnbeatableComputerPlayer('x');
        Board newBoard = board.playerMakesMove('x', unbeatableComputerPlayer.nextMove(board) - 1);
        assertThat(newBoard.getState(), is("xxx------"));
    }

    @Test
    public void pickWinningPositionOn4x4() {
        Board board = new Board("xxx-------------");
        UnbeatableComputerPlayer unbeatableComputerPlayer = new UnbeatableComputerPlayer('x');
        Board newBoard = board.playerMakesMove('x', unbeatableComputerPlayer.nextMove(board) - 1);
        assertThat(newBoard.getState(), is("xxxx------------"));
    }
}
