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

package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.MenuCoord;
import org.pixeltime.enchantmentsenhance.gui.menu.icons.*;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;
import org.pixeltime.enchantmentsenhance.util.XMaterial;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends GUIAbstract {
    public static Map<String, ItemStack> itemOnEnhancingSlot = new HashMap<String, ItemStack>();
    public static Map<String, Clickable> enhanceMode = new HashMap<>();
    public static EnhanceIcon enhance = new EnhanceIcon();
    public static ForceIcon force = new ForceIcon();
    public static RemoveIcon remove = new RemoveIcon();
    public static StatsIcon stats = new StatsIcon();
    public static StoreIcon store = new StoreIcon();
    public static StoneIcon stone = new StoneIcon();
    public static ItemIcon item = new ItemIcon();
    public static ValksIcon valks = new ValksIcon();
    public static GearIcon gear = new GearIcon();
    public static ToolIcon tool = new ToolIcon();
    public static AccessoryIcon accessory = new AccessoryIcon();

    public MainMenu(Player player) {
        super(player, 54, SettingsManager.lang.getString("Menu.gui.title"));
        update();
    }

    public static void clearPlayer(String playerName) {
        itemOnEnhancingSlot.remove(playerName);
    }

    @Override
    public void update() {
        getInventory().clear();
        getActions().clear();
        Player player = Bukkit.getPlayer(playerName);

        if (!enhanceMode.containsKey(playerName)) {
            enhanceMode.put(playerName, gear);
        }

        if (itemOnEnhancingSlot.containsKey(playerName)) {
            setItem(Util.getSlot(8, 4), itemOnEnhancingSlot.get(playerName));

            setItem(enhance.getPosition(), enhance.getItem(itemOnEnhancingSlot.get(playerName)), () ->
                    Enhance.diceToEnhancement(itemOnEnhancingSlot.get(playerName), player));


            if (DataManager.maximumFailstackApplied[ItemManager.getItemEnchantLevel(itemOnEnhancingSlot.get(playerName)) + 1] != -1
                    && DataManager.costToForceEnchant[ItemManager.getItemEnchantLevel(itemOnEnhancingSlot.get(playerName)) + 1] != -1) {
                setItem(force.getPosition(), force.getItem(itemOnEnhancingSlot.get(playerName)), () ->
                        Enhance.forceToEnhancement(itemOnEnhancingSlot.get(playerName), player));
            }

            setItem(remove.getPosition(), remove.getGlowingItem(playerName), () ->
                    clearPlayer(playerName));

            setItem(stats.getPosition(), stats.getItem(playerName));

            setItem(stone.getPosition(), stone.getItem(itemOnEnhancingSlot.get(playerName), player));

        } else {
            setItem(Util.getSlot(8, 4), new ItemStack(Material.AIR));
            setItem(remove.getPosition(), new ItemStack(Material.AIR));
            setItem(enhance.getPosition(), enhance.getItem(playerName));
            setItem(force.getPosition(), force.getItem(playerName));
            setItem(stats.getPosition(), stats.getItem(playerName));
        }

        setItem(store.getPosition(), Main.getAPI().getFailstack(player.getName()) == 0 ? store.getItem(playerName) : store.getGlowingItem(playerName), () ->
                Main.getAPI().addAdvice(player.getName()));

        setItem(item.getPosition(), item.getItem(player.getName()), () ->
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.closeInventory();
                        new ItemMenu(player).open();
                    }
                }.runTaskLaterAsynchronously(Main.getMain(), 2L));

        setItem(valks.getPosition(), valks.getItem(player), () ->
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.closeInventory();
                        new ValksMenu(player).open();
                    }
                }.runTaskLaterAsynchronously(Main.getMain(), 2L));

        setItem(gear.getPosition(), gear.getItem(playerName));
        setItem(tool.getPosition(), tool.getItem(playerName));
        setItem(accessory.getPosition(), accessory.getItem(playerName));

        for (int i : MenuCoord.getPlaceHolderCoords()) {
            setItem(i, new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial()).setDyeColor(DyeColor.BLACK).setName("&0").toItemStack());
        }
    }
}
