import java.util.Scanner;

public class Game {

    private static char turn = 'o';

    public static void showBoard(Board board) {
        System.out.println("|" + board.getState().substring(0, 3) + "|");
        System.out.println("|" + board.getState().substring(3, 6) + "|");
        System.out.println("|" + board.getState().substring(6, 9) + "|");
    }

    public static Board playerMakesMove(Board board, int pos) {
        System.out.println("Its your turn " + turn + "!");
        turn = (turn == 'x' ? 'o' : 'x');
        board = board.playerMakesMove(turn, pos - 1);
        showBoard(board);
        return board;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Board board = new Board("---------");
        showBoard(board);
        System.out.println("Where would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)");
        while (!board.checkForDraw()) {
            board = playerMakesMove(board, s.nextInt());
        }
    }
}
