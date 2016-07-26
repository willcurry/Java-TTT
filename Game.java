import java.io.InputStream;
import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private char turn = 'o';
    private InputStream stream;
    private Board board;

    public Game(InputStream stream, Board board) {
        this.stream = stream;
        this.scanner = new Scanner(stream);
        this.board = board;
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void showBoard(Board board) {
        print("|" + board.getState().substring(0, 3) + "|");
        print("|" + board.getState().substring(3, 6) + "|");
        print("|" + board.getState().substring(6, 9) + "|");
    }



    public Board playerMakesMove() {
        int pos = scanner.nextInt();
        print("Its your turn " + turn + "!");
        turn = (turn == 'x' ? 'o' : 'x');
        board = board.playerMakesMove(turn, pos - 1);
        showBoard(board);
        return board;
    }

    public void playGame(Board board) {
        this.showBoard(board);
        print("Where would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)");
        while (!board.checkForDraw()) {
            board = this.playerMakesMove();
        }
    }

    public static void main(String[] args) {
        Board board = new Board("---------");
        Game game = new Game(System.in, board);
        game.playGame(board);
    }
}
