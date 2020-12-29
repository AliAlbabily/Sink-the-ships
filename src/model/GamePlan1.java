package model;

public class GamePlan1 extends Board {

    @Override
    public void createGamePlan() {
        String[][] gameplan1 = {
            {"Ubåt", null, null, null, null, null, null, null, "Torp", "Torp"},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, "Crui", "Crui", "Crui", "Crui", null, null, null, null},
            {null, null, null, null, null, null, null, null, "Batt", null},
            {null, null, null, null, null, null, null, null, "Batt", null},
            {"Jaga", null, null, null, null, null, null, null, "Batt", null},
            {"Jaga", null, null, null, null, null, null, null, "Batt", null},
            {"Jaga", null, null, null, null, null, null, null, "Batt", null}
        };

        setBoard(gameplan1);
    }
}