package dvdav.nature;

import dvdav.math.Coordinates;

public class Cell {
    private final Coordinates coordinates;
    private Animal population;

    Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void populate(Animal animal) {
        this.population = animal;
    }

    public boolean isPopulated() {
        return population != null;
    }
}
