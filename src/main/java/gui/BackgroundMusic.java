
package gui;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class BackgroundMusic {

    private Clip musicPlayer;
    private AudioInputStream audioRecord;

    public BackgroundMusic()
            throws UnsupportedAudioFileException,
                   IOException,
                   LineUnavailableException {

        InputStream musicFile =
                getClass().getResourceAsStream("/bibi.wav");

        if (musicFile == null) {
            throw new IOException(
                "Audio resource not found: /bibi.wav"
            );
        }

        this.audioRecord = AudioSystem.getAudioInputStream(
                new BufferedInputStream(musicFile)
        );

        this.musicPlayer = AudioSystem.getClip();
        this.musicPlayer.open(this.audioRecord);
    }

    public void play() {
        if (this.musicPlayer != null && !this.musicPlayer.isRunning()) {
            this.musicPlayer.start();
        }
    }

    public void stop() {
        if (this.musicPlayer != null) {
            this.musicPlayer.stop();
            this.musicPlayer.close();
        }

        if (this.audioRecord != null) {
            try {
                this.audioRecord.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void playOnLoop() {
        if (this.musicPlayer == null || !this.musicPlayer.isOpen()) {
            return;
        }

        if (this.musicPlayer.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl =
                    (FloatControl) this.musicPlayer.getControl(
                            FloatControl.Type.MASTER_GAIN
                    );

            gainControl.setValue(-20.0f);
        }

        this.musicPlayer.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
