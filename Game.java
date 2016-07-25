import java.io.InputStream;
import java.util.Scanner;

public class Game {

    private char turn = 'o';
    private InputStream stream;

    public Game(InputStream stream) {
        this.stream = stream;
    }

    public void showBoard(Board board) {
        System.out.println("|" + board.getState().substring(0, 3) + "|");
        System.out.println("|" + board.getState().substring(3, 6) + "|");
        System.out.println("|" + board.getState().substring(6, 9) + "|");
    }

    public Boolean avaliablePos(Board board, int pos)  {
        StringBuilder currentBoard = new StringBuilder(board.getState());
        if (currentBoard.charAt(pos - 1) == '-') {
            if (!(pos > 9) || !(pos < 1)) {
                System.out.print("Must be a number from 1-9 \n");
                return true;
            }
        }
        return false;
    }

    public Board playerMakesMove(Board board, int pos) {
        if (!avaliablePos(board, pos)) return board;
        System.out.println("Its your turn " + turn + "!");
        turn = (turn == 'x' ? 'o' : 'x');
        board = board.playerMakesMove(turn, pos - 1);
        showBoard(board);
        return board;
    }

    public void playGame(Board board) {
        this.showBoard(board);
        Scanner scan = new Scanner(stream);
        System.out.println("Where would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)");
        while (!board.checkForDraw()) {
            board = this.playerMakesMove(board, scan.nextInt());
        }
    }

    public static void main(String[] args) {
        Game game = new Game(System.in);
        Board board = new Board("---------");
        game.playGame(board);
    }
}
