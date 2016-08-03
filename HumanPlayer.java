import java.io.InputStream;
import java.util.Scanner;

public class HumanPlayer implements Player{

    private final InputStream stream;
    private final Scanner scanner;
    private final char mark;

    public HumanPlayer(InputStream stream, char mark) {
        this.stream = stream;
        this.scanner = new Scanner(stream);
        this.mark = mark;
    }

    @Override
    public Integer nextMove(Board board) {
        int position = scanner.nextInt();
        return position;
    }

    @Override
    public char getMark() {
        return mark;
    }
}
