package game;

import java.awt.Color;
import java.awt.Graphics;

public class CrabEnemy extends Enemy {

    private int direction = 1;
    private int jumpCounter = 0;
    private final int jumpHeight = 20;
    private final int jumpDuration = 60;
    private double velocityX = 0;
    private final double maxSpeed = 2;
    private final double acceleration = 0.5;

    public CrabEnemy(int startX, int startY) {
        super(startX, startY, 30, 20);  // Using the constructor from Enemy class
    }

    @Override
    public void move() {
        // Movement logic for moving side to side
        if (x < 50) direction = 1;
        if (x > 4500) direction = -1;
        velocityX += acceleration * direction;
        velocityX = Math.max(-maxSpeed, Math.min(maxSpeed, velocityX));
        x += velocityX;

        // Jump logic
        jumpCounter++;
        if (jumpCounter % jumpDuration < jumpDuration / 2) {
            y -= jumpHeight;
        } else {
            y += jumpHeight;
        }
    }

    @Override
    public void draw(Graphics g, int cameraX, int cameraY) {
        g.setColor(Color.RED);
        g.fillRect(x - cameraX, y - cameraY, width, height);
    }
}
