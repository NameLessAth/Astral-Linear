package com.astrallinear.astrallinear.Plugin;


// these are the dependencies you should import in your save load plugin
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


public class SaveLoadDependenciesService {
    
    public GameManager getGameManagerInstance() throws Exception {
        return GameManager.getInstance();
    }
    
    public Toko getTokoInstance() throws Exception {
        return Toko.getToko();
    }

}