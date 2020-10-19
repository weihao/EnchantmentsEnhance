package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.locale.LocaleManager
import org.pixeltime.enchantmentsenhance.manager.InventoryManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

abstract class EnchantmentListener : Listener {
    fun name(): String {
        // FIXME: NPE hidden risks -> #172
        val name = SettingsManager.lang.getString("enchantments." + javaClass.simpleName.toLowerCase())!!
        return Util.toColor(name)!!
    }

    private fun addPermaPotion(player: Player, type: PotionEffectType, level: Int) {
        player.addPotionEffect(PotionEffect(type, 999999999, level - 1))
    }

    private fun removePermaPotion(player: Player, type: PotionEffectType) {
        for (PotionEffect in player.activePotionEffects) {
            if ((PotionEffect.type == type) && (PotionEffect.duration < 1000000000) && (PotionEffect.duration > 100000000)) {
                player.removePotionEffect(type)
            }
        }
    }

    fun permaPotion(player: Player, type: PotionEffectType, potionlvl: Int) {
        if (potionlvl > 0) {
            addPermaPotion(player, type, potionlvl)
        } else {
            removePermaPotion(player, type)
        }
    }

    fun addLang() {
        LocaleManager.addLang(
            "enchantments.${this.javaClass.simpleName.toLowerCase()}",
            arrayOf(this.javaClass.simpleName) + lang()
        )
    }

    fun addDesc() {
        LocaleManager.addLang("descriptions.${this.javaClass.simpleName.toLowerCase()}", desc())
    }

    abstract fun lang(): Array<String>

    abstract fun desc(): Array<String>

    fun roll(level: Int): Boolean {
        return (Math.random() * 100.0) < SettingsManager.enchant.getDouble("${this.javaClass.simpleName.toLowerCase()}.$level.chance")
    }

    fun getLevel(player: Player): Int {
        return InventoryManager.getHighestLevel(player, this.name())
    }
}
