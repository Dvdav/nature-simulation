package dvdav.controller;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

class Cell extends Region {

    private final int column;
    private final int row;

    public Cell(int column, int row) {
        this.column = column;
        this.row = row;

        setOpacity(0.9);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.1))));
        setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public String toString() {
        return this.column + "/" + this.row;
    }
}
