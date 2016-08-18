public class PlayerFactory {
    public static Player[] createPair(String gameMode, GameType gameType) {
        Player[] createdPair;
        if (gameMode.equals("pvp")) {
            createdPair = new Player[]{
                    new HumanPlayer('x', gameType),
                    new HumanPlayer('o', gameType)};
        } else if (gameMode.equals("pvc")) {
            createdPair = new Player[]{
                    new HumanPlayer('x', gameType),
                    new ComputerPlayer('o')};
        } else {
            createdPair = new Player[]{
                    new ComputerPlayer('x'),
                    new ComputerPlayer('o')};
        }
        return createdPair;
    }
}
