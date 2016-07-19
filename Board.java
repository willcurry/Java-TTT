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


    public void endGame(char winner) {
        System.out.print(winner + " won!");
        this.state = "---------";
    }

    public boolean checkForWin(char ply) {
        int count = 0;
        for (int i=0; i < 3; i++) {
               if (this.state.charAt(i) == ply) count += 1;
            if (count==3) return true;
        }
        int count2;
        for (int i=0; i < 3; i++) {
            if (this.state.charAt(i+2) == ply) count += 1;
            if (count==3) return true;
        }
        int count3;
        for (int i=0; i < 3; i++) {
            if (this.state.charAt(i+=4) == ply) count += 1;
            if (count==3) return true;
        }
        return false;
    }
}
