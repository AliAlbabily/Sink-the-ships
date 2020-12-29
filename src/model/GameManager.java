package model;

import controller.Controller;

import javax.swing.*;
import java.util.*;

public class GameManager {

    Scanner input = new Scanner(System.in);

    private Controller controller;

    private String[][] currGamePlan;
    private Player player = new Player();

    private Ship currShip = null;

    private Cruiser cruiser = new Cruiser();
    private Battleship battleship = new Battleship();
    private Submarine submarine = new Submarine();
    private TorpedoBoat torpedoBoat = new TorpedoBoat();
    private Jagare jagare = new Jagare();

    private int shipsPointsLeft = 0;
    private boolean gameHasEnded = false;

    public GameManager(Controller controller, int shipsPointsLeft) {
        this.controller = controller;
        this.shipsPointsLeft = shipsPointsLeft;
    }

    public String[][] getCurrGamePlan() {
        return currGamePlan;
    }

    public void PlayerMove(int row, int col) {
        player.updateNumOfShots(); // uppdaterar spelarens score efter varje drag
        controller.updateNumOfShots(player.getNumOfShots()); // uppdatera view:n via kontrollern

        if(currGamePlan[row][col] == null) {
            System.out.println("No hit");
            controller.updateGameStatus("No hit");
            currGamePlan[row][col] = "XXXX";
            controller.updateOnHitStatus(false);
        }
        else {
            shipsPointsLeft--; // uppdatera skeppens totala poäng
            controller.updateTotShipsLifePoints(shipsPointsLeft); // uppdatera view:n via kontrollern

            // dynamsik bindning
            currShip = returnCorrectShipObject(row, col);
            String message = currShip.returnMessageOnHit(currShip.getShipName());
            controller.updateGameStatus(message);
            // slutet på dynamsik bindning

            currGamePlan[row][col] = "XXXX";

            gameHasEnded = endGameMessage(shipsPointsLeft); // variablen tilldelas true när shipsPointsLeft == 0

            if(gameHasEnded) {
                Player[] tempList = controller.getHighScoreList();
                int numOfShots = player.getNumOfShots();
                int worstPointsResult = tempList[9].getNumOfShots(); // slutet på matrisen (höger sidan)

                tempList = checkIfNumOfShotsQualified(tempList, numOfShots, worstPointsResult);

                controller.setupEndFrame(); // starta/visa endFrame fönstret
                controller.updateHighScoreList(tempList); // uppdatera den orginala listan i kontrollern (måste ligga efter setupEndFrame())
            }
            controller.updateOnHitStatus(true);
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
        for (int i = listOfObjects.length-2; i >= index; i--) {
            listOfObjects[i+1] = listOfObjects[i];
        }
    }

    private Player[] checkIfNumOfShotsQualified(Player[] listOfPlayers, int numOfShots, int worstPointsResult) {
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