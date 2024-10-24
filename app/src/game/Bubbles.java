package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Bubbles {

    private final ArrayList<Bubble> bubbles;
    private final Random random;

    public Bubbles(int screenWidth, int screenHeight) {
        bubbles = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < 50; i++) {
            bubbles.add(new Bubble(random.nextInt(screenWidth), random.nextInt(screenHeight)));
        }
    }

    public void update() {
        for (Bubble bubble : bubbles) {
            bubble.update();
        }
    }

    public void draw(Graphics g) {
        for (Bubble bubble : bubbles) {
            bubble.draw(g);
        }
    }

    private class Bubble {
        int x, y;
        int radius;
        int speed;

        public Bubble(int x, int y) {
            this.x = x;
            this.y = y;
            this.radius = random.nextInt(5) + 5;
            this.speed = random.nextInt(2) + 1;
        }

        public void update() {
            y -= speed;
            if (y < 0) {
                y = random.nextInt(600) + 600;
            }
        }

        public void draw(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, radius, radius);
        }
    }
}
