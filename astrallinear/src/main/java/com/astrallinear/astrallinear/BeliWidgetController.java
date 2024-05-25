package com.astrallinear.astrallinear;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Kartu.KartuProduk;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Toko.Toko;

import javafx.event.ActionEvent;
public class BeliWidgetController {
    private static AudioManager audioManager;
    static {
        try {
            audioManager = AudioManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private Button BuyButton;

    @FXML
    private Label BuyItemDetailLabel;

    @FXML
    private ImageView BuyItemImage;

    @FXML
    private Label BuyItemNameLabel;

    public Label getBuyItemDetailLabel() {
        return BuyItemDetailLabel;
    }
    public ImageView getBuyItemImage() {
        return BuyItemImage;
    }
    public Label getBuyItemNameLabel() {
        return BuyItemNameLabel;
    }
    @FXML
    void OnBuyButtonClicked(ActionEvent event) throws Exception{

        GameManager gameManager = GameManager.getInstance();
        Pemain currentPlayer = gameManager.getCurrentPlayerInstance();
        KartuProduk kartuProduk = new KartuProduk(this.getBuyItemNameLabel().
        getText());

        if(currentPlayer.getGulden() < kartuProduk.getHarga()){
            audioManager.startSFX("Error");
            Alert miskinAlert = new Alert(AlertType.ERROR);
            miskinAlert.setTitle("Notifikasi pembelian");
            miskinAlert.setHeaderText("Aa kasian aa..\nduit anda tidak mencukupi, pembelian gagal");
            miskinAlert.show();
        }
        else if(currentPlayer.getDeck().countEmptySlot() == 0){
            audioManager.startSFX("Error");
            Alert fullDeckAlert = new Alert(AlertType.ERROR);
            fullDeckAlert.setTitle("Notifikasi pembelian");
            fullDeckAlert.setHeaderText("Deck anda sudah penuh, pembelian gagal");
            fullDeckAlert.show();
        }
        else{
            Alert nextButtonAlert = new Alert(AlertType.INFORMATION);
            audioManager.startSFX("DropSuccess");
            nextButtonAlert.setTitle("Notifikasi pembelian");
            nextButtonAlert.setHeaderText("Pembelian berhasil");
            currentPlayer.reduceGulden(kartuProduk.getHarga());
            currentPlayer.addActiveCard(Toko.getToko().buyProductByName(this.getBuyItemNameLabel().getText()));
            nextButtonAlert.show();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/MainBeli.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = gameManager.getGameStage();
            gameManager.setGameStage(stage);
            stage.setTitle("Tubes 2 OOP");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        }
    }

    @FXML
    void initialize() {

    }
}
