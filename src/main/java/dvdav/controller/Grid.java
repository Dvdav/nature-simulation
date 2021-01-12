package dvdav.controller;

import dvdav.math.Coordinates;
import javafx.scene.layout.Pane;

class Grid extends Pane {

    private final int cellSize;
    private final Cell[][] cells;

    public Grid(int columns, int rows, int cellSize) {
        this.cellSize = cellSize;
        cells = new Cell[rows][columns];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                addCell(x, y);
            }
        }
    }

    /**
     * Add cell to array and to the UI.
     */
    public void addCell(int column, int row) {
        Cell cell = new Cell(new Coordinates(column, row));

        cells[row][column] = cell;

        double x = cellSize * column;
        double y = cellSize * row;

        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(cellSize);
        cell.setPrefHeight(cellSize);

        getChildren().add(cell);
    }

    public Cell getCell(Coordinates coordinates) {
        return cells[coordinates.getY()][coordinates.getX()];
    }
}
