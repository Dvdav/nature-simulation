package dvdav.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ImageViewBuilder {
    private final Image wolf;
    private final Image rabbit;

    public ImageViewBuilder(
            @Value("classpath:wolf.png") Resource wolf,
            @Value("classpath:rabbit.png") Resource rabbit,
            @Value("${simulation.cell.size}") int cellSize
    ) throws IOException {
        this.rabbit = new Image(rabbit.getInputStream(), cellSize, cellSize, false, true);
        this.wolf = new Image(wolf.getInputStream(), cellSize, cellSize, false, true);
    }

    public ImageView build(Animal animal) {
        return switch (animal) {
            case WOLF -> new ImageView(wolf);
            case RABBIT -> new ImageView(rabbit);
        };
    }

    public enum Animal {
        WOLF, RABBIT
    }
}
