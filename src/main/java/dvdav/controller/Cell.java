package dvdav.controller;

import dvdav.math.Coordinates;
import javafx.scene.layout.Pane;

class Cell extends Pane {

    private final Coordinates coordinates;

    public Cell(Coordinates coordinates, int cellSize) {
        this.coordinates = coordinates;

        setBorder(BorderBuilder.build());
        setBackground(BackgroundBuilder.build());

        setLayoutX(cellSize * coordinates.getX());
        setLayoutY(cellSize * coordinates.getY());
        setPrefWidth(cellSize);
        setPrefHeight(cellSize);
    }

    public String toString() {
        return coordinates.toString();
    }
}
