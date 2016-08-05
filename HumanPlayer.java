import java.io.BufferedReader;

public class HumanPlayer implements Player {

    private final char mark;
    private final BufferedReader inputReader;

    public HumanPlayer(BufferedReader inputReader, char mark) {
        this.inputReader = inputReader;
        this.mark = mark;
    }

    @Override
    public Integer nextMove(Board board) {
        int nextPosition = -1;
        while (!validate(nextPosition)) {
            try {
                String line = inputReader.readLine();
                nextPosition = Integer.parseInt(line);
            } catch (Exception e) {
                return nextPosition;
            }
        }
        return nextPosition;
    }

    private boolean validate(int nextPosition) {
        return nextPosition >= 0 && nextPosition <= 9;
    }

    @Override
    public char getMark() {
        return mark;
    }
}
