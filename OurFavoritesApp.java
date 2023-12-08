import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OurFavoritesApp {

    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Our Favorite Books");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add components
        JLabel promptLabel = new JLabel("Click on the drop-down list to find out our favorite books!");
        promptLabel.setForeground(new Color(128, 0, 128)); // Set text color to purple
        promptLabel.setOpaque(true); 

        JComboBox<String> nameDropdown = new JComboBox<>(new String[]{"Alicia", "Carla", "Schyla", "Shamiah"});
        JButton submitButton = new JButton("Submit");
        JTextArea resultArea = new JTextArea();

        // Add components to the frame
        frame.setLayout(new GridLayout(4, 1));
        frame.add(promptLabel);
        frame.add(nameDropdown);
        frame.add(submitButton);
        frame.add(new JScrollPane(resultArea));

        // Handle button click event
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userAnswer = nameDropdown.getSelectedItem().toString();
                String books = getFavoriteBooks(userAnswer);
                resultArea.setText("Favorite Books:\n" + books);
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    private static String getFavoriteBooks(String userAnswer) {
        // Provide favorite books based on the user's request
        switch (userAnswer.toLowerCase()) {
            case "alicia":
                return "Alicia's Favorite Books:\n" +
                        "1. One Of Us Is Lying by Karen M. McManus\n" +
                        "2. Frozen Charlotte by Alex Bell\n" +
                        "3. 14 Ways To Die by Vincent Ralph";
            case "carla":
                return "Carla's Favorite Books:\n" +
                        "1. Can't Hurt Me by David Goggins\n" +
                        "2. Circe by Madeline Miller\n" +
                        "3. 12 Rules for Life by Jordan Peterson";
            case "schyla":
                return "Schyla's Favorite Books:\n" +
                        "1. The Power of Now by Eckhart Tolle\n" +
                        "2. 48 Laws of Power by Robert Greene\n" +
                        "3. How to Win Friends and Influence People by Dale Carnegie";
            case "shamiah":
                return "Shamiah's Favorite Books:\n" +
                        "1.  by \n" +
                        "2.  by \n" +
                        "3.  by ";
            default:
                return "No favorites for the selected name.";
        }
    }
}