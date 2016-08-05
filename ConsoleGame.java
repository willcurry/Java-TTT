public class ConsoleGame {
    private final Game game;

    public ConsoleGame(Game game) {
        this.game = game;
    }

    public String drawBoard(Board board) {
        print("|" + board.getState().substring(0, 3) + "|\n|" + board.getState().substring(3, 6) + "|\n|" + board.getState().substring(6, 9) + "|");
        return String.format("|" + board.getState().substring(0, 3) + "|\n|" + board.getState().substring(3, 6) + "|\n|" + board.getState().substring(6, 9) + "|");
    }

    public void drawTurn() {
        print("-----------------");
        print("Its your turn " + game.playerActive().getMark() + "!");
        print("-----------------");
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void drawNewGame() {
        print(game.gameMode.toUpperCase() + " game is starting..");
        print("Where would you like to go? (1, 2, 3, 4, 5, 6, 7, 8, 9)");
    }

    public void invalidMove(int position) {
        print(position + " is not a valid position!");
    }

    public void displayAllGamemodes() {
        print("Hello, what gamemode would you like?");
        for (Gamemodes gm : Gamemodes.values()) {
            print(gm.toString());
        }
    }

    public void invalidGamemode() {
        print("You did not pick a valid gamemode.");
    }
}
