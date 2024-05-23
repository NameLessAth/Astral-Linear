package com.astrallinear.astrallinear.Beruang;
import java.util.Random;

import com.astrallinear.astrallinear.Kartu.KartuHewan;
import com.astrallinear.astrallinear.Ladang.Ladang;
import com.astrallinear.astrallinear.Pemain.Pemain;

public class BearAttack {
    public boolean readyAtt = false;
    public boolean interrupted = false;
    private Integer attackedHeight;
    private Integer attackedWidth;

    public static boolean isAttacking(){
        Integer terjadi = new Random().nextInt(100);
        if (terjadi <= 25) return true;
        else return false;
    }

    synchronized void Attack(TimerProcRun tpr){
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
        readyAtt = false;
        interrupted = false;
        notify();
    }

    synchronized void Interrupt(){
        interrupted = true;
        notify();
    }

    synchronized void attackLadang(Ladang ladangDiserang, Pemain pemainDiserang) throws Exception{
        Random rd = new Random();
        readyAtt = true;
        notify();
        while(readyAtt){
            try {wait();}
            catch (InterruptedException e) {}
        } 

        Integer startPointHeight = rd.nextInt(4-this.attackedHeight);
        Integer startPointWidth = rd.nextInt(5-this.attackedWidth);

        boolean kenaTrap = false;

        Integer lastI = 0, lastJ = 0;

        for (int i = startPointHeight; i < startPointHeight+this.attackedHeight; i++){
            for (int j = startPointWidth; j < startPointWidth+this.attackedWidth; j++){
                if (ladangDiserang.is_trapped(i, j)){
                    kenaTrap = true;
                    lastI = i; lastJ = j;
                    // buat trap nya jadi false
                }
            }
        } 
        if (kenaTrap){
            System.out.println("Beruang berhasil ditangkap");
            try {
                pemainDiserang.addActiveCard(new KartuHewan("beruang"));
            } catch (Exception e) {
                ladangDiserang.kartu_destroy(lastI, lastJ);
                ladangDiserang.spawn_at(new KartuHewan("beruang"), lastI, lastJ);
            }
        } else{
            for (int i = startPointHeight; i < startPointHeight+this.attackedHeight; i++){
                for (int j = startPointWidth; j < startPointWidth+this.attackedWidth; j++){
                    if (ladangDiserang.is_filled(i, j)){
                        if (!ladangDiserang.is_protected(i, j)){
                            try{
                                ladangDiserang.kartu_destroy(i, j);
                            } catch (Exception e){}
                        }
                    }
                }
            } 
        }
    }
}