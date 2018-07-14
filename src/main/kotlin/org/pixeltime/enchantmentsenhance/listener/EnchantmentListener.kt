package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.locale.LM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

abstract class EnchantmentListener : Listener {
    fun name(): String {
        return ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + this.javaClass.simpleName.toLowerCase()))
    }

    fun addPermaPotion(player: Player, type: PotionEffectType, level: Int) {
        player.addPotionEffect(PotionEffect(type, Int.MAX_VALUE, level - 1))
    }

    fun removePermaPotion(player: Player, type: PotionEffectType) {
        for (PotionEffect in player.activePotionEffects) {
            if ((PotionEffect.type == type) && (PotionEffect.duration > 1000000000)) {
                player.removePotionEffect(type)
            }
        }
    }

    fun permaPotion(player: Player, type: PotionEffectType, level: Int) {
        if (level > 0) {
            addPermaPotion(player, type, level)
        } else {
            removePermaPotion(player, type)
        }
    }

    fun addLang() {
        LM.addLang("enchantments.${this.javaClass.simpleName.toLowerCase()}", arrayOf(this.javaClass.simpleName) + lang())
    }

    fun addDesc() {
        LM.addLang("descriptions.${this.javaClass.simpleName.toLowerCase()}", desc())
    }

    abstract fun lang(): Array<String>

    abstract fun desc(): Array<String>

    fun roll(level: Int): Boolean {
        return (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("${this.javaClass.simpleName.toLowerCase()}.$level.chance")
    }
}