package dvdav.nature.animal;

import dvdav.math.NearbyElements;
import dvdav.nature.area.Cell;
import dvdav.nature.area.Movement;

public class Rabbit extends Animal {
    public Rabbit() {
        super(0.3);
    }

    @Override
    protected Movement eat(NearbyElements<Cell> nearbyArea) {
        satiety = 1.0;
        return Movement.STAY;
    }

    public void kill() {
        dead = true;
    }
}
