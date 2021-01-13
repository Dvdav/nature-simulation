package dvdav.controller;

import dvdav.math.Coordinates;
import dvdav.math.Table;
import javafx.scene.layout.Pane;

class Grid extends Pane {

    private final int cellSize;
    private final Table<Cell> cells;

    public Grid(int columns, int rows, int cellSize) {
        this.cellSize = cellSize;
        cells = new Table<>(columns, rows);
        addCells();
    }

    public void addCells() {
        for (Coordinates coordinates : cells) {
            Cell cell = new Cell(coordinates, cellSize);

            cells.add(coordinates, cell);
            getChildren().add(cell);
        }
    }

    public Cell getCell(Coordinates coordinates) {
        return cells.get(coordinates);
    }
}
