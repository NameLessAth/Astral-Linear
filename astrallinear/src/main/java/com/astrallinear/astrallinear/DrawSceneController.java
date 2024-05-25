package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Pemain.Pemain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DrawSceneController {
    private static AudioManager audioManager;
    static {
        try {
            audioManager = AudioManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static GameManager gameManager;
    static {
        try {
            gameManager = GameManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private Button EndGameButton;

    @FXML
    private Label Player1Gulden;

    @FXML
    private Label Player2Gulden;

    @FXML
    void OnEndGameButton(ActionEvent event) {
        audioManager.startSFX("ButtonClick");
        Stage gameStage = gameManager.getGameStage();
        gameStage.close();
    }
    @FXML
    public void initialize(){
        Pemain Player1 = gameManager.getPlayer(0);
        Pemain Player2 = gameManager.getPlayer(1);
        Player1Gulden.setText(Integer.toString(Player1.getGulden()));
        Player2Gulden.setText(Integer.toString(Player2.getGulden()));
    }
}
