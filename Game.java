import java.io.*;
import java.util.Scanner;

public class Game {

    private InputStream stream;
    private Board board;
    public String gameMode = "pvp";
    private Player playerActive;
    private Player playerDeactive;
    private BufferedReader inputReader;
    private GameType gameType;

    public Game(InputStream stream, Board board, GameType gameType) {
        this.stream = stream;
        this.board = board;
        this.gameType = gameType;
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
            gameType.drawTurn(this);
            gameType.drawBoard(board);
            return board;
        }
        gameType.invalidMove();
        return board;
    }

    public Player playerActive() {
        return playerActive;
    }

    public Player playerDeactive() {
        return playerDeactive;
    }

    public void assignPlayers() {
        if (gameMode.equals("pvp")) {
            inputReader = new BufferedReader(new InputStreamReader(stream));
            playerActive = new HumanPlayer(inputReader, 'x');
            playerDeactive = new HumanPlayer(inputReader, 'o');
        } else if (gameMode.equals("pvc")) {
            inputReader = new BufferedReader(new InputStreamReader(stream));
            playerActive = new ComputerPlayer('x');
            playerDeactive = new HumanPlayer(inputReader, 'o');
        } else {
            playerActive = new ComputerPlayer('x');
            playerDeactive = new ComputerPlayer('o');
        }
    }

    public void playGame() {
        assignPlayers();
        gameType.drawNewGame(this);
        while (!board.isGameOver()){
            board = playerMakesMove();
        }
        gameType.gameIsOver(board, this);
    }

    public void pickGameMode() {
        gameType.displayAllGameModes();
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        for (Gamemodes gm : Gamemodes.values()) {
            if (input.equals(gm.toString().toLowerCase()) || input.equals(gm.toString())) {
                gameMode = input.toLowerCase();
                playGame();
                return;
            }
        }
        gameType.invalidGamemode();
        pickGameMode();
    }

    public static void main(String[] args) {
        Board board = new Board("---------");
        ConsoleGame consoleGame = new ConsoleGame();
        Game game = new Game(System.in, board, consoleGame);
        game.pickGameMode();
    }
}
