/*
 * Copyright (c) 2015 Jerrell Fang
 *
 * This project is Open Source and distributed under The MIT License (MIT)
 * (http://opensource.org/licenses/MIT)
 *
 * You should have received a copy of the The MIT License along with
 * this project.   If not, see <http://opensource.org/licenses/MIT>.
 */
package com.meowj.langutils;

import com.meowj.langutils.lang.LanguageRegistry;
import com.meowj.langutils.lang.convert.EnumLang;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Meow J on 6/20/2015.
 *
 * @author Meow J
 */
public class LangUtils {

    public static LangUtils plugin;
    private static boolean isCauldron = false;


    public void setUpLangUtils() {
        plugin = this;
        try {
            final long startTime = System.currentTimeMillis();
            EnumLang.init();
            info("Language Utils has been enabled." + "(" + (System.currentTimeMillis() - startTime) + "ms)");
        } catch (IOException e) {
            e.printStackTrace();
        }

        LanguageRegistry.INSTANCE = new LanguageRegistry();
    }


    /**
     * Display a info message
     *
     * @param msg message to be sent
     */
    public void info(String msg) {
        Bukkit.getLogger().log(Level.INFO, msg);
    }

    /**
     * Display a warning message
     *
     * @param msg message to be sent
     */
    public void warn(String msg) {
        Bukkit.getLogger().log(Level.WARNING, msg);
    }

    /**
     * Return true if Cauldron environment is detected
     *
     * @return true if Cauldron environment is detected
     */
    public boolean isCauldron() {
        return isCauldron;
    }
}
