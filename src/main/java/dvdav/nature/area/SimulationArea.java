package dvdav.nature.area;

import dvdav.math.Coordinates;
import dvdav.math.NearbyElements;
import dvdav.math.Table;
import dvdav.nature.animal.Animal;
import dvdav.nature.animal.AnimalDiedException;
import dvdav.nature.animal.Rabbit;
import dvdav.nature.animal.Wolf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

@Component
public class SimulationArea {

    private static final Logger LOG = LoggerFactory.getLogger(SimulationArea.class);

    private final int rows;
    private final int columns;
    private final Table<Cell> map;
    private final Set<Animal> processedAnimals;

    public SimulationArea(
            @Value("${simulation.cell.rows}") int rows,
            @Value("${simulation.cell.columns}") int columns
    ) {
        this.rows = rows;
        this.columns = columns;
        this.map = new Table<>(columns, rows);
        processedAnimals = new HashSet<>();

        addCells();
    }

    private void addCells() {
        for (Coordinates coordinates : map) {
            map.add(coordinates, new Cell(coordinates));
        }
    }

    public void populateAnimals() {
        Random random = new Random();
        for (Coordinates coordinates : map) {
            double chance = random.nextDouble();
            Animal animal;
            if (chance >= 0 && chance < 0.2) {
                animal = new Wolf();
            } else if (chance >= 0.2 && chance < 0.4) {
                animal = new Rabbit();
            } else {
                continue;
            }

            Cell cell = getCell(coordinates);
            cell.populate(animal);
        }
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
        processedAnimals.clear();

        for (Coordinates coordinates : map) {
            processCell(coordinates);
        }
    }

    private void processCell(Coordinates coordinates) {
        Cell cell = getCell(coordinates);
        if (cell.isPopulated() && !processedAnimals.contains(cell.getPopulation())) {
            processAnimal(coordinates, cell);
        }
    }

    private void processAnimal(Coordinates coordinates, Cell cell) {
        try {
            NearbyElements<Cell> cells = map.getNearbyElements(coordinates);

            Animal animal = cell.evict();
            Movement movement = animal.act(cells);

            Coordinates newCoordinates = coordinates.move(movement);
            Cell newCell = getCell(newCoordinates);

            LOG.info(animal.toString() + ": " + coordinates + "->" + newCoordinates);

            newCell.populate(animal);
            processedAnimals.add(animal);
        } catch (AnimalDiedException ignored) {
        }
    }

    public Iterator<Coordinates> iterator() {
        return map.iterator();
    }
}
