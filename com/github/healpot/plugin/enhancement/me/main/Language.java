package com.github.healpot.plugin.enhancement.me.main;

public class Language {
	public void iniLanguage(SettingsManager s, String language) {
		if (language.equals("cn")) {
			addCnDefault(s);
		} else {
			addEnDefault(s);
		}
	}

	public void addCnDefault(SettingsManager s) {
		s.lang.addDefault("Config.pluginTag", "&f[&6强化插件&f] ");
		s.lang.addDefault("Config.checkingVersion", "&a您正在使用的插件版本是v%version%.");
		s.lang.addDefault("Config.onEnable", "强化插件已开启!");
		s.lang.addDefault("Config.onDisable", "强化插件已禁用!");
		s.lang.addDefault("Config.consoleCommand", "服务器不可以使用这个指令哟!");
		s.lang.addDefault("Config.reload", "&a插件重载成功!");
		s.lang.addDefault("Config.welcome", "&a欢迎您, %player%勇士!使用&6/enhance help&a查看武器装备强化的指南!");
		s.lang.addDefault("Config.invalidCommand", "&a您输入的指令无效!使用&6/enhance help&a查看帮助!");
		s.lang.addDefault("Config.noPerm", "&c你没有权限这么做!");

		s.lang.addDefault("Annoucer.success", "&6强化成功: ");
		s.lang.addDefault("Annoucer.failed", "&6强化失败: ");
		s.lang.addDefault("Annoucer.got", " &6获得了 ");
		s.lang.addDefault("Annoucer.failed", " &6潜力突破失败了 ");

		s.lang.addDefault("Enhance.successRate", "&b物品的成功率为%chance%%.");
		s.lang.addDefault("Enhance.itemInvalid", "&c不可以强化!");
		s.lang.addDefault("Enhance.itemMax", "&6物品已是最高级");
		s.lang.addDefault("Enhance.enhanceSuccess", "&6强化成功!");
		s.lang.addDefault("Enhance.forceEnhanceSuccess", "&6强制突破成功!");
		s.lang.addDefault("Enhance.enhanceFailed", "&c强化失败!");
		s.lang.addDefault("Enhance.downgraded", "&4您的物品降级了!");
		s.lang.addDefault("Enhance.currentFailstack", "&b您目前的垫子是: ");

		s.lang.addDefault("Lore.UntradeableLore", "&8[&6死亡不掉落&8]&8[&4不可交易&8]&f");
		s.lang.addDefault("Lore.TradeableLore", "&8[&6死亡不掉落&8]&8[&2可交易&8]&f");

		s.lang.addDefault("Messages.NoItemInHand", "&4手中物品不符合强化标准!");
		s.lang.addDefault("Messages.AlreadyUntradeable", "&4已是不可交易物品!");
		s.lang.addDefault("Messages.AlreadyTradeable", "&4已是可交易物品!");
		s.lang.addDefault("Messages.AlreadyUnbound", "&4已是解绑物品!");
		s.lang.addDefault("Messages.MadeUntradeable", "&2现是不可交易物品!");
		s.lang.addDefault("Messages.MadeTradeable", "&现是可交易物品!");
		s.lang.addDefault("Messages.MadeUnbound", "&2现是解绑物品!");
		s.lang.addDefault("Messages.NoDrop", "&4这个物品不可被丢弃!");
		s.lang.addDefault("Messages.NoStorage", "&4这个物品不可被储存!");

		s.lang.addDefault("Save.createFailstack", "&6你创造了&c巴尔克斯的忠告+%failstack%");
		s.lang.addDefault("Save.noFailstack", "&c你没有保留任何巴尔克斯的忠告.");
		s.lang.addDefault("Save.failstackTitle", "&e-- 拥有的巴尔克斯的忠告  %page% --");
		s.lang.addDefault("Save.listing", "&e%NUMBER% &f- &c%FAILSTACK%");

		s.lang.addDefault("Help.help", "&6查看插件命令帮助.");
		s.lang.addDefault("Help.menu", "&6突破物品潜力界面.");
		s.lang.addDefault("Help.reload", "&6重新载入插件配置文件.");
		s.lang.addDefault("Help.list", "&6查看存储的巴尔克斯的忠告.");
		s.lang.addDefault("Help.version", "&6检测当前文件版本.");
		s.lang.addDefault("Help.lore", "&6删除或添加道具绑定标签.");
		s.lang.addDefault("Help.inventory", "&6查看你已收集的道具.");
		s.lang.addDefault("Help.select", "&/enhance select {n} 来使用巴尔克斯的忠告.");
		s.lang.addDefault("Help.add", "&6G给予你所有黑石999个.");

		s.lang.addDefault("Menu.gui.title", "潜力突破界面");
		s.lang.addDefault("Menu.gui.enhance", "强化");
		s.lang.addDefault("Menu.gui.force", "强突");
		s.lang.addDefault("Menu.gui.stats", "信息");
		s.lang.addDefault("Menu.gui.remove", "取消");
		s.lang.addDefault("Menu.gui.store", "保存垫子");
		s.lang.addDefault("Menu.lore.store1", "使用铁匠的秘笈创造巴尔克斯的忠告");
		s.lang.addDefault("Menu.lore.store2", "以保留当前的垫子");
		s.lang.addDefault("Menu.lore.force1", "&c强制突破百分百成功突破物品的潜力");
		s.lang.addDefault("Menu.lore.force2", "&c需要%COUNT%个%ITEM%");
		s.lang.addDefault("Menu.lore.remove", "&6取消选择当前强化物品");
		s.lang.addDefault("Menu.lore.stats1", "&b潜力突破会强化你的装备");
		s.lang.addDefault("Menu.lore.stats2", "&b玩家突破失败的次数会增加下次潜力突破的成功机率");
		s.lang.addDefault("Menu.lore.ifFail", "&6潜力突破可能会&9失败");
		s.lang.addDefault("Menu.lore.ifSuccess", "&6潜力突破可能会成功");
		s.lang.addDefault("Menu.lore.ifDowngrade", "&6潜力突破失败会使物品&c降级");
		s.lang.addDefault("Menu.lore.ifDestroy", "&6潜力突破失败会使物品&4炸裂");

		s.lang.addDefault("Name.0", "+1 ");
		s.lang.addDefault("Name.1", "+2 ");
		s.lang.addDefault("Name.2", "+3 ");
		s.lang.addDefault("Name.3", "+4 ");
		s.lang.addDefault("Name.4", "+5 ");
		s.lang.addDefault("Name.5", "+6 ");
		s.lang.addDefault("Name.6", "+7 ");
		s.lang.addDefault("Name.7", "+8 ");
		s.lang.addDefault("Name.8", "+9 ");
		s.lang.addDefault("Name.9", "+10 ");
		s.lang.addDefault("Name.10", "+11 ");
		s.lang.addDefault("Name.11", "+12 ");
		s.lang.addDefault("Name.12", "+13 ");
		s.lang.addDefault("Name.13", "+14 ");
		s.lang.addDefault("Name.14", "+15 ");
		s.lang.addDefault("Name.15", "I 场");
		s.lang.addDefault("Name.16", "II 光");
		s.lang.addDefault("Name.17", "III 高");
		s.lang.addDefault("Name.18", "IV 有");
		s.lang.addDefault("Name.19", "V 同");

		s.lang.addDefault("Item.title", "&6你已收集以下道具:");
		s.lang.addDefault("Item.listing", "&e%ITEM% &f: &c%COUNT%");
		s.lang.addDefault("Item.0", "&6黑石 (武器)");
		s.lang.addDefault("Item.1", "&6黑石 (防具)");
		s.lang.addDefault("Item.2", "&6凝聚魔力的黑石 (武器)");
		s.lang.addDefault("Item.3", "&6凝聚魔力的黑石 (防具)");
		s.lang.addDefault("Item.get", "&aYou got a ");
		s.lang.addDefault("Item.noItem", "&cYou don't have enough &6%STONE%&c to perform an enhancement");
		s.lang.addDefault("Item.invalid", "&cYou cannot enhance this item.");
		s.lang.options().copyDefaults(true);
		s.saveLang();
	}

