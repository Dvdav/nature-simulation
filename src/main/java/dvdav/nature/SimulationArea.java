package dvdav.nature;

import dvdav.math.Coordinates;
import dvdav.math.CoordinatesIterator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimulationArea {

    private final int rows;
    private final int columns;
    private final Cell[][] cells;

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
    }

    public void populateAnimals() {
        Wolf wolf = new Wolf();

        Coordinates coordinates = Coordinates.of(0, 0);
        Cell cell = getCell(coordinates);
        cell.populate(wolf);
    }

    public Cell getCell(Coordinates coordinates) {
        try {
            return cells[coordinates.getY()][coordinates.getX()];
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

    public void rollDay() {
        clearProcessedFlag();
        CoordinatesIterator it = new CoordinatesIterator(rows, columns);
        while (it.hasNext()) {
            Coordinates coordinates = it.next();

            Cell cell = getCell(coordinates);
            if (cell.isPopulated() && !cell.isProcessed()) {
                Animal animal = cell.evict();
                Movement movement = animal.makeMove();

                Coordinates newCoordinates = coordinates.move(movement);
                Cell newCell = getCell(newCoordinates);

                newCell.populate(animal);
                newCell.setProcessed(true);
            }
        }
    }

    private void clearProcessedFlag() {
        CoordinatesIterator it = new CoordinatesIterator(rows, columns);
        while (it.hasNext()) {
            Coordinates coordinates = it.next();
            Cell cell = getCell(coordinates);
            cell.setProcessed(false);
        }
    }
}
