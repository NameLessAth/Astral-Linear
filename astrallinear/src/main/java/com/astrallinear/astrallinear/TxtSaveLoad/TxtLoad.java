package com.astrallinear.astrallinear.TxtSaveLoad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

public class TxtLoad {

    private static Integer currentTurn;
    private static Toko toko;
    private static Pemain pemain1;
    private static Pemain pemain2;
    private static Ladang ladang1;
    private static Ladang ladang2;

    public static void LoadGame(GameManager gameManager, Toko toko, String folderPath) throws Exception{
        toko.resetToko();
        pemain1 = new Pemain();
        pemain2 = new Pemain();
        ladang1 = new Ladang();
        ladang2 = new Ladang();
        setState(folderPath + "/gamestate.txt");
        Pair<Pemain, Ladang> temp = setPlayer(folderPath + "/player1.txt");
        pemain1 = temp.getKey();
        ladang1 = temp.getValue();
        temp = setPlayer(folderPath + "/player2.txt");
        pemain2 = temp.getKey();
        ladang2 = temp.getValue();

        // push loaded data to gameManager
        gameManager.setCurrentTurn(currentTurn);
        pemain1.setLadang(ladang1);
        pemain2.setLadang(ladang2);
        gameManager.setPlayer(0, pemain1);
        gameManager.setPlayer(1, pemain2);
    }

    private static Pair<Integer, Integer> stringToCoordinate(String s){
        int firstVal = (int)s.charAt(0) - 65;
        int secondVal = ((int)s.charAt(1)-48) * 10 + ((int)s.charAt(2)-48)-1;
        return new Pair<Integer,Integer>(firstVal, secondVal);
    }

    private static void setState(String statePath) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(statePath))) {
            String line;

            line = br.readLine().strip();
            TxtLoad.currentTurn = Integer.valueOf(line);
            
            line = br.readLine().strip();
            Integer tokoCount = Integer.valueOf(line);
            toko = Toko.getToko();
            toko.resetToko();

            for(int i = 0; i < tokoCount; i++){
                line = br.readLine().strip();
                String[] word = line.split("\\s+");
                Integer count = Integer.valueOf(word[1]);
                for(int j = 0; j < count; j++){
                    toko.sellProduct(new KartuProduk(word[0]));
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    private static Pair<Pemain, Ladang> setPlayer(String playerPath) throws Exception{
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(playerPath))) {
            Integer count = 0;
            Pemain resPemain = new Pemain();
            Ladang resLadang = new Ladang();

            line = br.readLine().strip();
            resPemain.increaseGulden(Integer.parseInt(line));

            line = br.readLine().strip();
            resPemain.getDeck().setRemainingInactiveDeck(Integer.parseInt(line));

            line = br.readLine().strip();
            count = Integer.parseInt(line);

            for(int i = 0; i < count; i++){
                line = br.readLine().strip();
                Kartu kartu;
                String[] word = line.split("\\s+");
                Integer pos = stringToCoordinate(word[0]).getKey();

                try{
                    kartu = new KartuHewan(word[1]);
                } catch(Exception e1){
                try{
                    kartu = new KartuTanaman(word[1]);
                } catch(Exception e2){
                try{
                    kartu = new KartuItem(word[1]);
                } catch(Exception e3){
                try{
                    kartu = new KartuProduk(word[1]);
                } catch(Exception e4){
                    throw e4;
                }}}}
                resPemain.getDeck().addKartu(kartu, pos);
            }

            line = br.readLine().strip();
            count = Integer.parseInt(line);

            KartuMakhluk kartuMakhluk;
            Pair<Integer, Integer> coordinate;
            for(int i = 0; i < count; i++){
                line = br.readLine().strip();
                String[] word = line.split("\\s+");
                coordinate = stringToCoordinate(word[0]);
                try{
                    KartuHewan kartuTemp = new KartuHewan(word[1]);
                    kartuTemp.setBerat(Integer.parseInt(word[2]));
                    kartuMakhluk = kartuTemp;
                }catch(Exception e1){
                try{
                    KartuTanaman kartuTemp = new KartuTanaman(word[1]);
                    kartuTemp.setUmur(Integer.parseInt(word[2]));
                    kartuMakhluk = kartuTemp;
                }catch(Exception e2){
                    throw e2;
                }}
                resLadang.spawn_at(kartuMakhluk, coordinate.getValue(), coordinate.getKey());
                Integer count1 = Integer.parseInt(word[3]);
                for(int j = 0; j < count1; j++){
                    if(word[j+4].toLowerCase().equals("accelerate"))
                        resLadang.get(coordinate.getValue(), coordinate.getKey()).addItemAktif(new KartuItem("accelerate"));
                    if(word[j+4].toLowerCase().equals("delay"))
                        resLadang.get(coordinate.getValue(), coordinate.getKey()).addItemAktif(new KartuItem("delay"));
                    if(word[j+4].toLowerCase().equals("destroy"))
                        resLadang.get(coordinate.getValue(), coordinate.getKey()).addItemAktif(new KartuItem("destroy"));
                    if(word[j+4].toLowerCase().equals("protect"))
                        resLadang.get(coordinate.getValue(), coordinate.getKey()).addItemAktif(new KartuItem("protect"));
                    if(word[j+4].toLowerCase().equals("instant_harvest"))
                        resLadang.get(coordinate.getValue(), coordinate.getKey()).addItemAktif(new KartuItem("instant_harvest"));
                    if(word[j+4].toLowerCase().equals("bear_trap"))
                        resLadang.get(coordinate.getValue(), coordinate.getKey()).addItemAktif(new KartuItem("bear_trap"));
                }
            }
            return new Pair<Pemain,Ladang>(resPemain, resLadang);
        } catch (Exception e) {
            throw e;
        }
    }

}
