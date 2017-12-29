package me.bukkit.feier;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Main extends JavaPlugin {
	private Annoucer n;
    SettingsManager settings = SettingsManager.getInstance();
    
    
	public void onEnable() {
		settings.setup(this);
		Bukkit.getServer().getLogger().info(settings.getLang().getString("onEnable"));
		Bukkit.getServer().getPluginManager().registerEvents(n = new Annoucer(), this);
	}
	
	
	public void onDisable() {
		Bukkit.getServer().getLogger().info(settings.getLang().getString("onDisable"));
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + settings.getLang().getString("consoleCommand"));
			return true;
		}
		
		Player player = (Player) sender;
		ItemStack item = player.getItemInHand();
		Enchantment enchant;
		double chance;
		if (cmd.getName().equalsIgnoreCase("enhance")) {
			if ((item.getType() == Material.DIAMOND_SWORD
					|| item.getType() == Material.DIAMOND_AXE)
					&& sender.hasPermission("enhancement.weapon"))
			{
				enchant = Enchantment.DAMAGE_ALL;
			}
			else if ((item.getType() == Material.DIAMOND_CHESTPLATE
					|| item.getType() == Material.DIAMOND_HELMET
					|| item.getType() == Material.DIAMOND_LEGGINGS
					|| item.getType() == Material.DIAMOND_BOOTS)
					&& sender.hasPermission("enhancement.armor")) {
				enchant = Enchantment.PROTECTION_ENVIRONMENTAL;
			}
			else {
			    player.sendMessage(ChatColor.RED + settings.getLang().getString("itemInvalid"));
				return true;
			}
			
			if (item.getItemMeta().getEnchantLevel(enchant) == 0) {
				chance = 1.0;
			}
			else {
				chance = (1 / (Math.pow((item.getItemMeta().getEnchantLevel(enchant)), 1.05))
					+ ((item.getItemMeta().getEnchantLevel(enchant) - 1) *
							settings.getLang().getDouble("baseMultiplier")));
			}
			
			if (args.length == 0) {
				double random = Math.random();

				if (random < chance) {
					 item.addUnsafeEnchantment(enchant, item.getEnchantmentLevel(Enchantment.DAMAGE_ALL) + 1);
				     player.sendMessage(ChatColor.GREEN + settings.getLang().getString("enhanceSuccess"));
				     return true;
				}
				else {
					item.removeEnchantment(enchant);
					player.sendMessage(ChatColor.RED + settings.getLang().getString("enhanceFailed"));
					return true;
				}
			}
			if ((args[0].equalsIgnoreCase("ver")
					|| args[0].equalsIgnoreCase("version"))
					&& sender.hasPermission("enhancement.version"))
			{
			     player.sendMessage(ChatColor.GREEN + settings.getLang().getString("checkingVersion").replaceAll("%version%", getDescription().getVersion()));
			     return true;
			}
			if (args[0].equalsIgnoreCase("chance")
					&& sender.hasPermission("enhancement.chance"))
			{
			     player.sendMessage(ChatColor.GOLD + settings.getLang().getString("successRate").replaceAll("%chance%", Double.toString(chance * 100)));
			     return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("reload")) {
			reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Reloaded Enhancer config");
		}
		return true;
	}
	/**
	 * this is a getter method.
	 * @return the notifier.
	 */
	public Annoucer getAnnoucer() {
		return n;
	}
	
	
}