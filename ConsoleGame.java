import java.io.*;

public class ConsoleGame implements GameType {

    private final Writer writer;
    private BufferedReader reader;

    public ConsoleGame(Writer writer, InputStream stream) {
        this.writer = writer;
        InputStream inputStream = stream;
        reader = new BufferedReader(new InputStreamReader(stream));
    }

    @Override
    public void drawBoard(Board board) {
        for (int i=0; i < board.dimension * board.dimension; i+=board.dimension) {
            String row = "";
            for (int j=i; j < i + board.dimension; j++) {
                row += board.getState().charAt(j);
            }
            print("|" + row + "|\n");
        }
    }

    @Override
    public String userInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Invalid";
    }

    @Override
    public int userPickBoardSize() {
        print("\033[31mWhat board size would you like? \n");
        int size = -1;
        try {
            size = Integer.parseInt(userInput());
        } catch (Exception e) {
            return size;
        }
        return size;
    }

    @Override
    public void drawTurn(Player player) {
        print("----------------- \n");
        print("Its your turn " + player.getMark() + "!\n");
        print("----------------- \n");
    }

    public void print(String text) {
        try {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void drawNewGame(String gameMode) {
        print("\033[36m" + gameMode.toUpperCase() + "\033[31m game is starting..\n");
        print("\033[35mWhere would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)\n");
    }

    @Override
    public void invalidMove() {
        print("That is not a valid position!\n");
    }

    @Override
    public void displayAllGameModes() {
        print("Hello, what gamemode would you like?\n");
        for (GameModes gm : GameModes.values()) {
            print(gm.toString() + " (" + gm.description() + ")\n");
        }
    }

    @Override
    public void gameIsOver(Board board) {
        if (board.isGameOver()) {
            print(board.getWinner() == null ? "Draw!" : board.getWinner() + " has won the game! \n");
        }
    }

    @Override
    public String userPickGameMode() {
        String input = null;
        try {
            input = reader.readLine();
            for (GameModes gm : GameModes.values()) {
                if (input.equals(gm.toString().toLowerCase()) || input.equals(gm.toString())) {
                    return input.toLowerCase();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        print("Invalid gamemode, please pick again! \n");
        return userPickGameMode();
    }
}
