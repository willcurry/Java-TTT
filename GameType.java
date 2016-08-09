public interface GameType {
    void drawBoard(Board board);
    void drawTurn(Player player);
    void drawNewGame(Game game);
    void invalidMove();
    void displayAllGameModes();
    void invalidGamemode();
    void gameIsOver(Board board, Player winner);
}
