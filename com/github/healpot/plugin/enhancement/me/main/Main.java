package com.github.healpot.plugin.enhancement.me.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.healpot.plugin.enhancement.me.blacksmith.SecretBook;
import com.github.healpot.plugin.enhancement.me.blackspirit.Enhance;
import com.github.healpot.plugin.enhancement.me.effect.Broadcast;
import com.github.healpot.plugin.enhancement.me.effect.SpawnFirework;
import com.github.healpot.plugin.enhancement.me.failstack.Failstack;
import com.github.healpot.plugin.enhancement.me.handler.ItemDropHandler;
import com.github.healpot.plugin.enhancement.me.handler.LifeskillingHandler;
import com.github.healpot.plugin.enhancement.me.handler.MenuHandler;
import com.github.healpot.plugin.enhancement.me.handler.PlayerDeathHandler;
import com.github.healpot.plugin.enhancement.me.handler.PlayerStreamHandler;
import com.github.healpot.plugin.enhancement.me.lore.Data;
import com.github.healpot.plugin.enhancement.me.modular.Compatibility;
import com.github.healpot.plugin.enhancement.me.player.Inventory;
import com.github.healpot.plugin.enhancement.me.visual.Menu;

public class Main extends JavaPlugin {
	public SettingsManager settings = SettingsManager.getInstance();
	public Permissions permissions = new Permissions();
	public Failstack failstack = new Failstack();
	public SpawnFirework spawnFirework = new SpawnFirework();
	public Enhance enhance = new Enhance();
	public Menu menu = new Menu();
	public Data data = new Data();
	public Compatibility compatibility = new Compatibility();
	public Broadcast broadcast = new Broadcast();
	public SecretBook secretbook = new SecretBook();
	public Inventory inventory = new Inventory();

