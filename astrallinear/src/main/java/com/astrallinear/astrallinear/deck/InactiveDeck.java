package com.astrallinear.astrallinear.deck;
import java.util.ArrayDeque;
import java.util.Queue;

import com.astrallinear.astrallinear.Kartu.*;

public class InactiveDeck {
    private int cardLeft;

    public InactiveDeck(){
        cardLeft = 40;
    }

    public int getCardLeft(){
        return cardLeft;
    }

    public Queue <Kartu> takeCards(int n){
        int nTaken;
        if(n > cardLeft)
            nTaken = cardLeft;
        else
            nTaken = n;
        Queue <Kartu> res = new ArrayDeque<Kartu>();
        for(int i = 0; i < n;i++)
            res.add(new KartuHewan("domba", 10, "herbivora", new KartuProduk("daging_domba", 120, 6)));
        this.cardLeft -= nTaken;
        return res;
    }

    public void cancelTakeCards(int n){
        this.cardLeft += n;
    }
}
