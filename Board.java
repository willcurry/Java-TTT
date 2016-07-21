import java.util.ArrayList;

public class Board {
    private String state;
    private int move;
    private char lastTurn = 'o';

    public Board(String state, int move) {
        this.state = state;
        this.move = move;
    }

    public String getState() {
        return this.state;
    }

    public int getMove() {
        return this.move;
    }


    public void endGame(char winner) {
        System.out.print(winner + " won!");
        state = "---------";
    }

    public Board playerMakesMove(char ply, int pos, int move) {
        StringBuilder currentBoard = new StringBuilder(this.state);
        if (currentBoard.charAt(pos) == '-' && checkTurn(ply)) {
            currentBoard.setCharAt(pos, ply);
            lastTurn = ply;
            Board newBoard = new Board(currentBoard.toString(), ++move);
            if (newBoard.checkForWin(ply, pos)) {
                return null;
            }
            return newBoard;
        }
        return this;
    }

    public boolean checkRowWin(char ply, int pos) {
        int[] line = {0, 1, 2};
        if (pos == 3 || pos == 4 || pos == 5) line = new int[] {3, 4, 5};
        if (pos == 6 || pos == 7 || pos == 8) line = new int[] {6, 7, 8};
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;
         for (int i=line[0]; i <= line[2]; i++) {
            if (state.charAt(i) == ply) count += 1;
            if (count==3) return true;
        }
        return false;
    }

    public boolean checkColumWin(char ply, int pos) {
        int[] line = {0, 3, 6};
        if (pos == 1 || pos == 4 || pos == 7) line = new int[] {1, 4, 7};
        if (pos == 2 || pos == 5 || pos == 8) line = new int[] {2, 5, 8};
        int count = 0;
        for (int i=line[0]; i <= line[2]; i++) {
            if (state.charAt(i) == ply) count += 1;
            if (count==3) return true;
        }
        return false;
    }

    public boolean checkForDigionalWin(char ply, int pos) {
        int[] line = {0, 4, 8};
        int count = 0;
        if (pos == 0 || pos == 4 || pos == 8) {
            for (int i=line[0]; i <= line[2]; i++) {
                if (state.charAt(i) == ply) count += 1;
                if (count==3) return true;
            }
        }
        return false;
    }

    public boolean checkForWin(char ply, int pos) {
        return checkRowWin(ply, pos) || checkColumWin(ply, pos) || checkForDigionalWin(ply, pos);
    }

    public Boolean checkTurn(char ply) {
       return !(lastTurn == ply);
    }

}
