package model;

public abstract class Board implements IBoard {

    private String[][] board = new String[10][10];

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public abstract void createGamePlan();
}
