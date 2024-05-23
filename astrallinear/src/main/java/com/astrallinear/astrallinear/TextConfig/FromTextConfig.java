package com.astrallinear.astrallinear.TextConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

public class FromTextConfig {

    private Integer currentTurn;
    private Toko toko;
    private Pemain pemain1;
    private Pemain pemain2;
    private Ladang ladang1;
    private Ladang ladang2;

    public FromTextConfig(String folderPath) throws Exception{
        setState(folderPath + "/gamestate.txt");
        Pair<Pemain, Ladang> temp = setPlayer(folderPath + "/player1.txt");
        pemain1 = temp.getKey();
        ladang1 = temp.getValue();
        temp = setPlayer(folderPath + "/player2.txt");
        pemain2 = temp.getKey();
        ladang2 = temp.getValue();
    }

    public static Pair<Integer, Integer> stringToCoordinate(String s){
        return new Pair<Integer,Integer>((s.charAt(0)-'A'), (s.charAt(1)*10 + s.charAt(2) - 1));
    }

    private void setState(String statePath) throws Exception{
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
            throw e;
        }
    }

    private Pair<Pemain, Ladang> setPlayer(String playerPath) throws Exception{
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(playerPath))) {
            Integer count = 0;
            Pemain resPemain = new Pemain();
            Ladang resLadang = new Ladang();

            line = br.readLine();
            resPemain.increaseGulden(Integer.parseInt(line));

            line = br.readLine();
            resPemain.getDeck().setRemainingInactiveDeck(Integer.parseInt(line));

            line = br.readLine();
            count = Integer.parseInt(line);

            for(int i = 0; i < count; i++){
                line = br.readLine();
                Kartu kartu;
                String[] word = line.split("\\s+");
                Integer pos = stringToCoordinate(word[0]).getKey();

                try{
                    kartu = new KartuHewan(word[1]);
                } catch(Exception e){}
                try{
                    kartu = new KartuTanaman(word[1]);
                } catch(Exception e){}
                try{
                    kartu = new KartuItem(word[1]);
                } catch(Exception e){}
                try{
                    kartu = new KartuProduk(word[1]);
                } catch(Exception e){
                    throw e;
                }
                resPemain.getDeck().addKartu(kartu, pos);
            }

            line = br.readLine();
            count = Integer.parseInt(line);

            KartuMakhluk kartuMakhluk;
            Pair<Integer, Integer> coordinate;
            for(int i = 0; i < count; i++){
                line = br.readLine();
                String[] word = line.split("\\s+");
                coordinate = stringToCoordinate(word[0]);
                try{
                    KartuHewan kartuTemp = new KartuHewan(word[1]);
                    kartuTemp.setBerat(Integer.parseInt(word[2]));
                    kartuMakhluk = kartuTemp;
                }catch(Exception e){}
                try{
                    KartuTanaman kartuTemp = new KartuTanaman(word[1]);
                    kartuTemp.setUmur(Integer.parseInt(word[2]));
                    kartuMakhluk = kartuTemp;
                }catch(Exception e){
                    throw e;
                }
                resLadang.spawn_at(kartuMakhluk, coordinate.getValue(), coordinate.getKey());
                Integer count1 = Integer.parseInt(word[3]);
                for(int j = 0; j < count1; j++){
                    if(word[i+4].toLowerCase().equals("accelerate"));
                        resLadang.kartu_accelerate(coordinate.getValue(), coordinate.getKey());
                    if(word[i+4].toLowerCase().equals("delay"));
                        resLadang.kartu_delay(coordinate.getValue(), coordinate.getKey());
                    if(word[i+4].toLowerCase().equals("destroy"));
                        resLadang.kartu_destroy(coordinate.getValue(), coordinate.getKey());
                    if(word[i+4].toLowerCase().equals("protect"));
                        resLadang.kartu_protect(coordinate.getValue(), coordinate.getKey());
                    if(word[i+4].toLowerCase().equals("instant_harvest"));
                        resLadang.kartu_instant_harvest(coordinate.getValue(), coordinate.getKey());
                    if(word[i+4].toLowerCase().equals("trap"));
                        resLadang.kartu_trap(coordinate.getValue(), coordinate.getKey());
                }
            }
            return new Pair<Pemain,Ladang>(resPemain, resLadang);
        } catch (Exception e) {
            throw e;
        }
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
