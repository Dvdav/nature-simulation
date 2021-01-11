package dvdav.math;

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
}
