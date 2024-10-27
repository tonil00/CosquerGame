package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Renderer {
    private GamePanel gamePanel;

    public Renderer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void render(Graphics2D g2d) {
        // Save the original transform
        AffineTransform originalTransform = g2d.getTransform();

        // Set rendering hints for pixelated scaling
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        double zoom = gamePanel.getCamera().getZoom();

        // Apply scaling and translation for the game world
        g2d.scale(zoom, zoom);
        g2d.translate(-gamePanel.getCamera().getX(), -gamePanel.getCamera().getY());

        // Draw the map
        drawMap(g2d);

        // Draw the player
        drawPlayer(g2d);

        // Draw the enemies
        drawEnemies(g2d);

        // Draw collectibles (paintings)
        drawPaintings(g2d);

        // Restore the original transform to work in screen coordinates
        g2d.setTransform(originalTransform);

        // Draw the darkness overlay
        drawDarknessOverlay(g2d, zoom);

        // Reset the paint to default
        g2d.setPaint(null);

        // Draw UI elements (not scaled)
        gamePanel.drawUI(g2d);
    }

    private void drawMap(Graphics2D g2d) {
        g2d.drawImage(gamePanel.getMapImage(), 0, 0, null);
    }

    private void drawPlayer(Graphics2D g2d) {
        gamePanel.getPlayer().draw(g2d);
    }

    private void drawEnemies(Graphics2D g2d) {
        gamePanel.getEnemyManager().draw(g2d);
    }

    private void drawPaintings(Graphics2D g2d) {
        gamePanel.getPaintingManager().draw(g2d);
    }

    private void drawDarknessOverlay(Graphics2D g2d, double zoom) {
        // Calculate player's position on screen
        int playerScreenX = (int) ((gamePanel.getPlayer().getX() - gamePanel.getCamera().getX())
                * zoom + 32);
        int playerScreenY = (int) ((gamePanel.getPlayer().getY() - gamePanel.getCamera().getY())
                * zoom + 32);

        // Define block size for pixelation effect
        int blockSize = 8; // Adjust block size to match the scale of your pixel art

        // Loop through the screen in blocks
        for (int y = 0; y < gamePanel.getHeight(); y += blockSize) {
            for (int x = 0; x < gamePanel.getWidth(); x += blockSize) {
                // Calculate the distance from the player to the current block center
                int blockCenterX = x + blockSize / 2;
                int blockCenterY = y + blockSize / 2;
                double distanceToPlayer = Math.hypot(playerScreenX - blockCenterX,
                        playerScreenY - blockCenterY);

                // Define the maximum distance for light to fade out
                int maxDistance = 450;

                // Calculate the alpha value based on the distance
                float alpha = (float) Math.min(1.0, distanceToPlayer / maxDistance);
                alpha = Math.max(0.2f, alpha); // Ensure some minimum visibility for aesthetics

                // Set the color with the calculated alpha
                g2d.setColor(new Color(0, 0, 0.1f, alpha - 0.001f));

                // Draw the block
                g2d.fillRect(x, y, blockSize, blockSize);
            }
        }
    }
}
