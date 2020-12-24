package org.pixeltime.enchantmentsenhance.listener

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.version.VersionManager

class PlaceholderListener : PlaceholderExpansion() {

    override fun getIdentifier(): String {
        return "ee"
    }

    override fun getPlugin(): String {
        return "EnchantmentsEnhance"
    }

    override fun getAuthor(): String {
        return "HealPot"
    }

    override fun getVersion(): String {
        return VersionManager.getCurrentVersion()
    }

    override fun onPlaceholderRequest(player: Player?, s: String): String? {
        if (player == null) {
            return ""
        }
        val playerName = player.name
        if (s.startsWith("item_")) {
            return Main.getApi().getItem(playerName, s.split("_")[1].toInt()).toString()
        }
        if (s.equals("failstack", ignoreCase = true)) {
            return Main.getApi().getFailstack(playerName).toString()
        }
        return null
    }
}
