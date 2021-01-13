package dvdav.nature.area;

import dvdav.math.Coordinates;
import dvdav.math.NearbyElements;
import dvdav.math.Table;
import dvdav.nature.animal.Animal;
import dvdav.nature.animal.Wolf;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class SimulationArea {

    private final int rows;
    private final int columns;
    private final Table<Cell> map;

    public SimulationArea(
            @Value("${simulation.cell.rows}") int rows,
            @Value("${simulation.cell.columns}") int columns
    ) {
        this.rows = rows;
        this.columns = columns;
        this.map = new Table<>(columns, rows);

        addCells();
    }

    private void addCells() {
        for (Coordinates coordinates : map) {
            map.add(coordinates, new Cell(coordinates));
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
            return map.get(coordinates);
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

        for (Coordinates coordinates : map) {
            Cell cell = getCell(coordinates);
            if (cell.isPopulated() && !cell.isProcessed()) {
                NearbyElements<Cell> cells = map.getNearbyElements(coordinates);

                Animal animal = cell.evict();
                Movement movement = animal.makeMove(cells);

                Coordinates newCoordinates = coordinates.move(movement);
                Cell newCell = getCell(newCoordinates);

                newCell.populate(animal);
                newCell.setProcessed(true);
            }
        }
    }

    private void clearProcessedFlag() {
        for (Coordinates coordinates : map) {
            Cell cell = getCell(coordinates);
            cell.setProcessed(false);
        }
    }

    public Iterator<Coordinates> iterator() {
        return map.iterator();
    }
}
