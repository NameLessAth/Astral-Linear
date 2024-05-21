package com.astrallinear.astrallinear.Ladang;
import java.util.*;

import com.astrallinear.astrallinear.Kartu.KartuMakhluk;
import com.astrallinear.astrallinear.Kartu.KartuProduk;
import com.astrallinear.astrallinear.Kartu.KartuTanaman;
import com.astrallinear.astrallinear.Kartu.KartuHewan;


public class Ladang {

    private static int height = 4;
    private static int width = 5;

    private KartuMakhluk[][] MakhlukMatrix = new KartuMakhluk[height][width];
    private boolean[][] isFilled = new boolean[height][width];
    private boolean[][] isProtected = new boolean[height][width];
    private boolean[][] isTrapped = new boolean[height][width];

    public Ladang() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                isFilled[i][j] = false;
                isProtected[i][j] = false;
                isTrapped[i][j] = false;
            }
        }
    }

    /*
     * Cell operation functions/procedures
     */

    public KartuMakhluk get(Integer row, Integer col) throws EmptyCellException {
        if (isFilled[row][col]) {
            return MakhlukMatrix[row][col];
        } 
        else {
            throw new EmptyCellException(row, col);
        }
    }

    public KartuMakhluk pop(Integer row, Integer col) throws EmptyCellException {
        if (isFilled[row][col]) {
            isFilled[row][col] = false;
            return MakhlukMatrix[row][col];
        }
        else {
            throw new EmptyCellException(row, col);
        }
    }

    public void move(Integer src_row, Integer src_col, Integer dst_row, Integer dst_col) throws FilledCellException,EmptyCellException {

        if (isFilled[dst_row][dst_col]) {
            throw new FilledCellException(dst_row, dst_col);
        }
        MakhlukMatrix[dst_col][dst_row] = pop(src_row, src_col);
    
    }

    // Boolean functions
    public boolean is_filled(Integer row, Integer col) {
        return isFilled[row][col];
    }
    public boolean is_protected(Integer row, Integer col) {
        return isProtected[row][col];
    }
    public boolean is_trapped(Integer row, Integer col) {
        return isTrapped[row][col];
    }

    /*
     * Age
     */
    public void age_all_plants() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isFilled[i][j]) {
                    if (MakhlukMatrix[i][j] instanceof KartuTanaman) 
                       ((KartuTanaman) MakhlukMatrix[i][j]).age();
                }
            }
        }
    }
    
    /*
     * Give food at a certain magical index
     */
    public void give_food_at(KartuProduk product, Integer row, Integer col) throws Exception {
        if (!isFilled[row][col]) throw new EmptyCellException(row, col);
        if (MakhlukMatrix[row][col] instanceof KartuHewan) {
            ((KartuHewan) MakhlukMatrix[row][col]).beriMakan(product);
        }
        else {
            throw new NyobaNgasihMakanKeTanamanException(row, col);
        }
    }

    /*
     * Harvest at a certain magical index
     */
    public KartuProduk harvest_at(Integer row, Integer col) throws Exception {
        if (!isFilled[row][col]) throw new EmptyCellException(row, col);
        KartuProduk ret = MakhlukMatrix[row][col].panen();
        isFilled[row][col] = false;
        return ret;
    }

    /*
     *   EFEK KARTU ITEM
     */

    public void kartu_destroy(Integer row, Integer col) throws EmptyCellException {
        if (!isFilled[row][col]) throw new EmptyCellException(row, col);
        isFilled[row][col] = false;
    }
    public void kartu_trap(Integer row, Integer col) throws AlreadyTrappedCellException,EmptyCellException {
        if (isTrapped[row][col]) throw new AlreadyTrappedCellException(row, col);
        if (!isFilled[row][col]) throw new EmptyCellException(row, col);
        isTrapped[row][col] = true;
    }
    public void kartu_protect(Integer row, Integer col) throws AlreadyProtectedCellException,EmptyCellException {
        if (isProtected[row][col]) throw new AlreadyProtectedCellException(row, col);
        if (!isFilled[row][col]) throw new EmptyCellException(row, col);
        isProtected[row][col] = true;
    }
    public void kartu_accelerate(Integer row, Integer col) throws EmptyCellException {
        if (!isFilled[row][col]) throw new EmptyCellException(row, col);
        MakhlukMatrix[row][col].accelerate();
    }
    public void kartu_delay(Integer row, Integer col) throws EmptyCellException {
        if (!isFilled[row][col]) throw new EmptyCellException(row, col);
        MakhlukMatrix[row][col].delay();
    }
    public KartuProduk kartu_instant_harvest(Integer row, Integer col) throws EmptyCellException {
        if (!isFilled[row][col]) throw new EmptyCellException(row, col);
        isFilled[row][col] = false;
        return MakhlukMatrix[row][col].instant_harvest_panen();
    }
}

class EmptyCellException extends Exception {
    public EmptyCellException (Integer row, Integer col) {
        super("Row: " + Integer.toString(row) + '\n' + "Col: " + Integer.toString(col));
    }
}
class FilledCellException extends Exception {
    public FilledCellException (Integer row, Integer col) {
        super("Row: " + Integer.toString(row) + '\n' + "Col: " + Integer.toString(col));
    }
}
class AlreadyProtectedCellException extends Exception {
    public AlreadyProtectedCellException (Integer row, Integer col) {
        super("Row: " + Integer.toString(row) + '\n' + "Col: " + Integer.toString(col));
    }
}
class AlreadyTrappedCellException extends Exception {
    public AlreadyTrappedCellException (Integer row, Integer col) {
        super("Row: " + Integer.toString(row) + '\n' + "Col: " + Integer.toString(col));
    }
}
class NyobaNgasihMakanKeTanamanException extends Exception {
    public NyobaNgasihMakanKeTanamanException (Integer row, Integer col) {
        super("Row: " + Integer.toString(row) + '\n' + "Col: " + Integer.toString(col));
    }
}