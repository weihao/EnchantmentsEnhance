package com.github.healpot.plugin.enhancement.me.main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.healpot.plugin.enhancement.me.blackspirit.Enhance;
import com.github.healpot.plugin.enhancement.me.effect.Glow;
import com.github.healpot.plugin.enhancement.me.effect.PlaySound;
import com.github.healpot.plugin.enhancement.me.effect.SpawnFirework;
import com.github.healpot.plugin.enhancement.me.failstack.Failstack;
import com.github.healpot.plugin.enhancement.me.failstack.FailstackHandler;
import com.github.healpot.plugin.enhancement.me.lore.ItemDrop;
import com.github.healpot.plugin.enhancement.me.lore.playerdeath;
import com.github.healpot.plugin.enhancement.me.visual.Menu;
import com.github.healpot.plugin.enhancement.me.visual.MenuHandler;

public class Main extends JavaPlugin {
	private Map<String, Boolean> onConfirmation = new HashMap<String, Boolean>();
	public SettingsManager settings = SettingsManager.getInstance();
	public Permissions permissions = new Permissions();
	public Failstack failstack = new Failstack();
	public SpawnFirework spawnFirework = new SpawnFirework();
	public PlaySound playSound = new PlaySound();
	public Enhance enhance = new Enhance();
	public Menu menu = new Menu();
	public Glow glow = new Glow();

	public void onEnable() {
		settings.setup(this);
		this.registerCore();
		Bukkit.getServer().getLogger().info(settings.getLang().getString("Config.onEnable"));
	}

	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			this.failstack.saveLevels(this, player, false);
		}
		settings.saveData();
		Bukkit.getServer().getLogger().info(settings.getLang().getString("Config.onDisable"));
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + settings.getLang().getString("Config.consoleCommand"));
			return true;
		}

		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("enhance")) {
			if (args.length == 0) {
				printHelp(this, player);
				return true;
			}
			if (onConfirmation.containsKey(player.getName())) {
				onConfirmation.remove(player.getName());
				player.sendMessage(ChatColor.GREEN + settings.getLang().getString("Enhance.cancel"));
			}
			if ((args[0].equalsIgnoreCase("menu")) && permissions.commandMenu(this, player)) {
				menu.showEnhancingMenu(this, player, player.getItemInHand());
				return true;
			}
			if ((args[0].equalsIgnoreCase("ver") || args[0].equalsIgnoreCase("version"))
					&& permissions.commandVersion(this, player)) {
				player.sendMessage(ChatColor.GREEN + settings.getLang().getString("Config.checkingVersion")
						.replaceAll("%version%", getDescription().getVersion()));
				return true;
			}
			if (args[0].equalsIgnoreCase("reload") && permissions.commandReload(this, player)) {
				settings.reloadConfig();
				settings.reloadData();
				settings.reloadLang();
				player.sendMessage(ChatColor.GREEN + settings.getLang().getString("Config.reload"));
				return true;
			}
			if (args[0].equalsIgnoreCase("help") && permissions.commandHelp(this, player)) {
				printHelp(this, player);
				return true;
			}
		}
		player.sendMessage(
				ChatColor.translateAlternateColorCodes('&', settings.getLang().getString("Config.invalidCommand")));
		return true;
	}

	/**
	 * this is a helper method.
	 * 
	 * @param sender
	 */
	private void printHelp(Main m, Player player) {
		String help = "&b&l&m          &d EnchantmentsEnhance&b&l&m          ";
		if (permissions.commandHelp(m, player))
			help += "\n&6/enhance help &7- " + settings.getLang().getString("Help.help");
		if (permissions.enhancingArmor(m, player) || permissions.enhancingWeapon(m, player))
			help += "\n&6/enhance hand &7- " + settings.getLang().getString("Help.hand");
		if (permissions.commandReload(m, player))
			help += "\n&6/enhance reload &7- " + settings.getLang().getString("Help.reload");
		if (permissions.commandChance(m, player))
			help += "\n&6/enhance chance &7- " + settings.getLang().getString("Help.chance");
		if (permissions.commandVersion(m, player))
			help += "\n&6/enhance version &7- " + settings.getLang().getString("Help.version");

		player.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
	}

	/**
	 * This part includes the initialization of the lore.
	 */
	private void registerCore() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Annoucer(this), this);
		pm.registerEvents(new ItemDrop(this), this);
		pm.registerEvents(new playerdeath(this), this);
		pm.registerEvents(new FailstackHandler(this), this);
		pm.registerEvents(new MenuHandler(this), this);
	}

	/**
	 * 
	 * @param item
	 *            this is the item you want to rename.
	 * @param enchantLevel
	 *            this determines the corresponding name for the item.
	 */
	public void renameItem(ItemStack item, int enchantLevel) {
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
				(settings.getLang().getString("Name." + Integer.toString(enchantLevel)))));
		item.setItemMeta(im);
	}

	/**
	 * 
	 * @param player
	 * @return false if the player have not yet confirmed an action. false true if
	 *         the player have confirmed an action.
	 */

	public boolean onConfirmation(Player player) {
		// TODO Auto-generated method stub
		return false;
	}
}