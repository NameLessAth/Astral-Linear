package com.astrallinear.astrallinear.SaveLoad;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Kartu.KartuHewan;
import com.astrallinear.astrallinear.Kartu.KartuItem;
import com.astrallinear.astrallinear.Kartu.KartuProduk;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Toko.Toko;
import com.astrallinear.astrallinear.TxtSaveLoad.TxtLoad;
import com.astrallinear.astrallinear.TxtSaveLoad.TxtSave;

public class SaveLoadTest {
    @Test //test
    public void savePlayerTest() throws Exception{
        // Save.SaveGameState(null, null, null);
        Pemain pemain = new Pemain();
        pemain.addActiveCard(new KartuHewan("sapi"));
        pemain.addActiveCard(new KartuHewan("sapi"));
        pemain.addActiveCard(new KartuHewan("sapi"));
        pemain.addActiveCard(new KartuHewan("sapi"));
        pemain.addActiveCard(new KartuHewan("sapi"));
        pemain.addActiveCard(new KartuHewan("sapi"));

        pemain.getLadang().spawn_at(new KartuHewan("sapi"), 1, 1);
        pemain.getLadang().spawn_at(new KartuHewan("beruang"), 2, 1);
        // pemain.getLadang().kartu_delay(1, 1);
        // pemain.getLadang().kartu_accelerate(1, 1);

        // System.err.println(pemain.getDeck().getRemainingInactiveDeck());
        TxtSave.SavePlayerState(pemain, "test/tes", "pemain3");
    }

    @Test //test2
    public void SaveGameStateTest() throws Exception{
        GameManager gameManager = GameManager.getInstance();
        GameManager.gameManagerReset();
        Pemain pemain1 = gameManager.getPlayer(0);
        Pemain pemain2 = gameManager.getPlayer(1);
        
        pemain1.addActiveCard(new KartuHewan("sapi"));
        pemain1.addActiveCard(new KartuHewan("ayam"));
        
        pemain2.addActiveCard(new KartuHewan("hiu_darat"));
        pemain2.addActiveCard(new KartuHewan("beruang"));
        pemain1.getLadang().spawn_at(new KartuHewan("sapi"), 1, 1);
        pemain1.getLadang().spawn_at(new KartuHewan("beruang"), 2, 3);
        // pemain1.getLadang().kartu_delay(1, 1);
        pemain1.getLadang().get(1, 1).addItemAktif(new KartuItem("accelerate"));
        pemain1.getLadang().get(1, 1).addItemAktif(new KartuItem("accelerate"));
        pemain1.getLadang().get(1, 1).addItemAktif(new KartuItem("accelerate"));
        pemain1.getLadang().get(1, 1).addItemAktif(new KartuItem("protect"));
        
        Toko toko = Toko.getToko();

        toko.sellProduct(new KartuProduk("sirip_hiu"));
        toko.sellProduct(new KartuProduk("susu"));

        TxtSave.SaveGameState(gameManager, toko, "test/tes");
    }

    @Test //test3
    public void SaveAndLoadTest() throws Exception{
        GameManager gameManager = GameManager.getInstance();
        GameManager.gameManagerReset();
        Pemain pemain1 = gameManager.getPlayer(0);
        Pemain pemain2 = gameManager.getPlayer(1);
        
        pemain1.addActiveCard(new KartuHewan("sapi"));
        pemain1.addActiveCard(new KartuHewan("ayam"));
        
        pemain2.addActiveCard(new KartuHewan("hiu_darat"));
        pemain2.addActiveCard(new KartuHewan("beruang"));
        pemain1.getLadang().spawn_at(new KartuHewan("sapi"), 1, 3);
        pemain1.getLadang().spawn_at(new KartuHewan("beruang"), 3, 4);
        // pemain1.getLadang().kartu_delay(1, 1);
        pemain1.getLadang().get(1, 3).addItemAktif(new KartuItem("accelerate"));
        pemain1.getLadang().get(1, 3).addItemAktif(new KartuItem("accelerate"));
        pemain1.getLadang().get(1, 3).addItemAktif(new KartuItem("accelerate"));
        pemain1.getLadang().get(1, 3).addItemAktif(new KartuItem("protect"));
        gameManager.nextTurn();
        
        Toko toko = Toko.getToko();

        toko.sellProduct(new KartuProduk("sirip_hiu"));
        toko.sellProduct(new KartuProduk("susu"));

        TxtSave.SaveGameState(gameManager, toko, "test/tes");
        TxtLoad.LoadGame(gameManager, toko, "test/tes");
        TxtSave.SaveGameState(gameManager, toko, "test/tes1");
    }
}
