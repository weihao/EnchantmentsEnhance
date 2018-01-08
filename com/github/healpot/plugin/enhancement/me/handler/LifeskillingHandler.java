package com.github.healpot.plugin.enhancement.me.handler;

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

import com.github.healpot.plugin.enhancement.me.main.Main;

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
		for (String listedBlock : m.settings.getConfig().getStringList("dropConcWeapon.block")) {
			if (block.getType().toString().equals(listedBlock)) {
				randomDropConcWeapon(m, player);
				return;
			}
		}
		for (String listedBlock : m.settings.getConfig().getStringList("dropConcArmor.block")) {
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
			String[] stoneType = new String[] { m.settings.getLang().getString("Item.0"),
					m.settings.getLang().getString("Item.1") };
			if (0 < m.settings.getConfig().getDouble("dropWeaponNArmor.fishing.ratePerFish")) {
				int dice = new Random().nextInt(2);
				m.inventory.addLevel(m, player, dice, 1);
				m.sendMessage(m.settings.getLang().getString("Item.get") + stoneType[dice], player);
			}
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Monster) {
			if (e.getEntity().getKiller() instanceof Player) {
				String[] stoneType = new String[] { m.settings.getLang().getString("Item.0"),
						m.settings.getLang().getString("Item.1") };
				Player player = e.getEntity().getKiller();
				if (0 < m.settings.getConfig().getDouble("dropWeaponNArmor.allMob.ratePerKill")) {
					int dice = new Random().nextInt(2);
					m.inventory.addLevel(m, player, dice, 1);
					m.sendMessage(m.settings.getLang().getString("Item.get") + stoneType[dice], player);
				}
			}
		}
	}

	public void randomDropConcWeapon(Main m, Player player) {
		if (0 < m.settings.getConfig().getDouble("dropConcWeapon.ratePerBlock")) {
			m.inventory.addLevel(m, player, 2, 1);
			m.sendMessage(m.settings.getLang().getString("Item.get") + m.settings.getLang().getString("Item.2"),
					player);
		}
	}

	public void randomDropConcArmor(Main m, Player player) {
		if (0 < m.settings.getConfig().getDouble("dropConcWeapon.ratePerBlock")) {
			m.inventory.addLevel(m, player, 3, 1);
			m.sendMessage(m.settings.getLang().getString("Item.get") + m.settings.getLang().getString("Item.3"),
					player);
		}
	}
}
