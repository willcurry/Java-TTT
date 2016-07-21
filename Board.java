public class Board {
    private String state;
    private char lastTurn = 'o';
    private int width;

    static int[][] win = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {6, 7, 8}, {0, 4, 8}};

    public Board(String state) {
        this.state = state;
        width = state.length();
    }

    public String getState() {
        return this.state;
    }

    public void endGame(char winner) {
        System.out.print(winner + " won!");
        state = "---------";
    }

    public Board playerMakesMove(char ply, int pos) {
        StringBuilder currentBoard = new StringBuilder(state);
        if (currentBoard.charAt(pos) == '-' && checkTurn(ply)) {
            currentBoard.setCharAt(pos, ply);
            lastTurn = ply;
            Board newBoard = new Board(currentBoard.toString());
            if (newBoard.checkForWin(ply)) {
                return this;
            }
            return newBoard;
        }
        return this;
    }

    public boolean checkForDraw() {
        return !state.contains("-");
    }

    public boolean checkForWin(char ply) {
        for (int[] formation : win) {
            int count = 0;
           for (int i=0; i < formation.length; i++) {
               if (state.charAt(formation[i]) == ply) count += 1;
               if (count == 3) return true;
           }
        }
        return false || checkForDraw();
    }

    public Boolean checkTurn(char ply) {
       return !(lastTurn == ply);
    }

}
