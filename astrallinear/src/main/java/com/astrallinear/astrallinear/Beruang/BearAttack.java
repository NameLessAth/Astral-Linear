package com.astrallinear.astrallinear.Beruang;
import java.util.Random;

import com.astrallinear.astrallinear.Kartu.KartuHewan;
import com.astrallinear.astrallinear.Kartu.KartuItem;
import com.astrallinear.astrallinear.Kartu.KartuMakhluk;
import com.astrallinear.astrallinear.Ladang.Ladang;
import com.astrallinear.astrallinear.Pemain.Pemain;

public class BearAttack {
    public boolean readyAtt = false;
    public boolean interrupted = false;
    private Pemain pemainDiserang;
    private Integer attackedHeight;
    private Integer attackedWidth;

    public static boolean isAttacking(){
        Integer terjadi = new Random().nextInt(100);
        if (terjadi <= 25) return true;
        else return false;
    }

    synchronized void Attack(TimerProcRun tpr) throws Exception{
        // Idling
        while(!readyAtt){
            try {wait();}
            catch (InterruptedException e) {}
        } 
        // generating which field is attacked
        Random rd = new Random();
        Integer a = rd.nextInt(4)+1, b;
        if (a > 3) b = 1;
        else if (a == 3) b = rd.nextInt(1)+1;
        else if (a == 2) b = rd.nextInt(2)+1;
        else b = rd.nextInt(5)+1;
        
        this.attackedHeight = a;
        this.attackedWidth = b;

        Integer durasi = (rd.nextInt(30)+30)*1000;
        System.out.println("BERUANG SIAP MENYERANG DALAM " + durasi/1000 + " DETIK");
        tpr.tpc.ready(durasi); 
        long timeNow = System.currentTimeMillis(); 
        while(!interrupted && System.currentTimeMillis() - timeNow < durasi){
            try{wait(durasi);}
            catch (InterruptedException e){}
        } tpr.tpc.Interrupt();
        
        Ladang ladangDiserang = this.pemainDiserang.getLadang();
        
        Integer startPointHeight = rd.nextInt(4-this.attackedHeight);
        Integer startPointWidth = rd.nextInt(5-this.attackedWidth);

        boolean kenaTrap = false;

        Integer lastI = 0, lastJ = 0;

        for (int i = startPointHeight; i < startPointHeight+this.attackedHeight; i++){
            for (int j = startPointWidth; j < startPointWidth+this.attackedWidth; j++){
                if (ladangDiserang.get(i, j).getItemAktif().containsKey("trap")){
                    kenaTrap = true;
                    KartuMakhluk temp = ladangDiserang.get(i, j);
                    temp.removeItemAktif(new KartuItem("trap"));
                    ladangDiserang.spawn_at(temp, lastI, lastJ);
                }
            }
        } 
        if (kenaTrap){
            System.out.println("Beruang berhasil ditangkap");
            try {
                pemainDiserang.addActiveCard(new KartuHewan("beruang"));
            } catch (Exception e) {
                System.out.println("Beruang kabur karena deck aktif penuh!");
            }
        } else{
            for (int i = startPointHeight; i < startPointHeight+this.attackedHeight; i++){
                for (int j = startPointWidth; j < startPointWidth+this.attackedWidth; j++){
                    if (ladangDiserang.is_filled(i, j)){
                        if (!ladangDiserang.get(i, j).getItemAktif().containsKey("protect")){
                            try{
                                ladangDiserang.pop(i, j);
                            } catch (Exception e){}
                        }
                    }
                }
            } 
        }
    }

    synchronized void Interrupt(){
        interrupted = true;
        notify();
    }

    synchronized void attackLadang(Pemain pemainDiserang) throws Exception{
        this.pemainDiserang = pemainDiserang;
        readyAtt = true;
        notify();
    }
}