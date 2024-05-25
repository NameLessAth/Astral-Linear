package com.astrallinear.astrallinear.JsonSaveLoad;

import java.io.File;
import java.util.HashMap;

import com.astrallinear.astrallinear.Deck.Deck;
import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Kartu.Kartu;
import com.astrallinear.astrallinear.Kartu.KartuHewan;
import com.astrallinear.astrallinear.Kartu.KartuItem;
import com.astrallinear.astrallinear.Kartu.KartuMakhluk;
import com.astrallinear.astrallinear.Kartu.KartuProduk;
import com.astrallinear.astrallinear.Kartu.KartuTanaman;
import com.astrallinear.astrallinear.Ladang.Ladang;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Plugin.SaveLoadPlugin;
import com.astrallinear.astrallinear.Toko.Toko;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode; 


public class JsonSaveLoad implements SaveLoadPlugin {
    public void save(GameManager gmg, Toko shop, String filepath) throws Exception{
        // bikin directory dulu
        File directory = new File(filepath);
        if (!directory.exists()) directory.mkdirs();
        // declare var
        ObjectMapper objMap = new ObjectMapper();
        ObjectNode gameStateSave = objMap.createObjectNode();
        // write current turn
        gameStateSave.put("current_turn", gmg.getCurrentTurn());
        // write juml item shop
        gameStateSave.put("jml_item_shop", shop.getIsiToko().size());
        // write tiap item di toko
        int i = 1;
        for (String iterate : shop.getIsiToko().keySet()){
            gameStateSave.put(String.format("nama_item_%d", i), iterate);
            gameStateSave.put(String.format("jumlah_item_%d", i), shop.getIsiToko().get(iterate));
            i++;
        }
        // write game.json
        objMap.writeValue(new File(directory,"gamestate.json"), gameStateSave);

        // bagian player1 dan player2
        JsonSaveLoad.savePlayer(directory, gmg.getPlayer(0), filepath, "player1");
        JsonSaveLoad.savePlayer(directory, gmg.getPlayer(1), filepath, "player2");
    }

