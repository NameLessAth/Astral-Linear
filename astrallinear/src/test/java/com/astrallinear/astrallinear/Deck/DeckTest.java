package com.astrallinear.astrallinear.Deck;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.astrallinear.astrallinear.Kartu.Kartu;
import com.astrallinear.astrallinear.Kartu.KartuHewan;


public class DeckTest {
    
    @Test //test 1
    public void testAddKartu() throws Exception{
        Deck deck = new Deck();
        KartuHewan kartu;

        deck.addKartu(new KartuHewan("sapi"));
        kartu = (KartuHewan)deck.getActiveCard(0);

        assertTrue(kartu.getNama() == "sapi");
        assertTrue(kartu.getJenis() == "herbivora");
        assertTrue(kartu.getBerat() == 0);

        deck.addKartu(new KartuHewan("sapi"), 1);
        kartu = (KartuHewan)deck.getActiveCard(1);

        assertTrue(kartu.getNama() == "sapi");
        assertTrue(kartu.getJenis() == "herbivora");
        assertTrue(kartu.getBerat() == 0);
    }

    @Test //test 2
    public void testDeleteActiveCard() throws Exception{
        Deck deck = new Deck();

        deck.addKartu(new KartuHewan("sapi"), 1);
        deck.deleteActiveCard(1);

        
        try{
            deck.getActiveCard(1);
            assertTrue(false);
        }
        catch(Exception e){
            assertTrue(e.getMessage().equals("Slot deck ini tidak berisi kartu apapun!"));
        }
    }

    @Test //test 3
    public void testTakeShuffle() throws Exception{
        Deck deck = new Deck();
        assertTrue(deck.countEmptySlot() == 6);
        deck.takeShuffle();
        assertTrue(deck.countEmptySlot() == 2);
        deck.takeShuffle();
        assertTrue(deck.countEmptySlot() == 0);
        deck.deleteActiveCard(1);
        assertTrue(deck.countEmptySlot() == 1);
        deck.takeShuffle();
        assertTrue(deck.countEmptySlot() == 0);
    }

    @Test //test 4
    public void testGetRemainingInactiveDeck() throws Exception{
        Deck deck = new Deck();
        assertTrue(deck.getRemainingInactiveDeck() == 40);
        deck.takeShuffle();
        assertTrue(deck.getRemainingInactiveDeck() == 36);
        for(int i = 0; i < 9; i++){
            deck.deleteActiveCard(0);
            deck.deleteActiveCard(1);
            deck.deleteActiveCard(2);
            deck.deleteActiveCard(3);
            deck.takeShuffle();
        }
        assertTrue(deck.getRemainingInactiveDeck() == 0);
    }

    @Test //test5
    public void testMoveCard() throws Exception{
        Deck deck = new Deck();
        KartuHewan kartu;

        deck.addKartu(new KartuHewan("sapi"));
        deck.moveCard(0, 1);

        kartu =(KartuHewan) deck.getActiveCard(1);
        assertTrue(kartu.getNama() == "sapi");
        assertTrue(kartu.getJenis() == "herbivora");
        assertTrue(kartu.getBerat() == 0);

        try{
            deck.getActiveCard(0);
            assertTrue(false);
        } catch(Exception e){
            assertTrue(e.getMessage() == "Slot deck ini tidak berisi kartu apapun!");
        }
    }
}