	public void onEnable() {
		saveDefaultConfig();
		settings.setup(this);
		registerCore();
		registerNMS();
		Bukkit.getServer().getLogger().info(settings.getLang().getString("Config.onEnable"));
		if (Bukkit.getOnlinePlayers() != null) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				failstack.loadLevels(this, player);
				secretbook.loadStorage(this, player);
				inventory.loadInventory(this, player);
			}
		}
	}

	public void onDisable() {
		if (Bukkit.getOnlinePlayers() != null) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				this.failstack.saveLevels(this, player, false);
				this.secretbook.saveStorageToDisk(this, player, false);
				this.inventory.saveInventoryToDisk(this, player, false);
			}
		}
		settings.saveData();
		Bukkit.getServer().getLogger().info(settings.getLang().getString("Config.onDisable"));
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (!(sender instanceof Player)) {
			sendMessage(settings.getLang().getString("Config.pluginTag")
					+ settings.getLang().getString("Config.consoleCommand"), sender);
			return true;
		}

		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("enhance")) {
			if (args.length == 0) {
				printHelp(this, player);
				return true;
			}

			if ((args[0].equalsIgnoreCase("menu")) && permissions.commandEnhance(this, player)) {
				menu.showEnhancingMenu(this, player, player.getItemInHand());
				return true;
			}
			if ((args[0].equalsIgnoreCase("ver") || args[0].equalsIgnoreCase("version"))
					&& permissions.commandVersion(this, player)) {
				sendMessage(settings.getLang().getString("Config.pluginTag") + settings.getLang()
						.getString("Config.checkingVersion").replaceAll("%version%", getDescription().getVersion()),
						player);
				return true;
			}
			if (args[0].equalsIgnoreCase("reload") && permissions.commandReload(this, player)) {
				settings.reloadConfig();
				settings.reloadData();
				settings.reloadLang();
				sendMessage(settings.getLang().getString("Config.pluginTag")
						+ settings.getLang().getString("Config.reload"), player);
				return true;
			}
			if (args[0].equalsIgnoreCase("help") && permissions.commandHelp(this, player)) {
				printHelp(this, player);
				return true;
			}
			if (args[0].equalsIgnoreCase("inventory") && permissions.commandInventory(this, player)) {
				inventory.printInventory(this, player);
				return true;
			}
			if (args[0].equalsIgnoreCase("add") && permissions.commandAdd(this, player)) {
				inventory.addLevel(this, player, 0, 999);
				inventory.addLevel(this, player, 1, 999);
				inventory.addLevel(this, player, 2, 999);
				inventory.addLevel(this, player, 3, 999);
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("list")) {
					secretbook.list(this, player, 0);
					return true;
				}
				if (args[0].equalsIgnoreCase("select")) {
					secretbook.select(this, player, 1);
					return true;
				}
			}
			if (args.length == 2)
				if (args[0].equalsIgnoreCase("list")) {
					secretbook.list(this, player, Integer.parseInt(args[1]));
					return true;
				}
			if (args[0].equalsIgnoreCase("select")) {
				secretbook.select(this, player, Integer.parseInt(args[1]));
				return true;
			}
		}
		sendMessage(settings.getLang().getString("Config.pluginTag")
				+ settings.getLang().getString("Config.invalidCommand"), player);
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
		if (permissions.commandEnhance(m, player))
			help += "\n&6/enhance menu &7- " + settings.getLang().getString("Help.menu");
		if (permissions.commandLore(m, player))
			help += "\n&6/enhance lore &7- " + settings.getLang().getString("Help.lore");
		if (permissions.commandInventory(m, player))
			help += "\n&6/enhance inventory &7- " + settings.getLang().getString("Help.inventory");
		if (permissions.commandReload(m, player))
			help += "\n&6/enhance reload &7- " + settings.getLang().getString("Help.reload");
		if (permissions.commandVersion(m, player))
			help += "\n&6/enhance version &7- " + settings.getLang().getString("Help.version");
		if (permissions.commandAdd(m, player))
			help += "\n&6/enhance add &7- " + settings.getLang().getString("Help.add");

		sendMessage(help, player);
	}

	/**
	 * This part includes the initialization of the lore.
	 */
	private void registerCore() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ItemDropHandler(this), this);
		pm.registerEvents(new PlayerDeathHandler(this), this);
		pm.registerEvents(new PlayerStreamHandler(this), this);
		pm.registerEvents(new MenuHandler(this), this);
		pm.registerEvents(new LifeskillingHandler(this), this);
	}

	private void registerNMS() {
		if (compatibility.setupGlow()) {
			getLogger().info("Enhancement Glower setup was successful!");
		} else {

			getLogger().severe("Failed to setup Enhancement Glower!");
			getLogger().severe("Your server version is not compatible with this plugin!");

			Bukkit.getPluginManager().disablePlugin(this);
		}

		if (compatibility.setupSound()) {
			getLogger().info("Enhancement Sound setup was successful!");
		} else {

			getLogger().severe("Failed to setup Enhancement Sound!");
			getLogger().severe("Your server version is not compatible with this plugin!");
			Bukkit.getPluginManager().disablePlugin(this);
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		List<String> str = new ArrayList<String>();
		if (cmd.getName().equalsIgnoreCase("enhance")) {
			Player player = (Player) sender;
			if (permissions.commandHelp(this, player)) {
				str.add("help");
			}
			if (permissions.commandEnhance(this, player)) {
				str.add("menu");
				str.add("list");
				str.add("select");
			}
			if (permissions.commandReload(this, player)) {
				str.add("reload");
			}
			if (permissions.commandVersion(this, player)) {
				str.add("version");
			}
			if (permissions.commandInventory(this, player)) {
				str.add("inventory");
			}
			if (permissions.commandAdd(this, player)) {
				str.add("add");
			}
		}
		return str;
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

	public void sendMessage(String msg, CommandSender sender) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		sender.sendMessage(message);
	}

	public String toColor(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}
}