package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
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
    @Override
    public void start(Stage stage) throws IOException {
        audioManager.startNormalBGM();
        gameManager.setGameStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/splash-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tubes 2 OOP");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}