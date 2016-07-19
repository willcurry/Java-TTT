public class Board {
    private String state;
    private int move;

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

}
