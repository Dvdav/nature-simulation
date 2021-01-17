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
        Cell onLeft = nearbyArea.getOnLeft();
        Cell onRight = nearbyArea.getOnRight();
        Cell onTop = nearbyArea.getOnTop();
        Cell onBottom = nearbyArea.getOnBottom();

        Rabbit rabbit;
        Movement movement;

        if (onLeft != null && onLeft.isPopulated() && onLeft.getPopulation() instanceof Rabbit) {
            movement = Movement.LEFT;
            rabbit = (Rabbit) onLeft.getPopulation();
        } else if (onRight != null && onRight.isPopulated() && onRight.getPopulation() instanceof Rabbit) {
            movement = Movement.RIGHT;
            rabbit = (Rabbit) onRight.getPopulation();
        } else if (onTop != null && onTop.isPopulated() && onTop.getPopulation() instanceof Rabbit) {
            movement = Movement.UP;
            rabbit = (Rabbit) onTop.getPopulation();
        } else if (onBottom != null && onBottom.isPopulated() && onBottom.getPopulation() instanceof Rabbit) {
            movement = Movement.DOWN;
            rabbit = (Rabbit) onBottom.getPopulation();
        } else {
            return makeMove(nearbyArea);
        }

        rabbit.kill();
        satiety = 1.0;

        return movement;
    }
}
