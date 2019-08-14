package org.pixeltime.enchantmentsenhance.gui

import java.util.HashMap
import java.util.HashSet

object GUIManager {
    // Singleton
    // Making sure that the in-memory data doesn't get reset when reloading
    @JvmStatic
    val map: MutableMap<String, GUIAbstract> = HashMap()

    @JvmStatic
    val set = HashSet<GUIAbstract>()


}
