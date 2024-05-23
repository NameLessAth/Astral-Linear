package com.astrallinear.astrallinear.GameManager;

import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Ladang.Ladang;

import java.util.ArrayList;

//Game Manager menggunakan design pattern singleton untuk mengatur seluruh keberjalanan game
public class GameManager {
    private static GameManager instance;
    private static final Integer MAX_TURN = 20; //banyak turn maksimal
    private Integer currentTurn;
    private Integer currentPlayer;
    private Pemain currentPlayerInstance;
    ArrayList<Pemain> PlayerList; //daftar pemain
    private static String previousPressedButton; //button yang terakhir kali dipencet
    private GameManager() throws Exception{
        PlayerList = new ArrayList<>();
        PlayerList.add(new Pemain());
        PlayerList.add(new Pemain());
        currentTurn = 1;
        currentPlayer = 1;
        currentPlayerInstance = PlayerList.get(0);
        previousPressedButton = "";
    }

    public static GameManager getInstance() throws Exception{
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    public void nextTurn() throws Exception{
        if(currentTurn.equals(MAX_TURN)){
            endGame();
            return;
        }
        currentTurn++;
        if(currentPlayer == 1){
            currentPlayer++;
        } else if(currentPlayer == 2){
            currentPlayer = 1;
        }
        currentPlayerInstance = PlayerList.get(currentPlayer-1);
    }
    public void endGame(){
        if(currentTurn.equals(MAX_TURN)){
            if(PlayerList.get(0).getGulden() > PlayerList.get(1).getGulden()){
                System.out.println("Pemain 1 menang!");
            } else if(PlayerList.get(0).getGulden() < PlayerList.get(1).getGulden()){
                System.out.println("Pemain 2 menang!");
            } else {
                System.out.println("Seri!");
            }
        }
    }

    public Integer getCurrentTurn() {
        return currentTurn;
    }

    public Integer getCurrentPlayer() {
        return currentPlayer;
    }

    public Pemain getCurrentPlayerInstance() {
        return currentPlayerInstance;
    }

    public Pemain getPlayer(Integer index){
        return PlayerList.get(index);
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
}
