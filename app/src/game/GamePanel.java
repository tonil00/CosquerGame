package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private int screenWidth, screenHeight;
    private Player player;
    private Camera camera; // Declare the camera
    private Thread gameThread;
    private boolean running;

    public GamePanel(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.player = new Player(100, 100, 50, 30); // A big oval fish
        // this.camera = new Camera(player); // Initialize camera with the player
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        addKeyListener(this);
        setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            update();
            repaint();
            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update(); // Pass necessary arguments based on your control logic
        camera.update(); // Update the camera position
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, screenWidth, screenHeight);
        player.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setSpeedX(-5);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setSpeedX(5);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.setSpeedY(-5);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.setSpeedY(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setSpeedX(0);
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.setSpeedY(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
