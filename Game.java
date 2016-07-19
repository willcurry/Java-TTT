public class Game {

    private char lastTurn;

    public Board playerMakesMove(char ply, int pos, Board board, int move) {
        StringBuilder currentBoard = new StringBuilder(board.getState());
        if (currentBoard.charAt(pos) == '-' && checkTurn(ply)) {
            currentBoard.setCharAt(pos, ply);
            this.lastTurn = ply;
            Board newBoard = new Board(currentBoard.toString(), ++move);
            if (checkForWin(ply, newBoard)) {
                return null;
            }
            return newBoard;
        }
        return board;
    }

    public Boolean checkTurn(char ply) {
       return !(lastTurn == ply);
    }

    public void endGame(char winner) {

    }

    public Boolean checkForWin(char ply, Board board) {
        String state = board.getState();
        if (state.charAt(0) == ply && state.charAt(1) == ply && state.charAt(2) == ply) {
            endGame(ply);
        } else if (state.charAt(3) == ply && state.charAt(4) == ply && state.charAt(5) == ply) {
            endGame(ply);
            return true;
        } else if (state.charAt(6) == ply && state.charAt(7) == ply && state.charAt(8) == ply) {
            endGame(ply);
            return true;
        } else if (state.charAt(0) == ply && state.charAt(3) == ply && state.charAt(6) == ply) {
            endGame(ply);
            return true;
        } else if (state.charAt(1) == ply && state.charAt(4) == ply && state.charAt(7) == ply) {
            endGame(ply);
            return true;
        } else if (state.charAt(2) == ply && state.charAt(5) == ply && state.charAt(8) == ply) {
            endGame(ply);
            return true;
        } else if (state.charAt(0) == ply && state.charAt(4) == ply && state.charAt(8) == ply) {
            endGame(ply);
            return true;
        } if (board.getMove() == 9) {
            endGame('d');
            return true;
        }
        return false;
    }

}
