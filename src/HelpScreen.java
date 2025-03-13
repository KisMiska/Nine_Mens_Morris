import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HelpScreen extends JFrame {

    public HelpScreen(MainMenu mainMenu, MusicPlayer musicPlayer) {

        setTitle("Help - Nine Men's Morris guide");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(new Color(0, 165, 118));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0, 165, 118));

        JTextArea guideTextArea = new JTextArea();
        guideTextArea.setFont(new Font("Arial", Font.BOLD, 16));
        guideTextArea.setBackground(new Color(0, 165, 118));
        guideTextArea.setLineWrap(true);
        guideTextArea.setWrapStyleWord(true);
        guideTextArea.setEditable(false);
        guideTextArea.setText(loadGuide());
        guideTextArea.setCaretPosition(0);
        guideTextArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(guideTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonPanel.setBackground(new Color(0, 165, 118));
        buttonPanel.add(MainButtons.createButton("Back", e -> {
            mainMenu.setVisible(true);
            dispose();
        }));

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String loadGuide() {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("files/guide.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            content.append("Error : loading guide");
        }

        return content.toString();
    }
}
