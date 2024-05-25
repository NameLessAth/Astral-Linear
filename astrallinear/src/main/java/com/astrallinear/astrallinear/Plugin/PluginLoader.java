package com.astrallinear.astrallinear.Plugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import com.astrallinear.astrallinear.Plugin.SaveLoadPlugin;

public class PluginLoader {

    public List<SaveLoadPlugin> loadPlugins(String folderPath) {
        List<SaveLoadPlugin> plugins = new ArrayList<>();

        File folder = new File(folderPath);
        System.out.println("-----------------------");
        System.out.println(folder.getAbsolutePath());
        System.out.println("-----------------------");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files == null) {
            System.out.println("hey!");
            return plugins; // No JAR files found
        }

        for (File file : files) {
            try {
                URL jarUrl = file.toURI().toURL();
                System.out.println(jarUrl);
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl});

                for (URL url : classLoader.getURLs()) {
                    System.out.println("Loaded from: " + url);
                }
                // Load classes from JAR
                for (String className : getClassNamesFromJar(file.getAbsolutePath())) {
                        Class<?> clazz = classLoader.loadClass(className);
                        if (SaveLoadPlugin.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                            // Instantiate and add plugin to list
                            SaveLoadPlugin plugin = (SaveLoadPlugin) clazz.getDeclaredConstructor().newInstance();
                            plugins.add(plugin);
                    }
                }

                classLoader.close(); // Close the class loader
            } catch (Exception e) {
                e.printStackTrace(); // Handle or log any exceptions
            }
        }

        return plugins;
    }

    private List<String> getClassNamesFromJar(String jarFilePath) {
        List<String> classNames = new ArrayList<>();
        try (JarFile jarFile = new JarFile(jarFilePath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    System.out.println(entry);
                    String className = entry.getName().replace('/', '.').substring(0, entry.getName().length() - 6);
                    classNames.add(className);
                    System.out.println(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log any exceptions
        }
        return classNames;
    }

    // public static void main(String[] args) throws Exception {
    //     PluginLoader pluginLoader = new PluginLoader();
    //     List<SaveLoadPlugin> loadedPlugins = pluginLoader.loadPlugins("astrallinear/plugins/");
    //     System.out.println("huh");

    //     // Use the loaded plugins
    //     for (SaveLoadPlugin plugin : loadedPlugins) {
    //         // Perform actions with each plugin
    //         System.out.println(plugin.getExtName());
    //         plugin.save(null, null, null);
    //         plugin.load(null, null, null);
    //     }
    // }
}
