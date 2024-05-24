package com.astrallinear.astrallinear.GameManager;

import com.astrallinear.astrallinear.Main;
import com.astrallinear.astrallinear.Pemain.Pemain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import com.astrallinear.astrallinear.Ladang.Ladang;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

//Game Manager menggunakan design pattern singleton untuk mengatur seluruh keberjalanan game
public class GameManager {
    private static GameManager instance;
    private static final Integer MAX_TURN = 20; //banyak turn maksimal
    private Integer currentTurn;
    public Integer state = 0;
    private static Stage GameStage;
    private static Integer GameWinner;
    private static Integer WinnerGulden,LoserGulden;
    ArrayList<Pemain> PlayerList; //daftar pemain
    private static String previousPressedButton; //button yang terakhir kali dipencet
    private GameManager() throws Exception{
        PlayerList = new ArrayList<>();
        PlayerList.add(new Pemain());
        PlayerList.add(new Pemain());
        currentTurn = 1;
        previousPressedButton = "";
        GameWinner = 0;
        WinnerGulden = 0;
        LoserGulden = 0;
    }

    public static GameManager getInstance() throws Exception{
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public static void gameManagerReset() throws Exception{
        instance = new GameManager();
    }
    public void nextTurn() throws Exception{
        currentTurn++;
    }
    public void endGame() throws IOException{
        if(currentTurn.equals(MAX_TURN)){
            if(PlayerList.get(0).getGulden().equals(PlayerList.get(1).getGulden())){
                System.out.println("Seri!");
                GameWinner = 0;
                WinnerGulden = 0;
                Stage gameStage = GameStage;
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/drawscene.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                gameStage.setTitle("Tubes 2 OOP");
                gameStage.setScene(scene);
                gameStage.setResizable(false);
                gameStage.show();
            } else {
                if(PlayerList.get(0).getGulden() > PlayerList.get(1).getGulden()){
                    System.out.println("Pemain 1 menang!");
                    GameWinner = 1;
                    WinnerGulden = PlayerList.get(0).getGulden();
                } else if(PlayerList.get(0).getGulden() < PlayerList.get(1).getGulden()){
                    System.out.println("Pemain 2 menang!");
                    GameWinner = 2;
                    WinnerGulden = PlayerList.get(1).getGulden();
                }
                Stage gameStage = GameStage;
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/winnerscene.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                gameStage.setTitle("Tubes 2 OOP");
                gameStage.setScene(scene);
                gameStage.setResizable(false);
                gameStage.show();
            }
        }
    }

    public Integer getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Integer currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Integer getCurrentPlayer() {
        if(currentTurn%2 == 0){
            return 2;
        }
        else{
            return 1;
        }
    }

    public Pemain getCurrentPlayerInstance() {
        return PlayerList.get(getCurrentPlayer()-1);
    }

    public Pemain getPlayer(Integer index){
        return PlayerList.get(index);
    }

    public void setPlayer(Integer index, Pemain pemainBaru){
        this.PlayerList.set(index, pemainBaru);
    }
    
    public Ladang getLadangPemain1() {
        return PlayerList.get(0).getLadang();
    }

    public Ladang getLadangPemain2() {
        return PlayerList.get(1).getLadang();
    }

    public String getPreviousPressedButton(){
        return previousPressedButton;
    }

    public void setPreviousPressedButton(String previous){
        previousPressedButton = previous;
    }

    public void setGameStage(Stage stage){
        GameStage = stage;
    }

    public Stage getGameStage(){
        return GameStage;
    }
    public Integer getWinnerGulden(){
        return WinnerGulden;
    }

    public Integer getGameWinner(){
        return GameWinner;
    }

    public Integer getMaxTurn(){
        return MAX_TURN;
    }
}
