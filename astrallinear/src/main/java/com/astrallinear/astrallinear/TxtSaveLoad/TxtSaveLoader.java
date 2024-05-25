package com.astrallinear.astrallinear.TxtSaveLoad;

import com.astrallinear.astrallinear.Plugin.SaveLoadPlugin;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Kartu.Kartu;
import com.astrallinear.astrallinear.Kartu.KartuHewan;
import com.astrallinear.astrallinear.Kartu.KartuItem;
import com.astrallinear.astrallinear.Kartu.KartuMakhluk;
import com.astrallinear.astrallinear.Kartu.KartuProduk;
import com.astrallinear.astrallinear.Kartu.KartuTanaman;
import com.astrallinear.astrallinear.Ladang.Ladang;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Toko.Toko;
import javafx.util.Pair;

public class TxtSaveLoader implements SaveLoadPlugin  {
    public void save(GameManager gmg, Toko shop, String filepath) throws Exception {
        TxtSave.SaveGameState(gmg, shop, filepath);
    }
    public void load(GameManager gmg, Toko shop, String filepath) throws Exception {
        TxtLoad.LoadGame(filepath);
    }
    public String getExtName() {
        return "txt";
    }
}