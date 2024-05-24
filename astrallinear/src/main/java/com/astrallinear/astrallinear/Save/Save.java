package com.astrallinear.astrallinear.Save;

import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Kartu.KartuHewan;
import com.astrallinear.astrallinear.Kartu.KartuTanaman;
import com.astrallinear.astrallinear.Ladang.Ladang;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Toko.Toko;

public class Save {
    public static void SaveGameState(GameManager gmg, Toko shop, String filepath) throws Exception{
        FileWriter bufferWrite = new FileWriter(filepath + "/gamestate.txt");

        // write current turn
        bufferWrite.write(String.format("%d", gmg.getCurrentTurn()) + '\n');

        // write jumlah item di toko
        bufferWrite.write(String.format("%d", shop.getIsiToko().size()) + '\n');

        // write tiap item di toko
        List<String> listItems = new ArrayList<>(shop.getIsiToko().keySet());
        for (String iterate : listItems){
            bufferWrite.write(iterate + " " + String.format("%d", shop.getIsiToko().get(iterate)) + '\n');
        }

        bufferWrite.close();
    }
    public static void SavePlayerState(Pemain ply, String filepath, String namaPemain) throws Exception{
        File directory = new File(filepath);
        if (!directory.exists()) directory.mkdirs(); 
        FileWriter bufferWrite = new FileWriter(new File(directory, namaPemain + ".txt"));
        
        // write gulden
        bufferWrite.write(String.format("%d\n", ply.getGulden()));
        // write deck inaktif
        bufferWrite.write(String.format("%d\n", ply.getDeck().getRemainingInactiveDeck()));
        // write deck aktif
        bufferWrite.write(String.format("%d\n", (6-ply.getDeck().countEmptySlot())));
        
        // write tiap kartunya
        for (int i = 0; i < (6); i++){
            try{
                bufferWrite.write(((char)(i+'A')) + "01" + ply.getDeck().getActiveCard(i-1).getNama() + '\n');
            } catch (Exception e) {}
        }
        
        Ladang ldg = ply.getLadang();
        // write ladang yang terisi
        Integer countLdg = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                if (ldg.is_filled(i, j)) countLdg++;
            }
        } bufferWrite.write(String.format("%d\n", countLdg));

        // write elemen2 ladang yang terisi
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                if (ldg.is_filled(i, j)) {
                    // write koordinat
                    bufferWrite.write(((char)(j+'A')) + (String.format("%02d", i+1)) + " ");
                    // write namanya
                    bufferWrite.write(ldg.get(i, j).getNama() + " ");
                    // write berat/umurnya
                    if (ldg.get(i, j) instanceof KartuHewan) bufferWrite.write(String.format("%d ", ((KartuHewan)ldg.get(i, j)).getBerat()));
                    else if (ldg.get(i, j) instanceof KartuTanaman) bufferWrite.write(String.format("%d ", ((KartuTanaman)ldg.get(i, j)).getUmur()));
                    // write kartu aktif
                    HashMap<String, Integer> activeCardTemp = ldg.get(i, j).getItemAktif();
                    bufferWrite.write(String.format("%d", activeCardTemp.size()));
                    for (String iterate : activeCardTemp.keySet()){
                        bufferWrite.write(" " + iterate);
                    }
                    bufferWrite.write('\n');
                }
            }
        }

        bufferWrite.close();
    }
}
