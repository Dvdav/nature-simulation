package dvdav.controller;

import dvdav.SceneReadyEvent;
import dvdav.nature.area.SimulationArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainStageController implements Initializable {

    @FXML
    private Pane mainPane;

    private final SimulationArea simulationArea;
    private final Grid grid;

    public MainStageController(SimulationArea simulationArea, Grid grid) {
        this.simulationArea = simulationArea;
        this.grid = grid;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        simulationArea.populateAnimals();
        grid.syncCells();
        mainPane.getChildren().add(grid);
    }

    @EventListener
    public void processEvent(SceneReadyEvent event) {
        event.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                simulationArea.rollDay();
                grid.syncCells();
            }
        });
    }
}
