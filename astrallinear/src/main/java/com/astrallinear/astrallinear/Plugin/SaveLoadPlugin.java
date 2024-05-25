package com.astrallinear.astrallinear.Plugin;

import com.astrallinear.astrallinear.GameManager.GameManager;
import com.astrallinear.astrallinear.Toko.Toko;

public interface SaveLoadPlugin {
    /**
     * This function should populate game manager, toko, from filepath directory
     * 
     * @param gmg
     * @param shop
     * @param filepath
     * @throws Exception
     */
    public void save(GameManager gmg, Toko shop, String filepath) throws Exception;


    /**
     * This function should write to filepath directory files that are relevant
     * Store information of game manager, toko.
     * 
     * @param gmg
     * @param shop
     * @param filepath
     * @throws Exception
     */
    public void load(GameManager gmg, Toko shop, String filepath) throws Exception;

    /**
     * This function should write to filepath directory files that are relevant
     * Store information of game manager, toko.
     * 
     * @return extension name without period i.e. json, xml, txt
     */
    public String getExtName();
}