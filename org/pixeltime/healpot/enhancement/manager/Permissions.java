package org.pixeltime.healpot.enhancement.manager;

import org.bukkit.entity.Player;

public class Permissions {

	public static boolean commandHelp(Player player) {
		return player.hasPermission("Enchantmentsenhance.help") || player.hasPermission("Enchantmentsenhance.*");
	}

	public static boolean commandReload(Player player) {
		return player.hasPermission("Enchantmentsenhance.reload") || player.hasPermission("Enchantmentsenhance.*");
	}

	public static boolean commandVersion(Player player) {
		return player.hasPermission("Enchantmentsenhance.version") || player.hasPermission("Enchantmentsenhance.*");
	}

	public static boolean commandEnhance(Player player) {
		return player.hasPermission("Enchantmentsenhance.enhance") || player.hasPermission("Enchantmentsenhance.*");
	}

	public static boolean commandLore(Player player) {
		return player.hasPermission("Enchantmentsenhance.removelore") || player.hasPermission("Enchantmentsenhance.*");
	}

	public static boolean commandAdd(Player player) {
		return player.hasPermission("Enchantmentsenhance.add") || player.hasPermission("Enchantmentsenhance.*");
	}
}