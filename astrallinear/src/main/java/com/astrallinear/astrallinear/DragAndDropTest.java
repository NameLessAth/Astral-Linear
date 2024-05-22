package com.astrallinear.astrallinear;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.IOException;

public class DragAndDropTest extends Application {

    @FXML
    private Label source;

    @FXML
    private Label target;
    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;
    @FXML
    void handleDragDetection(MouseEvent event) {
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);

        ClipboardContent cb = new ClipboardContent();
        cb.putString(source.getText());
        db.setContent(cb);

        event.consume();
    }

    @FXML
    void handleTextDragOver(DragEvent event) {
        if(event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    void handleTextDrop(DragEvent event) {
        String str = event.getDragboard().getString();
        target.setText(str);
    }
    @FXML
    void handleDragDone(DragEvent event) {
        source.setText("tubes 6 biji");
    }

    @FXML
    void handleDragDetectIMG(MouseEvent event) {
        ImageView IMGSource = (ImageView) event.getSource();
        Dragboard db = IMGSource.startDragAndDrop(TransferMode.ANY);
        System.out.println(IMGSource.getImage().getUrl());
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(IMGSource.getImage());
        db.setContent(cb);

        event.consume();
    }

    @FXML
    void handleDragDoneIMG(DragEvent event) {
        if (event.getTransferMode() != null) {
            ImageView IMGSource = (ImageView) event.getSource();
            Image img = new Image(Main.class.getResourceAsStream("Hewan/horse.png"));
            IMGSource.setImage(img);
        }
    }

    @FXML
    void handleIMGDragOver(DragEvent event) {
        if (event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    @FXML
    void handleIMGDrop(DragEvent event) {
        ImageView target = (ImageView) event.getSource();
        ImageView source = (ImageView) event.getGestureSource();
        if (event.getDragboard().hasImage() && target != source) {
            Image img = event.getDragboard().getImage();
            target.setImage(img);
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/DragAndDropScene.fxml"));
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