	public void addEnDefault(SettingsManager s) {
		s.lang.addDefault("Config.pluginTag", "&f[&6EnchantmentsEnhance&f] ");
		s.lang.addDefault("Config.checkingVersion", "&aYou are using EnchantmentsEnhance v%version%.");
		s.lang.addDefault("Config.onEnable", "EnchantmentsEnhance is enabled!");
		s.lang.addDefault("Config.onDisable", "EnchantmentsEnhance is disabled!");
		s.lang.addDefault("Config.consoleCommand", "Console cannot use this!");
		s.lang.addDefault("Config.reload", "&aEnchantmentsEnhance is reloaded!");
		s.lang.addDefault("Config.welcome",
				"&aWelcome, Adventurer %player%! Use &6/enhance help&a to view enhancing guides!");
		s.lang.addDefault("Config.invalidCommand", "&aInvalid commands! use &6/enhance help&a to get helps!");
		s.lang.addDefault("Config.noPerm", "&aYou don't have permissions!");

		s.lang.addDefault("Annoucer.success", "&6Enhance Success: ");
		s.lang.addDefault("Annoucer.failed", "&6Enhance Failed: ");
		s.lang.addDefault("Annoucer.got", " &6got ");
		s.lang.addDefault("Annoucer.lost", " &6failed ");

		s.lang.addDefault("Enhance.successRate", "&bSuccess rate is %chance%%.");
		s.lang.addDefault("Enhance.itemInvalid", "&cThis item cannot be enhanced!");
		s.lang.addDefault("Enhance.itemMax", "&6Maximum enhancement level reached.");
		s.lang.addDefault("Enhance.enhanceSuccess", "&6Enhancement was successful!");
		s.lang.addDefault("Enhance.forceEnhanceSuccess", "&6Forcing enhancement was successful!");
		s.lang.addDefault("Enhance.enhanceFailed", "&cEnhancement failed!");
		s.lang.addDefault("Enhance.downgraded", "&4Item has downgraded!");
		s.lang.addDefault("Enhance.currentFailstack", "&bFailstack: ");

		s.lang.addDefault("Lore.UntradeableLore", "&8[&6Keep-On-Death&8]&8[&4Character Bound&8]&f");
		s.lang.addDefault("Lore.TradeableLore", "&8[&6Keep-on-death&8]&8[&2Trade Available&8]&f");

		s.lang.addDefault("Messages.NoItemInHand", "&4No item in hand!");
		s.lang.addDefault("Messages.AlreadyUntradeable", "&4Already character bound!");
		s.lang.addDefault("Messages.AlreadyTradeable", "&4Already trade available!");
		s.lang.addDefault("Messages.AlreadyUnbound", "&4Already unbound!");
		s.lang.addDefault("Messages.MadeUntradeable", "&2It is now character bound!");
		s.lang.addDefault("Messages.MadeTradeable", "&2It is now trade available!");
		s.lang.addDefault("Messages.MadeUnbound", "&2It is now unbound!");
		s.lang.addDefault("Messages.NoDrop", "&4This item cannot be dropped!");
		s.lang.addDefault("Messages.NoStorage", "&4This item cannot be stored!");

		s.lang.addDefault("Save.createFailstack", "&6You created &cAdvice Of Valks+%failstack%");
		s.lang.addDefault("Save.noFailstack", "&cYou don't have any Advice of Valks!");
		s.lang.addDefault("Save.failstackTitle", "&e-- Saved Advice of Valks %page% --");
		s.lang.addDefault("Save.listing", "&e%NUMBER% &f- &c%FAILSTACK%");

		s.lang.addDefault("Help.help", "&6view help.");
		s.lang.addDefault("Help.menu", "&6open enhancement menu.");
		s.lang.addDefault("Help.reload", "&6reload plugin.");
		s.lang.addDefault("Help.list", "&6View saved Advice of Valks.");
		s.lang.addDefault("Help.select", "&/enhance select {n} to use an Advice of Valks ");
		s.lang.addDefault("Help.version", "&6check version.");
		s.lang.addDefault("Help.lore", "&6remove/add lore to an item.");
		s.lang.addDefault("Help.inventory", "&6see items that you have collected.");
		s.lang.addDefault("Help.add", "&6Give you 999 items of every enhancement stone.");

		s.lang.addDefault("Menu.gui.title", "Enhancement");
		s.lang.addDefault("Menu.gui.enhance", "Enhance");
		s.lang.addDefault("Menu.gui.force", "Force");
		s.lang.addDefault("Menu.gui.stats", "Info");
		s.lang.addDefault("Menu.gui.remove", "Deselect-Item");
		s.lang.addDefault("Menu.gui.store", "Save Failstack");
		s.lang.addDefault("Menu.lore.store1", "Use Blacksmith’s Secret Book");
		s.lang.addDefault("Menu.lore.store2", "to store failstacks by creating Advice of Valks");
		s.lang.addDefault("Menu.lore.force1", "&cForce guarantees a successful enhancement");
		s.lang.addDefault("Menu.lore.force2", "&cNeeded %ITEM% x%COUNT%");
		s.lang.addDefault("Menu.lore.remove", "&6Remove current enhancing item");
		s.lang.addDefault("Menu.lore.stats1", "&bEnhancing is the act of increasing the stats of your items.");
		s.lang.addDefault("Menu.lore.stats2", "&bFailstacks increase the chance of a successful enhancement attempt.");
		s.lang.addDefault("Menu.lore.ifFail", "&6Enhancement could &9fail&6");
		s.lang.addDefault("Menu.lore.ifSuccess", "&6Enhancement could succeed");
		s.lang.addDefault("Menu.lore.ifDowngrade", "&6Item will be &cdowngraded&6 if failed");
		s.lang.addDefault("Menu.lore.ifDestroy", "&6Item will be &4destroyed&6 if failed");

		s.lang.addDefault("Name.0", "+1 ");
		s.lang.addDefault("Name.1", "+2 ");
		s.lang.addDefault("Name.2", "+3 ");
		s.lang.addDefault("Name.3", "+4 ");
		s.lang.addDefault("Name.4", "+5 ");
		s.lang.addDefault("Name.5", "+6 ");
		s.lang.addDefault("Name.6", "+7 ");
		s.lang.addDefault("Name.7", "+8 ");
		s.lang.addDefault("Name.8", "+9 ");
		s.lang.addDefault("Name.9", "+10 ");
		s.lang.addDefault("Name.10", "+11 ");
		s.lang.addDefault("Name.11", "+12 ");
		s.lang.addDefault("Name.12", "+13 ");
		s.lang.addDefault("Name.13", "+14 ");
		s.lang.addDefault("Name.14", "+15 ");
		s.lang.addDefault("Name.15", "I PRI");
		s.lang.addDefault("Name.16", "II DUO");
		s.lang.addDefault("Name.17", "III TRI");
		s.lang.addDefault("Name.18", "IV TET");
		s.lang.addDefault("Name.19", "V PEN");

		s.lang.addDefault("Item.title", "&6You Have Collected Those Items:");
		s.lang.addDefault("Item.listing", "&e%ITEM% &f: &c%COUNT%");
		s.lang.addDefault("Item.0", "&6Black Stone (Weapon)");
		s.lang.addDefault("Item.1", "&6Black Stone (Armor)");
		s.lang.addDefault("Item.2", "&6Concentrated Magical Black Stone (Weapon)");
		s.lang.addDefault("Item.3", "&6Concentrated Magical Black Stone (Armor)");
		s.lang.addDefault("Item.get", "&aYou got a ");
		s.lang.addDefault("Item.noItem", "&cYou don't have enough &6%STONE%&c to perform an enhancement");
		s.lang.addDefault("Item.invalid", "&cYou cannot enhance this item.");
		s.lang.options().copyDefaults(true);
		s.saveLang();

	}
}
