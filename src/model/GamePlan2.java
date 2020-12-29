package model;

public class GamePlan2 extends Board {

    @Override
    public void createGamePlan() {
        String[][] gameplan2 = {
            {"Ubåt", null, null, null, null, null, null, null, "Torp", "Torp"},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, "Crui", "Crui", "Crui", "Crui", null, null},
            {null, null, null, null, null, null, null, null, null, "Batt"},
            {null, null, null, null, null, null, null, null, null, "Batt"},
            {"Jaga", null, null, null, null, null, null, null, null, "Batt"},
            {"Jaga", null, null, null, null, null, null, null, null, "Batt"},
            {"Jaga", null, null, null, null, null, null, null, null, "Batt"}
        };

        setBoard(gameplan2);
    }
}
