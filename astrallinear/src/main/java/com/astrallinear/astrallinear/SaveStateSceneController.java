package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Plugin.PluginState;

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

import java.io.IOException;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.TxtSaveLoad.TxtSave;
import com.astrallinear.astrallinear.Toko.Toko;
import com.astrallinear.astrallinear.TxtSaveLoad.TxtSave;

public class SaveStateSceneController {
    private static GameManager gameManager;
    static {
        try {
            gameManager = GameManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static AudioManager audioManager;
    static {
        try {
            audioManager = AudioManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private Button BackButton;

    @FXML
    private TextField FileNameField;

    @FXML
    private Button SaveButton;
    @FXML
    private Scene scene;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    void BackToGame(ActionEvent e) throws IOException {
        //kembali ke ladang pemaiin yang sekarang bermain
        audioManager.startSFX("ButtonClick");
        root = FXMLLoader.load(getClass().getResource("View/player"+gameManager.getCurrentPlayer()+"field.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static boolean containsSpecialChars(String str) {
        return !(str.matches("[a-zA-Z0-9 ]*"));
    }

    @FXML
    void SaveState(ActionEvent event){
        audioManager.startSFX("ButtonClick");
        if(containsSpecialChars(FileNameField.getText()) || FileNameField.getText().equals("") || FileNameField.getText() == null){
            audioManager.startSFX("Error");
            Alert failSaveAlert = new Alert(AlertType.ERROR);
            failSaveAlert.setTitle("Notifikasi menyimpan");
            failSaveAlert.setHeaderText("Menyimpan dalam penamaan folder ini tidak diterima, simpan gagal");
            failSaveAlert.show();
        }
        else{
            try{
                
                // save here
                PluginState.getUsedPlugin().save(GameManager.getInstance(), Toko.getToko(), "test/" + FileNameField.getText());
                
                
                Alert nextButtonAlert = new Alert(AlertType.INFORMATION);
                nextButtonAlert.setTitle("Notifikasi menyimpan");
                nextButtonAlert.setHeaderText("State program berhasil disimpan");
                nextButtonAlert.show();
            }catch(Exception e){
                audioManager.startSFX("Error");
                Alert nextButtonAlert = new Alert(AlertType.ERROR);
                nextButtonAlert.setTitle("Notifikasi menyimpan");
                nextButtonAlert.setHeaderText("Terjadi anomali dalam menyimpan, simpan gagal");
                nextButtonAlert.show();
            }
        }
    }

    @FXML
    public void initialize(){
        audioManager.startSaveStateBGM();
    }
}
