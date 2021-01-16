package model;

public class PlayersList {

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

    public Player[] getHighScoreList() {
        return highScoreList;
    }

    public void setHighScoreList(Player[] highScoreList) {
        this.highScoreList = highScoreList;
    }

    public String[] convertObjListToStringList(Player[] listOfObjects) {
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

    public void printStringList(String[] list) {
        for(int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
        System.out.println();
    }
}
