public interface GameType {
    void drawBoard(Board board);
    void drawTurn(Game game);
    void drawNewGame(Game game);
    void invalidMove();
    void displayAllGameModes();
    void invalidGamemode();
    void gameIsOver(Board board, Game game);
}
