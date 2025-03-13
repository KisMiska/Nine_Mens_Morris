import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;

public class SettingsScreen extends JFrame {
    private MusicPlayer musicPlayer;
    private EffectPlayer placeSound;
    private EffectPlayer removeSound;
    private Preferences prefs;

    public SettingsScreen(MainMenu mainMenu, MusicPlayer musicPlayer, EffectPlayer placeSound, EffectPlayer removeSound) {
        this.musicPlayer = musicPlayer;
        this.placeSound = placeSound;
        this.removeSound = removeSound;

        prefs = Preferences.userRoot().node(this.getClass().getName());

        setTitle("Settings");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBackground(new Color(5, 88, 5));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(5, 88, 5));

        JLabel label = new JLabel("Adjust game volume", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.NORTH);

        JSlider volumeSlider = getjSlider();
        panel.add(volumeSlider, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonPanel.setBackground(new Color(5, 88, 5));
        buttonPanel.add(MainButtons.createButton("Back", e -> {
            mainMenu.setVisible(true);
            dispose();
        }));

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JSlider getjSlider() {
        int savedVolume = prefs.getInt("volume", 50);
        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, savedVolume);
        volumeSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int sliderWidth = volumeSlider.getWidth();
                int max = volumeSlider.getMaximum();
                int min = volumeSlider.getMinimum();

                double value = (min + ((double) mouseX / sliderWidth * (max - min)));

                volumeSlider.setValue((int) value);
            }
        });

        volumeSlider.setPreferredSize(new Dimension(400, 50));
        volumeSlider.setBackground(new Color(5, 88, 5));
        volumeSlider.setForeground(Color.WHITE);
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.addChangeListener(e -> {
            int volume = volumeSlider.getValue();
            float volumeFloat = volume / 100.0f;
            musicPlayer.setVolume(volumeFloat);
            placeSound.setVolume(volumeFloat);
            removeSound.setVolume(volumeFloat);
            prefs.putInt("volume", volume);
        });
        return volumeSlider;
    }


}
