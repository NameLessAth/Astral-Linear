package com.astrallinear.astrallinear.Pemain;

import com.astrallinear.astrallinear.Ladang.Ladang;
import com.astrallinear.astrallinear.Deck.Deck;
import com.astrallinear.astrallinear.Kartu.Kartu;

public class Pemain {
    Integer gulden;
    Deck deck;
    Ladang ladang;

    public Pemain()throws Exception{
        this.gulden = 0;
        this.deck = new Deck();
        this.ladang = new Ladang();
    }

    public Pemain(int gulden, Deck deck)throws Exception{
        this.gulden = gulden;
        this.deck = deck;
    }

    public void reduceGulden(int n){
        this.gulden -= n;
    }

    public Deck getDeck(){
        return this.deck;
    }

    public void increaseGulden(int n){
        this.gulden += n;
    }

    public void addActiveCard(Kartu kartu)throws Exception{
        deck.addKartu(kartu);
    }

    public void addActiveCard(Kartu kartu, Integer index)throws Exception{
        deck.addKartu(kartu, index);
    }

    public void deleteActiveCard(Integer index) throws Exception{
        deck.deleteActiveCard(index);
    }

    public void newTurn()throws Exception{
        deck.takeShuffle();
    }

    public Integer getGulden(){
        return gulden;
    }

    public Ladang getLadang() {
        return ladang;
    }

    public void setLadang(Ladang ladang){
        this.ladang = ladang;
    }
}
