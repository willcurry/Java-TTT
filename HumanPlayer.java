import java.io.InputStream;
import java.util.Scanner;

public class HumanPlayer implements Player{

    private final InputStream stream;
    private final Scanner scanner;

    public HumanPlayer(InputStream stream) {
        this.stream = stream;
        this.scanner = new Scanner(stream);
    }

    @Override
    public Integer nextMove(Board board) {
        int position = scanner.nextInt();
        return position;
    }
}
