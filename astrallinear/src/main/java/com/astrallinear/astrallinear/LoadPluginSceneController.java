package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadPluginSceneController {
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
    private Button LoadButton;
    @FXML
    private Scene scene;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private Label PluginNameLabel;

    @FXML
    private ChoiceBox<String> PluginSelectionDropdown;

    @FXML
    private Button RefreshButton;


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
    @FXML
    void Refresh(ActionEvent event) {
        audioManager.startSFX("ButtonClick");
        System.out.println("Refresh"+PluginSelectionDropdown.getValue());
    }
    @FXML
    void LoadPlugin(ActionEvent event) {
        //tau dah dikerjain apa gak ini aowokawoko
        audioManager.startSFX("ButtonClick");
        System.out.println("Load Plugin"+PluginSelectionDropdown.getValue());
    }

    @FXML
    public void initialize(){
        //ini cuma buat nunjukkin cara masukin valuenya ke dropdown
        audioManager.startLoadPluginBGM();
        ArrayList<String> dummyList = new ArrayList<>();
        dummyList.add("Cara masukin valuenya");
        dummyList.add("begini");
        dummyList.add("ikutin aja");
        PluginSelectionDropdown.getItems().setAll(dummyList);
        PluginNameLabel.setText("ini nama plugin yang udah diload");
    }
}
