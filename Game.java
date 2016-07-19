public class Game {

    private char lastTurn;

    public Board playerMakesMove(char ply, int pos, Board board, int move) {
        StringBuilder currentBoard = new StringBuilder(board.getState());
        if (currentBoard.charAt(pos) == '-' && checkTurn(ply)) {
            currentBoard.setCharAt(pos, ply);
            this.lastTurn = ply;
            Board newBoard = new Board(currentBoard.toString(), ++move);
            return newBoard;
        }
        return board;
    }

    public Boolean checkTurn(char ply) {
       return !(lastTurn == ply);
    }

    public void endGame() {

    }

    public Boolean checkForWin(char ply, Board board) {
        String state = board.getState();
        if (state.charAt(0) == ply && state.charAt(1) == ply && state.charAt(2) == ply) {
            endGame();
            return true;
        } else if (state.charAt(3) == ply && state.charAt(4) == ply && state.charAt(5) == ply) {
            endGame();
            return true;
        } else if (state.charAt(6) == ply && state.charAt(7) == ply && state.charAt(8) == ply) {
            endGame();
            return true;
        } else if (state.charAt(0) == ply && state.charAt(3) == ply && state.charAt(6) == ply) {
            endGame();
            return true;
        } else if (state.charAt(1) == ply && state.charAt(4) == ply && state.charAt(7) == ply) {
            endGame();
            return true;
        } else if (state.charAt(2) == ply && state.charAt(5) == ply && state.charAt(8) == ply) {
            endGame();
            return true;
        } else if (state.charAt(0) == ply && state.charAt(4) == ply && state.charAt(8) == ply) {
            endGame();
            return true;
        }
        return false;
    }

}
