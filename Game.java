import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private char turn = 'o';
    private InputStream stream;
    private Board board;
    private String gameMode = "pvp";
    private ComputerPlayer computerPlayer;

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
        if (getTurn() == 'o' && gameMode.equals("pvc")) {
            computerPlayer = new ComputerPlayer();
            board = board.playerMakesMove(getTurn(), computerPlayer.nextMove(board));
        } else if(gameMode.equals("cvc")) {
            computerPlayer = new ComputerPlayer();
            board = board.playerMakesMove(getTurn(), computerPlayer.nextMove(board));
        } else {
            int position = scanner.nextInt();
            if (!board.availablePosition(position - 1)) return board;
            board = board.playerMakesMove(getTurn(), position - 1);
        }
        switchTurn();
        print("-----------------");
        print("Its your turn " + getTurn() + "!");
        print("-----------------");
        showBoard(board);
        print("-----------------");
        return board;
    }

    public void playGame(Board board) {
        print(gameMode.toUpperCase() + " game is starting..");
        print("Where would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)");
        while (!board.checkForDraw()) {
            board = playerMakesMove();
        }
    }

    public enum Gamemodes {
        PVP,
        PVC,
        CVH,
        CVC
    }

    public void displayAllGamemodes() {
        print("Hello, what gamemode would you like?");
        for (Gamemodes gm : Gamemodes.values()) {
            print(gm.toString());
        }
    }

    public void pickGameMode() {
        displayAllGamemodes();
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        for (Gamemodes gm : Gamemodes.values()) {
            if (input.equals(gm.toString())) {
                gameMode = input.toLowerCase();
                playGame(board);
                return;
            }
        }
        print("You did not pick a valid gamemode.");
    }

    public static void main(String[] args) {
        Board board = new Board("---------");
        Game game = new Game(System.in, board);
        game.pickGameMode();
    }
}
