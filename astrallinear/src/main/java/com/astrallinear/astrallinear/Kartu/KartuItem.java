package com.astrallinear.astrallinear.Kartu;

import javafx.scene.effect.Effect;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KartuItem extends Kartu {
    private static Set<String> daftar_nama = new HashSet<>(Arrays.asList(
        "accelerate", "delay", "instant_harvest", "destroy", "protect", "trap"
    ));

    public KartuItem(String nama) throws NamaKartuTidakAdaException {
        super(nama);
        if (nama == null || !daftar_nama.contains(nama)) throw new NamaKartuTidakAdaException("Error konstruktor kartu item dengan nama " + nama);
    }
}
