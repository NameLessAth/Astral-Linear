package com.astrallinear.astrallinear;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

import com.astrallinear.astrallinear.Deck.Deck;
import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Kartu.Kartu;
import com.astrallinear.astrallinear.Kartu.KartuProduk;
import com.astrallinear.astrallinear.Ladang.Ladang;
import com.astrallinear.astrallinear.Pemain.Pemain;

public class PanenController {
    //Tolong baca teks yang ada di scene panen.fxml dulu buat cara displaynya
    @FXML
    private Button BackButton;

    @FXML
    private Label ItemDetailsLabel;

    @FXML
    private Button PanenButton;

    @FXML
    private ImageView PanenObjectIMG;

    @FXML
    private Label PanenObjectNameLabel;

    private Kartu kartu;
    private Integer row;
    private Integer col;
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
        //unblock window game utama

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/player"+gameManager.getCurrentPlayer()+"field.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage gameStage = gameManager.getGameStage();
        gameStage.setTitle("Tubes 2 OOP");
        gameStage.setScene(scene);
        gameStage.setResizable(false);
        gameStage.show();
    }

    @FXML
    void OnPanenButtonClick(ActionEvent event) throws Exception {
        GameManager gameManager = GameManager.getInstance();

        Pemain pemain = gameManager.getCurrentPlayerInstance();
        Ladang ladang = pemain.getLadang();
        Deck deck = pemain.getDeck();
     
        if (deck.countEmptySlot() == 0) {
            Alert deckIsFullAlert = new Alert(AlertType.ERROR);
            deckIsFullAlert.setTitle("Tidak ada tempat kosong!");
            deckIsFullAlert.setHeaderText("Deck aktif Anda masih penuh! Silakan kosongkan minimal satu slot agar bisa panen!");
            deckIsFullAlert.show();
            return;
        }

        KartuProduk produk = ladang.harvest_at(row,col);
        deck.addKartu(produk);
        System.out.println(produk);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/player"+gameManager.getCurrentPlayer()+"field.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage gameStage = gameManager.getGameStage();
        gameStage.setTitle("Tubes 2 OOP");
        gameStage.setScene(scene);
        gameStage.setResizable(false);
        gameStage.show();
    }
    @FXML
    public void initialize() {
        if (kartu == null) return;
        PanenObjectNameLabel.setText(kartu.getNama());
        Image img = new Image(getClass().getResource(kartu.getPathToImg()).toString());
        PanenObjectIMG.setImage(img);
        ItemDetailsLabel.setText(kartu.getInfo());
    }

    public void setKartuRowCol(Kartu kartu, Integer row, Integer col) {
        this.kartu = kartu;
        this.row = row;
        this.col = col;
    }
}
