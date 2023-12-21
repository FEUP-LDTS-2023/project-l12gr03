package project;


import javax.sound.sampled.*;

import java.io.File;


public class Music {
    private Clip sound;
    private long msp = 0;
    private float level = -23.0f;
    public Music(String sound) {
        this.sound = loadSound(sound);
    }

    public Clip loadSound(String sound) throws NullPointerException{
        try {
            String rootPath = new File(System.getProperty("user.dir")).getPath();
            String musicPath = rootPath + "/resources/" + sound;
            File musicFile = new File(musicPath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(level);
            return musicClip;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    public void start() {
        sound.setMicrosecondPosition(msp);
        sound.start();
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pause() {
        sound.stop();
        msp = sound.getMicrosecondPosition();
    }

    public void stop(){
        sound.stop();
    }
    public void setMsp(){
        msp=0;
    }
}
