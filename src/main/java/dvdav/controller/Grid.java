package dvdav.controller;

import dvdav.math.Coordinates;
import dvdav.math.Table;
import dvdav.nature.animal.Animal;
import dvdav.nature.animal.Rabbit;
import dvdav.nature.animal.Wolf;
import dvdav.nature.area.SimulationArea;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Iterator;

import static dvdav.controller.ImageViewBuilder.Animal.RABBIT;
import static dvdav.controller.ImageViewBuilder.Animal.WOLF;

@Component
class Grid extends Pane {
    private final ImageViewBuilder imageViewBuilder;
    private final int cellSize;
    private final Table<Cell> table;
    private final SimulationArea simulationArea;

    @Autowired
    public Grid(
            SimulationArea simulationArea,
            ImageViewBuilder imageViewBuilder,
            @Value("${simulation.cell.size}") int cellSize
    ) {
        this.imageViewBuilder = imageViewBuilder;
        this.cellSize = cellSize;
        this.simulationArea = simulationArea;
        table = new Table<>(simulationArea.getColumns(), simulationArea.getRows());
        addCells();
    }

    public void addCells() {
        for (Coordinates coordinates : table) {
            Cell cell = new Cell(coordinates, cellSize);

            table.add(coordinates, cell);
            getChildren().add(cell);
        }
    }

    public Cell getCell(Coordinates coordinates) {
        return table.get(coordinates);
    }

    public void syncCells() {
        Iterator<Coordinates> it = simulationArea.iterator();

        while (it.hasNext()) {
            Coordinates coordinates = it.next();
            dvdav.nature.area.Cell areaCell = simulationArea.getCell(coordinates);
            Cell gridCell = getCell(coordinates);
            gridCell.getChildren().clear();

            if (areaCell.isPopulated()) {
                populateAnimal(gridCell.getChildren(), areaCell.getPopulation());
            }
        }
    }

    private void populateAnimal(ObservableList<Node> children, Animal population) {
        if (population instanceof Wolf) {
            children.add(imageViewBuilder.build(WOLF));
        } else if (population instanceof Rabbit) {
            children.add(imageViewBuilder.build(RABBIT));
        }
    }


}
