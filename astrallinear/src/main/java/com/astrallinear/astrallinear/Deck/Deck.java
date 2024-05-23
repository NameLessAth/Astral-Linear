package com.astrallinear.astrallinear.Deck;

import java.util.HashMap;
import java.util.Queue;
import com.astrallinear.astrallinear.Kartu.*;

public class Deck {
    private Kartu[] activeDeck;
    private InactiveDeck inactiveDeck;

    public Deck() throws Exception{
        inactiveDeck = new InactiveDeck();
        activeDeck  = new Kartu[6];
    }

    public void moveCard(int source, int target) throws Exception{
        if(activeDeck[source] == null){
            throw new EmptyDeckSlotException();
        }
        if(activeDeck[target] != null){
            throw new FilledDeckSlotException();
        }
        activeDeck[target] = activeDeck [source];
        activeDeck[source] = null;
    }

    public Kartu getActiveCard(int i) throws Exception{
        if(activeDeck[i] != null)
            return activeDeck[i];
        else
            throw new EmptyDeckSlotException();
    }

    public void deleteActiveCard(Integer i) throws Exception{
        if(activeDeck[i] == null)
            throw new EmptyDeckSlotException();
        else{
            activeDeck[i] = null;
        }
    }

    public int countEmptySlot(){
        int count = 0;
        for(int i = 0; i < 6; i++){
            if(activeDeck[i] == null)
                count++;
        }
        return count;
    }

    public void takeShuffle()throws Exception{
        Integer takenCard = this.countEmptySlot();
        if(takenCard > 4){
            takenCard = 4;
        }
        Queue<Kartu> newCards = inactiveDeck.takeCards(takenCard);

        for(int i = 0; i < 6; i++){
            if(newCards.isEmpty())
                break;
            if(activeDeck[i] == null){
                activeDeck[i] = newCards.remove();
            }
        }
    }

    public Integer getRemainingInactiveDeck(){
        return inactiveDeck.getCardLeft();
    }

    public void setRemainingInactiveDeck(Integer i){
        inactiveDeck.setCardLeft(i);
    }

    public void addKartu(Kartu k) throws Exception{
        if(!(countEmptySlot() == 0)){
            for(int i = 0; i < 6; i++){
                if(activeDeck[i] == null){
                    activeDeck[i] = k;
                    break;
                }
            }
        }
        else{
            throw new FullDeckSlotException();
        }
    }

    public void addKartu(Kartu k, Integer index) throws Exception{
        if(activeDeck[index] == null){
            activeDeck[index] = k;
        }
        else{
            throw new FilledDeckSlotException();
        }
    }
}

class FilledDeckSlotException extends Exception {
    public FilledDeckSlotException(){
        super("Slot deck ini sudah terisi!");
    }
}

class EmptyDeckSlotException extends Exception {
    public EmptyDeckSlotException(){
        super("Slot deck ini tidak berisi kartu apapun!");
    }
}

class FullDeckSlotException extends Exception {
    public FullDeckSlotException(){
        super("Deck ini sudah penuh!");
    }
}