public class TTT {
    private String board;
    private Character lastTurn;
    private String[] winningFormations = {"xxx"};
    private int move;

    public Boolean isTurn(Character ply) {
        return !(lastTurn == ply);
    }

    public String foundWinner(Character winner) {
       return String.format("%s won in %s moves!", winner, this.move);
    }

    public Boolean checkWinner(Character ply) {
        if (board.charAt(0) == ply && board.charAt(1) == ply && board.charAt(2) == ply) {
           foundWinner(ply);
            return true;
        } else if (board.charAt(3) == ply && board.charAt(4) == ply && board.charAt(5) == ply) {
            foundWinner(ply);
            return true;
        } else if (board.charAt(6) == ply && board.charAt(7) == ply && board.charAt(8) == ply) {
            foundWinner(ply);
            return true;
        } else if (board.charAt(0) == ply && board.charAt(3) == ply && board.charAt(6) == ply) {
            foundWinner(ply);
            return true;
        } else if (board.charAt(4) == ply && board.charAt(7) == ply && board.charAt(3) == ply) {
            foundWinner(ply);
            return true;
        } else if (board.charAt(5) == ply && board.charAt(8) == ply && board.charAt(6) == ply) {
            foundWinner(ply);
            return true;
        } else if (board.charAt(0) == ply && board.charAt(4) == ply && board.charAt(8) == ply) {
            foundWinner(ply);
            return true;
        }
        return false;
    }

    public String createBoard() {
        this.board = "---------";
        this.lastTurn = 'o';
        this.move = 0;
        return this.board;
    }

    public String updateBoard(Character ply, int pos) {
        StringBuilder board = new StringBuilder(this.board);
        pos -= 1;
        board.setCharAt(pos, ply);
        this.board = board.toString();
        if (checkWinner(ply)) return ply + " won";
        if (this.move == 9) return "Draw";
        return this.board;
    }

    public String playerMove(Character ply, int pos) {
        if (!isTurn(ply)) return "It is not your turn " + ply + "!";
        this.move += 1;
        this.lastTurn = ply;
        updateBoard(ply, pos);
        return ply + "" + pos;
    }

}
