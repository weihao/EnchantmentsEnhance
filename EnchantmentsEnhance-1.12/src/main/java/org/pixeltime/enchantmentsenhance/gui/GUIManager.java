package org.pixeltime.enchantmentsenhance.gui;

import java.util.HashMap;
import java.util.Map;

public class GUIManager {
    // Singleton
    private static final Map<String, GUIAbstract> GUIMAP = new HashMap<>();

    // Making sure that the in-memory data doesn't get reset when reloading
    public static Map<String, GUIAbstract> getMap() {
        return GUIMAP;
    }
}
