import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer implements Player {

    private final char mark;

    public ComputerPlayer(char mark) {
        this.mark = mark;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    @Override
    public Integer nextMove(Board board) {
        ArrayList availiblePositions = board.availablePositions();
        int index = randInt(2, availiblePositions.size());
        return (int) availiblePositions.get(index - 1);
    }

    @Override
    public char getMark() {
        return mark;
    }
}
