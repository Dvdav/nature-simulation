package dvdav.math;

import dvdav.nature.Movement;

public class Coordinates {
    private final int x; //abscissa
    private final int y; //ordinate

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates of(int x, int y) {
        return new Coordinates(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Coordinates move(Movement movement) {
        switch (movement) {
            case UP:
                return of(x, y - 1);
            case DOWN:
                return of(x, y + 1);
            case LEFT:
                return of(x - 1, y);
            case RIGHT:
                return of(x + 1, y);
            default:
                throw new IllegalArgumentException(movement.name());
        }
    }
}
