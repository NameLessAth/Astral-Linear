package com.astrallinear.astrallinear.Kartu;

import javafx.scene.effect.Effect;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KartuItem extends Kartu {
    private static Set<String> daftar_nama = new HashSet<>(Arrays.asList(
        "accelerate", "delay", "instant_harvest", "destroy", "protect", "bear_trap"
    ));

    public KartuItem(String nama) throws NamaKartuTidakAdaException {
        super(nama);
        if (nama == null || !daftar_nama.contains(nama)) throw new NamaKartuTidakAdaException("Error konstruktor kartu item dengan nama " + nama);
    }

    public String getPathToImg() {
        return "Item/" + this.getNama() + ".png";
    }

    public String getInfo() {
        if (this.getNama().equals("accelerate")) return "\n\tNama: accelerate\n\tEfek: Menambah umur tanaman sebanyak 2 turn atau menambah berat kartu hewan sebesar 8.";
        else if (this.getNama().equals("delay")) return "\n\tNama: delay\n\tEfek: Mengurangi umur tanaman sebanyak 2 turn (umur tanaman minimal bernilai 0) atau mengurangi berat kartu hewan sebesar 5 (berat hewan minimal bernilai 0).";
        else if (this.getNama().equals("instant_harvest")) return "\n\tNama: instant harvest\n\tEfek: Melakukan harvest secara langsung untuk kartu tanaman/hewan yang dipilih.";
        else if (this.getNama().equals("destroy")) return "\n\tNama: destroy\n\tEfek: Menghancurkan kartu tanaman/hewan lawan.";
        else if (this.getNama().equals("protect")) return "\n\tNama: protect\n\tEfek: Melindungi kartu tanaman/hewan diri sendiri dari item yang ditambahkan oleh lawan ke ladang atau serangan beruang.";
        else return "\n\tNama: trap\n\tEfek: Mengubah beruang menjadi kartu hewan yang dapat diternak apabila menyerang hewan/tanaman yang diberikan item ini.";
    }
}
