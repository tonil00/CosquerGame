package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

    private int health;
    private int score;

    public HUD() {
        // Initial player stats
        this.health = 100;
        this.score = 0;
    }

    // Decrease health by a certain amount
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    // Increase score by a certain amount
    public void increaseScore(int points) {
        score += points;
    }

    // Draw the HUD (health and score) on the screen
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        
        // Display Health
        g.drawString("Health: " + health, 20, 40);
        
        // Display Score
        g.drawString("Score: " + score, 20, 70);
    }

    // Getter methods for health and score
    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }
}
