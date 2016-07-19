public class Game {

    private char lastTurn;

    public Board playerMakesMove(char ply, int pos, Board board, int move) {
        StringBuilder currentBoard = new StringBuilder(board.getState());
        if (currentBoard.charAt(pos) == '-' && checkTurn(ply)) {
            currentBoard.setCharAt(pos, ply);
            this.lastTurn = ply;
            Board newBoard = new Board(currentBoard.toString(), ++move);
            if (newBoard.checkForWin(ply)) {
                return null;
            }
            return newBoard;
        }
        return board;
    }

    public Boolean checkTurn(char ply) {
       return !(lastTurn == ply);
    }



}
