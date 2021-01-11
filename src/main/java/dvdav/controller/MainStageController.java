package dvdav.controller;

import dvdav.math.Coordinates;
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
        mainPane.getChildren().add(grid);
        syncCells();
    }

    private void syncCells() {
        for (int x = 0; x < simulationArea.getRows(); x++) {
            for (int y = 0; y < simulationArea.getColumns(); y++) {
                Coordinates coordinates = Coordinates.of(x, y);
                dvdav.nature.Cell areaCell = simulationArea.getCell(coordinates);

                if (areaCell.isPopulated()) {
                    Cell gridCell = grid.getCell(coordinates);
                    gridCell.getChildren().add(new ImageView(wolf));
                }
            }
        }
    }
}
