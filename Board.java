import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

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

    public boolean checkForDraw() {
        return !state.contains("-");
    }

    public boolean checkForWin(char player) {
        return (getWinner().equals(Character.toString(player)));
    }

    public List<String> containsOnlySame(String player, List<String> combination) {
        return combination.stream()
                .filter(p -> p.contains(player))
                .map(i -> i + 1)
                .collect(toList());
    }

    public String getWinner() {
        final String[] winner = {"No winner"};

        allWinningCombinations().forEach(combination -> {
            if (containsOnlySame("o", combination).size() == dimension) winner[0] = "o";
            else if (containsOnlySame("x", combination).size() == dimension) winner[0] = "x";
        });
        return winner[0];
    }

    public boolean hasWinner() {
        return getWinner() != "No winner";
    }


    public boolean isGameOver() {
        return hasWinner() || checkForDraw();
    }

    public ArrayList<List<String>> allWinningCombinations() {
        ArrayList<List<String>> combinations = new ArrayList<>();
        combinations.addAll(getAllRows());
        combinations.addAll(getAllColumns());
        combinations.addAll(getDigonals());
        return combinations;
    }

    public List<List<String>> getLeftDiagonal() {
        return range(0, dimension)
                .mapToObj(i -> getLeftDiagonalCells())
                .collect(toList());
    }

    public List<List<String>> getRightDiagonal() {
        return range(0, dimension)
                .mapToObj(i -> getRightDiagonalCells())
                .collect(toList());
    }

    public List<List<String>> getAllRows() {
         return range(0, dimension)
                .mapToObj(i -> getRowCells(i))
                .collect(toList());
    }

    public List<List<String>> getAllColumns() {
        return range(0, dimension)
                .mapToObj(i -> getColumnCells(i))
                .collect(toList());
    }

    private List<List<String>> getDigonals() {
        List<List<String>> diagonals = new ArrayList<>();
        diagonals.addAll(getLeftDiagonal());
        diagonals.addAll(getRightDiagonal());
        return diagonals;
    }

    public List<Integer> availablePositions() {
        return range(0, boardSize())
                .filter(p -> state.charAt(p) == '-')
                .mapToObj(i -> i + 1)
                .collect(toList());
    }

    public static Board createBoard(int size) {
        final String[] state = {""};
        range(0, size * size).forEach(i -> {
            state[0] += "-";
        });
        return new Board(state[0]);
    }

    public List<String> getRightDiagonalCells() {
        String[] test = state.split("");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(test));
        return range(0, dimension)
                .mapToObj(i -> {
                    int j = i * dimension + i;
                    return list.get(j);
                })
                .collect(toList());
    }

     public List<String> getLeftDiagonalCells() {
        String[] test = state.split("");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(test));
         ArrayList<String> cells = new ArrayList<>();
         for (int j = dimension - 1; j < boardSize() - 1; j+=dimension - 1) {
             cells.add(list.get(j));
         }
         return cells;
    }

    public List<String> getColumnCells(int index) {
        String[] test = state.split("");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(test));
        return range(0, dimension)
                .mapToObj(i -> list.get(index + i * dimension))
                .collect(toList());
    }

    public List<String> getRowCells(int index) {
        String[] test = state.split("");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(test));
        int start = index * dimension;
        int end = start + dimension;
        return range(start, end)
                .mapToObj(i -> list.get(i))
                .collect(toList());
    }
}