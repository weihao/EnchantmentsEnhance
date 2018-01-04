package com.github.healpot.plugin.enhancement.me.main;

public class Language {
	public void addCnDefault(SettingsManager s) {
		s.lang.addDefault("Config.pluginTag", "&f[&6强化插件&f]");
		s.lang.addDefault("Config.checkingVersion", "您正在使用的插件版本是v%version%.");
		s.lang.addDefault("Config.onEnable", "强化插件已开启!");
		s.lang.addDefault("Config.onDisable", "强化插件已禁用!");
		s.lang.addDefault("Config.consoleCommand", "服务器不可以使用这个指令哟!");
		s.lang.addDefault("Config.reload", "插件重载成功!");
		s.lang.addDefault("Config.welcome", "&a欢迎您, %player%勇士!使用&6/enhance help&a查看武器装备强化的指南!");
		s.lang.addDefault("Config.invalidCommand", "&a您输入的指令无效!使用&6/enhance help&a查看帮助");

		s.lang.addDefault("Enhance.successRate", "您手中的物品的成功率为%chance%%.");
		s.lang.addDefault("Enhance.itemInvalid", "您手里拿着的武器不可以强化!");
		s.lang.addDefault("Enhance.itemMax", "您手里拿着的道具已是最高级");
		s.lang.addDefault("Enhance.enhanceSuccess", "强化成功!");
		s.lang.addDefault("Enhance.forceEnhanceSuccess", "强制突破成功!");
		s.lang.addDefault("Enhance.enhanceFailed", "强化失败!");
		s.lang.addDefault("Enhance.downgraded", "您的道具降级了!");
		s.lang.addDefault("Enhance.confirm", "请输入/enhance confirm确认本次强化");
		s.lang.addDefault("Enhance.nothingToConfirm", "您没有什么要确认的!");
		s.lang.addDefault("Enhance.cancel", "您未输入确认指令，本次强化已取消!");
		s.lang.addDefault("Enhance.currentFailstack", "您目前的失败次数是: ");

		s.lang.addDefault("Lore.UntradeableLore", "&8[&6死亡不掉落&8]&8[&4不可交易&8]&f");
		s.lang.addDefault("Lore.TradeableLore", "&8[&6死亡不掉落&8]&8[&2可交易&8]&f");

		s.lang.addDefault("Messages.NoItemInHand", "&4手中物品不符合强化标准!");
		s.lang.addDefault("Messages.AlreadyUntradeable", "&4已是不可交易物品!");
		s.lang.addDefault("Messages.AlreadyTradeable", "&4已是可交易物品!");
		s.lang.addDefault("Messages.AlreadyUnbound", "&4已是解绑物品!");
		s.lang.addDefault("Messages.MadeUntradeable", "&2现是不可交易物品!");
		s.lang.addDefault("Messages.MadeTradeable", "&现是可交易物品!");
		s.lang.addDefault("Messages.MadeUnbound", "&2现是解绑物品!");
		s.lang.addDefault("Messages.NoDrop", "&4这个道具不可被丢弃!");
		s.lang.addDefault("Messages.NoStorage", "&4这个道具不可被储存!");

		s.lang.addDefault("Help.help", "查看插件命令帮助.");
		s.lang.addDefault("Help.menu", "突破物品潜力界面.");
		s.lang.addDefault("Help.reload", "重新载入插件配置文件.");
		s.lang.addDefault("Help.chance", "了解潜力突破机率.");
		s.lang.addDefault("Help.version", "检测当前文件版本.");

		s.lang.addDefault("Menu.title", "潜力突破界面");
		s.lang.addDefault("Menu.enhance", "强化");
		s.lang.addDefault("Menu.force", "强突");
		s.lang.addDefault("Menu.stats", "信息");
		s.lang.addDefault("Menu.remove", "取消");
		s.lang.addDefault("Menu.lore.force", "");
		s.lang.addDefault("Menu.lore.remove", "");
		s.lang.addDefault("Menu.lore.stats1", "潜力突破会强化你的装备");
		s.lang.addDefault("Menu.lore.stats2", "玩家突破失败的次数会增加下次潜力突破的成功机率");
		s.lang.addDefault("Menu.lore.ifFail", "潜力突破可能会失败");
		s.lang.addDefault("Menu.lore.ifSuccess", "潜力突破可能会成功");
		s.lang.addDefault("Menu.lore.ifDowngrade", "潜力突破失败会使物品降级");
		s.lang.addDefault("Menu.lore.ifDestroy", "潜力突破失败会使物品炸裂");

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
		s.lang.options().copyDefaults(true);
		s.saveLang();
	}
}
