import java.util.ArrayList;

public class UnbeatableComputerPlayer implements Player {

    private final char mark;

    public UnbeatableComputerPlayer(char mark) {
        this.mark = mark;
    }

    @Override
    public Integer nextMove(Board board) {
        return bestMove(board);
    }

    private Integer bestMove(Board board) {
        int maximumDepth = 8;
        return minimax(maximumDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, board, mark).move();
    }

    @Override
    public char getMark() {
        return mark;
    }

    private ScoredMove minimax(int depth, int alpha, int beta, Board board, char player) {
        ScoredMove bestMove = resetBestScore(player);
        if (board.isGameOver() || depth == 0) {
            return new ScoredMove(score(board, depth), bestMove.score());
        }
        ArrayList<Integer> availablePositions = board.availablePositions();
        for (int move : availablePositions) {
            Board newBoard = board.playerMakesMove(player, move - 1);
            ScoredMove score = minimax(depth -1, alpha, beta, newBoard, player == 'x' ? 'o' : 'x');
            bestMove = updateScore(player, bestMove, move, score);
            if (player == mark) {
                alpha = Math.max(alpha, bestMove.score);
            } else {
                beta = Math.min(beta, bestMove.score);
            }
        }
        return bestMove;
    }

    private ScoredMove updateScore(char player, ScoredMove currentBestMove, int position, ScoredMove score) {
        if (score.isBetterThan(currentBestMove, player)) {
            currentBestMove = new ScoredMove(score.score(), position);
        }
        return currentBestMove;
    }

    private int score(Board board, int remainingMovesCount) {
        if (board.checkForWin(mark)) {
            return remainingMovesCount;
        }
        if (board.checkForDraw()){
            return 0;
        }
        return -remainingMovesCount;
    }

    private ScoredMove resetBestScore(char player) {
        int bestMovePlaceholder = -1;
        if (player == mark) {
            return new ScoredMove(-1000, bestMovePlaceholder);
        } else {
            return new ScoredMove(1000, bestMovePlaceholder);
        }
    }

    private class ScoredMove {
        private final int score;
        private final int move;

        public ScoredMove(int score, int move) {
            this.score = score;
            this.move = move;
        }

        int score() {
            return score;
        }

        int move() {
            return move;
        }

        private boolean isBetterThan(ScoredMove scoredMove, char player) {
            return (player == mark && score > scoredMove.score) ||
                    (player != mark && score < scoredMove.score);
        }
    }
}