    private static void savePlayer(File directory, Pemain ply, String filepath, String namaPemain) throws Exception{
        ObjectMapper objMap = new ObjectMapper();
        ObjectNode playerStateSave = objMap.createObjectNode();

        // write gulden
        playerStateSave.put("gulden", ply.getGulden());
        // write deck inaktif
        playerStateSave.put("jumlah_deck", ply.getDeck().getRemainingInactiveDeck());
        // write deck aktif
        playerStateSave.put("jumlah_deck_aktif", 6-ply.getDeck().countEmptySlot());

        // write tiap kartunya
        for (int i = 0; i < (6); i++){
            try{
                playerStateSave.put(String.format("nama_kartu_%d", i+1), ply.getDeck().getActiveCard(i).getNama());
                playerStateSave.put(String.format("posisi_%d", i+1), ((char)(i+'A')) + "01");
            } catch (Exception e) {}
        }

        Ladang ldg = ply.getLadang();
        Integer countLdg = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                if (ldg.is_filled(i, j)) countLdg++;
            }
        } playerStateSave.put("jumlah_kartu_ladang", countLdg); 

        Integer kartuke = 1;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                if (ldg.is_filled(i, j)) {
                    // write koordinat
                    playerStateSave.put(String.format("lokasi_kartu_%d", kartuke), ((char)(j+'A')) + (String.format("%02d", i+1)));
                    // write namanya
                    playerStateSave.put(String.format("nama_kartu_ladang_%d", kartuke), ldg.get(i, j).getNama());
                    // write berat/umurnya
                    if (ldg.get(i, j) instanceof KartuHewan) playerStateSave.put(String.format("berat_kartu_%d", kartuke), ((KartuHewan)ldg.get(i, j)).getBerat());
                    else if (ldg.get(i, j) instanceof KartuTanaman) playerStateSave.put(String.format("umur_kartu_%d", kartuke), ((KartuTanaman)ldg.get(i, j)).getUmur());
                    // write kartu aktif
                    HashMap<String, Integer> activeCardTemp = ldg.get(i, j).getItemAktif();
                    playerStateSave.put(String.format("jumlah_item_aktif_%d", kartuke), activeCardTemp.size());
                    int k = 0;
                    for (String iterate : activeCardTemp.keySet()){
                        playerStateSave.put(String.format("jumlah_%d_kartu_%d",k+1, kartuke), activeCardTemp.get(iterate));
                        playerStateSave.put(String.format("item_%d_kartu_%d", k+1, kartuke), iterate);
                    }
                    kartuke++;
                }
            }
        }
        objMap.writeValue(new File(directory, String.format("%s.json", namaPemain)), playerStateSave);
    }

    public void load(GameManager gmg, Toko shop, String filepath) throws Exception{
        // variable
        ObjectMapper objMap = new ObjectMapper();
        JsonNode gameStateLoad = objMap.readTree(new File(filepath, "gamestate.json"));

        // reset2 variabel
        Toko toko = Toko.getToko();
        toko.resetToko();

        // baca gamestate.json
        // turn
        gmg.setCurrentTurn(gameStateLoad.get("current_turn").asInt());
        int jmlitem = gameStateLoad.get("jml_item_shop").asInt();
        for (int i = 0; i < jmlitem; i++){
            String produknya = gameStateLoad.get(String.format("nama_item_%d", i+1)).asText();
            for(int j = 0; j < gameStateLoad.get(String.format("jumlah_item_%d", i+1)).asInt(); j++){
                toko.sellProduct(new KartuProduk(produknya));
            }
        }

        // baca pemain1 dan pemain2
        gmg.setPlayer(0, loadPlayer(filepath, "player1"));
        gmg.setPlayer(1, loadPlayer(filepath, "player2"));
    }

    private static Pemain loadPlayer(String filepath, String namaPemain) throws Exception{
        ObjectMapper objMap = new ObjectMapper();
        JsonNode playerStateLoad = objMap.readTree(new File(filepath, String.format("%s.json", namaPemain)));

        Integer gulden = playerStateLoad.get("gulden").asInt();
        Integer jmldeckinaktif = playerStateLoad.get("jumlah_deck").asInt();
        Integer jmldeck = playerStateLoad.get("jumlah_deck_aktif").asInt();
        Deck newDeck = new Deck();
        newDeck.setRemainingInactiveDeck(jmldeckinaktif);
        for (int i = 0; i < jmldeck; i++){
            Character posisi = playerStateLoad.get(String.format("posisi_%d", i+1)).asText().charAt(0);
            Integer posisiInt = (Integer) (posisi-'A');
            String namakartu = playerStateLoad.get(String.format("nama_kartu_%d", i+1)).asText();
            Kartu kartu;
            try{
                kartu = new KartuHewan(namakartu);
            } catch(Exception e1){
            try{
                kartu = new KartuTanaman(namakartu);
            } catch(Exception e2){
            try{
                kartu = new KartuItem(namakartu);
            } catch(Exception e3){
            try{
                kartu = new KartuProduk(namakartu);
            } catch(Exception e4){
                throw e4;
            }}}}
            
            newDeck.addKartu(kartu, posisiInt);
        }
        Pemain tempPlayer = new Pemain(gulden, newDeck);
        Ladang tempLadang = new Ladang();

        Integer ladangDitempati = playerStateLoad.get("jumlah_kartu_ladang").asInt();

        for (int i = 0; i < ladangDitempati; i++){
            String posisi = playerStateLoad.get(String.format("lokasi_kartu_%d", i+1)).asText();
            Integer posisiI = Integer.parseInt(posisi.substring(1));
            Integer posisiJ = (Integer) (posisi.charAt(0) -'A');
            
            String namaKartu = playerStateLoad.get(String.format("nama_kartu_ladang_%d", i+1)).asText();
            KartuMakhluk kartuMakhluk;
            try{
                kartuMakhluk = new KartuHewan(namaKartu);
            } catch(Exception e1){
            try{
                kartuMakhluk = new KartuTanaman(namaKartu);
            } catch(Exception e2) {throw e2;}}
            
            if (kartuMakhluk instanceof KartuHewan) ((KartuHewan)kartuMakhluk).setBerat(playerStateLoad.get(String.format("berat_kartu_%d",i+1)).asInt());
            else if (kartuMakhluk instanceof KartuTanaman) ((KartuTanaman)kartuMakhluk).setUmur(playerStateLoad.get(String.format("umur_kartu_%d",i+1)).asInt());
            
            Integer jumlahItemAktif = playerStateLoad.get(String.format("jumlah_item_aktif_%d",i+1)).asInt();
            for (int j = 0; j < jumlahItemAktif; j++){
                Integer jumlahDiTambah = playerStateLoad.get(String.format("jumlah_%d_kartu_%d", j+1, i+1)).asInt();
                String namaDiTambah = playerStateLoad.get(String.format("item_%d_kartu_%d", j+1, i+1)).asText();
                for (int itr = 0; itr < jumlahDiTambah; itr++){
                    kartuMakhluk.addItemAktif(new KartuItem(namaDiTambah));
                }
            }

            tempLadang.spawn_at(kartuMakhluk, posisiI-1, posisiJ);
        }

        tempPlayer.setLadang(tempLadang);
        return tempPlayer;
    }

    public String getExtName(){return "json";}
}
