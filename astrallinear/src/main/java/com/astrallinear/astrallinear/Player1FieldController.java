package com.astrallinear.astrallinear;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Kartu.Kartu;
import com.astrallinear.astrallinear.Deck.Deck;
import com.astrallinear.astrallinear.Ladang.Ladang;
import com.astrallinear.astrallinear.Beruang.BearAttack;
import com.astrallinear.astrallinear.Beruang.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;

public class Player1FieldController{

    @FXML
    private Label CurrentPlayerLabel;

    @FXML
    private Button EnemyFieldButton;

    @FXML
    private Button LoadButton;

    @FXML
    private Button MyFieldButton;

    @FXML
    private Button NextButton;

    @FXML
    private Label Player1Gold;

    @FXML
    private Label Player2Gold;

    @FXML
    private Button PluginButton;

    @FXML
    private Button SaveButton;

    @FXML
    private Button ShopButton;

    @FXML
    private Label turnCount;
    @FXML
    private Scene scene;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private GridPane LadangGridPane;
    @FXML
    private GridPane DeckGridPane;
    @FXML
    private AnchorPane DeckIndicatorPane;
    @FXML
    private Label CardLeftLabel;
    private static final String PLACEHOLDER_IMAGE_URL = "Placeholder/EmptyCell.png";
    private static GameManager gameManager;
    static {
        try {
            gameManager = GameManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Method to set visibility based on indices
    private void set_grid_cell_not_visible(GridPane gridPane, int targetRow, int targetColumn) {
        for (Node node : gridPane.getChildren()) {
            Integer row = GridPane.getRowIndex(node);
            Integer column = GridPane.getColumnIndex(node);
            
            // Check for nulls because they can be unspecified
            if (row == null) row = 0;
            if (column == null) column = 0;

            if (row == targetRow && column == targetColumn) {
                node.setVisible(false);
                node.setManaged(false);
            }
        }
    }

    // @FXML
    // private void initialize() {
    //     Integer r = LadangGridPane.getRowCount();
    //     Integer c = LadangGridPane.getColumnCount();
    //     for (int i = 0; i < r; i++) {
    //         for (int j = 0; j < c; j++) {
    //             set_grid_cell_not_visible(LadangGridPane, i, j);
    //         }
    //     }
    // }

    @FXML
    void OnEnemyFieldButtonClick(ActionEvent e) throws IOException{
        //tanpa mengubah giliran
        Integer currentPlayer = gameManager.getCurrentPlayer();
        System.out.println(currentPlayer);
        if(currentPlayer == 2){ //jika pemain sekarang adalah pemain 2 yang menekan tombol "Ladang Lawan" di ladang pemain 1
            //Dia sudah mengunjungi ladang lawan, keluarkan pesan error
            Alert alreadyInOpponentField = new Alert(AlertType.ERROR);
            alreadyInOpponentField.setTitle("Sudah di ladang lawan!");
            alreadyInOpponentField.setHeaderText("Pemain 2, kamu sudah berada di ladang lawan!");
            alreadyInOpponentField.show();
        } else {
            root = FXMLLoader.load(getClass().getResource("View/player2field.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void OnLoadPluginButtonClick(ActionEvent e) throws IOException {
        System.out.println("Load Plugin");
        root = FXMLLoader.load(getClass().getResource("View/loadplugin.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(gameManager.getCurrentPlayer());
    }

    @FXML
    void OnLoadStateButtonClick(ActionEvent e) throws IOException{
        System.out.println("Load State");
        root = FXMLLoader.load(getClass().getResource("View/loadstate.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(gameManager.getCurrentPlayer());
    }

    @FXML
    void OnMyFieldButtonClick(ActionEvent e) throws IOException{
        //tanpa mengubah giliran
        Integer currentPlayer = gameManager.getCurrentPlayer();
        System.out.println(currentPlayer);
        if(currentPlayer == 1){
            //Jika sekarang giliran pemain 1 (sudah berada di ladang sendiri) tampilkan error ini
            Alert alreadyInMyField = new Alert(AlertType.ERROR);
            alreadyInMyField.setTitle("Sudah di ladang sendiri");
            alreadyInMyField.setHeaderText("Pemain 1, kamu sudah berada di ladangmu sendiri!");
            alreadyInMyField.show();
        } else {
            root = FXMLLoader.load(getClass().getResource("View/player2field.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void OnNextButtonClick(ActionEvent e) throws Exception {
        //ganti giliran ke pemain selanjutnya jika pemain sekarang sama dengan nama controller ini
        Integer currentPlayer = gameManager.getCurrentPlayer();
        System.out.println(currentPlayer);
        if(currentPlayer == 2){
            Alert nextButtonAlert = new Alert(AlertType.ERROR);
            nextButtonAlert.setTitle("Next Button Error");
            nextButtonAlert.setHeaderText("Pemain 2, kamu masih di ladang lawan! Kembali ke ladangmu terlebih dahulu untuk lanjut ke turn berikutnya!");
            nextButtonAlert.show();
        } else {
            gameManager.nextTurn();
            System.out.println(gameManager.getCurrentPlayer());
            root = FXMLLoader.load(getClass().getResource("View/player2field.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void OnSaveStateButtonClick(ActionEvent e) throws IOException {
        System.out.println("Save State");
        root = FXMLLoader.load(getClass().getResource("View/savestate.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(gameManager.getCurrentPlayer());
    }
    @FXML
    void OnShopButtonClick(ActionEvent e) throws IOException {
        System.out.println("Shop");
        root = FXMLLoader.load(getClass().getResource("View/shop.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(gameManager.getCurrentPlayer());
    }
    @FXML
    void handleDragDetectIMG(MouseEvent event) {
        ImageView IMGSource = (ImageView) event.getSource();
        GridPane sourceGridPane = (GridPane) IMGSource.getParent();
        String sourceGridPaneName = "";
        if (sourceGridPane == LadangGridPane) {
            sourceGridPaneName = "LadangGridPane";
        } else if (sourceGridPane == DeckGridPane) {
            sourceGridPaneName = "DeckGridPane";
        }
        System.out.println(sourceGridPaneName);
        if(gameManager.getCurrentPlayer() == 2 && sourceGridPaneName.equals("LadangGridPane")){
            return;
        } //larang player musuh untuk menggeser kartu yang ada di ladang
        if (!isPlaceholderImage(IMGSource)) {
            Dragboard db = IMGSource.startDragAndDrop(TransferMode.ANY);
            ClipboardContent cb = new ClipboardContent();
            cb.putImage(IMGSource.getImage());
            db.setContent(cb);
        }
        event.consume();
    }

    @FXML
    void handleDragDoneIMG(DragEvent event) {
        if (event.getTransferMode() != null) {
            ImageView IMGSource = (ImageView) event.getSource();
            Image img = new Image(getClass().getResource(PLACEHOLDER_IMAGE_URL).toString());
            IMGSource.setImage(img);
            updateDraggableStatus(IMGSource);
            Image imgS = IMGSource.getImage();
            System.out.println(imgS.getUrl());
        }
        event.consume();
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
        if (event.getDragboard().hasImage() && target != source ) {
            if(isPlaceholderImage(target)) {
                target.setImage(event.getDragboard().getImage());
                event.setDropCompleted(true);
                updateDraggableStatus(target);
                //Debug Koordinat

                //cari koordinat source
                Integer sourceRow = GridPane.getRowIndex(source);
                Integer sourceColumn = GridPane.getColumnIndex(source);
                //cari koordinat target
                Integer targetRow = GridPane.getRowIndex(target);
                Integer targetColumn = GridPane.getColumnIndex(target);
                //handle nilai null
                sourceRow = (sourceRow == null) ? 0 : sourceRow;
                sourceColumn = (sourceColumn == null) ? 0 : sourceColumn;
                targetRow = (targetRow == null) ? 0 : targetRow;
                targetColumn = (targetColumn == null) ? 0 : targetColumn;

                //dapetin parent dari imageviewnya dari gridpane yang mana
                GridPane sourceGridPane = (GridPane) source.getParent();
                GridPane targetGridPane = (GridPane) target.getParent();

                String sourceGridPaneName = "";
                String targetGridPaneName = "";

                if (sourceGridPane == LadangGridPane) {
                    sourceGridPaneName = "LadangGridPane";
                } else if (sourceGridPane == DeckGridPane) {
                    sourceGridPaneName = "DeckGridPane";
                }

                if (targetGridPane == LadangGridPane) {
                    targetGridPaneName = "LadangGridPane";
                } else if (targetGridPane == DeckGridPane) {
                    targetGridPaneName = "DeckGridPane";
                }
                System.out.println("ImageView asal - baris: " + sourceRow + ", kolom: " + sourceColumn + " GridPane: " + sourceGridPaneName);
                System.out.println("ImageView tujuan - baris: " + targetRow + ", kolom: " + targetColumn + " GridPane: " + targetGridPaneName);
            }
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
    }

    private boolean isPlaceholderImage(ImageView imageView) {
        Image image = imageView.getImage();
        if (image == null) {
            return false;
        }
        String imageUrl = image.getUrl();
        if (imageUrl == null) {
            return false;
        }
        String placeholderUrl = getClass().getResource(PLACEHOLDER_IMAGE_URL).toString();
        return imageUrl.equals(placeholderUrl);
    }

    private void updateDraggableStatus(ImageView imageView) {
        if (isPlaceholderImage(imageView)) {
            imageView.setOnDragDetected(null);
        } else {
            imageView.setOnDragDetected(this::handleDragDetectIMG);
        }
    }
    @FXML
    public void initialize() {
        System.out.println("Giliran Pemain: "+gameManager.getCurrentPlayer());
        CurrentPlayerLabel.setText("Pemain: "+gameManager.getCurrentPlayer());
        System.out.println("Turn Ke- "+gameManager.getCurrentTurn());
        turnCount.setText(Integer.toString(gameManager.getCurrentTurn()));
        System.out.println("Gulden Pemain 1: "+gameManager.getPlayer(0).getGulden());
        Player1Gold.setText(Integer.toString(gameManager.getPlayer(0).getGulden()));
        System.out.println("Gulden Pemain 2: "+gameManager.getPlayer(1).getGulden());
        Player2Gold.setText(Integer.toString(gameManager.getPlayer(1).getGulden()));
    }
}
