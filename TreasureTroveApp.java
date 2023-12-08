import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class TreasureTroveApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Treasure Trove App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Text above the buttons
        JLabel introLabel = new JLabel("Welcome to Treasure Trove App!");
        introLabel.setBounds(10, 5, 280, 15);
        panel.add(introLabel);

        // Buttons
        JButton quizButton = new JButton("Quiz");
        quizButton.setBounds(10, 30, 80, 25);
        panel.add(quizButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(100, 30, 80, 25);
        panel.add(searchButton);

        JButton textButton = new JButton("Music");
        textButton.setBounds(190, 30, 80, 25);
        panel.add(textButton);

        JButton textButton2 = new JButton("Social Media Links");
        textButton2.setBounds(280, 30, 80, 25);
        panel.add(textButton2);

        // Text below the buttons
        JLabel outroLabel = new JLabel("Explore and Enjoy!");
        outroLabel.setBounds(10, 70, 280, 15);
        panel.add(outroLabel);

        // ActionListener for Quiz button
        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to launch BookQuizApp
                SwingUtilities.invokeLater(() -> {
                    BookQuizApp.main(new String[]{});
                });
            }
        });

        // ActionListener for Search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to launch search functionality
                System.out.println("Launching Search...");
            }
        });

        // ActionListener for Text button
        textButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to launch text functionality
                System.out.println("Launching Text...");

                // Call the playBackgroundMusic method here
                playBackgroundMusic();
            }
        });

        // ActionListener for Social Media Links button
        textButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Socials window when Social Media Links button is clicked
                SwingUtilities.invokeLater(() -> {
                    new Socials();
                });
            }
        });
    }

    // Method to play background music
    private static void playBackgroundMusic() {
        try {
            // Load the audio file (replace "background_music.wav" with your file name)
            URL url = TreasureTroveApp.class.getResource("/Option1.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

            // Get a Clip resource
            Clip clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream
            clip.open(audioInputStream);

            // Start playing the clip
            clip.start();

            // Loop the background music
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
