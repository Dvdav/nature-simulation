package dvdav.nature.animal;

import dvdav.math.NearbyElements;
import dvdav.nature.area.Cell;
import dvdav.nature.area.Movement;

public class Wolf extends Animal {

    public Wolf() {
        super(0.1);
    }

    @Override
    protected Movement eat(NearbyElements<Cell> nearbyArea) {
        return Movement.STAY;
    }

    Movement makeMove(NearbyElements<Cell> nearbyArea) {
        Cell onLeft = nearbyArea.getOnLeft();
        Cell onRight = nearbyArea.getOnRight();
        Cell onTop = nearbyArea.getOnTop();
        Cell onBottom = nearbyArea.getOnBottom();

        if (onLeft != null && !onLeft.isPopulated()) {
            return Movement.LEFT;
        } else if (onRight != null && !onRight.isPopulated()) {
            return Movement.RIGHT;
        } else if (onTop != null && !onTop.isPopulated()) {
            return Movement.UP;
        } else if (onBottom != null && !onBottom.isPopulated()) {
            return Movement.DOWN;
        } else {
            return Movement.STAY;
        }
    }
}
