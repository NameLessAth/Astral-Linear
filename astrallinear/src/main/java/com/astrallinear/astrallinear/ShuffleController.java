package com.astrallinear.astrallinear;

import java.util.*;

import com.astrallinear.astrallinear.Deck.Deck;
import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Kartu.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShuffleController {

    @FXML
    private Button BackToGameButton;

    @FXML
    private GridPane CardShuffleGridPane;

    @FXML
    private Button ShuffleButton;

    private static GameManager gameManager;
    private Queue<Kartu> shuffledCards;
    static {
        try {
            gameManager = GameManager.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void OnBackToGameButton(ActionEvent event) {
        //masukin method sebelum tutup windownya wajib di atas stage.close

        // Queue<Kartu> shuffledCardsCopy = new LinkedList<>();
        // shuffledCardsCopy.addAll(shuffledCards);
        // for (;;) {
        //     Kartu k = shuffledCardsCopy.remove();
        //     System.out.println(k.getNama());
        //     if (shuffledCardsCopy.isEmpty()) break;
        // }
        
        gameManager.getCurrentPlayerInstance().getDeck().acceptShuffle(shuffledCards);
        System.out.println( gameManager.getCurrentPlayerInstance().getDeck().countEmptySlot());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void OnShuffleButton(ActionEvent event) throws Exception {
        //method buat shuffle
        Pemain pemain = gameManager.getCurrentPlayerInstance();
        Deck deck = pemain.getDeck();
        deck.rejectShuffle(shuffledCards);
        Integer deck_kartu_kosong = deck.countEmptySlot();
        shuffledCards = deck.takeShuffle();
        Queue<Kartu> shuffledCardsCopy = new LinkedList<>();
        shuffledCardsCopy.addAll(shuffledCards);
        
        for (Node node : CardShuffleGridPane.getChildren()) {
            if (deck_kartu_kosong-- > 0) {
                Kartu kartu = shuffledCardsCopy.remove();
                Image img = new Image(Main.class.getResource(kartu.getPathToImg()).toString());
                ((ImageView) node).setImage(img);

            } else break;
        }
    }
    
    @FXML
    void initialize() {
        Pemain pemain = gameManager.getCurrentPlayerInstance();
        Deck deck = pemain.getDeck();
        Integer deck_kartu_kosong = deck.countEmptySlot();
        
        try {
            shuffledCards = deck.takeShuffle();
        } catch (Exception e) {System.out.println(e.getMessage()) ; return ; }

        Queue<Kartu> shuffledCardsCopy = new LinkedList<>();
        shuffledCardsCopy.addAll(shuffledCards);
        
        for (Node node : CardShuffleGridPane.getChildren()) {
            if (deck_kartu_kosong-- > 0) {
                Kartu kartu = shuffledCardsCopy.remove();
                Image img = new Image(Main.class.getResource(kartu.getPathToImg()).toString());
                ((ImageView) node).setImage(img);

            } else break;
        }
    }
}

