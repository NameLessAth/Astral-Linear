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
    private String EndGameBGM,ShopBGM,SaveStateBGM,LoadStateBGM,LoadPluginBGM;
    private Random random;

    private AudioManager() {
        random = new Random();
        NormalBGMList = new ArrayList<>();
        BearAttackBGMList = new ArrayList<>();
        addAudioList(NormalBGMList,3,"NormalBGM");
        addAudioList(BearAttackBGMList,3,"BearAttackBGM");
        EndGameBGM = Main.class.getResource("BGM/EndGameBGM.mp3").toString();
        ShopBGM = Main.class.getResource("BGM/ShopBGM.mp3").toString();
        SaveStateBGM = Main.class.getResource("BGM/SaveStateBGM.mp3").toString();
        LoadStateBGM = Main.class.getResource("BGM/LoadStateBGM.mp3").toString();
        LoadPluginBGM = Main.class.getResource("BGM/LoadPluginBGM.mp3").toString();
    }
    private void addAudioList(List<String> AudioList, Integer n, String AudioType){
        for(Integer i = 1; i<=n; i++){
            AudioList.add(Main.class.getResource("BGM/"+AudioType+i+".mp3").toString());
        }
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
    public void startShopBGM(){
        playBGM(ShopBGM);
    }
    public void startSaveStateBGM(){
        playBGM(SaveStateBGM);
    }
    public void startLoadStateBGM(){
        playBGM(LoadStateBGM);
    }
    public void startLoadPluginBGM(){
        playBGM(LoadPluginBGM);
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
