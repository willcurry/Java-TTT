import java.io.*;

public class Game {

    private InputStream reader;
    private Board board;
    private Player playerActive;
    private Player playerDeactive;
    private GameType gameType;

    public Game(InputStream reader, Board board, GameType gameType) {
        this.reader = reader;
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

    public void assignPlayers(String gameMode) {
        Player[] pair = PlayerFactory.createPair(gameMode, gameType);
        playerActive = pair[0];
        playerDeactive = pair[1];
    }

    public void playGame(String gameMode) {
        assignPlayers(gameMode);
        gameType.drawNewGame(gameMode);
        while (!board.isGameOver()){
            board = playerMakesMove();
        }
        gameType.gameIsOver(board);
        startNewGame();
    }

    public void pickGameMode() {
        this.board = Board.createBoard(gameType.userPickBoardSize());
        gameType.displayAllGameModes();
        playGame(gameType.userPickGameMode());
        pickGameMode();
    }

    public static void startNewGame() {
        Board board = new Board("---------");
        Writer writer = new PrintWriter(System.out);
        InputStream reader = System.in;
        ConsoleGame consoleGame = new ConsoleGame(writer, reader);
        Game game = new Game(System.in, board, consoleGame);
        game.pickGameMode();
    }

    public static void main(String[] args) {
        startNewGame();
    }
}
