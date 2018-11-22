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

package org.pixeltime.enchantmentsenhance.manager

import com.lgou2w.ldk.bukkit.item.ItemFactory
import com.lgou2w.ldk.bukkit.item.readTag
import com.lgou2w.ldk.bukkit.item.readTagSafe
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.enums.ItemType
import org.pixeltime.enchantmentsenhance.event.Lore
import org.pixeltime.enchantmentsenhance.gui.Clickable
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu
import org.pixeltime.enchantmentsenhance.util.ItemBuilder
import org.pixeltime.enchantmentsenhance.util.Util
import org.pixeltime.enchantmentsenhance.util.XMaterial
import org.pixeltime.enchantmentsenhance.util.datastructure.DoublyLinkedList
import java.util.*

object ItemManager {

    fun isValid(item: ItemStack?, comparable: List<Material>): Boolean {
        return item != null && comparable.contains(item.type)
    }

    /**
     * Determines the enchantment type for the enhancing item.
     *
     * @param item
     * @return
     */
    fun getItemEnchantmentType(item: ItemStack): ItemType {
        return if (isValid(item, MaterialManager.weapon)) {
            ItemType.WEAPON
        } else if (isValid(item, MaterialManager.armor)) {
            ItemType.ARMOR
        } else if (isValid(item, Arrays.asList(Material.BOW))) {
            ItemType.BOW
        } else {
            ItemType.INVALID
        }
    }

    fun getToolItemEnchantmentType(item: ItemStack): ItemType {
        return if (isValid(item, MaterialManager.axe)) {
            ItemType.AXE
        } else if (isValid(item, MaterialManager.pickaxe)) {
            ItemType.PICKAXE
        } else if (isValid(item, MaterialManager.hoe)) {
            ItemType.HOE
        } else {
            ItemType.INVALID
        }
    }

    fun setLevel(item: ItemStack, enhanceLevel: Int): ItemStack {
        val tag = item.readTagSafe()
        tag.putInt("ELevel", enhanceLevel)
        return ItemFactory.writeTag(item, tag)
    }

    fun setName(item: ItemStack, name: String): ItemStack {
        val tag = item.readTagSafe()
        tag.putString("EName", name)
        return ItemFactory.writeTag(item, tag)
    }

    fun getItemEnchantLevel(item: ItemStack): Int {
        return item.readTag()?.getIntOrNull("ELevel") ?: 0
    }

    fun getToolEnchantLevel(item: ItemStack): Int {
        return item.readTag()?.getIntOrNull("ETool") ?: 0
    }

    fun setToolEnchantLevel(item: ItemStack, enhanceLevel: Int): ItemStack {
        val tag = item.readTagSafe()
        tag.putInt("ETool", enhanceLevel)
        return ItemFactory.writeTag(item, tag)
    }

    fun getItemLore(item: ItemStack): String {
        return item.readTag()?.getStringOrNull("ELore") ?: ""
    }

    fun getItemName(item: ItemStack): String? {
        return item.readTag()?.getStringOrNull("EName") ?: ""
    }

    fun setHistory(item: ItemStack, history: String): ItemStack {
        val tag = item.readTagSafe()
        tag.putString("EHistory", history)
        return ItemFactory.writeTag(item, tag)
    }

    fun getHistory(item: ItemStack): String {
        return item.readTag()?.getStringOrNull("EHistory") ?: ""
    }

    fun setGive(item: ItemStack, give: String): ItemStack {
        val tag = item.readTagSafe()
        tag.putString("EGive", give)
        return ItemFactory.writeTag(item, tag)
    }

    fun getGive(item: ItemStack): String {
        return item.readTag()?.getStringOrNull("EGive") ?: ""
    }

    fun soulbound(item: ItemStack) {
        Lore.removeLore(item)
        Lore.addLore(item,
                SettingsManager.lang.getString("lore." + SettingsManager.config
                    .getString("lore.bound") + "Lore"), !SettingsManager.config
            .getString("lore.bound").contains("un"))
    }

    fun forgeItem(player: Player, item: ItemStack, enchantLevel: Int, addition: Boolean, clicked: Clickable?): ItemStack? {
        var currItem = when (clicked) {
            MainMenu.gear -> setLevel(item, enchantLevel)
            MainMenu.tool -> setToolEnchantLevel(item, enchantLevel)
            else -> return null
        }

        // Getting Unique Name.
        val oldLore = KotlinManager.stripLore(item)

        if (enchantLevel == 1 && getItemName(currItem) == null && SettingsManager.config.getBoolean("enableRename")) {
            currItem = setName(currItem, currItem.itemMeta.displayName)
        }

        // Unique ID applied.
        currItem = applyEnchantments(currItem, addition, clicked)!!

        if (SettingsManager.config.getBoolean("enableRename")) {
            renameItem(currItem, clicked)
        }

        addlore(currItem, oldLore, clicked)
        if (!SettingsManager.config.getString("lore.bound").equals("disabled", ignoreCase = true)) {
            soulbound(currItem)
        }
        player.inventory.removeItem(item)
        MainMenu.itemOnEnhancingSlot.put(player.name, currItem)
        player.inventory.addItem(currItem)
        return currItem
    }

