package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.GraphicsManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import net.minecraft.server.v1_8_R3.EntityEnderDragon;

public class DebugCommand extends SubCommand {
    ArmorStand as;
    public static int animationTask;


    @Override
    public void onCommand(Player p, String[] args) {
// p.updateInventory();
// this.as = (ArmorStand)p.getWorld().spawnEntity(p.getLocation().add(0.5,
// 0.0, 0.5), EntityType.ARMOR_STAND);
// this.as.getLocation().setYaw(0.0f);
// this.as.setVisible(false);
// this.as.setBasePlate(false);
// this.as.setGravity(false);
// this.as.setSmall(true);
// this.as.setMaxHealth(2048.0);
// this.as.setHealth(2048.0);
// // this.as.setHelmet(this.getHead());
// this.as.setCanPickupItems(false);
// this.as.getLocation().setYaw(Math.abs(p.getLocation().getYaw()));
// final Chest chest = new Chest(this.as);
// final ArmorStand open = chest.getArmorStand();
//
// final OpenChest b = new OpenChest(open, p, Main.getMain(), p
// .getLocation());
// Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main
// .getMain(), (BukkitRunnable)b, 0L, 1L);
        GraphicsManager gm = new GraphicsManager();
    }


    @Override
    public String name() {
        return "debug";
    }


    @Override
    public String info() {
        return "\n&6/enhance debug &7- " + SettingsManager.lang.getString(
            "Help.debug");
    }


    @Override
    public String[] aliases() {
        return new String[] { "debug", "tiaoshi", "ts" };
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.debug";
    }


    public void log(String s) {
        Main.getMain().getLogger().info("DEBUG >>>" + s);
    }

}
