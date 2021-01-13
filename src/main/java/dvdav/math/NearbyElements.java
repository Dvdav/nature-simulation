package dvdav.math;

public class NearbyElements<T> {

    private final T onLeft;
    private final T onRight;
    private final T onTop;
    private final T onBottom;

    public NearbyElements(T onLeft, T onRight, T onTop, T onBottom) {
        this.onLeft = onLeft;
        this.onRight = onRight;
        this.onTop = onTop;
        this.onBottom = onBottom;
    }

    public T getOnLeft() {
        return onLeft;
    }

    public T getOnRight() {
        return onRight;
    }

    public T getOnTop() {
        return onTop;
    }

    public T getOnBottom() {
        return onBottom;
    }
}
