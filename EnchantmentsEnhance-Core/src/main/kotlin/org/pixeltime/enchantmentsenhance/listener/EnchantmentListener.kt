/*
 *     Copyright (C) 2017-Present HealPot
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.locale.LocaleManager
import org.pixeltime.enchantmentsenhance.manager.InventoryManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

abstract class EnchantmentListener : Listener {
    fun name(): String {
        return ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + this.javaClass.simpleName.toLowerCase()))
    }

    fun addPermaPotion(player: Player, type: PotionEffectType, level: Int) {
        player.addPotionEffect(PotionEffect(type, 999999999, level - 1))
    }

    fun removePermaPotion(player: Player, type: PotionEffectType, potionlvl: Int) {
        for (PotionEffect in player.activePotionEffects) {
            if ((PotionEffect.type == type) && (PotionEffect.duration < 1000000000) && (PotionEffect.duration > 500000000)) {
                player.removePotionEffect(type)
            }
        }
    }

    fun permaPotion(player: Player, type: PotionEffectType, potionlvl: Int) {
        if (potionlvl > 0) {
            addPermaPotion(player, type, potionlvl)
        } else {
            removePermaPotion(player, type, potionlvl)
        }
    }

    fun addLang() {
        LocaleManager.addLang("enchantments.${this.javaClass.simpleName.toLowerCase()}", arrayOf(this.javaClass.simpleName) + lang())
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