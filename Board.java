public class Board {
    private String state;
    private int dimension;

    private final static int[][] win = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {2, 4, 8}};

    public Board(String state) {
        this.state = state;
        dimension = state.length();
    }

    public String getState() {
        return this.state;
    }

     public Boolean avaliablePos(int pos) {
        if (state.charAt(pos) == '-') {
            if (!(pos > 9) || !(pos < 1)) {
                return true;
            }
        }
        System.out.println("Not a valid position");
        return false;
    }

    public Board playerMakesMove(char ply, int pos) {
        StringBuilder currentBoard = new StringBuilder(state);
        if (avaliablePos(pos)) {
            currentBoard.setCharAt(pos, ply);
            Board newBoard = new Board(currentBoard.toString());
            if (newBoard.checkForWin(ply)) {
                System.out.print(ply + " has won this game! \n");
                System.out.print("New game starting... \n");
                return new Board("---------");
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
        return checkForDraw();
    }

}
