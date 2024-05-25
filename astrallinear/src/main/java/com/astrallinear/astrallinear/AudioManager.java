package com.astrallinear.astrallinear;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AudioManager {

    private static AudioManager instance;
    private MediaPlayer mediaPlayer;
    private List<String> NormalBGMList;
    private List<String> BearAttackBGMList;
    private String EndGameBGM;
    private Random random;

    private AudioManager() {
        random = new Random();
        NormalBGMList = new ArrayList<>();
        BearAttackBGMList = new ArrayList<>();
        NormalBGMList.add(Main.class.getResource("BGM/NormalBGM1.mp3").toString());
        NormalBGMList.add(Main.class.getResource("BGM/NormalBGM2.mp3").toString());
        BearAttackBGMList.add(Main.class.getResource("BGM/BearAttackBGM1.mp3").toString());
        BearAttackBGMList.add(Main.class.getResource("BGM/BearAttackBGM2.mp3").toString());
        EndGameBGM = Main.class.getResource("BGM/EndGameBGM.mp3").toString();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void startNormalBGM() {
        playRandomNormalBGM();
    }
    public void startBearAttackBGM() {
        playRandomBearAttackBGM();
    }
    public void startEndGameBGM(){
        playBGM(EndGameBGM);
    }
    private void playRandomBearAttackBGM() {
        if (BearAttackBGMList == null || BearAttackBGMList.isEmpty()) {
            throw new IllegalStateException("Kosong");
        }
        int index = random.nextInt(BearAttackBGMList.size());
        playBGM(BearAttackBGMList.get(index));
    }

    private void playRandomNormalBGM() {
        if (NormalBGMList == null || NormalBGMList.isEmpty()) {
            throw new IllegalStateException("Kosong");
        }
        int index = random.nextInt(NormalBGMList.size());
        playBGM(NormalBGMList.get(index));
    }

    private void playBGM(String musicFilePath) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Media media = new Media(musicFilePath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
        mediaPlayer.play();
    }

    public void stopBGM() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
