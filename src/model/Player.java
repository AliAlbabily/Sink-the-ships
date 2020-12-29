package model;

public class Player {

    private String playerName;
    private int numOfShots = 0;

    public Player() {
        this("No value", 0);
    }

    public Player(String playerName, int numOfShots) {
        this.playerName = playerName;
        this.numOfShots = numOfShots;
    }

    public int getNumOfShots() {
        return numOfShots;
    }

    public void updateNumOfShots() {
        numOfShots++;
    }

    @Override
    public String toString() {
        return String.format("Player's name: %s | Score: %d", playerName, numOfShots);
    }
}
