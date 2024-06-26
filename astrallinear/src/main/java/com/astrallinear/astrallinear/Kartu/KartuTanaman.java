package com.astrallinear.astrallinear.Kartu;
import java.util.*;


public class KartuTanaman extends KartuMakhluk {

    private Integer umur;
    private Integer turnUntukHarvest;

    private static final Map<String, Integer> tanaman_turn_map = Map.ofEntries(
        Map.entry("biji_jagung", 3),
        Map.entry("biji_labu", 5),
        Map.entry("biji_stroberi", 4)
    );

    private static final Map<String, String> tanaman_drop_map = Map.ofEntries(
        Map.entry("biji_jagung", "jagung"),
        Map.entry("biji_labu", "labu"),
        Map.entry("biji_stroberi", "stroberi")
    );

    public KartuTanaman(String nama) throws NamaKartuTidakAdaException {
        super(nama, new KartuProduk(tanaman_drop_map.get(nama)));
        if (tanaman_drop_map.get(nama) == null) throw new NamaKartuTidakAdaException("Error konstruktor kartu tanaman dengan nama " + nama);
        Integer turn = tanaman_turn_map.get(nama);
        this.turnUntukHarvest = turn;
        umur = 0;
    }

    public Integer getUmur(){
        return this.umur;
    }

    public void setUmur(Integer age){
        this.umur = age;
    }

    public void age() {
        ++umur;
    }

    public void accelerate() {
        this.umur += 2;
    }
    public void delay() {
        this.umur = (this.umur - 2 > 0 ? this.umur - 2 : 0);
    }

    public boolean isSiapPanen() {
        return umur >= turnUntukHarvest;
    }

    public Integer getTurnUntukHarvest() {
        return turnUntukHarvest;
    }

    public String getPathToImg() {
        return "Tanaman/" + this.getNama() + ".png";
    }

    public String getInfo() {
        String temp = ""; boolean first = true;
        for (String iterate : this.itemAktif.keySet()){
            if (!first) temp += ",";
            else first = false;
            temp = temp + " " + iterate.replace("_", " ") + " (" + this.itemAktif.get(iterate) + ")";
        }
        return (
            "\n\tNama: " + getNama().replace("_", " ") +
            "\n\tUmur: "                    + umur +
            "\n\tUmur threshold panen: "    + turnUntukHarvest +
            "\n\tSiap panen: "              + (isSiapPanen() ? "ya!" : "tidak!") +
            "\n\tHasil panen: "             + drop.getNama().replace("_", " ") +
            "\n\tItem Aktif:" + temp
        );
    }
}