package me.bukkit.main;

public class Language {
	public void addCnDefault(SettingsManager s) {
		s.lang.addDefault("Config.pluginTag", "&f[&6强化插件&f]");
		s.lang.addDefault("Config.checkingVersion", "你正在使用的插件版本是v%version%.");
		s.lang.addDefault("Config.onEnable", "强化插件已开启!");
		s.lang.addDefault("Config.onDisable", "强化插件已禁用!");
		s.lang.addDefault("Config.consoleCommand", "服务器不可以使用这个指令哟!");
		s.lang.addDefault("Config.reload", "插件重载成功!");
		s.lang.addDefault("Config.welcome", "&a欢迎你, %player%勇士!使用&6/enhance help&a查看武器装备强化的指南!");

		s.lang.addDefault("Enhance.successRate", "你手中的物品的成功率为%chance%%.");
		s.lang.addDefault("Enhance.itemInvalid", "你手里拿着的武器不可以强化!");
		s.lang.addDefault("Enhance.enhanceSuccess", "强化成功!");
		s.lang.addDefault("Enhance.enhanceFailed", "强化失败!你失去了当前强化等级!");

		s.lang.addDefault("Lore.UntradeableLore", "&8[&6死亡不掉落&8]&8[&4不可交易&8]&f");
		s.lang.addDefault("Lore.TradeableLore", "&8[&6死亡不掉落&8]&8[&2可交易&8]&f");

		s.lang.addDefault("Messages.NoItemInHand", "&4You have to hold an item to execute this command!");
		s.lang.addDefault("Messages.AlreadyUntradeable", "&4This item is already untradeable!");
		s.lang.addDefault("Messages.AlreadyTradeable", "&4This item is already tradeable!");
		s.lang.addDefault("Messages.AlreadyUnbound", "&4This item has already been unbound!");
		s.lang.addDefault("Messages.MadeUntradeable", "&2This item is now untradeable!");
		s.lang.addDefault("Messages.MadeTradeable", "&2This item is now tradeable!");
		s.lang.addDefault("Messages.MadeUnbound", "&2This item has been unbound!");
		s.lang.addDefault("Messages.NoDrop", "&4This item can't be dropped!");
		s.lang.addDefault("Messages.NoStorage", "&4You can't store this item!");

		s.lang.options().copyDefaults(true);
		s.saveLang();
	}
}
