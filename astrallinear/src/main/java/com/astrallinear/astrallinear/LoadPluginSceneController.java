package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Plugin.PluginLoader;
import com.astrallinear.astrallinear.Plugin.PluginState;
import com.astrallinear.astrallinear.Plugin.SaveLoadPlugin;

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
        PluginLoader pl = new PluginLoader();
        List<SaveLoadPlugin> pluginsList = pl.loadPlugins("plugins/");
        PluginState.setAvailablePlugins(pluginsList);
        initialize();
    }
    @FXML
    void LoadPlugin(ActionEvent event) {
        for (SaveLoadPlugin plugin : PluginState.getAvailalePlugins()) {
            if (PluginSelectionDropdown.getValue().equals(plugin.getExtName())) {
                PluginState.setUsedPlugin(plugin);
            }
        }
        //tau dah dikerjain apa gak ini aowokawoko
        audioManager.startSFX("ButtonClick");
        System.out.println("Load Plugin"+PluginSelectionDropdown.getValue());
    }

    @FXML
    public void initialize(){
        //ini cuma buat nunjukkin cara masukin valuenya ke dropdown
        audioManager.startLoadPluginBGM();
        ArrayList<String> dummyList = new ArrayList<>();
        for (SaveLoadPlugin plugin : PluginState.getAvailalePlugins()) {
            dummyList.add(plugin.getExtName());
        }
        PluginSelectionDropdown.getItems().setAll(dummyList);

        PluginNameLabel.setText(PluginState.getUsedPlugin().getExtName());
    }
}
