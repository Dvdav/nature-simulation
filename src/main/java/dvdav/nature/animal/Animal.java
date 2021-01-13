package dvdav.nature.animal;

import dvdav.math.NearbyElements;
import dvdav.nature.area.Cell;
import dvdav.nature.area.Movement;

public class Animal {
    public Movement makeMove(NearbyElements<Cell> nearbyArea) {
        if (nearbyArea.getOnLeft() != null) {
            return Movement.LEFT;
        } else if (nearbyArea.getOnRight() != null) {
            return Movement.RIGHT;
        } else if (nearbyArea.getOnTop() != null) {
            return Movement.UP;
        } else if (nearbyArea.getOnBottom() != null) {
            return Movement.DOWN;
        }

        throw new IllegalArgumentException("Can't make move");
    }
}
