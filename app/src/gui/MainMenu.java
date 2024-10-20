package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainMenu extends JFrame {

    private final JButton startButton;
    private final JButton settingsButton;
    private final JButton exitButton;

    public MainMenu() {
        // Set up the frame
        setTitle("Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.DARK_GRAY);

        // Create the buttons
        startButton = new JButton("Start Game");
        settingsButton = new JButton("Settings");
        exitButton = new JButton("Exit");

        // Customize the buttons
        customizeButton(startButton);
        customizeButton(settingsButton);
        customizeButton(exitButton);

        // Add action listeners for button interactions using lambda expressions
        startButton.addActionListener(e -> {
            // Code to start the game
            System.out.println("Start Game button clicked.");
            // Transition to game screen or functionality
        });

        settingsButton.addActionListener(e -> {
            // Code to open settings menu
            System.out.println("Settings button clicked.");
            // Open settings window
        });

        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to the panel
        panel.add(Box.createVerticalGlue());
        panel.add(startButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(settingsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(exitButton);
        panel.add(Box.createVerticalGlue());

        // Add panel to the frame
        add(panel);

        setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setFocusPainted(false);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
    }

    public static void main(String[] args) {
        // Run the main menu
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}
