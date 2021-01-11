package dvdav.nature;

import dvdav.math.Coordinates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class SimulationArea {

    private final int rows;
    private final int columns;
    private final Cell[][] cells;
    private final Map<Animal, Coordinates> animalCoordinates;

    public SimulationArea(
            @Value("${simulation.cell.rows}") int rows,
            @Value("${simulation.cell.columns}") int columns
    ) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new Cell[rows][columns];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                cells[y][x] = new Cell(new Coordinates(x, y));
            }
        }

        animalCoordinates = new HashMap<>();
    }

    public void populateAnimals() {
        Wolf wolf = new Wolf();

        Coordinates coordinates = Coordinates.of(0, 0);
        Cell cell = getCell(coordinates);
        cell.populate(wolf);

        animalCoordinates.put(wolf, coordinates);
    }

    public Cell getCell(Coordinates coordinates) {
        try {
        return cells[coordinates.getX()][coordinates.getY()];

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(coordinates.toString());
        }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
