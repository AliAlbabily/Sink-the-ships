package model;

import javax.swing.*;
import java.util.*;

public class GameManager {

    Scanner input = new Scanner(System.in);

    private String[][] currGamePlan;
    private Player player = new Player();
    private PlayersList playersList;

    private Ship currShip = null;

    private Cruiser cruiser = new Cruiser();
    private Battleship battleship = new Battleship();
    private Submarine submarine = new Submarine();
    private TorpedoBoat torpedoBoat = new TorpedoBoat();
    private Jagare jagare = new Jagare();

    private String currentShotStatus;
    private boolean hitStatus;
    private int shipsPointsLeft = 0;
    private boolean gameHasEnded = false;

    public GameManager(PlayersList playersList, int shipsPointsLeft) {
        this.shipsPointsLeft = shipsPointsLeft;
        this.playersList = playersList;
    }

    // TODO : getters and setters
    public String[][] getCurrGamePlan() {
        return currGamePlan;
    }

    public Player getPlayer() {
        return player;
    }

    public String getCurrentShotStatus() {
        return currentShotStatus;
    }

    public boolean isHitStatus() {
        return hitStatus;
    }

    public int getShipsPointsLeft() {
        return shipsPointsLeft;
    }

    public boolean isGameHasEnded() {
        return gameHasEnded;
    }

    // slutet på getters and setters

    public void PlayerMove(int row, int col) {
        player.updateNumOfShots(); // uppdaterar spelarens score efter varje drag

        if(currGamePlan[row][col] == null) {
            System.out.println("No hit");
            currentShotStatus = "No hit";
            currGamePlan[row][col] = "XXXX";
            hitStatus = false;
        }
        else {
            shipsPointsLeft--; // uppdatera skeppens totala poäng

            // dynamsik bindning
            currShip = returnCorrectShipObject(row, col);
            currentShotStatus = currShip.returnMessageOnHit(currShip.getShipName());
            // slutet på dynamsik bindning

            currGamePlan[row][col] = "XXXX";
            gameHasEnded = endGameMessage(shipsPointsLeft); // variablen tilldelas true när shipsPointsLeft == 0
            hitStatus = true;
        }
        printGamePlan();
    }

    public void printGamePlan() {
        for (int row = 0; row < currGamePlan.length; row++) {
            for (int col = 0; col < currGamePlan[row].length; col++) {
                System.out.print(currGamePlan[row][col] + "\t");
            }
            System.out.println();
        }
    }

    public void chooseGamePlan() {
        System.out.println("Choose a gameplan, (1 or 2)");

        while(!input.hasNextInt()) {
            System.out.println("Invalid value! Choose 1 or 2");
            input.next();
        }

        int gameplanChosen = input.nextInt();

        switch (gameplanChosen) {
            case 1:
                System.out.println("Gameplan 1");
                GamePlan1 gamePlan1 = new GamePlan1();
                gamePlan1.createGamePlan();
                currGamePlan = gamePlan1.getBoard();
                printGamePlan();
                break;
            case 2:
                System.out.println("Gameplan 2");
                GamePlan2 gamePlan2 = new GamePlan2();
                gamePlan2.createGamePlan();
                currGamePlan = gamePlan2.getBoard();
                printGamePlan();
                break;
            default:
                System.out.println("Restart the program and choose either 1 or 2");
                System.exit(0); // stäng av programmet
                break;
        }

    }

    private boolean endGameMessage(int shipsPointsLeft) {
        boolean gameHasEnded = false;

        if(shipsPointsLeft == 0) {
            System.out.println("You have won!");
            return true;

        } else
            return gameHasEnded;
    }

    private void moveElementsToRight(int index, Player[] listOfObjects) {
        for (int i = listOfObjects.length-2; i >= index; i--) { // f.v: vi börjar på index 8 i listan , a.v: så länge 8 är större/lika med indexet som man hamnar i listan
            listOfObjects[i+1] = listOfObjects[i];
        }
    }

    public Player[] checkIfNumOfShotsQualified(Player[] listOfPlayers, int numOfShots, int worstPointsResult) {
        if(numOfShots < worstPointsResult) { // kolla om numOfShots kvalar in i HighScore-listan
            for(int i = 0; i < listOfPlayers.length; i++) {
                int points = listOfPlayers[i].getNumOfShots(); // nuvarande rad poäng

                if(numOfShots < points) {
                    moveElementsToRight(i, listOfPlayers);

                    String playerName = JOptionPane.showInputDialog(null, "Enter your name: "); // fråga efter användarens namn
                    listOfPlayers[i] = new Player(playerName, numOfShots);
                    break;
                }
            }
        }

        return listOfPlayers;
    }

    private Ship returnCorrectShipObject(int row, int col) {
        if(currGamePlan[row][col] == cruiser.getShipRepresentation()) { // "Crui"
            currShip = cruiser;
        }
        else if(currGamePlan[row][col] == battleship.getShipRepresentation()) { // "Batt"
            currShip = battleship;
        }
        else if(currGamePlan[row][col] == jagare.getShipRepresentation()) { // "Jaga"
            currShip = jagare;
        }
        else if(currGamePlan[row][col] == torpedoBoat.getShipRepresentation()) { // "Torp"
            currShip = torpedoBoat;
        }
        else if(currGamePlan[row][col] == submarine.getShipRepresentation()) { // "Ubåt"
            currShip = submarine;
        }

        return currShip;
    }
}