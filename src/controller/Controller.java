package controller;

import model.GameManager;
import model.Player;
import model.PlayersList;
import view.EndFrame;
import view.MainFrame;

public class Controller {

    private GameManager model;
    private MainFrame view;
    private EndFrame endFrame;

    private final PlayersList playersList = new PlayersList();

    private boolean functionalityHasTurnedOff = false;

    public Controller() {
        startNewGame();
    }

    private void startNewGame() {
        model = new GameManager(playersList, 15);
        model.chooseGamePlan();
        view = new MainFrame(this);

        functionalityHasTurnedOff = false;
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
                    updateNumOfShots();
                    updateGameStatus();
                    updateTotShipsLifePoints();
                    updateOnHitStatus();
                    if(!functionalityHasTurnedOff) {
                        checkIfGameHasEnded();
                    }
                    return; // avsluta loopen
                }
                else {
                    counter++;
                }
            }
        }
    }

    public void updateNumOfShots() {
        int numOfShots = model.getPlayer().getNumOfShots();
        view.getPanel().getSouthPanel().updateNumOfShotsGUI(numOfShots);
    }

    public void updateGameStatus() {
        String message = model.getCurrentShotStatus();
        view.getPanel().getSouthPanel().updateGameStatusGUI(message);
    }

    public void updateTotShipsLifePoints() {
        int shipsPointsLeft = model.getShipsPointsLeft();
        view.getPanel().getSouthPanel().updateTotShipsLifePointsGUI(shipsPointsLeft);
    }

    public void updateOnHitStatus() {
        boolean onHitStatus = model.isHitStatus();
        view.getPanel().getNorthPanel().updateOnHitStatusGUI(onHitStatus);
    }

    public void setupEndFrame() {
        endFrame = new EndFrame(this);
    }

    public void updateHighScoreListGUI(Player[] highScoreList) {
        String[] list = playersList.convertObjListToStringList(highScoreList);
        playersList.printStringList(list);

        endFrame.getEndPanel().updateHighscoreGUI(list);
    }

    private void checkIfGameHasEnded() {
        boolean gameHasEnded = model.isGameHasEnded();

        if(gameHasEnded) {
            Player[] tempList = playersList.getHighScoreList();
            int numOfShots = model.getPlayer().getNumOfShots();
            int worstPointsResult = tempList[9].getNumOfShots(); // slutet på matrisen (höger sidan)

            tempList = model.checkIfNumOfShotsQualified(tempList, numOfShots, worstPointsResult);

            playersList.setHighScoreList(tempList); // uppdatera den orginala listan i PlayersList
            setupEndFrame(); // starta och visa endFrame fönstret
            updateHighScoreListGUI(tempList); // måste ligga efter setupEndFrame(), vi kan inte uppdatera ett fönster som inte finns än

            functionalityHasTurnedOff = true;
        }
    }


}