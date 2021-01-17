package dvdav.nature.animal;

import dvdav.math.CellRandomizer;
import dvdav.math.NearbyElements;
import dvdav.nature.area.Cell;
import dvdav.nature.area.Movement;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal {

    private final double dailyHunger;
    boolean dead;
    double satiety;

    public Animal(double dailyHunger) {
        this.dailyHunger = dailyHunger;
        dead = false;
        satiety = 1.0;
    }

    public Movement act(NearbyElements<Cell> nearbyArea) {
        getHungry();
        if (satiety <= 0.5) {
            return eat(nearbyArea);
        }
        return makeMove(nearbyArea);
    }

    void getHungry() {
        satiety -= dailyHunger;
        if (satiety <= 0) {
            dead = true;
            throw new AnimalDiedException();
        }
    }

    abstract Movement eat(NearbyElements<Cell> nearbyArea);

    Movement makeMove(NearbyElements<Cell> nearbyArea) {
        List<Movement> possibleMovements = new ArrayList<>();

        Cell onLeft = nearbyArea.getOnLeft();
        Cell onRight = nearbyArea.getOnRight();
        Cell onTop = nearbyArea.getOnTop();
        Cell onBottom = nearbyArea.getOnBottom();

        possibleMovements.add(Movement.STAY);
        if (onLeft != null && !onLeft.isPopulated()) {
            possibleMovements.add(Movement.LEFT);
        }
        if (onRight != null && !onRight.isPopulated()) {
            possibleMovements.add(Movement.RIGHT);
        }
        if (onTop != null && !onTop.isPopulated()) {
            possibleMovements.add(Movement.UP);
        }
        if (onBottom != null && !onBottom.isPopulated()) {
            possibleMovements.add(Movement.DOWN);
        }

        return CellRandomizer.getRandom(possibleMovements);
    }
}
