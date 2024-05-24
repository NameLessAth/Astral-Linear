package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Kartu.Kartu;
import com.astrallinear.astrallinear.Kartu.KartuProduk;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Toko.Toko;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class JualWidgetController {

    @FXML
    private Button SellButton;

    @FXML
    private Label SellItemDetailLabel;

    @FXML
    private ImageView SellItemImage;

    @FXML
    private Label SellItemNameLabel;

    @FXML
    void OnSellButtonClicked(ActionEvent event) throws Exception {
        GameManager gameManager = GameManager.getInstance();
        Pemain pemain = gameManager.getCurrentPlayerInstance();
        Integer indeksKartu = Integer.parseInt(this.getSellItemNameLabel().getText());
        KartuProduk kartuProduk = (KartuProduk) pemain.getDeck().getActiveCard(indeksKartu);
        pemain.deleteActiveCard(indeksKartu);
        pemain.increaseGulden(Toko.getToko().sellProduct(kartuProduk));
        Alert nextButtonAlert = new Alert(AlertType.INFORMATION);
            nextButtonAlert.setTitle("Notifikasi pembelian");
            nextButtonAlert.setHeaderText("Penjualan berhasil");
            nextButtonAlert.show();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/MainJual.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = gameManager.getGameStage();
            gameManager.setGameStage(stage);
            stage.setTitle("Tubes 2 OOP");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
    }
    public Label getSellItemDetailLabel() {
        return SellItemDetailLabel;
    }
    public ImageView getSellItemImage() {
        return SellItemImage;
    }
    public Label getSellItemNameLabel() {
        return SellItemNameLabel;
    }
}
