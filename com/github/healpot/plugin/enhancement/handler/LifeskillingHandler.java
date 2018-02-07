package com.github.healpot.plugin.enhancement.handler;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import com.github.healpot.plugin.enhancement.main.Main;
import com.github.healpot.plugin.enhancement.main.SettingsManager;

public class LifeskillingHandler implements Listener {
	private Main m;

	public LifeskillingHandler(Main m) {
		this.m = m;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if (player.getGameMode() != GameMode.SURVIVAL || !m.permissions.commandEnhance(m, player)) {
			return;
		}
		Block block = e.getBlock();
		for (String listedBlock : SettingsManager.config.getStringList("dropConcWeapon.block")) {
			if (block.getType().toString().equals(listedBlock)) {
				randomDropConcWeapon(m, player);
				return;
			}
		}
		for (String listedBlock : SettingsManager.config.getStringList("dropConcArmor.block")) {
			if (block.getType().toString().equals(listedBlock)) {
				randomDropConcArmor(m, player);
				return;
			}
		}
	}

	@EventHandler
	public void onFish(PlayerFishEvent e) {
		if ((e.getCaught() instanceof Item)) {
			Player player = e.getPlayer();
			String[] stoneType = new String[] { SettingsManager.lang.getString("Item.0"),
					SettingsManager.lang.getString("Item.1") };
			if (Math.random() < SettingsManager.config.getDouble("dropWeaponNArmor.fishing.ratePerFish")) {
				int dice = new Random().nextInt(2);
				m.inventory.addLevel(m, player, dice, 1);
				m.sendMessage(
						SettingsManager.lang.getString("Config.pluginTag")
								+ SettingsManager.lang.getString("Item.get").replaceAll("%ITEM%", stoneType[dice]),
						player);
			}
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Monster) {
			if (e.getEntity().getKiller() instanceof Player) {
				String[] stoneType = new String[] { SettingsManager.lang.getString("Item.0"),
						SettingsManager.lang.getString("Item.1") };
				Player player = e.getEntity().getKiller();
				if (Math.random() < SettingsManager.config.getDouble("dropWeaponNArmor.allMob.ratePerKill")) {
					int dice = new Random().nextInt(2);
					m.inventory.addLevel(m, player, dice, 1);
					m.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
							+ SettingsManager.lang.getString("Item.get") + stoneType[dice], player);
				}
			}
		}
	}

	public void randomDropConcWeapon(Main m, Player player) {
		if (Math.random() < SettingsManager.config.getDouble("dropConcWeapon.ratePerBlock")) {
			m.inventory.addLevel(m, player, 2, 1);
			m.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
					+ SettingsManager.lang.getString("Item.get") + SettingsManager.lang.getString("Item.2"), player);
		}
	}

	public void randomDropConcArmor(Main m, Player player) {
		if (Math.random() < SettingsManager.config.getDouble("dropConcWeapon.ratePerBlock")) {
			m.inventory.addLevel(m, player, 3, 1);
			m.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
					+ SettingsManager.lang.getString("Item.get") + SettingsManager.lang.getString("Item.3"), player);
		}
	}
}
