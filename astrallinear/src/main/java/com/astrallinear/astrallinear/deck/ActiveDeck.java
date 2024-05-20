package com.astrallinear.astrallinear.deck;

import java.util.HashMap;
import com.astrallinear.astrallinear.Kartu.*;

public class ActiveDeck {
    private HashMap <Integer, Kartu> content;

    public ActiveDeck(){
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
    
}

class EmptyDeckSlotException extends Exception {
    public EmptyDeckSlotException(){
        super("Slot deck ini tidak berisi kartu apapun!");
    }
}
