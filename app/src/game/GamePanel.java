package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private final Timer timer;  
    private final Player player;  
    private final HUD hud;  

    // Track which keys are pressed
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public GamePanel() {
        this.setBackground(java.awt.Color.BLUE); 
        this.setFocusable(true);

        player = new Player(100, 100);  
        hud = new HUD(player);  
        timer = new Timer(30, this); 

        initializeListeners(); 
    }

    // Moved key listener setup here to avoid leaking 'this'
    private void initializeListeners() {
        this.addKeyListener(this); 
    }

    // Start the game loop
    public void startGame() {
        timer.start(); 
    }

    // Render the game and the HUD
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the player
        player.draw(g);

        // Draw the HUD (health, score, etc.)
        hud.render(g);
    }

    // Update the game (move the player, etc.)
    @Override
    public void actionPerformed(ActionEvent e) {
        updatePlayerMovement();  // Check and update player movement
        player.update();  // Update player position
        repaint();  // Redraw the screen
    }

    // Update player movement based on key states
    private void updatePlayerMovement() {
        int speedX = 0;
        int speedY = 0;

        if (upPressed) speedY = -5;  // Move up
        if (downPressed) speedY = 5;  // Move down
        if (leftPressed) speedX = -5;  // Move left
        if (rightPressed) speedX = 5;  // Move right

        player.setSpeed(speedX, speedY);  // Set player speed based on key input
    }

    // Handle key presses
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> upPressed = true;  // W key pressed
            case KeyEvent.VK_S -> downPressed = true;  // S key pressed
            case KeyEvent.VK_A -> leftPressed = true;  // A key pressed
            case KeyEvent.VK_D -> rightPressed = true;  // D key pressed
            case KeyEvent.VK_UP -> upPressed = true;  // Up arrow pressed
            case KeyEvent.VK_DOWN -> downPressed = true;  // Down arrow pressed
            case KeyEvent.VK_LEFT -> leftPressed = true;  // Left arrow pressed
            case KeyEvent.VK_RIGHT -> rightPressed = true;  // Right arrow pressed
        }
    }

    // Handle key releases
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> upPressed = false;  // W key released
            case KeyEvent.VK_S -> downPressed = false;  // S key released
            case KeyEvent.VK_A -> leftPressed = false;  // A key released
            case KeyEvent.VK_D -> rightPressed = false;  // D key released
            case KeyEvent.VK_UP -> upPressed = false;  // Up arrow released
            case KeyEvent.VK_DOWN -> downPressed = false;  // Down arrow released
            case KeyEvent.VK_LEFT -> leftPressed = false;  // Left arrow released
            case KeyEvent.VK_RIGHT -> rightPressed = false;  // Right arrow released
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}
