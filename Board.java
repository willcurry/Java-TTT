public class Board {
    private String state;
    private int dimension;

    private final static int[][] win = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {6, 7, 8}, {0, 4, 8}};

    public Board(String state) {
        this.state = state;
        dimension = state.length();
    }

    public String getState() {
        return this.state;
    }

    public Board playerMakesMove(char ply, int pos) {
        StringBuilder currentBoard = new StringBuilder(state);
        if (currentBoard.charAt(pos) == '-') {
            currentBoard.setCharAt(pos, ply);
            Board newBoard = new Board(currentBoard.toString());
            if (newBoard.checkForWin(ply)) {
                System.out.print(ply + " won! \n");
                return new Board("----------");
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

}
