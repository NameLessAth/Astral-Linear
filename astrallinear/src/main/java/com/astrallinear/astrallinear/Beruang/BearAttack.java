package com.astrallinear.astrallinear.Beruang;
import java.util.Random;

import com.astrallinear.astrallinear.Ladang.Ladang;

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
        
        System.out.println("BERUANG SIAP MENYERANG DALAM 5 DETIK");
        tpr.tpc.ready(); 
        long timeNow = System.currentTimeMillis(); 
        notify();
        while(!interrupted && System.currentTimeMillis() - timeNow < 5000){
            try{wait(5000);}
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

    synchronized Ladang attackLadang(Ladang ladangDiserang) throws Exception{
        Random rd = new Random();
        readyAtt = true;
        notify();
        while(readyAtt){
            try {wait();}
            catch (InterruptedException e) {}
        } 

        Integer startPointHeight = rd.nextInt(4-this.attackedHeight);
        Integer startPointWidth = rd.nextInt(5-this.attackedWidth);

        for (int i = startPointHeight; i < startPointHeight+this.attackedHeight; i++){
            for (int j = startPointWidth; j < startPointWidth+this.attackedWidth; j++){
                if (ladangDiserang.is_trapped(i, j)) throw new BearKenaTrap("Beruang Terkena Trap");
            }
        } 
        for (int i = startPointHeight; i < startPointHeight+this.attackedHeight; i++){
            for (int j = startPointWidth; j < startPointWidth+this.attackedWidth; j++){
                if (ladangDiserang.is_filled(i, j)){
                    if (!ladangDiserang.is_protected(i, j)){
                        try{
                            ladangDiserang.pop(i, j);
                        } catch (Exception e){}
                    }
                }
            }
        } return ladangDiserang;
    }
}

class BearAttackRun extends Thread{
    BearAttack brt;
    TimerProcRun tpr;

    public BearAttackRun(TimerProcRun tpr){
        this.brt = new BearAttack();
        this.tpr = tpr;
    }

    @Override
    public void run(){
        while (true) {
            this.brt.Attack(this.tpr);    
        }  
    } 
}

class BearKenaTrap extends Exception{
    public BearKenaTrap(String message){
        super(message);
    }
}