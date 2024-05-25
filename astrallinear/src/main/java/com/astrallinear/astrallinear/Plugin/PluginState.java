package com.astrallinear.astrallinear.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.astrallinear.astrallinear.TxtSaveLoad.TxtSaveLoader;

public class PluginState {
    private static SaveLoadPlugin usedPlugin = new TxtSaveLoader();
    private static List<SaveLoadPlugin> plugins = new ArrayList<SaveLoadPlugin>(Arrays.asList(usedPlugin));

    public static void setUsedPlugin(SaveLoadPlugin plugin) {
        PluginState.usedPlugin = plugin;
    }
    
    public static void setAvailablePlugins(List<SaveLoadPlugin> plugins) {
        PluginState.plugins = plugins;
    }
    
    public void addPlugin(SaveLoadPlugin plugin) {
        PluginState.plugins.add(plugin);
    }

    public static SaveLoadPlugin getUsedPlugin() {
        return usedPlugin;
    }
    
    public static List<SaveLoadPlugin> getAvailalePlugins() {
        return plugins;
    }
}