package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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
    private final double accelerationX = 0.3;
    private final double accelerationY = 0.3;
    private final double deceleration = 0.1;
    private final double maxSpeed = 4.0;
    private final double bounceReduction = 0.75;
    private final double stopThreshold = 0.1;
    private int health;
    private final boolean isAlive;
    private final long invincibilityEndTime;
    private boolean isInvincible = false;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.width = 50;
        this.height = 30;
        this.speedX = 0;
        this.speedY = 0;
        this.health = 3;
        this.isAlive = true;
        this.invincibilityEndTime = 0;
    }

    public void draw(Graphics g) {
        if (isInvincible) {
            g.setColor(new Color(173, 216, 230, 50));
            g.fillOval(x, y, width, height);
        }
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height);
    }

    public void update(boolean movingUp, boolean movingDown, boolean movingLeft, boolean movingRight, GamePanel gamePanel) {
        if (!isAlive) return;

        handleMovement(movingUp, movingDown, movingLeft, movingRight);

        // Update the player's position based on speed
        x += speedX;
        y += speedY;

        // Ensure smooth bouncing off the screen edges
        handleScreenEdgeCollisions(gamePanel);

        // Handle invincibility timeout
        if (isInvincible && System.currentTimeMillis() > invincibilityEndTime) {
            isInvincible = false;
        }
    }

    private void handleMovement(boolean movingUp, boolean movingDown, boolean movingLeft, boolean movingRight) {
        if (movingLeft) {
            speedX = Math.max(speedX - accelerationX, -maxSpeed);
        } else if (movingRight) {
            speedX = Math.min(speedX + accelerationX, maxSpeed);
        } else {
            speedX = (speedX > 0) ? Math.max(speedX - deceleration, 0) : Math.min(speedX + deceleration, 0);
        }

        if (movingUp) {
            speedY = Math.max(speedY - accelerationY, -maxSpeed);
        } else if (movingDown) {
            speedY = Math.min(speedY + accelerationY, maxSpeed);
        } else {
            speedY = (speedY > 0) ? Math.max(speedY - deceleration, 0) : Math.min(speedY + deceleration, 0);
        }
    }

    private void handleScreenEdgeCollisions(GamePanel gamePanel) {
        // Handle bouncing off the screen edges (walls around the entire map)
        if (x <= 50) {
            x = 50;
            speedX = Math.abs(speedX) * bounceReduction; // Bounce off left wall smoothly
        }
        if (y <= GamePanel.HUD_HEIGHT + 50) {
            y = GamePanel.HUD_HEIGHT + 50;
            speedY = Math.abs(speedY) * bounceReduction; // Bounce off top wall smoothly
        }
        if (x + width >= GamePanel.MAP_WIDTH - 50) {
            x = GamePanel.MAP_WIDTH - width - 50;
            speedX = -Math.abs(speedX) * bounceReduction; // Bounce off right wall smoothly
        }
        if (y + height >= GamePanel.MAP_HEIGHT - 50) {
            y = GamePanel.MAP_HEIGHT - height - 50;
            speedY = -Math.abs(speedY) * bounceReduction; // Bounce off bottom wall smoothly
        }

        // Stop bouncing if the speed gets too low
        if (Math.abs(speedX) < stopThreshold) speedX = 0;
        if (Math.abs(speedY) < stopThreshold) speedY = 0;
    }

    public void takeDamage() {
        health--;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
