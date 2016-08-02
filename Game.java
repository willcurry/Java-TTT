import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private char turn = 'o';
    private InputStream stream;
    private Board board;
    private String gameMode = "pvp";

    public Game(InputStream stream, Board board) {
        this.stream = stream;
        this.scanner = new Scanner(stream);
        this.board = board;
    }

    public char getTurn() {
        return turn;
    }

    public void switchTurn() {
        turn = (turn == 'o' ? 'x' : 'o');
    }

    public void print(String text) {
        System.out.println(text);
    }

    public String showBoard(Board board) {
        print("|" + board.getState().substring(0, 3) + "|\n|" + board.getState().substring(3, 6) + "|\n|" + board.getState().substring(6, 9) + "|");
        return String.format("|" + board.getState().substring(0, 3) + "|\n|" + board.getState().substring(3, 6) + "|\n|" + board.getState().substring(6, 9) + "|");
    }

    public Board playerMakesMove() {
        int index = randInt(0, board.availablePositions().size());
        int pos;
        if (turn == 'o' && gameMode.equals("pvc")) {
            pos = (int) board.availablePositions().get(index);
            board = board.playerMakesMove('o', pos);
        } else if(gameMode.equals("cvc")) {
            pos = (int) board.availablePositions().get(index);
            board = board.playerMakesMove(getTurn(), pos);
        } else {
            pos = scanner.nextInt();
            if (!board.availablePosition(pos - 1)) return board;
            board = board.playerMakesMove(getTurn(), pos - 1);
        }
        switchTurn();
        print("-----------------");
        print("Its your turn " + getTurn() + "!");
        print("-----------------");
        showBoard(board);
        print("-----------------");
        return board;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void playGame(Board board) {
        print(gameMode.toUpperCase() + " game is starting..");
        print("Where would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)");
        while (!board.checkForDraw()) {
            board = playerMakesMove();
        }
    }

    public void displayAllGamemodes() {
        print("Hello, what gamemode would you like?");
        print("Human vs player (pvp)");
        print("Human vs computer (pvc)");
        print("Computer vs human (cvh)");
        print("Computer vs computer (cvc)");
    }

    public void pickGameMode() {
        displayAllGamemodes();
        Scanner s = new Scanner(System.in);
        String gm = s.nextLine();
        if (gm.equals("pvp") || gm.equals("pvc") || gm.equals("cvp") || gm.equals("cvc")) {
            gameMode = gm;
            playGame(board);
        } else {
            print("You did not pick a valid gamemode.");
        }
    }

    public static void main(String[] args) {
        Board board = new Board("---------");
        Game game = new Game(System.in, board);
        game.pickGameMode();
    }
}
