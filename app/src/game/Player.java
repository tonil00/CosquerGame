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
    }

    private void handleCollisions(List<Obstacle> obstacles, List<Enemy> enemies, List<Painting> paintings, GamePanel gamePanel) {
        Rectangle playerHitbox = getHitbox();

        for (Obstacle obstacle : obstacles) {
            if (playerHitbox.intersects(obstacle.getHitbox())) {
                // Calculate sliding bounce effect when hitting a wall or stone
                handleSlideOffWall(obstacle.getHitbox());
            }
        }

        for (Enemy enemy : enemies) {
            if (playerHitbox.intersects(enemy.getHitbox())) {
                // Knockback effect when touching an enemy
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

    private void handleSlideOffWall(Rectangle wallHitbox) {
        // Check for collision on the left or right side of the obstacle
        if (x + width > wallHitbox.x && x < wallHitbox.x + wallHitbox.width) {
            // Vertical collision (top or bottom)
            if (y < wallHitbox.y && speedY > 0) {
                // Bumping into the top of the wall
                y = wallHitbox.y - height;
                speedY = -Math.abs(speedY) * 0.5; // Reduce vertical speed
            } else if (y + height > wallHitbox.y + wallHitbox.height && speedY < 0) {
                // Bumping into the bottom of the wall
                y = wallHitbox.y + wallHitbox.height;
                speedY = Math.abs(speedY) * 0.5;
            }
        }

        // Horizontal collision (left or right side of the obstacle)
        if (y + height > wallHitbox.y && y < wallHitbox.y + wallHitbox.height) {
            if (x < wallHitbox.x && speedX > 0) {
                // Bumping into the left side of the wall
                x = wallHitbox.x - width;
                speedX = -Math.abs(speedX) * 0.5; // Reduce horizontal speed
            } else if (x + width > wallHitbox.x + wallHitbox.width && speedX < 0) {
                // Bumping into the right side of the wall
                x = wallHitbox.x + wallHitbox.width;
                speedX = Math.abs(speedX) * 0.5;
            }
        }
    }

    private void handleKnockbackFromEnemy(Rectangle enemyHitbox) {
        // Calculate the center of the player and enemy
        int playerCenterX = x + width / 2;
        int playerCenterY = y + height / 2;
        int enemyCenterX = enemyHitbox.x + enemyHitbox.width / 2;
        int enemyCenterY = enemyHitbox.y + enemyHitbox.height / 2;

        // Calculate the vector from the enemy's center to the player's center
        double deltaX = playerCenterX - enemyCenterX;
        double deltaY = playerCenterY - enemyCenterY;
        double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Normalize the direction and apply a stronger knockback effect
        deltaX /= magnitude;
        deltaY /= magnitude;

        // Apply the knockback effect in the opposite direction of the contact point
        speedX = deltaX * maxSpeed * 1.5; // Stronger knockback on collision
        speedY = deltaY * maxSpeed * 1.5;
    }

    private void handleScreenEdgeCollisions() {
        // Handle bouncing off the screen edges
        if (x <= 0) {
            x = 0;
            speedX = Math.abs(speedX) * 0.9; // Bounce off left edge smoothly (reduce speed slightly)
        }
        if (x + width >= 800) {
            x = 800 - width;
            speedX = -Math.abs(speedX) * 0.9; // Bounce off right edge smoothly
        }
        if (y <= hudHeight) {
            y = hudHeight;
            speedY = Math.abs(speedY) * 0.9; // Bounce off top edge smoothly
        }
        if (y + height >= 600) {
            y = 600 - height;
            speedY = -Math.abs(speedY) * 0.9; // Bounce off bottom edge smoothly
        }
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
