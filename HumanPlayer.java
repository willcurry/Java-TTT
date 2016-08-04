import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HumanPlayer implements Player {

    private final Scanner scanner;
    private final char mark;
    private final BufferedReader inputReader;

    public HumanPlayer(InputStream stream, char mark) {
        this.scanner = new Scanner(stream);
        this.inputReader = new BufferedReader(new InputStreamReader(stream));
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
                nextPosition = -1;
            }
        }
        return nextPosition;
//        int nextLine = scanner.nextInt();
//        while (!scanner.hasNextInt()) {
//            if (scanner.hasNextInt()) break;
//            System.out.println(scanner.nextLine() + " is not valid!");
//        }
//        int nextLine2 = scanner.nextInt();
//        return nextLine2;
//        return scanner.nextInt();
    }

    private boolean validate(int nextPosition) {
        return nextPosition >= 0 && nextPosition < 9;
    }

    @Override
    public char getMark() {
        return mark;
    }
}
