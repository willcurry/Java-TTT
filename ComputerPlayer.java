import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ComputerPlayer implements Player {

    private final char mark;

    public ComputerPlayer(char mark) {
        this.mark = mark;
    }

    @Override
    public Integer nextMove(Board board) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return board.availablePositions().get(randInt(0, board.availablePositions().size() - 1));
    }

    @Override
    public char getMark() {
        return mark;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
