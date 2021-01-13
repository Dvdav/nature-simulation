package dvdav.controller;

import dvdav.SceneReadyEvent;
import dvdav.math.Coordinates;
import dvdav.nature.area.SimulationArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

@Controller
public class MainStageController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        simulationArea.populateAnimals();
        syncCells();
        mainPane.getChildren().add(grid);
    }

    @EventListener
    public void processEvent(SceneReadyEvent event) {
        event.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                simulationArea.rollDay();
                syncCells();
            }
        });
    }

    private void syncCells() {
        Iterator<Coordinates> it = simulationArea.iterator();

        while (it.hasNext()) {
            Coordinates coordinates = it.next();
            dvdav.nature.area.Cell areaCell = simulationArea.getCell(coordinates);
            Cell gridCell = grid.getCell(coordinates);

            if (areaCell.isPopulated()) {
                gridCell.getChildren().add(new ImageView(wolf));
            } else {
                gridCell.getChildren().clear();
            }
        }
    }
}
