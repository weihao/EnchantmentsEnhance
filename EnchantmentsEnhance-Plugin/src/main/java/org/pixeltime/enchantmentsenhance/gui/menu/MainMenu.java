/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.event.Enhance;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.MenuCoord;
import org.pixeltime.enchantmentsenhance.gui.menu.icons.*;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainMenu extends GUIAbstract {
    public static Map<String, ItemStack> itemOnEnhancingSlot = new HashMap<String, ItemStack>();
    public static Map<String, Clickable> enhancingMode = new HashMap<>();
    public static Map<String, BukkitTask> inProgress = new HashMap<>();
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
    public static CancelIcon cancel = new CancelIcon();

    public MainMenu(Player player) {
        super(player, 54, SettingsManager.lang.getString("menu.gui.title"));
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

        if (!enhancingMode.containsKey(playerName)) {
            enhancingMode.put(playerName, gear);
        }

        if (itemOnEnhancingSlot.containsKey(playerName)) {
            // Sets Enhancing item on display.
            setItem(Util.getSlot(8, 4), itemOnEnhancingSlot.get(playerName));
            // Sets Enhance button.
            if (enhancingMode.get(playerName).equals(gear)) {
                if (Enhance.getValidationOfItem(itemOnEnhancingSlot.get(playerName)) && Enhance.getValidationOfPlayer(itemOnEnhancingSlot.get(playerName), player, enhancingMode.get(playerName))) {
                    setItem(enhance.getPosition(), enhance.getGlowingItem(itemOnEnhancingSlot.get(playerName)), (clickType) -> {
                        if (clickType == ClickType.LEFT || clickType == ClickType.DOUBLE_CLICK) {
                            inProgress.put(playerName, new BukkitRunnable() {
                                int count = 0;
                                List<ItemStack> animate = new ArrayList<>();

                                @Override
                                public void run() {
                                    if (count == 0) {
                                        setItem(cancel.getPosition(), cancel.getGlowingItem(playerName), (clicktype) -> {
                                            this.cancel();
                                            update();
                                        });
                                        setItem(force.getPosition(), cancel.getGlowingItem(playerName), (clicktype) -> {
                                            this.cancel();
                                            update();
                                        });
                                    }
                                    if (count == 5) {
                                        try {
                                            Enhance.diceToEnhancement(itemOnEnhancingSlot.get(playerName), player, enhancingMode.get(playerName));
                                        } catch (Exception ex) {
                                            // Player might not be online.
                                        }
                                        this.cancel();
                                        update();
                                        inProgress.remove(playerName);
                                        return;
                                    }
                                    animate.add(Util.randomWool());
                                    if (count >= 0) {
                                        for (int i = 0; i < animate.size(); i++) {
                                            setItem(Util.getSlot(3 + i, 4), animate.get(animate.size() - i - 1));
                                        }
                                    }
                                    count++;
                                }
                            }.runTaskTimer(Main.getMain(), 0L, 10L));
                        } else {
                            // Right click.
                            Enhance.diceToEnhancement(itemOnEnhancingSlot.get(playerName), player, enhancingMode.get(playerName));
                        }
                    });
                } else {
                    setItem(enhance.getPosition(), enhance.getItem(itemOnEnhancingSlot.get(playerName)), (clickType) -> {
                        int enchantLevel = ItemManager.getItemEnchantLevel(itemOnEnhancingSlot.get(playerName)) + 1;
                        int stoneId = Enhance.getStoneId(itemOnEnhancingSlot.get(playerName), enchantLevel, enhancingMode.get(playerName));
                        Util.sendMessage(SettingsManager.lang.getString("item.noItem")
                                .replaceAll("%STONE%", SettingsManager.lang.getString(
                                        "item." + stoneId)), player);
                    });
                }
            } else if (enhancingMode.get(playerName).equals(tool)) {
                if (Enhance.getValidationOfToolItem(itemOnEnhancingSlot.get(playerName)) && Enhance.getToolValidationOfPlayer(itemOnEnhancingSlot.get(playerName), player, enhancingMode.get(playerName))) {
                    setItem(enhance.getPosition(), enhance.getGlowingItem(itemOnEnhancingSlot.get(playerName)), (clickType) -> {
                        if (clickType == ClickType.LEFT || clickType == ClickType.DOUBLE_CLICK) {
                            inProgress.put(playerName, new BukkitRunnable() {
                                int count = 0;
                                List<ItemStack> animate = new ArrayList<>();

                                @Override
                                public void run() {
                                    if (count == 0) {
                                        setItem(cancel.getPosition(), cancel.getGlowingItem(playerName), (clicktype) -> {
                                            this.cancel();
                                            update();
                                        });
                                        setItem(force.getPosition(), cancel.getGlowingItem(playerName), (clicktype) -> {
                                            this.cancel();
                                            update();
                                        });
                                    }
                                    if (count == 5) {
                                        try {
                                            Enhance.diceToEnhancement(itemOnEnhancingSlot.get(playerName), player, enhancingMode.get(playerName));
                                        } catch (Exception ex) {
                                            // Player might not be online.
                                        }
                                        this.cancel();
                                        update();
                                        inProgress.remove(playerName);
                                        return;
                                    }
                                    animate.add(Util.randomWool());
                                    if (count >= 0) {
                                        for (int i = 0; i < animate.size(); i++) {
                                            setItem(Util.getSlot(3 + i, 4), animate.get(animate.size() - i - 1));
                                        }
                                    }
                                    count++;
                                }
                            }.runTaskTimer(Main.getMain(), 0L, 10L));
                        } else {
                            // Right click.
                            Enhance.diceToEnhancement(itemOnEnhancingSlot.get(playerName), player, enhancingMode.get(playerName));
                        }
                    });
                } else {
                    setItem(enhance.getPosition(), enhance.getItem(itemOnEnhancingSlot.get(playerName)), (clickType) -> {
                        int enchantLevel = ItemManager.getItemEnchantLevel(itemOnEnhancingSlot.get(playerName)) + 1;
                        int stoneId = Enhance.getStoneId(itemOnEnhancingSlot.get(playerName), enchantLevel, enhancingMode.get(playerName));
                        Util.sendMessage(SettingsManager.lang.getString("item.noItem")
                                .replaceAll("%STONE%", SettingsManager.lang.getString(
                                        "item." + stoneId)), player);
                    });
                }
            }


            if ((DataManager.maximumFailstackApplied[ItemManager.getItemEnchantLevel(itemOnEnhancingSlot.get(playerName)) + 1] > 0
                    || DataManager.maximumFailstackApplied[ItemManager.getToolEnchantLevel(itemOnEnhancingSlot.get(playerName)) + 1] > 0)
                    && (DataManager.costToForceEnchant[ItemManager.getItemEnchantLevel(itemOnEnhancingSlot.get(playerName)) + 1] > 0
                    || DataManager.costToForceEnchant[ItemManager.getToolEnchantLevel(itemOnEnhancingSlot.get(playerName)) + 1] > 0)) {
                if (Enhance.getValidationOfForce(itemOnEnhancingSlot.get(playerName), player, enhancingMode.get(playerName))) {
                    setItem(force.getPosition(), force.getGlowingItem(itemOnEnhancingSlot.get(playerName), enhancingMode.get(playerName)), (clickType) -> {
                        Enhance.forceToEnhancement(itemOnEnhancingSlot.get(playerName), player, enhancingMode.get(playerName));
                    });
                } else {
                    setItem(force.getPosition(), force.getItem(itemOnEnhancingSlot.get(playerName), enhancingMode.get(playerName)), (clickType) -> {
                        // Current enchant level before enhancing
                        int enchantLevel = ItemManager.getItemEnchantLevel(itemOnEnhancingSlot.get(playerName)) + 1;
                        // Finds the stone used in the enhancement
                        int stoneId = Enhance.getStoneId(itemOnEnhancingSlot.get(playerName), enchantLevel, enhancingMode.get(playerName));
                        Util.sendMessage(SettingsManager.lang.getString("item.noItem")
                                .replaceAll("%STONE%", SettingsManager.lang.getString(
                                        "item." + stoneId)), player);
                    });
                }
            }

            setItem(remove.getPosition(), remove.getGlowingItem(playerName), (clickType) -> {
                clearPlayer(playerName);
                if (MainMenu.inProgress.containsKey(player.getName())) {
                    MainMenu.inProgress.get(player.getName()).cancel();
                }
            });


            setItem(stats.getPosition(), stats.getItem(playerName, enhancingMode.get(playerName)));

            setItem(stone.getPosition(), stone.getItem(itemOnEnhancingSlot.get(playerName), player, enhancingMode.get(playerName)));

        } else {
            setItem(Util.getSlot(8, 4), new ItemStack(Material.AIR));
            setItem(remove.getPosition(), new ItemStack(Material.AIR));
            setItem(enhance.getPosition(), enhance.getItem(playerName));
            setItem(force.getPosition(), force.getItem(playerName));
            setItem(stats.getPosition(), stats.getItem(playerName));
        }

        setItem(store.getPosition(), Main.getApi().getFailstack(player.getName()) == 0 ? store.getItem(playerName) : store.getGlowingItem(playerName), (clickType) ->
                Main.getApi().addAdvice(player.getName()));

        setItem(item.getPosition(), item.getItem(player.getName()), (clickType) ->
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.closeInventory();
                        new ItemMenu(player).open();
                    }
                }.runTaskLater(Main.getMain(), 2L));

        setItem(valks.getPosition(), valks.getItem(player), (clickType) ->
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.closeInventory();
                        new ValksMenu(player).open();
                    }
                }.runTaskLater(Main.getMain(), 2L));

        setItem(gear.getPosition(), (enhancingMode.containsKey(playerName) && enhancingMode.get(playerName).equals(gear))
                        ? gear.getGlowingItem(playerName)
                        : gear.getItem(playerName),
                (clickType) -> {
                    enhancingMode.put(playerName, gear);
                    clearPlayer(playerName);
                });

        setItem(tool.getPosition(), (enhancingMode.containsKey(playerName) && enhancingMode.get(playerName).equals(tool))
                        ? tool.getGlowingItem(playerName)
                        : tool.getItem(playerName),
                (clickType) -> {
                    enhancingMode.put(playerName, tool);
                    clearPlayer(playerName);
                });
        setItem(accessory.getPosition(), (enhancingMode.containsKey(playerName) && enhancingMode.get(playerName).equals(accessory))
                        ? accessory.getGlowingItem(playerName)
                        : accessory.getItem(playerName),
                (clickType) -> {
                    enhancingMode.put(playerName, accessory);
                    clearPlayer(playerName);
                });

        for (int i : MenuCoord.getPlaceHolderCoords()) {
            setItem(i, new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.toBukkit()).setDyeColor(DyeColor.BLACK).setName("&0").toItemStack());
        }
    }
}
