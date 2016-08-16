public class HumanPlayer implements Player {

    private final char mark;
    private final GameType gameType;

    public HumanPlayer(char mark, GameType gameType) {
        this.mark = mark;
        this.gameType = gameType;
    }

    @Override
    public Integer nextMove(Board board) {
        int nextPosition = -1;
        try {
            nextPosition = Integer.parseInt(gameType.userInput());
        } catch (Exception e) {
            return nextPosition;
        }
        return nextPosition;
    }

    @Override
    public char getMark() {
        return mark;
    }
}
