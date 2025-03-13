import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

public class MainMenu extends JFrame {
    private MusicPlayer musicPlayer;
    private EffectPlayer placeSound;
    private EffectPlayer removeSound;

    public MainMenu() {
        Preferences prefs = Preferences.userRoot().node("SettingsScreen");

        setTitle("Main Menu");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        musicPlayer = new MusicPlayer("sounds/background.wav");
        musicPlayer.setVolume(50/100.0f * 0.9f);
        prefs.putInt("volume", 50);
        musicPlayer.play();

        placeSound = new EffectPlayer("sounds/place.wav");
        removeSound = new EffectPlayer("sounds/remove.wav");
        placeSound.setVolume(50/100.0f * 0.9f);
        removeSound.setVolume(50/100.0f * 0.9f);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(50, 50, 100));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel title = new JLabel("Nine Men's Morris");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        panel.add(MainButtons.createButton("Start Game", e -> {
            setVisible(false);
            new GameScreen(this, musicPlayer, placeSound, removeSound);
        }));
        panel.add(Box.createVerticalStrut(20));
        panel.add(MainButtons.createButton("Settings", e -> {
            setVisible(false);
            new SettingsScreen(this, musicPlayer, placeSound, removeSound);
        }));
        panel.add(Box.createVerticalStrut(20));
        panel.add(MainButtons.createButton("Help", e -> {
            setVisible(false);
            new HelpScreen(this, musicPlayer);
        }));
        panel.add(Box.createVerticalStrut(20));
        panel.add(MainButtons.createButton("Exit", e -> System.exit(0)));
        panel.add(Box.createVerticalStrut(20));

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
