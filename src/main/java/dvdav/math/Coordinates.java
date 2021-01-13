package dvdav.math;

import dvdav.nature.area.Movement;

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
        return switch (movement) {
            case UP -> of(x, y - 1);
            case DOWN -> of(x, y + 1);
            case LEFT -> of(x - 1, y);
            case RIGHT -> of(x + 1, y);
        };
    }
}
