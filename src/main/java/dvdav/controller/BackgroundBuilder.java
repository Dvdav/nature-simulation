package dvdav.controller;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;

import static javafx.scene.layout.BackgroundPosition.DEFAULT;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static javafx.scene.layout.BackgroundSize.AUTO;

class BackgroundBuilder {
    private static final Image GRASS = new Image(Cell.class.getResourceAsStream("../../grass.png"));

    static Background build() {
        return new Background(
                new BackgroundImage(GRASS, NO_REPEAT, NO_REPEAT, DEFAULT,
                        new BackgroundSize(AUTO, AUTO, true, true, true, true))
        );
    }
}
