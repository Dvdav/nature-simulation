package dvdav.controller;

import dvdav.math.Coordinates;
import javafx.scene.layout.Pane;

class Grid extends Pane {

    private final int rows;
    private final int columns;

    private final double width;
    private final double height;

    private final Cell[][] cells;

    public Grid(int columns, int rows, int cellSize) {
        this.columns = columns;
        this.rows = rows;
        this.width = columns * cellSize;
        this.height = rows * cellSize;

        cells = new Cell[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell cell = new Cell(new Coordinates(column, row));
                add(cell, column, row);
            }
        }
    }

    /**
     * Add cell to array and to the UI.
     */
    public void add(Cell cell, int column, int row) {

        cells[row][column] = cell;

        double w = width / columns;
        double h = height / rows;
        double x = w * column;
        double y = h * row;

        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(w);
        cell.setPrefHeight(h);

        getChildren().add(cell);
    }

    public Cell getCell(Coordinates coordinates) {
        return cells[coordinates.getX()][coordinates.getY()];
    }
}
