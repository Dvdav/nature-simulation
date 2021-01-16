package dvdav.nature.animal;

import dvdav.math.NearbyElements;
import dvdav.nature.area.Cell;
import dvdav.nature.area.Movement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rabbit extends Animal {
    public Rabbit() {
        super(0.3);
    }

    @Override
    protected Movement eat(NearbyElements<Cell> nearbyArea) {
        satiety = 1.0;
        return Movement.STAY;
    }

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

        Random random = new Random();
        int move = random.nextInt(possibleMovements.size());

        return possibleMovements.get(move);
    }
}
