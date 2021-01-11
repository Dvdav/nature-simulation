package dvdav.math;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class CoordinatesIterator implements Iterator<Coordinates> {

    private final int rows;
    private final int columns;

    private int currentX;
    private int currentY;

    public CoordinatesIterator(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        currentY = 0;
        currentX = 0;
    }

    @Override
    public boolean hasNext() {
        return !(currentX == rows - 1 && currentY == columns - 1);
    }

    @Override
    public Coordinates next() {
        if (currentX == rows) {
            currentX = 0;
            currentY++;
        }

        return new Coordinates(currentX++, currentY);
    }
}
