package com.astrallinear.astrallinear;

import java.lang.reflect.Field;
import java.util.*;
import com.astrallinear.astrallinear.Deck.InactiveDeck;
import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Kartu.Kartu;
import com.astrallinear.astrallinear.Kartu.KartuProduk;
import com.astrallinear.astrallinear.Kartu.KartuHewan;
import com.astrallinear.astrallinear.Kartu.KartuItem;
import com.astrallinear.astrallinear.Kartu.KartuMakhluk;
import com.astrallinear.astrallinear.Deck.Deck;
import com.astrallinear.astrallinear.Ladang.Ladang;
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

public class Player1FieldController implements Initializable{

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
    public Label BearAttackTimer;
    @FXML
    private GridPane BearAttackMonitor;
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
    Scene popupScene;
    Stage popupStage;
    Scene PanenPopUpScene;
    Stage PanenPopUpStage;
    Scene CardDetailPopUpScene;
    Stage CardDetailPopUpStage;
    private static final String PLACEHOLDER_IMAGE_URL = "Placeholder/EmptyCell.png";
    private static final String SAFE_GRID_IMAGE_URL = "BearAttackOverlay/SafeGrid.png";
    private static final String UNDER_ATTACK_GRID_URL = "BearAttackOverlay/UnderAttackGrid.png";
    private static GameManager gameManager;
    static {
        try {
            gameManager = GameManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


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
            gameManager.setPreviousPressedButton("EnemyField");
            if(popupStage != null){
                popupStage.close();
            }
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
        gameManager.setPreviousPressedButton("LoadPlugin");
        if(popupStage != null){
            popupStage.close();
        }
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
        gameManager.setPreviousPressedButton("LoadState");
        if(popupStage != null){
            popupStage.close();
        }
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
            gameManager.setPreviousPressedButton("MyField");
            if(popupStage != null){
                popupStage.close();
            }
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
        gameManager.getLadangPemain1().age_all_plants();
        
        if(currentPlayer == 2){
            Alert nextButtonAlert = new Alert(AlertType.ERROR);
            nextButtonAlert.setTitle("Next Button Error");
            nextButtonAlert.setHeaderText("Pemain 2, kamu masih di ladang lawan! Kembali ke ladangmu terlebih dahulu untuk lanjut ke turn berikutnya!");
            nextButtonAlert.show();
        } else {
            gameManager.setPreviousPressedButton("Next");
            if(popupStage != null){
                popupStage.close();
            }
            gameManager.state = 0;
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
        gameManager.setPreviousPressedButton("SaveState");
        if(popupStage != null){
            popupStage.close();
        }
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
        gameManager.setPreviousPressedButton("Shop");
        if(popupStage != null){
            popupStage.close();
        }
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
        Integer r = GridPane.getRowIndex(IMGSource);
        Integer c = GridPane.getColumnIndex(IMGSource);
        r = (r == null ? 0 : r);
        c = (c == null ? 0 : c);

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

        try {

            if (gameManager.getCurrentPlayer() == 2 && (sourceGridPane == DeckGridPane) && !(gameManager.getPlayer(1).getDeck().getActiveCard(c) instanceof KartuItem)) return;
        } catch (Exception e) { System.out.println(c); }
            
        if (!isPlaceholderImage(IMGSource)) {

            boolean empty = false;
            if ((GridPane) IMGSource.getParent() == LadangGridPane) {
                empty = !gameManager.getLadangPemain1().is_filled(r, c);
            } else {
                empty = gameManager.getCurrentPlayerInstance().getDeck().isEmpty(c);
                System.out.println(c);
                System.out.println(empty);
            }

            if (!empty) {
                Dragboard db = IMGSource.startDragAndDrop(TransferMode.ANY);
                ClipboardContent cb = new ClipboardContent();
                cb.putImage(IMGSource.getImage());
                db.setContent(cb);
            }
        }

        // event.consume();
    }

    @FXML
    void handleDragDoneIMG(DragEvent event) {
        // if (event.getTransferMode() != null) {
        //     ImageView IMGSource = (ImageView) event.getSource();
        //     Image img = new Image(getClass().getResource(PLACEHOLDER_IMAGE_URL).toString());
        //     IMGSource.setImage(img);
        //     updateDraggableStatus(IMGSource);
        //     Image imgS = IMGSource.getImage();
        //     System.out.println(imgS.getUrl());
        // }
        // event.consume();
    }

    void custom(DragEvent event) {
        if (event.getTransferMode() != null) {

            ImageView IMGSource = (ImageView) event.getGestureSource();
            ImageView IMGTarget = (ImageView) event.getGestureTarget();
            
            System.out.println((Object)IMGTarget);
            System.out.println((Object)IMGTarget);

            if (IMGSource == IMGTarget) {
                event.consume();
                updateDraggableStatus(IMGSource);
                System.out.println("same");
                return;
            }

            Image img = new Image(getClass().getResourceAsStream(PLACEHOLDER_IMAGE_URL));
            IMGSource.setImage(img);
            updateDraggableStatus(IMGSource);

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
    void handleIMGDrop(DragEvent event) throws Exception {
        ImageView target = (ImageView) event.getGestureTarget();
        ImageView source = (ImageView) event.getGestureSource();
        if (event.getDragboard().hasImage() && target != source) {

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

            System.out.println("ImageView asal - baris: " + sourceRow + ", kolom: " + sourceColumn + " GridPane: " + sourceGridPaneName);
            System.out.println("ImageView tujuan - baris: " + targetRow + ", kolom: " + targetColumn + " GridPane: " + targetGridPaneName);
            boolean fromDeck = sourceGridPane == DeckGridPane;
            
            // logic
            Deck deck = gameManager.getCurrentPlayerInstance().getDeck();
            Ladang ladang = gameManager.getLadangPemain1();

            try {
                if (!fromDeck) {
                    ladang.move(sourceRow, sourceColumn, targetRow, targetColumn);
                }
                if (fromDeck) {
                    KartuMakhluk obj = (KartuMakhluk) deck.getActiveCard(sourceColumn);
                    ladang.spawn_at((KartuMakhluk) obj, targetRow, targetColumn);
                    deck.deleteActiveCard(sourceColumn);
                }
                
                target.setImage(event.getDragboard().getImage());
                event.setDropCompleted(true);
                updateDraggableStatus(target);

            } 
            catch (ClassCastException e) { 
                // this means player has moved a product/item from deck
                Kartu obj = deck.getActiveCard(sourceColumn);
                if (obj instanceof KartuProduk) {
                    try {
                        ladang.give_food_at((KartuProduk) obj, targetRow, targetColumn);
                    } catch (Exception e2) { throw e2 ; } // beri makan gagal
                }
                else { // item. todo: implement


                    try {
                        if (obj.getNama().equals("accelerate")) {
                            ladang.kartu_accelerate(targetRow, targetColumn);
                        }
                        if (obj.getNama().equals("instant_harvest")) {
                            if (deck.countEmptySlot() == 0) {
                                Alert deckIsFullAlert = new Alert(AlertType.ERROR);
                                deckIsFullAlert.setTitle("Tidak ada tempat kosong!");
                                deckIsFullAlert.setHeaderText("Deck aktif Anda masih penuh! Silakan kosongkan minimal satu slot agar bisa panen!");
                                deckIsFullAlert.showAndWait();
                                return;
                            }
                            else {
                                KartuProduk produk = ladang.kartu_instant_harvest(targetRow, targetColumn);
                                deck.addKartu(produk);
                                Image img = new Image(Main.class.getResource(PLACEHOLDER_IMAGE_URL).toString());
                                target.setImage(img);
                                initialize();
                            }
                        }
                        if (obj.getNama().equals("bear_trap")) {
                            ladang.kartu_trap(targetRow, targetColumn);
                        }
                        if (obj.getNama().equals("delay")) {
                            ladang.kartu_delay(targetRow, targetColumn);
                        }
                        if (obj.getNama().equals("destroy")) {
                            ladang.kartu_destroy(targetRow, targetColumn);
                            Image img = new Image(Main.class.getResource(PLACEHOLDER_IMAGE_URL).toString());
                            target.setImage(img);
                        }
                        if (obj.getNama().equals("protect")) {
                            ladang.kartu_protect(targetRow, targetColumn);
                        }
                        deck.deleteActiveCard(sourceColumn);

                    } catch (Exception e2) { 
                        if (e2.getMessage().equals("sel ini di-protect!")) {
                            Alert isProtectedAlert = new Alert(AlertType.ERROR);
                            isProtectedAlert.setTitle("Eits! Tidak bisa!");
                            isProtectedAlert.setHeaderText("Kamu tidak bisa serang sel ini karena pemain lawan melindungi dengan item protect!");
                            isProtectedAlert.show();
                        }
                        else if (e2.getMessage().equals("serang sel sendiri")) {
                            Alert suicideAlert = new Alert(AlertType.ERROR);
                            suicideAlert.setTitle("Lho.. jangan serang ladang sendiri dong!");
                            suicideAlert.setHeaderText("Silakan ganti ladang terlebih dahulu sebelum menyerang");
                            suicideAlert.show();
                        }
                        else if (e2.getMessage().equals("bukan sel sendiri")) {
                            Alert notSelfLadang = new Alert(AlertType.ERROR);
                            notSelfLadang.setTitle("Lho.. Ini bukan ladangmu!");
                            notSelfLadang.setHeaderText("Silakan ganti ladang terlebih dahulu sebelum menggunakan kartu item powerup");
                            notSelfLadang.show();
                        }
                        event.setDropCompleted(true);
                        event.consume();
                        return;
                    }
                }
                

                event.setDropCompleted(true);
                event.consume();
                custom(event);

                return;

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                event.setDropCompleted(true);
                event.consume();
                return;
            }

        } else {
            event.setDropCompleted(false);
        }
        custom(event);
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

    void OnPanenClick(MouseEvent event) throws IOException {
        System.out.println("OnPanenClick");
        //block window game utama
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/blocker.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage gameStage = gameManager.getGameStage();
        gameStage.setTitle("Tubes 2 OOP");
        gameStage.setScene(scene);
        gameStage.setResizable(false);
        gameStage.show();
        ImageView source = (ImageView) event.getSource();
        //cari koordinat source
        Integer sourceRow = GridPane.getRowIndex(source);
        Integer sourceColumn = GridPane.getColumnIndex(source);
        sourceRow = (sourceRow == null) ? 0 : sourceRow;
        sourceColumn = (sourceColumn == null) ? 0 : sourceColumn;
        Kartu kartu;
        try {
            kartu = gameManager.getLadangPemain1().get(sourceRow, sourceColumn);
        } catch (Exception e) {System.out.println(e.getMessage()); return ;}

        if(gameManager.getCurrentPlayer() == 2){ //kalau yang buka scene ini adalah pemain 1, keluarin pesan error
            Alert incorrectPlayer = new Alert(AlertType.ERROR);
            incorrectPlayer.setTitle("Ladang ini bukan punyamu");
            incorrectPlayer.setHeaderText("Hei pemain 2! Ladang ini bukan punyamu! \nPergi sana, jangan coba-coba kau panen di sini!");
            incorrectPlayer.show();
        } else {
            if(isPlaceholderImage(source)){ //jika petak kosong, keluarkan error
                Alert emptyCell = new Alert(AlertType.ERROR);
                emptyCell.setTitle("Petak kosong");
                emptyCell.setHeaderText("Pemain 1, petak ini kosong!");
                emptyCell.show();
            } else {
                System.out.println("Pop-up panen");
                FXMLLoader PanenPopUpLoader = new FXMLLoader(Main.class.getResource("View/panen.fxml"));
                PanenPopUpScene = new Scene(PanenPopUpLoader.load());
                
                PanenController pc = PanenPopUpLoader.getController();
                
                pc.setKartuRowCol(kartu, sourceRow, sourceColumn);
                
                pc.initialize();

                PanenPopUpStage = new Stage();
                PanenPopUpStage.setTitle("Panen pop-up");
                PanenPopUpStage.setScene(PanenPopUpScene);
                PanenPopUpStage.setResizable(false);
                PanenPopUpStage.setOnCloseRequest(e -> {
                    e.consume(); // Consumes the close request event
                });
                PanenPopUpStage.showAndWait();
            }
        }
    }
    
    @FXML
    void OnCardDetailClick(MouseEvent event) throws Exception {
        // System.out.println("OnCardDetailClick");

        // info
        ImageView source = (ImageView) event.getSource();
        //cari koordinat source
        Integer sourceRow = GridPane.getRowIndex(source);
        Integer sourceColumn = GridPane.getColumnIndex(source);
        sourceRow = (sourceRow == null) ? 0 : sourceRow;
        sourceColumn = (sourceColumn == null) ? 0 : sourceColumn;
        GridPane sourceGridPane = (GridPane) source.getParent();
        boolean onDeck = (sourceGridPane == DeckGridPane) ;

        if (!onDeck && !gameManager.getLadangPemain1().is_filled(sourceRow, sourceColumn)) return;
        if (onDeck && gameManager.getCurrentPlayerInstance().getDeck().isEmpty(sourceRow)) return;

        //block window game utama
        // FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/blocker.fxml"));
        // Scene scene = new Scene(fxmlLoader.load());
        // Stage gameStage = gameManager.getGameStage();
        // gameStage.setTitle("Tubes 2 OOP");
        // gameStage.setScene(scene);
        // gameStage.setResizable(false);
        // gameStage.show();

        Kartu kartu;
        try {
            if (onDeck) {
                kartu = gameManager.getCurrentPlayerInstance().getDeck().getActiveCard(sourceColumn);
            }
            else {   
                kartu = gameManager.getLadangPemain1().get(sourceRow, sourceColumn);
            }
        } catch (Exception e) { System.out.println(e.getMessage()); return ; }

        if (!onDeck && ((KartuMakhluk)kartu).isSiapPanen()) {
            OnPanenClick(event);
            initialize();
            return;
        }

        FXMLLoader CardDetailPopUpLoader = new FXMLLoader(Main.class.getResource("View/carddetail.fxml"));
        
        Parent root = CardDetailPopUpLoader.load();

        CardDetailController cdc = CardDetailPopUpLoader.getController();
        
        cdc.setKartu(kartu);
        
        cdc.initialize();

        CardDetailPopUpStage = new Stage();
        CardDetailPopUpStage.setTitle("Card Detail pop-up");
        CardDetailPopUpStage.setScene(new Scene(root));
        CardDetailPopUpStage.setResizable(false);
        CardDetailPopUpStage.setOnCloseRequest(e -> {
            e.consume(); // Consumes the close request event
        });
        CardDetailPopUpStage.show();

    }
    @FXML
    public void initialize() throws Exception {
        //setup monitor serangan beruang
        for (var node : BearAttackMonitor.getChildren()) {
            if (node instanceof ImageView) {
                ((ImageView) node).setImage(new Image(Main.class.getResource(SAFE_GRID_IMAGE_URL).toString()));
            }
        }
        // shuffle kartu
        if (gameManager.state == 0) {
            gameManager.state = 2;
            FXMLLoader popupLoader = new FXMLLoader(Main.class.getResource("View/shuffle.fxml"));
            Scene popupScene = new Scene(popupLoader.load());
            Stage popupStage = new Stage();
            popupStage.setTitle("Shuffle Pop-up");
            popupStage.setScene(popupScene);
            popupStage.setResizable(false);
            popupStage.setOnCloseRequest(event -> {
                event.consume(); // Consumes the close request event
            });
            popupStage.showAndWait();
        }
        
        // display ladang
        for (Node node : LadangGridPane.getChildren()) {
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);
            r = (r == null ? 0 : r);
            c = (c == null ? 0 : c);
            try {
                KartuMakhluk kartu = gameManager.getLadangPemain1().get(r, c);
                System.out.println(r + ' ' + c);
                String path = kartu.getPathToImg();
                System.out.println(path);

                Image img = new Image(Main.class.getResource(path).toString());
                ((ImageView) node).setImage(img);
                
            } catch (Exception e) {
                Image img = new Image(Main.class.getResource(PLACEHOLDER_IMAGE_URL).toString());
                try { ((ImageView) node).setImage(img); }
                catch (Exception e2){System.out.println(e2.getLocalizedMessage());}
            }
        }

        // display deck
        Deck deck = gameManager.getCurrentPlayerInstance().getDeck();

        System.out.println(deck.countEmptySlot());
        for (Node node : DeckGridPane.getChildren()) {
            Integer c = GridPane.getColumnIndex(node);
            c = (c == null ? 0 : c);
            try {
                Kartu kartu = deck.getActiveCard(c);
                String path = kartu.getPathToImg();
                System.out.println(path);
                
                Image img = new Image(Main.class.getResource(path).toString());
                ((ImageView) node).setImage(img);
                
            } catch (Exception e) {
                Image img = new Image(Main.class.getResource(PLACEHOLDER_IMAGE_URL).toString());
                try { ((ImageView) node).setImage(img); }
                catch (Exception e3) {System.out.println(e3.getLocalizedMessage());}

            }
        }
        
        // debug print
        Pemain curPlayer = gameManager.getCurrentPlayerInstance();
        System.out.println("Giliran Pemain: "+gameManager.getCurrentPlayer());
        CurrentPlayerLabel.setText("Pemain: "+gameManager.getCurrentPlayer());
        System.out.println("Turn Ke- "+gameManager.getCurrentTurn());
        turnCount.setText(Integer.toString(gameManager.getCurrentTurn()));
        System.out.println("Gulden Pemain 1: "+gameManager.getPlayer(0).getGulden());
        Player1Gold.setText(Integer.toString(gameManager.getPlayer(0).getGulden()));
        System.out.println("Gulden Pemain 2: "+gameManager.getPlayer(1).getGulden());
        Player2Gold.setText(Integer.toString(gameManager.getPlayer(1).getGulden()));
        System.out.println("Kartu tersisa di deck: "+ curPlayer.getDeck().getRemainingInactiveDeck());
        CardLeftLabel.setText(Integer.toString(curPlayer.getDeck().getRemainingInactiveDeck()));
        
        BearAttackTimer.setVisible(false);

        Set<Button> daftar_button = new HashSet<>(Arrays.asList());
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.get(this) instanceof Button) daftar_button.add((Button)f.get(this));
        }
        
        System.out.println(BearAttackTimer);
        System.out.println("State: " + gameManager.state);
        if (gameManager.state == 2) {
            
            if (BearAttack.isAttacking() && gameManager.getCurrentTurn() > 4) {
            // if (true) { // kalau mau paksa setiap turn

                Alert BeruangAlert = new Alert(AlertType.WARNING);
                BeruangAlert.setTitle("WADAW!");
                BeruangAlert.setHeaderText("KAMU SEDANG DISERANG OLEH BERUANG!!!");
                BeruangAlert.showAndWait();

                System.out.println("uwoooghh");
                BearAttackTimer.setVisible(true);
                BearAttackTimer.setVisible(true);
                TimerProcRun tpr = new TimerProcRun();
                tpr.start();
                BearAttackRun brt = new BearAttackRun(tpr);
                brt.start();

                List<Integer> coordinate_info = brt.brt.attackLadang(gameManager.getCurrentPlayerInstance(), this);
               
                for (Button b : daftar_button) b.setDisable(true);

                // buat mark di GUI
                Integer startPointRow = coordinate_info.get(0);
                Integer startPointColumn = coordinate_info.get(1);
                Integer attackedWidth = coordinate_info.get(2);
                Integer attackedHeight = coordinate_info.get(3);
                Integer endPointRow = startPointRow+attackedHeight;
                Integer endPointColumn = startPointColumn+attackedWidth;
                System.out.println("StartPointRow: "+startPointRow);
                System.out.println("StartPointColumn: "+startPointColumn);
                System.out.println("attackedWidth: "+attackedWidth);
                System.out.println("attackedHeight: "+attackedHeight);
                //tandai petak yang diserang
                for (int row = startPointRow; row < endPointRow; row++) {
                    for (int col = startPointColumn; col < endPointColumn; col++) {
                        ImageView imageView = getBearAttackMonitorImageView(BearAttackMonitor,col, row);
                        if (imageView != null) {
                            imageView.setImage(new Image(Main.class.getResource(UNDER_ATTACK_GRID_URL).toString()));
                        }
                    }
                }

            }

            gameManager.state = 1;
        }
        else {
            System.out.println("Done!!");
            for (Button b : daftar_button) b.setDisable(false);
            //setup monitor serangan beruang
            for (var node : BearAttackMonitor.getChildren()) {
                if (node instanceof ImageView) {
                    ((ImageView) node).setImage(new Image(Main.class.getResource(SAFE_GRID_IMAGE_URL).toString()));
                }
            }
        }
    }

    public Label getBearTimer() {
        return this.BearAttackTimer;
    }
    private ImageView getBearAttackMonitorImageView(GridPane gridPane, int col, int row){
        for (var node : gridPane.getChildren()) {
            Integer colIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);
            if ((colIndex == null ? 0 : colIndex) == col && (rowIndex == null ? 0 : rowIndex) == row && node instanceof ImageView) {
                return (ImageView) node;
            }
        }
        return null;
    }
}
