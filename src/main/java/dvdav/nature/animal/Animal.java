package dvdav.nature.animal;

import dvdav.math.NearbyElements;
import dvdav.nature.area.Cell;
import dvdav.nature.area.Movement;

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

    abstract Movement makeMove(NearbyElements<Cell> nearbyArea);
}
