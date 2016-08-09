import java.io.*;

public class Game {

    private InputStream stream;
    private Board board;
    public String gameMode = "pvp";
    private Player playerActive;
    private Player playerDeactive;
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
            gameType.drawTurn(playerActive);
            gameType.drawBoard(board);
            return board;
        }
        gameType.invalidMove();
        return board;
    }

    public void assignPlayers() {
        if (gameMode.equals("pvp")) {
            playerActive = new HumanPlayer('x', gameType);
            playerDeactive = new HumanPlayer('o', gameType);
        } else if (gameMode.equals("pvc")) {
            playerActive = new ComputerPlayer('x');
            playerDeactive = new HumanPlayer('o', gameType);
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
        gameType.gameIsOver(board, playerDeactive);
        startNewGame();
    }

    public void pickGameMode() {
        gameType.displayAllGameModes();
        String input = gameType.userPickGameMode();
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

    public static void startNewGame() {
        Board board = new Board("---------");
        Writer writer = new PrintWriter(System.out);
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        ConsoleGame consoleGame = new ConsoleGame(writer, inputReader);
        Game game = new Game(System.in, board, consoleGame);
        game.pickGameMode();
    }

    public static void main(String[] args) {
        startNewGame();
    }
}
