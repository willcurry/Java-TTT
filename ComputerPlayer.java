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
        int index = randInt(0, board.availablePositions().size());
        return (int) board.availablePositions().get(index);
    }

    @Override
    public char getMark() {
        return mark;
    }
}
