package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.event.blacksmith.Failstack;
import org.pixeltime.enchantmentsenhance.event.blacksmith.SecretBook;
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
    public static BackpackIcon backpack = new BackpackIcon();
    public static ValksIcon valks = new ValksIcon();
    public static GearIcon gear = new GearIcon();
    public static ToolIcon tool = new ToolIcon();
    public static AccessoryIcon accessory = new AccessoryIcon();

    public MainMenu(Player player) {
        super(player, 54, SettingsManager.lang.getString("Menu.gui.title"));
        update();
    }

    @Override
    public void update() {
        getInventory().clear();
        getActions().clear();
        Player player = Bukkit.getPlayer(playerName);
        if (itemOnEnhancingSlot.containsKey(playerName)) {
            setItem(Util.getSlot(8, 4), itemOnEnhancingSlot.get(playerName));

            setItem(enhance.getPosition(), enhance.getItem(itemOnEnhancingSlot.get(playerName)), () ->
                    Enhance.diceToEnhancement(itemOnEnhancingSlot.get(playerName), player));


            if (DataManager.maximumFailstackApplied[ItemManager.getItemEnchantLevel(itemOnEnhancingSlot.get(playerName))] != -1
                    && DataManager.costToForceEnchant[ItemManager.getItemEnchantLevel(itemOnEnhancingSlot.get(playerName))] != -1) {
                setItem(force.getPosition(), force.getItem(itemOnEnhancingSlot.get(playerName)), () ->
                        Enhance.forceToEnhancement(itemOnEnhancingSlot.get(playerName), player));
            }

            setItem(remove.getPosition(), remove.getGlowingItem(), () ->
                    itemOnEnhancingSlot.remove(playerName));

            setItem(stats.getPosition(), stats.getItem(playerName));

            setItem(stone.getPosition(), stone.getItem(itemOnEnhancingSlot.get(playerName), player));

        } else {
            setItem(Util.getSlot(8, 4), new ItemStack(Material.AIR));
            setItem(remove.getPosition(), new ItemStack(Material.AIR));
            setItem(enhance.getPosition(), enhance.getItem());
            setItem(force.getPosition(), force.getItem());
            setItem(stats.getPosition(), stats.getItem(playerName));
        }

        setItem(store.getPosition(), Failstack.getLevel(player) == 0 ? store.getItem() : store.getGlowingItem(), () ->
                SecretBook.addFailstackToStorage(player));

        setItem(backpack.getPosition(), backpack.getItem(player), () ->
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.closeInventory();
                        new BackpackMenu(player).open();
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

        setItem(gear.getPosition(), gear.getItem());
        setItem(tool.getPosition(), tool.getItem());
        setItem(accessory.getPosition(), accessory.getItem());

        for (int i : MenuCoord.getPlaceHolderCoords()) {
            setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDyeColor(DyeColor.BLACK).setName("&0").toItemStack());
        }


    }

}
