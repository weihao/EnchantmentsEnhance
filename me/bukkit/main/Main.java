package me.bukkit.main;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.bukkit.effect.SpawnFirework;
import me.bukkit.failstack.Failstack;
import me.bukkit.failstack.Handler;
import me.bukkit.lore.Data;
import me.bukkit.lore.ItemDrop;
import me.bukkit.lore.ItemInChest;
import me.bukkit.lore.playerdeath;

public class Main extends JavaPlugin {
	private Map<String, Boolean> onConfirmation = new HashMap<String, Boolean>();
	public SettingsManager settings = SettingsManager.getInstance();
	public Permissions permissions = new Permissions();
	public Failstack failstack = new Failstack();
	public SpawnFirework spawnFirework = new SpawnFirework();

	public void onEnable() {
		settings.setup(this);
		this.registerLoreCore();
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
		ItemStack item = player.getItemInHand();
		Enchantment enchant = null;

		if (cmd.getName().equalsIgnoreCase("enhance")) {
			if (args.length == 0) {
				printHelp(this, player);
				return true;
			}

			if ((item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.DIAMOND_AXE)
					&& permissions.enhancingWeapon(this, player)) {
				enchant = Enchantment.DAMAGE_ALL;
			} else if ((item.getType() == Material.DIAMOND_CHESTPLATE || item.getType() == Material.DIAMOND_HELMET
					|| item.getType() == Material.DIAMOND_LEGGINGS || item.getType() == Material.DIAMOND_BOOTS)
					&& permissions.enhancingArmor(this, player)) {
				enchant = Enchantment.PROTECTION_ENVIRONMENTAL;
			}
			if (args[0].equalsIgnoreCase("hand")) {
				if (enchant == null) {
					player.sendMessage(ChatColor.RED + settings.getLang().getString("Enhance.itemInvalid"));
					return true;
				}
				if (item.getEnchantmentLevel(enchant) > 20) {
					player.sendMessage(ChatColor.RED + settings.getLang().getString("Enhance.itemMax"));
					return true;
				}
				if (!onConfirmation.containsKey(player.getName())) {
					onConfirmation.put(player.getName(), true);
				}
				player.sendMessage(ChatColor.GREEN + settings.getLang().getString("Enhance.confirm"));
				return true;
			}

			if (args[0].equalsIgnoreCase("confirm")) {
				if (!onConfirmation.containsKey(player.getName())) {
					player.sendMessage(ChatColor.RED + settings.getLang().getString("Enhance.nothingToConfirm"));
					return true;
				}
				if (enchant == null) {
					player.sendMessage(ChatColor.RED + settings.getLang().getString("Enhance.itemInvalid"));
					return true;
				}
				onConfirmation.remove(player.getName());
				double random = Math.random();
				int enchantLevel = item.getEnchantmentLevel(enchant);
				if (random < failstack.getChance(this, player, enchantLevel)) {
					item.addUnsafeEnchantment(enchant, enchantLevel + 1);
					spawnFirework.launch(this, player, getConfig().getInt("fireworkCount." + enchantLevel),
							getConfig().getInt("fireworkRounds." + enchantLevel), getConfig().getInt("fireworkDelay"));
					player.sendMessage(ChatColor.GREEN + settings.getLang().getString("Enhance.enhanceSuccess"));
					failstack.resetLevel(this, player);
					Data.addLore(item, player, ChatColor.translateAlternateColorCodes('&',
							settings.getLang().getString("Lore.UntradeableLore")), settings.getLang(), true);
					renameItem(item, enchantLevel);
					return true;
				} else {
					String str = settings.getLang().getString("Enhance.enhanceFailed");
					if (enchantLevel > 15) {
						item.addUnsafeEnchantment(enchant, enchantLevel - 1);
						renameItem(item, enchantLevel - 1);
						str += settings.getLang().getString("Enhance.downgraded");
					}
					player.sendMessage(ChatColor.RED + str);
					failstack.addLevel(this, player, settings.getConfig().getInt("failstackGained." + enchantLevel));
					return true;
				}
			}

		}

		if (onConfirmation.containsKey(player.getName())) {
			onConfirmation.remove(player.getName());
			player.sendMessage(ChatColor.GREEN + settings.getLang().getString("Enhance.cancel"));
		}

		if ((args[0].equalsIgnoreCase("ver") || args[0].equalsIgnoreCase("version"))
				&& permissions.commandVersion(this, player)) {
			player.sendMessage(ChatColor.GREEN + settings.getLang().getString("Config.checkingVersion")
					.replaceAll("%version%", getDescription().getVersion()));
			return true;
		}

		if (args[0].equalsIgnoreCase("chance") && permissions.commandChance(this, player)) {
			if (enchant != null) {
				player.sendMessage(ChatColor.GOLD + settings.getLang().getString("Enhance.currentFailstack")
						+ failstack.getLevel(this, player));
				String chance = String.format("%.2f",
						failstack.getChance(this, player, item.getEnchantmentLevel(enchant)) * 100);
				player.sendMessage(ChatColor.GOLD
						+ settings.getLang().getString("Enhance.successRate").replaceAll("%chance%", chance));
				return true;
			} else {
				player.sendMessage(ChatColor.RED + settings.getLang().getString("Enhance.itemInvalid"));
			}
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
		player.sendMessage(item.getItemMeta().getDisplayName());
		player.sendMessage(
				ChatColor.translateAlternateColorCodes('&', settings.getLang().getString("Config.invalidCommand")));
		return true;

	}

	/**
	 * this is a helper method.
	 * 
	 * @param sender
	 *            this is a player.
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
	private void registerLoreCore() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Annoucer(this), this);
		pm.registerEvents(new ItemDrop(this), this);
		pm.registerEvents(new playerdeath(this), this);
		pm.registerEvents(new ItemInChest(this), this);
		pm.registerEvents(new Handler(this), this);
	}

	private void renameItem(ItemStack item, int enchantLevel) {
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
				(settings.getLang().getString("Name." + Integer.toString(enchantLevel)))));
		item.setItemMeta(im);
	}
}