    private fun addlore(currItem: ItemStack, old: List<String>?, clicked: Clickable) {
        val im = currItem.itemMeta
        val lore = if (old != null && old.isNotEmpty()) old.toMutableList() else ArrayList()
        val newlore = if (im.hasLore()) im.lore else ArrayList()
        newlore.removeIf { e -> !e.startsWith(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', "&7")) }
        var applyingLores: List<String>? = null
        if (clicked == MainMenu.gear) {
            applyingLores = SettingsManager.config.getList("enhance." + getItemEnchantLevel(currItem) + ".lore") as List<String>
        } else if (clicked == MainMenu.tool) {
            applyingLores = SettingsManager.config.getList("enhance." + getToolEnchantLevel(currItem) + ".lore") as List<String>
        }
        for (s in applyingLores!!) {
            lore.add(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', s))
        }
        newlore.addAll(lore)
        im.lore = newlore
        currItem.itemMeta = im
    }

    fun applyEnchantments(item: ItemStack, addition: Boolean, clicked: Clickable): ItemStack? {
        val enchantLevel: Int = when (clicked) {
            MainMenu.gear -> getItemEnchantLevel(item)
            MainMenu.tool -> getToolEnchantLevel(item)
            else -> return null
        }
        if (enchantLevel > 0) {
            val node = DoublyLinkedList<List<String>>()
            val history = ItemManager.getHistory(item)
            if (!history.isEmpty()) {
                val temp = history
                    .replace("{", "")
                    .replace("}", "")
                    .split("; ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                for (i in temp.indices) {
                    // Nodes
                    node.add(Arrays.asList(*temp[i]
                        .replace("[", "")
                        .replace("]", "")
                        .split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
                }
            }
            if (addition) {
                var type: ItemType? = null
                if (clicked == MainMenu.gear) {
                    type = getItemEnchantmentType(item)
                } else if (clicked == MainMenu.tool) {
                    type = getToolItemEnchantmentType(item)
                }
                val temp = SettingsManager.config.getStringList("enhance."
                                                                + enchantLevel + ".enchantments." + type!!.toString())
                val newNode = ArrayList<String>()
                //Adding New enchantment.
                for (str in temp) {
                    var s = str
                    var condition = false
                    // Conditional Checks.
                    if (s.contains("!")) {
                        val processing = s.split("!".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                        val conditionalEnch: String
                        if (processing.contains("^")) {
                            conditionalEnch = processing.split("\\^".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                            condition = !hasEnchantment(item, conditionalEnch)
                        } else {
                            conditionalEnch = processing
                            condition = hasEnchantment(item, conditionalEnch)
                        }
                        s = s.split("!".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                    } else {
                        condition = true
                    }

                    // Chance checks.
                    val prob: Double
                    if (s.contains("?")) {
                        prob = java.lang.Double.parseDouble(s.split("\\?".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0])
                        s = s.split("\\?".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                    } else {
                        prob = 100.0
                    }

                    // Get enchantment name.
                    val ench = s.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                    s = s.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]


                    // Get enchantment level.
                    val level: Int
                    // Random level.
                    if (s.contains("-")) {
                        val range = s.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        val upper = Integer.parseInt(range[1])
                        val lower = Integer.parseInt(range[0])
                        level = (Math.random() * (upper - lower)).toInt() + lower
                    } else {
                        level = Integer.parseInt(s)
                    }
                    // Random enchantment.
                    val random = Random()
                    if (random.nextDouble() * 100.0 <= prob) {
                        // If all the conditions are met.
                        if (condition) {
                            applyEnchantmentToItem(item, ench, level)
                            newNode.add("$ench:$level")
                        }
                    }
                }
                node.add(newNode)
                return setHistory(item, node.toString())
            } else {
                val it = node.iterator()
                var downgrade: List<String>? = null
                while (it.hasNext()) {
                    downgrade = it.next()
                }
                for (s in downgrade!!) {
                    val a = s.replace("[", "").replace("]", "").split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    applyEnchantmentToItem(item, a[0], -Integer.parseInt(a[1]))
                }
            }
        }
        return item
    }

    fun applyEnchantmentToItem(item: ItemStack, ench: String, level: Int): Boolean {
        val meta = item.itemMeta
        val newlore = if (meta.hasLore()) meta.lore else ArrayList()
        val vanilla = Enchantment.getByName(ench.toUpperCase())

        if (vanilla != null) {
            val lvl = item.getEnchantmentLevel(vanilla) + level
            if (lvl > 0) {
                item.addUnsafeEnchantment(Enchantment.getByName(ench.toUpperCase()), lvl)
            } else {
                item.removeEnchantment(vanilla)
            }
            return true
        } else {
            val enchantment = SettingsManager.lang.getString("enchantments." + ench.toLowerCase())
            var keptLevel = 0
            if (enchantment != null) {
                val currEnch = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', enchantment))
                var i = 0
                while (i < newlore.size) {
                    val curr = ChatColor.stripColor(newlore[i])
                    if (curr.contains(currEnch)) {
                        // Adds original level.
                        keptLevel += Util.romanToInt(curr.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])
                        newlore.removeAt(i)
                        i--
                    }
                    i++
                }
                var max = 1
                try {
                    max = Main.getApi().getEnchantmentMaxLevel(ench)
                } catch (ex: NullPointerException) {
                }

                val finalLevel = if (level + keptLevel > max) max else level + keptLevel
                if (finalLevel > 0) {
                    newlore.add(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', "&7" + enchantment + " " + Util.intToRoman(finalLevel)))
                    meta.lore = newlore
                    item.itemMeta = meta
                    if (item.enchantments.isEmpty()) {
                        CompatibilityManager.glow
                            .addGlow(item)
                    }
                    return true
                } else {
                    meta.lore = newlore
                    item.itemMeta = meta
                    if (item.enchantments.isEmpty()) {
                        CompatibilityManager.glow
                            .addGlow(item)
                    }
                }
            }
            return false
        }
    }

    fun renameItem(item: ItemStack, clicked: Clickable) {
        val enchantLevel: Int = when (clicked) {
            MainMenu.gear -> ItemManager.getItemEnchantLevel(item)
            MainMenu.tool -> ItemManager.getToolEnchantLevel(item)
            else -> return
        }
        var name = getFriendlyName(item)

        if (SettingsManager.config.getBoolean("renamingIncludes.prefix")) {
            val prefix = SettingsManager.config.getString("enhance."
                                                          + enchantLevel + ".prefix")
            if (prefix != null && prefix != "") {
                name = "$prefix $name"
            }
        }
        if (SettingsManager.config.getBoolean("renamingIncludes.suffix")) {
            val suffix = SettingsManager.config.getString("enhance."
                                                          + enchantLevel + ".suffix")
            if (suffix != null && suffix != "") {
                name += " $suffix"
            }
        }

        val im = item.itemMeta
        im.displayName = ChatColor.translateAlternateColorCodes('&', name)
        item.itemMeta = im
    }

    /**
     * This will return a formatted name of a item.
     *
     * @param item
     * @return
     */
    fun getFriendlyName(item: ItemStack): String? {
        return if (getItemName(item) == "") Util.format(item.type.name) else getItemName(item)
    }

    fun itemMaterialize(stoneId: Int, amount: Int): ItemStack {
        return CompatibilityManager.glow
            .addGlow(setGive(ItemBuilder(MaterialManager.stoneTypes[stoneId])
                .setName(SettingsManager.lang.getString("item.$stoneId") + " Bundle: " + amount)
                .addLoreLine(SettingsManager.lang.getString("materialize.info1"))
                .addLoreLine(SettingsManager.lang.getString("materialize.info2")
                    .replace("%amount%", Integer.toString(amount))
                    .replace("%item%", SettingsManager.lang.getString("item.$stoneId")))
                .toItemStack(),
                    stoneId.toString() + ":" + amount))
    }

    fun adviceMaterialize(level: Int): ItemStack {
        return CompatibilityManager.glow
            .addGlow(setGive(ItemBuilder(XMaterial.BOOK.parseItem())
                .setName(SettingsManager.lang.getString("item.valks") + "+" + level)
                .addLoreLine(SettingsManager.lang.getString("materialize.info1"))
                .addLoreLine(SettingsManager.lang.getString("materialize.advice1")
                    .replace("%level%", Integer.toString(level)))
                .toItemStack(),
                    "-1:$level"))
    }

    fun hasEnchantment(item: ItemStack, ench: String): Boolean {
        val vanilla = Enchantment.getByName(ench.toUpperCase())
        if (vanilla != null) {
            val lvl = item.getEnchantmentLevel(vanilla)
            return lvl > 0
        } else {
            val enchantment = SettingsManager.lang.getString("enchantments." + ench.toLowerCase())
            if (enchantment != null) {
                val currEnch = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', enchantment))
                val lores = item.itemMeta.lore
                for (i in lores.indices) {
                    val curr = ChatColor.stripColor(lores[i])
                    if (curr.contains(currEnch)) {
                        return true
                    }
                }
            }
        }
        return false
    }
}
