import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Socials extends JFrame {
    public Socials() {
        setTitle("Social Media Links");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Add social media links
        addLink(panel, "Instagram", "https://www.instagram.com/book_challenges_monthly");
        addLink(panel, "Facebook", "https://www.facebook.com/groups/whatshouldiread/");
        addLink(panel, "Twitter", "https://twitter.com/susanorlean");

        add(panel);
        setVisible(true);
    }

    private void addLink(JPanel panel, String platform, String url) {
        JButton linkButton = new JButton(platform);
        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebPage(url);
            }
        });
        panel.add(linkButton);
    }

    private void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Socials();
        });
    }
}
