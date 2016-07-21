import java.util.Scanner;

public class Game {

    private static char turn;

    public static void showBoard(Board board) {
        System.out.println("|" + board.getState().substring(0, 3) + "|");
        System.out.println("|" + board.getState().substring(3, 6) + "|");
        System.out.println("|" + board.getState().substring(6, 9) + "|");
    }

    public static Board playerMakesMove(Board board, int pos) {
        if (turn == 'x')  {
            turn = 'o';
        } else {
            turn = 'x';
        }
        System.out.println("Its your turn " + turn + "!");
        System.out.println("Where would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)");
        board = board.playerMakesMove(turn, pos - 1);
        showBoard(board);
        return board;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Board board = new Board("---------");
        showBoard(board);
        System.out.println("Its your turn X");
        System.out.println("Where would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)");
        while (!board.checkForDraw()) {
            board = playerMakesMove(board, s.nextInt());
        }
    }
}
