public enum Gamemodes {
    PVP("Player vs player"),
    PVC("Player vs computer"),
    CVC("Computer vs computer");

     Gamemodes(String description) {
        this.description = description;
    }

    private final String description;

    public String description() {
        return description;
    }
}
