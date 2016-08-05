import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Game {

    private InputStream stream;
    private Board board;
    public String gameMode = "pvp";
    private Player playerActive;
    private Player playerDeactive;
    private BufferedReader inputReader;
    private ConsoleGame consoleGame = new ConsoleGame(this);

    public Game(InputStream stream, Board board) {
        this.stream = stream;
        this.board = board;
    }

    public void switchTurn() {
        Player newActivePlayer = playerActive;
        playerActive = playerDeactive;
        playerDeactive = newActivePlayer;
    }


    public Board playerMakesMove() {
        int position = playerActive.nextMove(board);
        if (board.availablePosition(position - 1)) {
            board = board.playerMakesMove(playerActive.getMark(), position - 1);
            switchTurn();
            consoleGame.drawTurn();
            consoleGame.drawBoard(board);
            return board;
        }
        consoleGame.invalidGamemode();
        return board;
    }

    public Player playerActive() {
        return playerActive;
    }

    public void assignPlayers() {
        if (gameMode.equals("pvp")) {
            inputReader = new BufferedReader(new InputStreamReader(stream));
            playerActive = new HumanPlayer(inputReader, 'x');
            playerDeactive = new HumanPlayer(inputReader, 'o');
        } else if (gameMode.equals("pvc")) {
            playerActive = new ComputerPlayer('x');
            playerDeactive = new HumanPlayer(inputReader, 'o');
        } else {
            playerActive = new ComputerPlayer('x');
            playerDeactive = new ComputerPlayer('o');
        }
    }

    public void playGame(Board board) {
        assignPlayers();
        consoleGame.drawNewGame();

        while (!board.checkForDraw() || !board.getState().equals("Game Over")) {
            board = playerMakesMove();
        }
        System.out.println(playerActive + " has won this game!");
    }

    public void pickGameMode() {
        consoleGame.displayAllGamemodes();
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        for (Gamemodes gm : Gamemodes.values()) {
            if (input.equals(gm.toString())) {
                gameMode = input.toLowerCase();
                playGame(board);
                return;
            }
        }
        consoleGame.invalidGamemode();
    }

    public static void main(String[] args) {
        Board board = new Board("---------");
        Game game = new Game(System.in, board);
        game.pickGameMode();
    }
}
