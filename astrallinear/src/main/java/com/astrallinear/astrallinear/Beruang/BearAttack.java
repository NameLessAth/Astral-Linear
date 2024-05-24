package com.astrallinear.astrallinear.Beruang;
import java.util.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import com.astrallinear.astrallinear.Initializable;
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
    private Integer startPointRow;
    private Integer startPointColumn;
    private Initializable initializable;

    public static boolean isAttacking(){
        Integer terjadi = new Random().nextInt(100+1);
        if (terjadi <= 25) return true;
        else return false;
    }

    synchronized void Attack(TimerProcRun tpr) throws Exception{
        
        Ladang ladangDiserang = this.pemainDiserang.getLadang();
        Random rd = new Random();

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

        // Integer lastI = 0, lastJ = 0;

        for (int i = startPointRow; i < startPointRow+this.attackedHeight; i++){
            for (int j = startPointColumn; j < startPointColumn+this.attackedWidth; j++){
                if (ladangDiserang.is_filled(i, j)) {
                    if (ladangDiserang.get(i, j).getItemAktif().containsKey("bear_trap")){
                        kenaTrap = true;
                        KartuMakhluk temp = ladangDiserang.get(i, j);
                        temp.removeItemAktif(new KartuItem("bear_trap"));
                        // ladangDiserang.spawn_at(temp, lastI, lastJ);
                    }
                }
            }
        } 
        if (kenaTrap){
            try {
                pemainDiserang.addActiveCard(new KartuHewan("beruang"));
            } catch (Exception e) {
                 Alert InventoryFullAlert = new Alert(AlertType.INFORMATION);
                InventoryFullAlert.setTitle("Yah...");
                InventoryFullAlert.setHeaderText("Kamu menangkap beruang, tetapi karena inventory-mu penuh, jadinya beruangnya kabur... :(");
                InventoryFullAlert.show();
            }
        } else{
            for (int i = startPointRow; i < startPointRow+this.attackedHeight; i++){
                for (int j = startPointColumn; j < startPointColumn+this.attackedWidth; j++){
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

    public synchronized List<Integer> attackLadang(Pemain pemainDiserang, Initializable initializable) throws Exception{
        this.initializable = initializable;
        this.pemainDiserang = pemainDiserang;

        // generating which field is attacked
        Random rd = new Random();
        Integer width = rd.nextInt(5)+1, height;
        if (width > 3) height = 1;
        else if (width == 3) height = rd.nextInt(2)+1;
        else if (width == 2) height = rd.nextInt(3)+1;
        else height = rd.nextInt(4)+1;
        
        this.attackedWidth = width;
        this.attackedHeight = height;

        this.startPointRow = rd.nextInt(4-this.attackedHeight+1);
        this.startPointColumn = rd.nextInt(5-this.attackedWidth+1);
        
        // debug info
        System.out.println("----------------");
        System.out.println("Ladang diserang di: ");
        System.out.println(startPointRow + ", " + startPointColumn);
        System.out.println("Ukuran Serangan: ");
        System.out.println(attackedWidth + "x" + attackedHeight);
        System.out.println("----------------");
        
        List<Integer> coordinate_info = new ArrayList<>(Arrays.asList(
            startPointRow,
            startPointColumn,
            attackedWidth,
            attackedHeight
        ));

        readyAtt = true;
        notify();
            
        return coordinate_info;

    }

    // public synchronized void attackLadang(Pemain pemainDiserang, Player2FieldController controller) throws Exception{
    //     this.timerLabel = controller.Bear;
    //     this.pemainDiserang = pemainDiserang;
    //     readyAtt = true;
    //     notify();
    // }
}