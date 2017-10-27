package chris.activity;

class Point {

    private int x, y;

    Point(int X, int Y) {
        setX(X);
        setY(Y);
    }

    int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    String get_representation() {
        return String.format("%d, %d", this.x, this.y);
    }

    Point get_symetry() {
        return new Point(this.x, -this.y);
    }
}
