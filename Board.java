import java.util.ArrayList;

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

     public Boolean availablePosition(int position) {
         return position >= 0 && position <= dimension && state.charAt(position) == '-';
    }

    public Board playerMakesMove(char player, int position) {
        StringBuilder currentBoard = new StringBuilder(state);
        if (availablePosition(position)) {
            currentBoard.setCharAt(position, player);
            Board newBoard = new Board(currentBoard.toString());
            if (newBoard.checkForWin(player)) {
                System.out.print(player + " has won this game! \n");
                System.out.print("New game starting... \n");
                return new Board("Game Over");
            }
            return newBoard;
        }
        return this;
    }

    public boolean checkForDraw() {
        return !state.contains("-");
    }

    public boolean checkForWin(char player) {
        for (int[] formation : win) {
            int count = 0;
            for (int i=0; i < formation.length; i++) {
               if (state.charAt(formation[i]) == player) count += 1;
               if (count == 3) return true;
           }
        }
        return checkForDraw();
    }

    public ArrayList availablePositions() {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i=0; i < dimension; i++) {
            if (state.charAt(i) == '-') {
                positions.add(i+1);
            }
        }
        return positions;
    }

}
