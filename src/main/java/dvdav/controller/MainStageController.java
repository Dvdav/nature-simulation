package dvdav.controller;

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

    private final int rows;
    private final int columns;
    private final int cellSize;
    private final Image wolf;

    public MainStageController(
            @Value("${simulation.cell.rows}") int rows,
            @Value("${simulation.cell.columns}") int columns,
            @Value("${simulation.cell.size}") int cellSize,
            @Value("classpath:wolf.png") Resource wolf
    ) throws IOException {
        this.rows = rows;
        this.columns = columns;
        this.cellSize = cellSize;
        this.wolf = new Image(wolf.getInputStream(), cellSize, cellSize, false, true);
    }

    public void initialize() {
        Grid grid = new Grid(columns, rows, cellSize);
        mainPane.getChildren().add(grid);

        Cell cell = grid.getCell(new Location(0, 0));
        cell.getChildren().add(new ImageView(wolf));
    }

}
