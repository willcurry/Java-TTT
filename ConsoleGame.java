import java.io.*;

public class ConsoleGame implements GameType {

    public ConsoleGame() {
    }

    @Override
    public String drawBoard(Board board) {
        print("|" + board.getState().substring(0, 3) + "|\n|" + board.getState().substring(3, 6) + "|\n|" + board.getState().substring(6, 9) + "|\n");
        return String.format("|" + board.getState().substring(0, 3) + "|\n|" + board.getState().substring(3, 6) + "|\n|" + board.getState().substring(6, 9) + "|\n");
    }

    @Override
    public void drawTurn(Game game) {
        print("----------------- \n");
        print("Its your turn " + game.playerActive().getMark() + "!\n");
        print("----------------- \n");
    }

    public void print(String text) {
        byte[] stringByte = text.getBytes();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(text.length());
        try {
            bos.write(stringByte);
            System.out.write(stringByte);
        } catch (IOException e) {
            e.printStackTrace();
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
    public void invalidGamemode() {
        print("You did not pick a valid gamemode.\n");
    }

    @Override
    public void gameIsOver(Board board, Game game) {
        if (!board.checkForWin('x') && !board.checkForWin('o')) {
            print("Draw!\n");
            return;
        }
        print("\n" + game.playerDeactive().getMark() + " has won the game!\n");
    }
}
