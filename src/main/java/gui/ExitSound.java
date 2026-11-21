
package gui;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class ExitSound {

    private Clip musicPlayer;
    private AudioInputStream audioRecord;

    public ExitSound()
            throws UnsupportedAudioFileException,
                   IOException,
                   LineUnavailableException {

        InputStream musicFile =
                getClass().getResourceAsStream("/megaman.wav");

        if (musicFile == null) {
            throw new IOException(
                "Audio resource not found: /megaman.wav"
            );
        }

        this.audioRecord = AudioSystem.getAudioInputStream(
                new BufferedInputStream(musicFile)
        );

        this.musicPlayer = AudioSystem.getClip();
        this.musicPlayer.open(this.audioRecord);
    }

    public void play() {
        if (this.musicPlayer != null && this.musicPlayer.isOpen()) {
            if (!this.musicPlayer.isRunning()) {
                this.musicPlayer.setFramePosition(0);
                this.musicPlayer.start();
            }
        }
    }

    public void stop() {
        if (this.musicPlayer != null && this.musicPlayer.isOpen()) {
            this.musicPlayer.stop();
            this.musicPlayer.setFramePosition(0);
        }
    }

    public void close() {
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
    
    public void playThen(Runnable afterSound) {
        if (this.musicPlayer == null || !this.musicPlayer.isOpen()) {
            afterSound.run();
            return;
        }
        this.musicPlayer.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                close();
                afterSound.run();
            }
        });
        this.musicPlayer.setFramePosition(0);
        this.musicPlayer.start();
    }
}
