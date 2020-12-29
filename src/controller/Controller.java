package controller;

import model.GameManager;
import model.Player;
import view.EndFrame;
import view.MainFrame;

public class Controller {

    private GameManager model;
    private MainFrame view;
    private EndFrame endFrame;

    private Player[] highScoreList = {
        new Player("Kalle", 18),
        new Player("Melisa", 18),
        new Player("Johan", 19),
        new Player("Ahmed", 20),
        new Player("Younes", 25),
        new Player("Johanna", 30),
        new Player("Ivan", 35),
        new Player("Thomas", 40),
        new Player("Merlin", 45),
        new Player("Felix", 50)
    };

    public Controller() {
        startNewGame();
    }

    private void startNewGame() {
        model = new GameManager(this, 15);
        model.chooseGamePlan();
        view = new MainFrame(this);
    }

    public void restartGameButtonPressed() {
        view.closeMainFrameWindow(); // stäng av den gamla MainFrame
        endFrame.closeEndFrameWindow(); // stäng av den gamla EndFrame

        startNewGame(); // starta om spelet
    }

    public void squareButtonPressed(int index) {
        String[][] gamePlan = model.getCurrGamePlan();
        int counter = 0;

        for (int row = 0; row < gamePlan.length; row++) {
            for (int col = 0; col < gamePlan[row].length; col++) {
                if(counter == index) {
                    model.PlayerMove(row, col); // ändra värdet i den riktiga matrisen
                    return; // avsluta loopen
                }
                else {
                    counter++;
                }
            }
        }
    }

    public void updateNumOfShots(int numOfShots) {
        view.getPanel().getSouthPanel().updateNumOfShotsGUI(numOfShots);
    }

    public void updateGameStatus(String message) {
        view.getPanel().getSouthPanel().updateGameStatusGUI(message);
    }

    public void updateTotShipsLifePoints(int shipsPointsLeft) {
        view.getPanel().getSouthPanel().updateTotShipsLifePointsGUI(shipsPointsLeft);
    }

    public void updateOnHitStatus(boolean shipIsHit) {
        view.getPanel().getNorthPanel().updateOnHitStatusGUI(shipIsHit);
    }

    public void setupEndFrame() {
        endFrame = new EndFrame(this);
    }

    public Player[] getHighScoreList() {
        return highScoreList;
    }

    public void setHighScoreList(Player[] highScoreList) {
        this.highScoreList = highScoreList;
    }

    public void updateHighScoreList(Player[] highScoreList) {
        setHighScoreList(highScoreList);

        String[] list = convertObjListToStringList(highScoreList);
        printStringList(list);

        endFrame.getEndPanel().updateHighscoreGUI(list);
    }

    private String[] convertObjListToStringList(Player[] listOfObjects) {
        String[] ItemToString = new String[10];

        for (int i = 0; i < listOfObjects.length; i++) {
            if (listOfObjects[i] != null) {
                ItemToString[i] = listOfObjects[i].toString();
            } else {
                break;
            }
        }

        return ItemToString;
    }

    private void printStringList(String[] list) {
        for(int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
        System.out.println();
    }
}