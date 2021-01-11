package dvdav.controller;

import dvdav.math.Coordinates;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

class Cell extends Pane {

    private static final Image GRASS = new Image(Cell.class.getResourceAsStream("../../grass.png"));

    private final Coordinates coordinates;

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;

        setOpacity(0.9);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.1))));
        setBackground(new Background(new BackgroundImage(GRASS, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true))));
    }

    public String toString() {
        return coordinates.toString();
    }
}
