import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer implements Player {

    private final char mark;

    public ComputerPlayer(char mark) {
        this.mark = mark;
    }

    @Override
    public Integer nextMove(Board board) {
        ArrayList availblePositions = board.availablePositions();
        int index = randInt(0, availblePositions.size() - 1);
        return (int) availblePositions.get(index);
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
