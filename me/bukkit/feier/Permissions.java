package me.bukkit.feier;

import org.bukkit.entity.Player;

public class Permissions {

    public boolean commandHelp(Main m, Player player) {
        return player.hasPermission("Enchantmentsenhance.help") || player.hasPermission("Enchantmentsenhance.*");
    }

    public boolean commandReload(Main m, Player player) {
        return player.hasPermission("Enchantmentsenhance.reload") || player.hasPermission("Enchantmentsenhance.*");
    }

    public boolean commandChance(Main m, Player player) {
        return player.hasPermission("Enchantmentsenhance.chance") || player.hasPermission("Enchantmentsenhance.*");
    }
    
    public boolean commandVersion(Main m, Player player) {
        return player.hasPermission("Enchantmentsenhance.version") || player.hasPermission("Enchantmentsenhance.*");
    }
    
    public boolean enhancingArmor(Main m, Player player) {
        return player.hasPermission("Enchantmentsenhance.armor") || player.hasPermission("Enchantmentsenhance.*");
    }
    
    public boolean enhancingWeapon(Main m, Player player) {
        return player.hasPermission("Enchantmentsenhance.weapon") || player.hasPermission("Enchantmentsenhance.*");
    }
    
}