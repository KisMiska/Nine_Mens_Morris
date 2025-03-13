import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class EffectPlayer {
    private Clip clip;
    private FloatControl volumeControl;

    public EffectPlayer(String fileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fileName));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error : sound");
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            float range = volumeControl.getMaximum() - volumeControl.getMinimum();
            float value = volume * 2.3f * range + volumeControl.getMinimum();
            try {
                volumeControl.setValue(value);
            } catch (Exception ignored) {
            }
        }
    }

}
