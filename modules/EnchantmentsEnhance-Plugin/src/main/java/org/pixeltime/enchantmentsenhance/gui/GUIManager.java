package org.pixeltime.enchantmentsenhance.gui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GUIManager {
    // Singleton
    private static final Map<String, GUIAbstract> GUIMAP = new HashMap<>();
    private static final HashSet<GUIAbstract> GUISET = new HashSet<>();

    // Making sure that the in-memory data doesn't get reset when reloading
    public static Map<String, GUIAbstract> getMap() {
        return GUIMAP;
    }

    public static HashSet<GUIAbstract> getSet() {
        return GUISET;
    }


}
