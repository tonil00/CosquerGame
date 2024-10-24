package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import game.CosquerGame;

public class MainMenu extends JPanel {
    private final JButton startButton, exitButton, settingsButton;
    private JFrame window;
    private CosquerGame game;

    public MainMenu(JFrame window) {
        this.window = window;
        setLayout(new GridLayout(3, 1));

        startButton = new JButton("Start");
        exitButton = new JButton("Exit");
        settingsButton = new JButton("Settings");

        add(startButton);
        add(settingsButton);
        add(exitButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = new CosquerGame();  // Starting the game
                window.dispose();          // Close the main menu window
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open settings window
            }
        });
    }
}
