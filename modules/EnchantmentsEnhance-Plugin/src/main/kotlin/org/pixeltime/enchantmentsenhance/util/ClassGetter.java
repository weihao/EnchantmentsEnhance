package org.pixeltime.enchantmentsenhance.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.pixeltime.enchantmentsenhance.Main;

import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassGetter {

    public static ArrayList<Class<?>> getClassesForPackage(JavaPlugin plugin, String pkgname) {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        CodeSource src = plugin.getClass().getProtectionDomain().getCodeSource();
        if (src != null) {
            URL resource = src.getLocation();
            resource.getPath();
            processJarfile(resource, pkgname, classes);
        }
        return classes;
    }

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
        } catch (NoClassDefFoundError e) {
            return null;
        }
    }

    private static void processJarfile(URL resource, String pkgname, ArrayList<Class<?>> classes) {
        String relPath = pkgname.replace('.', '/');
        String resPath = resource.getPath().replace("%20", " ");
        String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        JarFile jarFile;
        try {
            jarFile = new JarFile(jarPath);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                String className = null;
                if (entryName.endsWith(".class") && entryName.startsWith(relPath)
                        && entryName.length() > (relPath.length() + "/".length())) {
                    className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
                }
                if (className != null) {
                    Class<?> c = loadClass(className);
                    if (c != null)
                        classes.add(c);
                }
            }
        } catch (IOException e) {
//            throw new RuntimeException("Unexpected IOException reading JAR File '" + jarPath + "'. Do you have strange characters in your folders? Such as #?", e);
            for (int i = 0; i < 10; i++) {
                Main.getMain().getLogger().severe("Unexpected IOException. Do Not Rename the Jar File!");
            }
            Main.getMain().getLogger().severe("Plugin disabled.");
            Bukkit.getPluginManager().disablePlugin(Main.getMain());
        }
    }
}