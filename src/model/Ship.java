package model;

public class Ship {

    private final String shipRepresentation;
    private final String shipName;
    private int shipLifePoints;

    public Ship(String shipRepresentation, String shipName, int shipLifePoints) {
        this.shipRepresentation = shipRepresentation;
        this.shipName = shipName;
        this.shipLifePoints = shipLifePoints;
    }

    public boolean checkIfSunk() {
        boolean dead = false;

        if(shipLifePoints == 0) {
            dead = true;
        }
        return dead;
    }

    public void updateShipLifePoints() {
        shipLifePoints--;
    }

    public String returnMessageOnHit(String shipName) {
        updateShipLifePoints();

        boolean shipIsDown = checkIfSunk();

        if(shipIsDown){
            System.out.println(shipLifePoints);
            return shipName + " Hit! "+ shipName + " Sunk!";
        }

        System.out.println(shipLifePoints);
        return shipName + " Hit!";
    }

    public String getShipName() {
        return shipName;
    }

    public String getShipRepresentation() {
        return shipRepresentation;
    }
}