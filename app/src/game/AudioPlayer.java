package game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class AudioPlayer {
    private Clip clip;

    /**
     * Constructor to load and play the audio file.
     *
     * @param filePath The path to the audio file.
     */
    public AudioPlayer(String filePath) {
        try {
            // Load the audio file from resources
            URL soundURL = getClass().getResource(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);

            // Get audio format and info
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // Create the clip
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
            System.out.println("Error loading audio file.");
        }
    }

    /**
     * Play the audio clip from the beginning.
     */
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start();
        }
    }

    /**
     * Loop the audio clip continuously.
     */
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Stop the audio clip.
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    /**
     * Close the audio clip and release resources.
     */
    public void close() {
        if (clip != null) {
            clip.close();
        }
    }
}
