package com.astrallinear.astrallinear.Deck;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

import com.astrallinear.astrallinear.Kartu.*;

public class InactiveDeck {
    private Integer cardLeft;
    private ArrayList<String> hewanAcak;
    private ArrayList<String> tanamanAcak;
    private ArrayList<String> itemAcak;

    public InactiveDeck() throws Exception{
        cardLeft = 40;
        hewanAcak = new ArrayList<>();
        tanamanAcak = new ArrayList<>();
        itemAcak = new ArrayList<>();
        hewanAcak.add("sapi");
        hewanAcak.add("domba");
        hewanAcak.add("kuda");
        hewanAcak.add("ayam");
        hewanAcak.add("beruang");
        hewanAcak.add("hiu_darat");
        hewanAcak.add("sapi");
        hewanAcak.add("domba");
        hewanAcak.add("kuda");
        hewanAcak.add("ayam");
        hewanAcak.add("beruang");
        tanamanAcak.add("biji_jagung");
        tanamanAcak.add("biji_labu");
        tanamanAcak.add("biji_stroberi");
        itemAcak.add("accelerate");
        itemAcak.add("delay");
        itemAcak.add("instant_harvest");
        itemAcak.add("destroy");
        itemAcak.add("protect");
        itemAcak.add("trap");

    }

    public Integer getCardLeft(){
        return cardLeft;
    }

    public void setCardLeft(Integer n){
        this.cardLeft = n;
    }

    public Queue <Kartu> takeCards(int n)throws Exception{
        if(cardLeft == 0){
            throw new EmptyInactiveDeckException();
        }
        Queue <Kartu> res = new ArrayDeque<Kartu>();
        int nTaken;
        Random random = new Random();

        if(n > cardLeft)
            nTaken = cardLeft;
        else
            nTaken = n;
            
        this.cardLeft -= nTaken;

        if(nTaken == 0) return res;

        res.add(new KartuTanaman(tanamanAcak.get(random.nextInt(3))));
        if(nTaken == 1) return res;

        res.add(new KartuHewan(hewanAcak.get(random.nextInt(11))));
        if(nTaken == 2) return res;

        res.add(new KartuItem(itemAcak.get(random.nextInt(6))));
        if(nTaken == 3) return res;
        
        res.add(new KartuTanaman(tanamanAcak.get(random.nextInt(3))));
        return res;
    }

    public void cancelTakeCards(int n){
        this.cardLeft += n;
    }
}

class EmptyInactiveDeckException extends Exception{
    public EmptyInactiveDeckException(){
        super("Inactive deck sudah tidak memiliki kartu lagi");
    }
}