package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Plugin.PluginState;
import com.astrallinear.astrallinear.Plugin.SaveLoadPlugin;
import com.astrallinear.astrallinear.Toko.Toko;
import com.astrallinear.astrallinear.TxtSaveLoad.TxtLoad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;

public class LoadStateSceneController {
    private static GameManager gameManager;
    static {
        try {
            gameManager = GameManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private Button BackButton;

    @FXML
    private TextField FileNameField;

    @FXML
    private Button LoadButton;
    @FXML
    private Scene scene;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;

    public static boolean containsSpecialChars(String str) {
        return !(str.matches("[a-zA-Z0-9 ]*"));
    }

    @FXML
    void BackToGame(ActionEvent e) throws IOException {
        //kembali ke ladang pemaiin yang sekarang bermain
        root = FXMLLoader.load(getClass().getResource("View/player"+gameManager.getCurrentPlayer()+"field.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void LoadState(ActionEvent event) {
        //nanti algoritma buat savenya taroh di sini
        if(containsSpecialChars(FileNameField.getText())){
            Alert failSaveAlert = new Alert(AlertType.ERROR);
            failSaveAlert.setTitle("Notifikasi memuat");
            failSaveAlert.setHeaderText("Memuat dalam penamaan folder ini tidak diterima, muat gagal");
            failSaveAlert.show();
        }
        else{
            File directory = new File("test/" + FileNameField.getText());
            if (!directory.exists()){
                Alert nextButtonAlert = new Alert(AlertType.ERROR);
                    nextButtonAlert.setTitle("Notifikasi memuat");
                    nextButtonAlert.setHeaderText("Folder dengan nama tersebut tidak ada, muat gagal");
                    nextButtonAlert.show();
            }
            else{
                try{

                    // load here
                    PluginState.getUsedPlugin().load(gameManager, Toko.getToko(), "test/" + FileNameField.getText());
                    
                    
                    Alert nextButtonAlert = new Alert(AlertType.INFORMATION);
                    nextButtonAlert.setTitle("Notifikasi memuat");
                    nextButtonAlert.setHeaderText("State program berhasil dimuat");
                    nextButtonAlert.show();
                }catch(Exception e){
                    Alert nextButtonAlert = new Alert(AlertType.ERROR);
                    nextButtonAlert.setTitle("Notifikasi memuat");
                    nextButtonAlert.setHeaderText("Terjadi anomali dalam memuat, muat gagal");
                    nextButtonAlert.show();
                }
            }
        }
    }
    @FXML
    public void initialize(){

    }
}
