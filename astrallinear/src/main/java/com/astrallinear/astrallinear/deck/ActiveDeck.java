package com.astrallinear.astrallinear.deck;

import java.util.HashMap;
import java.util.Queue;
import com.astrallinear.astrallinear.Kartu.*;

public class ActiveDeck {
    private HashMap <Integer, Kartu> content;
    private InactiveDeck iDeck;

    public ActiveDeck(){
        iDeck = new InactiveDeck();
        content  = new HashMap<>();
        for(int i = 0; i < 6; i++)
            content.put(i, null);
    }

    public Kartu getCard(int i) throws Exception{
        if(content.get(i) != null)
            return content.get(i);
        else
            throw new EmptyDeckSlotException();
    }

    public void deleteCard(int i){
        content.replace(i, null);
    }

    public int countEmptySlot(){
        int count = 0;
        for(int i = 0; i < 6; i++){
            if(content.get(i) == null)
                count++;
        }
        return count;
    }

    public void takeShuffle(){
        int nEmptySlot = this.countEmptySlot();
        Queue<Kartu> newCards = iDeck.takeCards(nEmptySlot);

        for(int i = 0; i < 6; i++){
            if(newCards.isEmpty())
                break;
            if(content.get(i) == null){
                content.replace(i, newCards.remove());
            }
        }
    }

    public int getRemainingInactiveDeck(){
        return iDeck.getCardLeft();
    }
}

class EmptyDeckSlotException extends Exception {
    public EmptyDeckSlotException(){
        super("Slot deck ini tidak berisi kartu apapun!");
    }
}