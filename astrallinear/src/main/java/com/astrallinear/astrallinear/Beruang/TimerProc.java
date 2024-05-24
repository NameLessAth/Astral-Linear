package com.astrallinear.astrallinear.Beruang;

import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

class TimerProc{
    public boolean timer = false;
    public boolean stop = false;
    public Integer durasi = 60000;
    private Label timerLabel;

    synchronized void Timer(){  
        while(!timer){
            try {wait();}
            catch (Exception e) {}
        }
        long timeNow = System.currentTimeMillis(); long timeTemp;
        
        System.out.println(timerLabel);
        float waktu = ((timeNow+durasi)-System.currentTimeMillis());
        StringProperty waktuFloatProperty = new SimpleStringProperty(String.format("%.2f", waktu));

        timerLabel.textProperty().bind(waktuFloatProperty);

        while((timeNow+durasi) > System.currentTimeMillis() && !stop){
            try{
                timeTemp = ((timeNow+durasi)-System.currentTimeMillis());
                String s = timeTemp/1000 + "." + (timeTemp % 1000);
                
                // System.out.print(s);
                // System.out.print('\r');
                Platform.runLater(() -> {
                    waktuFloatProperty.set(s);
                });

                wait(100);
            } catch (Exception e) {}
        } System.out.print("");
        timer = false;
        stop = false;
    }

    synchronized void ready(Integer durasiStart, Label label){
        durasi = durasiStart;
        timer = true;
        timerLabel = label;
        notify();
    }

    synchronized void Interrupt(){
        stop = true;
        notify();
    }
}

