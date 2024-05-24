package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadPluginSceneController {
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
    void LoadPlugin(ActionEvent event) {
        //tau dah dikerjain apa gak ini aowokawoko
        System.out.println(FileNameField.getText());
    }

}
