import java.util.ArrayList;

public class Board {
    private final String state;
    public final int dimension;

    public Board(String state) {
        this.state = state;
        dimension = state.length() / (int) Math.sqrt(state.length());
    }

    public int boardSize() {
        return dimension * dimension;
    }

    public String getState() {
        return this.state;
    }

     public Boolean availablePosition(int position) {
         return validPositionOnBoard(position) && isEmpty(position);
    }

    private boolean isEmpty(int position) {
        return state.charAt(position) == '-';
    }

    private boolean validPositionOnBoard(int position) {
        return position >= 0 && position <= boardSize();
    }

    public Board playerMakesMove(char player, int position) {
        if (isGameOver()) return this;
        StringBuilder currentBoard = new StringBuilder(state);
        if (availablePosition(position)) {
            currentBoard.setCharAt(position, player);
            Board newBoard = new Board(currentBoard.toString());
            return newBoard;
        }
        return this;
    }

    public String getWinner() {
        for (String combination : allWinningCombinations()) {
            if (containsOnlySame('x', combination)) {
                return "x";
            }
            if (containsOnlySame('o', combination)) {
                return "o";
            }
        }
        return null;
    }

    public boolean hasWinner() {
        return getWinner() != null;
    }


    public boolean isGameOver() {
        return hasWinner() || checkForDraw();
    }

    public ArrayList<String> allWinningCombinations() {
        ArrayList<String> combinations = new ArrayList<>();
        combinations.addAll(getAllRows());
        combinations.addAll(getAllColumns());
        combinations.addAll(getAllDiagonals());
        return combinations;
    }

    public ArrayList<String> getAllRows() {
        ArrayList<String> rows = new ArrayList<>();
        for (int i=0; i < boardSize(); i+=dimension) {
            String row = "";
            for (int j = i; j < i + dimension; j++) {
                row += state.charAt(j);
            }
            rows.add(row);
        }
        return rows;
    }

    public ArrayList<String> getAllDiagonals() {
        ArrayList<String> diagonals = new ArrayList<>();
        String diagonal = "";
        for (int j = 0; j < boardSize() + 1; j+=dimension + 1) {
            diagonal += state.charAt(j);
        }
        diagonals.add(diagonal);

        diagonal = "";
        for (int j = dimension - 1; j < boardSize() - 1; j+=dimension - 1) {
            diagonal += state.charAt(j);
        }
        diagonals.add(diagonal);
        return diagonals;
    }

    public ArrayList<String> getAllColumns() {
        ArrayList<String> columns = new ArrayList<>();
        for (int i=0; i < dimension; i++) {
            String column = "";
            for (int j=i; j < i + boardSize(); j+= dimension) {
                column += state.charAt(j);
            }
            columns.add(column);
        }
        return columns;
    }

    public boolean checkRowsForWin(char player) {
        for (String row : getAllRows()) {
            if (containsOnlySame(player, row)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonalsForWin(char player) {
        for (String row : getAllDiagonals()) {
            if (containsOnlySame(player, row)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkColumnsForWin(char player) {
        for (String row : getAllColumns()) {
            if (containsOnlySame(player, row)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkForDraw() {
        return !state.contains("-");
    }

    public boolean checkForWin(char player) {
        return checkRowsForWin(player) || checkColumnsForWin(player) || checkDiagonalsForWin(player);
    }

    public boolean containsOnlySame(char player, String row) {
        for (int i=0; i < dimension; i++) {
            if (!(row.charAt(i) == player)) return false;
        }
        return true;
    }

    public ArrayList availablePositions() {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i=0; i < boardSize(); i++) {
            if (state.charAt(i) == '-') {
                positions.add(i + 1);
            }
        }
        return positions;
    }
}
