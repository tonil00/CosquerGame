package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

/***
 * Player class represents the player (fish) in the game.
 */
public class Player {

    private int x;
    private int y;
    private final int width;
    private final int height;
    private double speedX;
    private double speedY;
    private final double acceleration = 0.2;
    private final double deceleration = 0.1;
    private final double maxSpeed = 3.0;
    private int health;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.width = 50;
        this.height = 30;
        this.speedX = 0;
        this.speedY = 0;
        this.health = 3;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height); // Draw the player as a yellow oval
    }

    public void update(boolean movingUp, boolean movingDown, boolean movingLeft, boolean movingRight,
                       List<Obstacle> obstacles, List<WaterCurrent> currents, List<Enemy> enemies, List<Painting> paintings) {
        if (movingLeft) {
            speedX = Math.max(speedX - acceleration, -maxSpeed);
        } else if (movingRight) {
            speedX = Math.min(speedX + acceleration, maxSpeed);
        } else {
            speedX = (speedX > 0) ? Math.max(speedX - deceleration, 0) : Math.min(speedX + deceleration, 0);
        }

        if (movingUp) {
            speedY = Math.max(speedY - acceleration, -maxSpeed);
        } else if (movingDown) {
            speedY = Math.min(speedY + acceleration, maxSpeed);
        } else {
            speedY = (speedY > 0) ? Math.max(speedY - deceleration, 0) : Math.min(speedY + deceleration, 0);
        }

        int nextX = x + (int) speedX;
        int nextY = y + (int) speedY;

        // Check for collisions with obstacles
        if (!isColliding(nextX, nextY, obstacles)) {
            x = nextX;
            y = nextY;
        }

        applyCurrentEffect(currents);
        handleEnemiesAndPaintings(enemies, paintings);
    }

    private boolean isColliding(int nextX, int nextY, List<Obstacle> obstacles) {
        Rectangle hitbox = new Rectangle(nextX, nextY, width, height);
        for (Obstacle obstacle : obstacles) {
            if (hitbox.intersects(obstacle.getHitbox())) {
                return true;
            }
        }
        return false;
    }

    private void applyCurrentEffect(List<WaterCurrent> currents) {
        Rectangle hitbox = new Rectangle(x, y, width, height);
        for (WaterCurrent current : currents) {
            if (hitbox.intersects(current.getHitbox())) {
                speedX += current.getPushX();
                speedY += current.getPushY();
                return;
            }
        }
    }

    private void handleEnemiesAndPaintings(List<Enemy> enemies, List<Painting> paintings) {
        Rectangle hitbox = new Rectangle(x, y, width, height);
        for (Enemy enemy : enemies) {
            if (hitbox.intersects(enemy.getHitbox())) {
                takeDamage();
            }
        }

        for (Painting painting : paintings) {
            if (hitbox.intersects(painting.getHitbox()) && !painting.isCollected()) {
                painting.collect();
            }
        }
    }

    public void takeDamage() {
        health--;
        if (health <= 0) {
            health = 0; // Player dies
        }
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public int getHealth() {
        return health;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
