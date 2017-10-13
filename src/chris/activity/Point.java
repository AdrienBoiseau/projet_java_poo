package hello;

public class Point {

    int x, y;

    public Point(int X, int Y) {
        setX(X);
        setY(Y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    String get_representation() {
        return String.format("%d, %d",this.x, this.y);
    }

    Point get_symetry() {
        return new Point(this.x, -this.y);
    }
}
