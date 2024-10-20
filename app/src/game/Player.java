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
    private double accelerationX = 0.3; // More acceleration over time
    private double accelerationY = 0.3;
    private final double deceleration = 0.1;
    private double maxSpeed = 4.0; // Slightly faster speed
    private final double bounceReduction = 0.75; // Reduces speed on bounce (deceleration factor)
    private final double stopThreshold = 0.1; // Speed threshold to stop bouncing
    private int health;
    private boolean isAlive;
    private final int hudHeight = 50; // Height of the HUD to restrict movement

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.width = 50;
        this.height = 30;
        this.speedX = 0;
        this.speedY = 0;
        this.health = 3;
        this.isAlive = true;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height); // Draw the player as a yellow oval
    }

    public void update(boolean movingUp, boolean movingDown, boolean movingLeft, boolean movingRight,
                       List<Obstacle> obstacles, List<WaterCurrent> currents, List<Enemy> enemies, 
                       List<Painting> paintings, GamePanel gamePanel) {
        if (!isAlive) return;

        handleMovement(movingUp, movingDown, movingLeft, movingRight);
        handleCollisions(obstacles, enemies, paintings, gamePanel);

        // Apply currents if intersecting
        for (WaterCurrent current : currents) {
            if (getHitbox().intersects(current.getHitbox())) {
                x += current.getPushX();
                y += current.getPushY();
            }
        }

        // Update the player's position based on speed
        x += speedX;
        y += speedY;

        // Ensure smooth bouncing off the screen edges
        handleScreenEdgeCollisions();
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

    private void handleCollisions(List<Obstacle> obstacles, List<Enemy> enemies, List<Painting> paintings, GamePanel gamePanel) {
        Rectangle playerHitbox = getHitbox();

        for (Obstacle obstacle : obstacles) {
            if (playerHitbox.intersects(obstacle.getHitbox())) {
                handleBounceOffObstacle(obstacle.getHitbox());
            }
        }

        for (Enemy enemy : enemies) {
            if (playerHitbox.intersects(enemy.getHitbox())) {
                handleKnockbackFromEnemy(enemy.getHitbox());
                takeDamage(); // Take damage from the enemy
                if (health <= 0) {
                    isAlive = false;
                }
            }
        }

        for (Painting painting : paintings) {
            if (!painting.isCollected() && playerHitbox.intersects(painting.getHitbox())) {
                painting.collect();
                gamePanel.increasePoints(); // Increase points when painting is collected
            }
        }
    }

    private void handleBounceOffObstacle(Rectangle wallHitbox) {
        // Check for top/bottom collision (y-axis bounce)
        if (y < wallHitbox.y && speedY > 0) {
            // Bumping into the top of the wall
            y = wallHitbox.y - height;
            speedY = -Math.abs(speedY) * bounceReduction; // Decelerate bounce
        } else if (y + height > wallHitbox.y + wallHitbox.height && speedY < 0) {
            // Bumping into the bottom of the wall
            y = wallHitbox.y + wallHitbox.height;
            speedY = Math.abs(speedY) * bounceReduction;
        }

        // Check for left/right collision (x-axis bounce)
        if (x < wallHitbox.x && speedX > 0) {
            // Bumping into the left side of the wall
            x = wallHitbox.x - width;
            speedX = -Math.abs(speedX) * bounceReduction; // Decelerate bounce
        } else if (x + width > wallHitbox.x + wallHitbox.width && speedX < 0) {
            // Bumping into the right side of the wall
            x = wallHitbox.x + wallHitbox.width;
            speedX = Math.abs(speedX) * bounceReduction;
        }

        // Stop bouncing if the speed gets too low
        if (Math.abs(speedX) < stopThreshold) speedX = 0;
        if (Math.abs(speedY) < stopThreshold) speedY = 0;
    }

    private void handleScreenEdgeCollisions() {
        // Handle bouncing off the screen edges
        if (x <= 0) {
            x = 0;
            speedX = Math.abs(speedX) * bounceReduction; // Bounce off left edge smoothly (reduce speed)
        }
        if (x + width >= 800) {
            x = 800 - width;
            speedX = -Math.abs(speedX) * bounceReduction; // Bounce off right edge smoothly
        }
        if (y <= hudHeight) {
            y = hudHeight;
            speedY = Math.abs(speedY) * bounceReduction; // Bounce off top edge smoothly
        }
        if (y + height >= 600) {
            y = 600 - height;
            speedY = -Math.abs(speedY) * bounceReduction; // Bounce off bottom edge smoothly
        }

        // Stop bouncing if the speed gets too low
        if (Math.abs(speedX) < stopThreshold) speedX = 0;
        if (Math.abs(speedY) < stopThreshold) speedY = 0;
    }

    private void handleKnockbackFromEnemy(Rectangle enemyHitbox) {
        // Calculate the vector and apply knockback from the enemy
        int playerCenterX = x + width / 2;
        int playerCenterY = y + height / 2;
        int enemyCenterX = enemyHitbox.x + enemyHitbox.width / 2;
        int enemyCenterY = enemyHitbox.y + enemyHitbox.height / 2;

        double deltaX = playerCenterX - enemyCenterX;
        double deltaY = playerCenterY - enemyCenterY;
        double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        deltaX /= magnitude;
        deltaY /= magnitude;

        // Apply the knockback effect
        speedX = deltaX * maxSpeed * 1.5;
        speedY = deltaY * maxSpeed * 1.5;
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

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}
