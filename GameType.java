public interface GameType {
    void drawBoard(Board board);
    void drawTurn(Player player);
    void drawNewGame(String gameMode);
    void invalidMove();
    void displayAllGameModes();
    void gameIsOver(Board board);
    String userPickGameMode();
    String userInput();
    int userPickBoardSize();
}
