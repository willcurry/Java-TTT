import java.io.InputStream;
import java.util.Scanner;

public class HumanPlayer implements Player{

    private final Scanner scanner;
    private final char mark;

    public HumanPlayer(InputStream stream, char mark) {
        this.scanner = new Scanner(stream);
        this.mark = mark;
    }

    @Override
    public Integer nextMove(Board board) {
        while (!scanner.hasNextInt()) {
            System.out.println(scanner.next() + " is not valid!");
            if (scanner.hasNextInt()) break;
        }
        return scanner.nextInt();
    }

    @Override
    public char getMark() {
        return mark;
    }
}
