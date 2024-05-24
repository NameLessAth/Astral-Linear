package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Kartu.*;
import com.astrallinear.astrallinear.Toko.Toko;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class CardDetailController {
    //tolong baca text yang muncul di carddetail.fxml terlebih dahulu buat display
    @FXML
    private Button BackButton;

    @FXML
    private ImageView CardIMG;

    @FXML
    private Label CardNameLabel;

    @FXML
    private Label ItemDetailsLabel;
    
    private Kartu kartu;
    private static GameManager gameManager;
    static {
        try {
            gameManager = GameManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void OnBackButtonClick(ActionEvent event) throws IOException {

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        //unblock window game utama
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/player"+gameManager.getCurrentPlayer()+"field.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage gameStage = gameManager.getGameStage();
        gameStage.setTitle("Tubes 2 OOP");
        gameStage.setScene(scene);
        gameStage.setResizable(false);
        gameStage.show();
    }
    @FXML
    public void initialize(){
        if (kartu == null) return;
        CardNameLabel.setText(kartu.getNama());
        Image img = new Image(getClass().getResource(kartu.getPathToImg()).toString());
        CardIMG.setImage(img);
        ItemDetailsLabel.setText(kartu.getInfo());
    }

    public void setKartu(Kartu kartu) {
        this.kartu = kartu;
    }
}
