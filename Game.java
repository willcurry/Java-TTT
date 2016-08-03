import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private char turn = 'o';
    private InputStream stream;
    private Board board;
    private String gameMode = "pvp";
    private Player playerActive;
    private Player playerDeactive;

    public Game(InputStream stream, Board board) {
        this.stream = stream;
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
        int position = playerActive.nextMove(board);
        if (!board.availablePosition(position - 1)) {
            print("Invalid Position");
            return board;
        }
        board = board.playerMakesMove(getTurn(), position - 1);

        switchTurn();
        print("-----------------");
        print("Its your turn " + getTurn() + "!");
        print("-----------------");
        showBoard(board);
        print("-----------------");
        return board;
    }

    public void assignPlayers() {
        if (gameMode.equals("pvp")) {
            playerActive = new HumanPlayer(stream);
            playerDeactive = new HumanPlayer(stream);
        } else if (gameMode.equals("pvc")) {
            playerActive = new ComputerPlayer();
            playerDeactive = new HumanPlayer(stream);
        } else {
            playerActive = new ComputerPlayer();
            playerDeactive = new ComputerPlayer();
        }
    }

    public void playGame(Board board) {
        assignPlayers();
        print(gameMode.toUpperCase() + " game is starting..");
        print("Where would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)");
        while (!board.checkForDraw()) {
            board = playerMakesMove();
        }
    }

    public enum Gamemodes {
        PVP,
        PVC,
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
