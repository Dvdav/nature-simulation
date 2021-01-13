package dvdav.nature.area;

import dvdav.math.Coordinates;
import dvdav.nature.animal.Animal;

public class Cell {
    private final Coordinates coordinates;
    private Animal population;
    private boolean processed;

    Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void populate(Animal animal) {
        this.population = animal;
    }

    public boolean isPopulated() {
        return population != null;
    }

    public Animal evict() {
        Animal animal = population;
        population = null;
        return animal;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isProcessed() {
        return processed;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "coordinates=" + coordinates +
                ", population=" + population +
                ", processed=" + processed +
                '}';
    }
}
