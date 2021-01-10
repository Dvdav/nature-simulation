package dvdav.controller;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

class Cell extends Pane {

    private static final Image GRASS = new Image(Cell.class.getResourceAsStream("../../grass.png"));

    private final int column;
    private final int row;

    public Cell(int column, int row) {
        this.column = column;
        this.row = row;

        setOpacity(0.9);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.1))));
        setBackground(new Background(new BackgroundImage(GRASS, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true))));
    }

    public String toString() {
        return this.column + "/" + this.row;
    }
}
