import java.io.*;

public class ConsoleGame implements GameType {

    private final Writer writer;
    private InputStream stream;
    private BufferedReader reader;

    public ConsoleGame(Writer writer, InputStream stream) {
        this.writer = writer;
        this.stream = stream;
        reader = new BufferedReader(new InputStreamReader(stream));
    }

    @Override
    public void drawBoard(Board board) {
        print("|" + board.getState().substring(0, 3) + "|\n|" + board.getState().substring(3, 6) + "|\n|" + board.getState().substring(6, 9) + "|\n");
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
    public void drawNewGame(Game game) {
        print(game.gameMode.toUpperCase() + " game is starting..\n");
        print("Where would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)\n");
    }

    @Override
    public void invalidMove() {
        print("That is not a valid position!\n");
    }

    @Override
    public void displayAllGameModes() {
        print("Hello, what gamemode would you like?\n");
        for (Gamemodes gm : Gamemodes.values()) {
            print(gm.toString() + " (" + gm.description() + ")\n");
        }
    }

    @Override
    public void gameIsOver(Board board, Player winner) {
        if (!board.checkForWin('x') && !board.checkForWin('o')) {
            print("Draw!\n");
            return;
        }
        print("\n" + winner.getMark() + " has won the game!\n");
    }

    @Override
    public String userPickGameMode() {
        String input = null;
        try {
            input = reader.readLine();
            for (Gamemodes gm : Gamemodes.values()) {
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
