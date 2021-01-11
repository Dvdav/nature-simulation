package dvdav;

import dvdav.javafx.SpringFxmlLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageListener {

    private final String applicationTitle;
    private final Resource mainStageFxml;
    private final ApplicationContext applicationContext;
    private final int width;
    private final int height;

    public StageListener(
            @Value("classpath:main-stage.fxml") Resource mainStageFxml,
            @Value("${simulation.cell.rows}") int rows,
            @Value("${simulation.cell.columns}") int columns,
            @Value("${simulation.cell.size}") int cellSize,
            ApplicationContext applicationContext
    ) {
        this.width = columns * cellSize;
        this.height = rows * cellSize;
        this.applicationTitle = "Nature Simulation";
        this.mainStageFxml = mainStageFxml;
        this.applicationContext = applicationContext;
    }

    @EventListener
    public void processEvent(JavaFxApplication.StageReadyEvent event) {
        try {
            SpringFxmlLoader loader = new SpringFxmlLoader(mainStageFxml, applicationContext);

            Parent root = loader.load();
            Scene scene = new Scene(root, width, height);

            Stage stage = event.getStage();
            stage.setScene(scene);
            stage.setTitle(applicationTitle);
            stage.show();

            applicationContext.publishEvent(new SceneReadyEvent(scene));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
