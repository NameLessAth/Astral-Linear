package com.astrallinear.astrallinear;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class AudioManager {

    private static AudioManager instance;
    private MediaPlayer BGMPlayer,SFXPlayer;
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
    public void startSFX(String SFXPath){
        String path = Main.class.getResource("SFX/"+SFXPath+"SFX.mp3").toString();
        playSFX(path);
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
        if (BGMPlayer != null) {
            BGMPlayer.stop();
        }
        System.out.println(musicFilePath);
        Media media = new Media(musicFilePath);
        BGMPlayer = new MediaPlayer(media);
        BGMPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
        BGMPlayer.play();
    }

    public void stopBGM() {
        if (BGMPlayer != null) {
            BGMPlayer.stop();
        }
    }

    private void playSFX(String SFXFilePath) {
        if (SFXPlayer != null) {
            SFXPlayer.stop();
        }
        System.out.println(SFXFilePath);
        Media media = new Media(SFXFilePath);
        SFXPlayer = new MediaPlayer(media);
        SFXPlayer.setCycleCount(1); // Loop the music
        SFXPlayer.play();
    }
}
