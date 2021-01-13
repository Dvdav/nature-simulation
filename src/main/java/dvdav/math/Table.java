package dvdav.math;

import dvdav.nature.area.Movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Table<T> implements Iterable<Coordinates> {

    private final List<List<T>> table;
    private final int columnCount;
    private final int rowCount;

    public Table(int columnCount, int rowCount) {
        this.columnCount = columnCount;
        this.rowCount = rowCount;


        List<List<T>> rows = new ArrayList<>(rowCount);

        for (int i = 0; i < rowCount; i++) {
            rows.add(new ArrayList<>(columnCount));
        }

        table = Collections.unmodifiableList(rows);
    }

    public void add(Coordinates coordinates, T element) {
        table.get(coordinates.getY()).add(coordinates.getX(), element);
    }

    public T get(Coordinates coordinates) {
        return table.get(coordinates.getY()).get(coordinates.getX());
    }

    private T getNullable(Coordinates coordinates) {
        try {
            return get(coordinates);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public NearbyElements<T> getNearbyElements(Coordinates coordinates) {
        T onLeft = getNullable(coordinates.move(Movement.LEFT));
        T onRight = getNullable(coordinates.move(Movement.RIGHT));
        T onTop = getNullable(coordinates.move(Movement.UP));
        T onBottom = getNullable(coordinates.move(Movement.DOWN));

        return new NearbyElements<>(onLeft, onRight, onTop, onBottom);
    }

    public Iterator<Coordinates> iterator() {
        return new CoordinatesIterator(rowCount, columnCount);
    }
}
