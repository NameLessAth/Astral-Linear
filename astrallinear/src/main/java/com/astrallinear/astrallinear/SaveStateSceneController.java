package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
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
import com.astrallinear.astrallinear.SaveLoad.Save;
import com.astrallinear.astrallinear.Toko.Toko;

public class SaveStateSceneController {
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
    private ChoiceBox<String> FileFormatSelector;
    private String[] validFileFormats = {".txt",".json",".xml"};
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

        //nanti algoritma buat savenya taroh di sini
        if(!(FileFormatSelector.getValue() == ".txt")){
            Alert failSaveAlert = new Alert(AlertType.ERROR);
            failSaveAlert.setTitle("Notifikasi menyimpan");
            failSaveAlert.setHeaderText("Menyimpan dalam format ini tidak tersedia, simpan gagal");
            failSaveAlert.show();
        }
        else if(containsSpecialChars(FileNameField.getText())){
            Alert failSaveAlert = new Alert(AlertType.ERROR);
            failSaveAlert.setTitle("Notifikasi menyimpan");
            failSaveAlert.setHeaderText("Menyimpan dalam penamaan folder ini tidak diterima, simpan gagal");
            failSaveAlert.show();
        }
        else{
            try{
                Save.SaveGameState(GameManager.getInstance(), Toko.getToko(), "test/" + FileNameField.getText());
                Alert nextButtonAlert = new Alert(AlertType.INFORMATION);
                nextButtonAlert.setTitle("Notifikasi menyimpan");
                nextButtonAlert.setHeaderText("State program berhasil disimpan");
                nextButtonAlert.show();
            }catch(Exception e){
                Alert nextButtonAlert = new Alert(AlertType.ERROR);
                nextButtonAlert.setTitle("Notifikasi menyimpan");
                nextButtonAlert.setHeaderText("Terjadi anomali dalam menyimpan, simpan gagal");
                nextButtonAlert.show();
            }
        }
    }

    @FXML
    public void initialize(){
        FileFormatSelector.getItems().addAll(validFileFormats);
    }
}
