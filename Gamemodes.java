public enum GameModes {
    PVP("Player vs Player"),
    PVC("Player vs Computer"),
    CVC("Computer vs Computer");

    GameModes(String description) {
        this.description = description;
    }

    private final String description;

    public String description() {
        return description;
    }
}
