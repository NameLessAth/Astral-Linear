package com.astrallinear.astrallinear.Ladang;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.astrallinear.astrallinear.Kartu.*;

public class LadangTest {
    
    @Test
    public void TestCellActions() throws Exception {
        Ladang l = new Ladang();
        l.age_all_plants();

        l.spawn_at(new KartuHewan("domba"), 2, 3);
        l.spawn_at(new KartuHewan("ayam"), 3, 1);
        l.spawn_at(new KartuTanaman("biji_labu"), 3, 4);
        l.move(2, 3, 1, 1);

        // spawn at a filled cell
        try {
            l.spawn_at(new KartuHewan("domba"), 1, 1);
            assertTrue(false);
        } catch (FilledCellException e) {}

        // move to a filled cell
        try {
            l.move(3, 1, 3, 4);
            assertTrue(false);
        } catch (FilledCellException e) {}
        
        // move empty cell
        try {
            l.move(0, 0, 2, 2);
            assertTrue(false);
        } catch (EmptyCellException e) {}

        assertTrue(l.is_filled(1, 1));
        assertTrue(l.is_filled(3, 1));
        assertTrue(l.is_filled(3, 4));
        assertTrue(!l.is_filled(0, 1));
        assertTrue(!l.is_filled(2, 2));
        assertTrue(!l.is_filled(2, 4));

        // pop and get
        try {
            l.pop(0, 0);
        } catch (EmptyCellException e) {}
        
        assertTrue(l.get(1, 1).getNama().equals(l.pop(1, 1).getNama()));
        assertTrue(!l.is_filled(1, 1));


        // get empty cell
        try { l.get(3, 0); assertTrue(false); } catch (EmptyCellException e) { assertTrue(true) ; }
    }

    @Test
    public void TestAgeGiveFoodHarvest() throws Exception {
        Ladang l = new Ladang();
        l.spawn_at(new KartuHewan("hiu_darat"), 1, 1);
        l.spawn_at(new KartuTanaman("biji_stroberi"), 0, 0);
        l.spawn_at(new KartuTanaman("biji_stroberi"), 1, 0);
        l.spawn_at(new KartuTanaman("biji_labu"), 2, 0);
        l.spawn_at(new KartuTanaman("biji_labu"), 3, 0);
        l.spawn_at(new KartuTanaman("biji_jagung"), 2, 1);
        l.spawn_at(new KartuTanaman("biji_jagung"), 3, 1);

        try { l.harvest_at(2, 0) ; assertTrue(false) ; }
        catch (Exception e) { assertTrue(e.getMessage().equals("Hey, makhluk ini belum bisa dipanen!"));}

        try { l.harvest_at(3, 3) ; assertTrue(false) ; }
        catch (EmptyCellException e) {}

        KartuProduk food = new KartuProduk("daging_beruang");
        l.give_food_at(food, 1, 1);
        l.give_food_at(food, 1, 1);
        l.give_food_at(food, 1, 1);
        l.give_food_at(food, 1, 1);
        l.give_food_at(food, 1, 1);
        l.give_food_at(food, 1, 1);
        l.give_food_at(food, 1, 1);
        l.give_food_at(food, 1, 1);
        assert(l.get(1, 1).isSiapPanen());

        try { l.give_food_at(food, 2, 0) ; assertTrue(false) ; }
        catch (NyobaNgasihMakanKeTanamanException e) {}
        try { l.give_food_at(food, 3, 3) ; assertTrue(false) ; }
        catch (EmptyCellException e) {}

        for (int i = 0; i < 4; i++) l.age_all_plants();
        assertTrue(!l.get(2, 0).isSiapPanen());
        assertTrue(l.get(2, 1).isSiapPanen());
        assertTrue(l.get(0, 0).isSiapPanen());
    }
}
