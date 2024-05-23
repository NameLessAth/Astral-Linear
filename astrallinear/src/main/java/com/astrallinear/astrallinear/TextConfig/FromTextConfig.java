package com.astrallinear.astrallinear.TextConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.astrallinear.astrallinear.Kartu.Kartu;
import com.astrallinear.astrallinear.Kartu.KartuProduk;
import com.astrallinear.astrallinear.Ladang.Ladang;
import com.astrallinear.astrallinear.Pemain.Pemain;
import com.astrallinear.astrallinear.Toko.Toko;

public class FromTextConfig {

    private Integer currentTurn;
    private Toko toko;
    private Pemain pemain1;
    private Pemain pemain2;
    private Ladang ladang1;
    private Ladang ladang2;

    public FromTextConfig(String folderPath){
        setState(folderPath + "/gamestate.txt");
        setPlayer(folderPath);
    }

    private void setState(String statePath){
        try (BufferedReader br = new BufferedReader(new FileReader(statePath))) {
            String line;

            line = br.readLine();
            this.currentTurn = Integer.valueOf(line);
            
            line = br.readLine();
            Integer tokoCount = Integer.valueOf(line);
            toko = Toko.getToko();
            toko.resetToko();

            for(int i = 0; i < tokoCount; i++){
                line = br.readLine();
                String[] word = line.split("\\s+");
                Integer count = Integer.valueOf(word[1]);
                for(int j = 0; j < count; j++){
                    toko.sellProduct(new KartuProduk(word[0]));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPlayer(String folderPath){
        ladang1 = new Ladang();
        ladang2 = new Ladang();
    }

    public Ladang getLadangSatu(){
        return ladang1;
    }

    public Ladang getLadangDua(){
        return ladang2;
    }

    public Integer getTurn(){
        return currentTurn;
    }

    public Pemain getPemain1(){
        return pemain1;
    }

    public Pemain getPemain2(){
        return pemain2;
    }

}
