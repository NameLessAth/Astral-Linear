package com.astrallinear.astrallinear.Beruang;
import java.util.Random;
import javafx.scene.control.Label;

import com.astrallinear.astrallinear.Initializable;
import com.astrallinear.astrallinear.Player1FieldController;
import com.astrallinear.astrallinear.Player2FieldController;
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
    private Initializable initializable;

    public static boolean isAttacking(){
        Integer terjadi = new Random().nextInt(100+1);
        if (terjadi <= 25) return true;
        else return false;
    }

    synchronized void Attack(TimerProcRun tpr) throws Exception{
        // generating which field is attacked
        Random rd = new Random();
        Integer width = rd.nextInt(5)+1, height;
        if (width > 3) height = 1;
        else if (width == 3) height = rd.nextInt(2)+1;
        else if (width == 2) height = rd.nextInt(3)+1;
        else height = rd.nextInt(4)+1;
        
        this.attackedWidth = width;
        this.attackedHeight = height;

        Ladang ladangDiserang = this.pemainDiserang.getLadang();
        Integer startPointHeight = rd.nextInt(4-this.attackedHeight+1);
        Integer startPointWidth = rd.nextInt(5-this.attackedWidth+1);
        

        System.out.println("----------------");
        System.out.println("Ladang diserang di: ");
        System.out.println(startPointHeight + ", " + startPointWidth);
        System.out.println("Ukuran Serangan: ");
        System.out.println(attackedWidth + "x" + attackedHeight);
        System.out.println("----------------");
        
        
        // Idling
        while(!readyAtt){
            try {wait();}
            catch (InterruptedException e) {}
        } 
        Integer durasi = (rd.nextInt(10+1)+10)*1000;
        tpr.tpc.ready(durasi, initializable.getBearTimer()); 
        long timeNow = System.currentTimeMillis(); 
        while(!interrupted && System.currentTimeMillis() - timeNow < durasi){
            try{wait(durasi);}
            catch (InterruptedException e){}
        } tpr.tpc.Interrupt();
        
        boolean kenaTrap = false;

        Integer lastI = 0, lastJ = 0;

        for (int i = startPointHeight; i < startPointHeight+this.attackedHeight; i++){
            for (int j = startPointWidth; j < startPointWidth+this.attackedWidth; j++){
                if (ladangDiserang.is_filled(i, j)) {
                    if (ladangDiserang.get(i, j).getItemAktif().containsKey("bear_trap")){
                        kenaTrap = true;
                        KartuMakhluk temp = ladangDiserang.get(i, j);
                        temp.removeItemAktif(new KartuItem("bear_trap"));
                        ladangDiserang.spawn_at(temp, lastI, lastJ);
                    }
                }
            }
        } 
        if (kenaTrap){
            try {
                pemainDiserang.addActiveCard(new KartuHewan("beruang"));
            } catch (Exception e) {
            }
        } else{
            for (int i = startPointHeight; i < startPointHeight+this.attackedHeight; i++){
                for (int j = startPointWidth; j < startPointWidth+this.attackedWidth; j++){
                    if (ladangDiserang.is_filled(i, j)){
                        if (!ladangDiserang.get(i, j).getItemAktif().containsKey("protect")){
                            try{
                                ladangDiserang.pop(i, j);
                            } catch (Exception e){
                                System.out.println("Gagal diserang di " + i + ", " + j);
                                System.out.println(e.getLocalizedMessage());
                            }
                        }
                    }
                }
            } 
        }

        System.out.println("Done!");

        initializable.initialize();
    }

    synchronized void Interrupt(){
        interrupted = true;
        notify();
    }

    public synchronized void attackLadang(Pemain pemainDiserang, Initializable initializable) throws Exception{
        this.initializable = initializable;
        this.pemainDiserang = pemainDiserang;
        readyAtt = true;
        notify();
    }

    // public synchronized void attackLadang(Pemain pemainDiserang, Player2FieldController controller) throws Exception{
    //     this.timerLabel = controller.Bear;
    //     this.pemainDiserang = pemainDiserang;
    //     readyAtt = true;
    //     notify();
    // }
}