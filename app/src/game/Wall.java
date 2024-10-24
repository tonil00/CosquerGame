package game;

public class Wall {
    public int x, y, width, height;

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isVertical() {
        return width < height;
    }
}
