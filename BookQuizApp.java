import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BookQuizApp {

    private static final String API_KEY = "YOUR_GOOGLE_BOOKS_API_KEY";

    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Book Quiz App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add components
        JLabel questionLabel = new JLabel("What genre of books do you enjoy?");
        questionLabel.setForeground(new Color(128, 0, 128)); // Set text color to purple
        questionLabel.setOpaque(true); // Make sure to setOpaque to true for background color to be visible

        JComboBox<String> genreDropdown = new JComboBox<>(new String[]{"Drama", "Fantasy", "Self Help", "Science Fiction"});
        JButton submitButton = new JButton("Submit");
        JTextArea resultArea = new JTextArea();

        // Add components to the frame
        frame.setLayout(new GridLayout(4, 1));
        frame.add(questionLabel);
        frame.add(genreDropdown);
        frame.add(submitButton);
        frame.add(new JScrollPane(resultArea));

        // Handle button click event
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userAnswer = genreDropdown.getSelectedItem().toString();
                String books = getRecommendedBooks(userAnswer);
                resultArea.setText("Suggested Books:\n" + books);
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    private static String getRecommendedBooks(String userAnswer) {
        // Provide recommended books based on the user's genre preference
        switch (userAnswer.toLowerCase()) {
            case "drama":
                return "Drama Recommendations:\n" +
                        "1. Long Shot by Kennedy Ryan\n" +
                        "2. Everything I Never Told You by Celeste Ng\n" +
                        "3. Liar, Dreamer, Thief by Maria Dong";
            case "fantasy":
                return "Fantasy Recommendations:\n" +
                        "1. Shannara series by Terry Brooks - 21 million copies\n" +
                        "2. Artemis Fowl series by Eoin Colfer - 21 million copies";
            case "self help":
                return "Self-Help Recommendations:\n" +
                        "1. The Power of Now\n" +
                        "2. Atomic Habits\n" +
                        "3. How to Win Friends and Influence People";
            case "science fiction":
                return "Science Fiction Recommendations:\n" +
                        "1. The Wormwood Trilogy by Tade Thompson\n" +
                        "2. The Left Hand of Darkness by Ursula K. Le Guin\n" +
                        "3. The Daughter of Doctor Moreau by Silvia Moreno-Garcia";
            default:
                return "No recommendations for the selected genre.";
        }
    }
}
