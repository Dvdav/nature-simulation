package dvdav.controller;

import dvdav.math.Coordinates;
import dvdav.math.CoordinatesIterator;
import dvdav.nature.SimulationArea;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MainStageController {

    @FXML
    private Pane mainPane;

    private final SimulationArea simulationArea;
    private final Grid grid;
    private final Image wolf;

    public MainStageController(
            @Value("${simulation.cell.size}") int cellSize,
            @Value("classpath:wolf.png") Resource wolf,
            SimulationArea simulationArea
    ) throws IOException {
        this.simulationArea = simulationArea;
        this.grid = new Grid(simulationArea.getColumns(), simulationArea.getRows(), cellSize);
        this.wolf = new Image(wolf.getInputStream(), cellSize, cellSize, false, true);
    }

    public void initialize() {
        simulationArea.populateAnimals();
        syncCells();
        mainPane.getChildren().add(grid);
    }

    private void syncCells() {
        CoordinatesIterator it = new CoordinatesIterator(simulationArea.getRows(), simulationArea.getColumns());
        while (it.hasNext()) {
            Coordinates coordinates = it.next();
            dvdav.nature.Cell areaCell = simulationArea.getCell(coordinates);

            if (areaCell.isPopulated()) {
                Cell gridCell = grid.getCell(coordinates);
                gridCell.getChildren().add(new ImageView(wolf));
            }
        }
    }
}
