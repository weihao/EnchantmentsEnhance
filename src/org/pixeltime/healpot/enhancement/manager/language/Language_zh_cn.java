package org.pixeltime.healpot.enhancement.manager.language;

import org.pixeltime.healpot.enhancement.interfaces.Readable;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;

public class Language_zh_cn implements Readable{
    /**
     * Adds Chinese localization file to the plugin.
     */
    public static void addLang() {
        SettingsManager.lang.addDefault("Config.pluginTag", "&f[&6强化插件&f] ");
        SettingsManager.lang.addDefault("Config.checkingVersion", "&a您正在使用的插件版本是v%version%.");
        SettingsManager.lang.addDefault("Config.onEnable", "强化插件已开启!");
        SettingsManager.lang.addDefault("Config.onDisable", "强化插件已禁用!");
        SettingsManager.lang.addDefault("Config.consoleCommand", "服务器不可以使用这个指令哟!");
        SettingsManager.lang.addDefault("Config.reload", "&a插件重载成功!");
        SettingsManager.lang.addDefault("Config.welcome", "&a欢迎您, %player%勇士!使用&6/enhance help&a查看武器装备强化的指南!");
        SettingsManager.lang.addDefault("Config.invalidCommand", "&a您输入的指令无效!使用&6/enhance help&a查看帮助!");
        SettingsManager.lang.addDefault("Config.noPerm", "&c你没有权限这么做!");
        SettingsManager.lang.addDefault("Config.playerNotFound", "&c无此在线玩家!");
        SettingsManager.lang.addDefault("Config.invalidNumber", "&c无效数字");

        SettingsManager.lang.addDefault("Annoucer.success", "&6强化成功: ");
        SettingsManager.lang.addDefault("Annoucer.failed", "&6强化失败: ");
        SettingsManager.lang.addDefault("Annoucer.got", "&6获得了 ");
        SettingsManager.lang.addDefault("Annoucer.lost", "&6潜力突破失败了 ");

        SettingsManager.lang.addDefault("Enhance.successRate", "&b物品的成功率为%chance%%.");
        SettingsManager.lang.addDefault("Enhance.itemInvalid", "&c不可以强化!");
        SettingsManager.lang.addDefault("Enhance.itemMax", "&6物品已是最高级");
        SettingsManager.lang.addDefault("Enhance.enhanceSuccess", "&6强化成功!");
        SettingsManager.lang.addDefault("Enhance.forceEnhanceSuccess", "&6强制突破成功!");
        SettingsManager.lang.addDefault("Enhance.enhanceFailed", "&c强化失败!");
        SettingsManager.lang.addDefault("Enhance.downgraded", "&4您的物品降级了!");
        SettingsManager.lang.addDefault("Enhance.currentFailstack", "&b您目前的垫子是: ");

        SettingsManager.lang.addDefault("Lore.UntradeableLore", "&8[&6死亡不掉落&8]&8[&4不可交易&8]&f");
        SettingsManager.lang.addDefault("Lore.TradeableLore", "&8[&6死亡不掉落&8]&8[&2可交易&8]&f");

        SettingsManager.lang.addDefault("Messages.NoItemInHand", "&4手中物品不符合强化标准!");
        SettingsManager.lang.addDefault("Messages.AlreadyUntradeable", "&4已是不可交易物品!");
        SettingsManager.lang.addDefault("Messages.AlreadyTradeable", "&4已是可交易物品!");
        SettingsManager.lang.addDefault("Messages.AlreadyUnbound", "&4已是解绑物品!");
        SettingsManager.lang.addDefault("Messages.MadeUntradeable", "&2现是不可交易物品!");
        SettingsManager.lang.addDefault("Messages.MadeTradeable", "&现是可交易物品!");
        SettingsManager.lang.addDefault("Messages.MadeUnbound", "&2现是解绑物品!");
        SettingsManager.lang.addDefault("Messages.NoDrop", "&4这个物品不可被丢弃!");
        SettingsManager.lang.addDefault("Messages.NoStorage", "&4这个物品不可被储存!");

        SettingsManager.lang.addDefault("Save.createFailstack", "&6你创造了&c巴尔克斯的忠告+%failstack%");
        SettingsManager.lang.addDefault("Save.noFailstack", "&c你不存有任何巴尔克斯的忠告.");
        SettingsManager.lang.addDefault("Save.failstackTitle", "&e-- 拥有的巴尔克斯的忠告  第%page%页 --");
        SettingsManager.lang.addDefault("Save.listing", "顺序&e%NUMBER% &f- 等级&c%FAILSTACK%");

        SettingsManager.lang.addDefault("Help.help", "&6查看插件命令帮助.");
        SettingsManager.lang.addDefault("Help.menu", "&6突破物品潜力界面.");
        SettingsManager.lang.addDefault("Help.reload", "&6重新载入插件配置文件.");
        SettingsManager.lang.addDefault("Help.list", "&6查看存储的巴尔克斯的忠告.");
        SettingsManager.lang.addDefault("Help.version", "&6检测当前文件版本.");
        SettingsManager.lang.addDefault("Help.lore", "&6删除或添加道具绑定标签.");
        SettingsManager.lang.addDefault("Help.inventory", "&6查看你已收集的道具.");
        SettingsManager.lang.addDefault("Help.select", "&6/enhance select {n} 来使用巴尔克斯的忠告.");
        SettingsManager.lang.addDefault("Help.add", "&6给予一个玩家指定黑石.");

        SettingsManager.lang.addDefault("Menu.gui.title", "潜力突破界面");
        SettingsManager.lang.addDefault("Menu.gui.enhance", "强化");
        SettingsManager.lang.addDefault("Menu.gui.force", "强突");
        SettingsManager.lang.addDefault("Menu.gui.stats", "信息");
        SettingsManager.lang.addDefault("Menu.gui.remove", "取消");
        SettingsManager.lang.addDefault("Menu.gui.store", "保存垫子");
        SettingsManager.lang.addDefault("Menu.lore.store1", "使用铁匠的秘笈创造巴尔克斯的忠告");
        SettingsManager.lang.addDefault("Menu.lore.store2", "以保留当前的垫子");
        SettingsManager.lang.addDefault("Menu.lore.force1", "&c强制突破百分百成功突破物品的潜力");
        SettingsManager.lang.addDefault("Menu.lore.force2", "&c需要%COUNT%个%ITEM%");
        SettingsManager.lang.addDefault("Menu.lore.remove", "&6取消选择当前强化物品");
        SettingsManager.lang.addDefault("Menu.lore.stats1", "&b潜力突破会强化你的装备");
        SettingsManager.lang.addDefault("Menu.lore.stats2", "&b玩家突破失败的次数会增加下次潜力突破的成功机率");
        SettingsManager.lang.addDefault("Menu.lore.ifFail", "&6潜力突破可能会&9失败");
        SettingsManager.lang.addDefault("Menu.lore.ifSuccess", "&6潜力突破可能会成功");
        SettingsManager.lang.addDefault("Menu.lore.ifDowngrade", "&6潜力突破失败会使物品&c降级");
        SettingsManager.lang.addDefault("Menu.lore.ifDestroy", "&6潜力突破失败会使物品&4炸裂");

        SettingsManager.lang.addDefault("Name.0", "+1 ");
        SettingsManager.lang.addDefault("Name.1", "+2 ");
        SettingsManager.lang.addDefault("Name.2", "+3 ");
        SettingsManager.lang.addDefault("Name.3", "+4 ");
        SettingsManager.lang.addDefault("Name.4", "+5 ");
        SettingsManager.lang.addDefault("Name.5", "+6 ");
        SettingsManager.lang.addDefault("Name.6", "+7 ");
        SettingsManager.lang.addDefault("Name.7", "+8 ");
        SettingsManager.lang.addDefault("Name.8", "+9 ");
        SettingsManager.lang.addDefault("Name.9", "+10 ");
        SettingsManager.lang.addDefault("Name.10", "+11 ");
        SettingsManager.lang.addDefault("Name.11", "+12 ");
        SettingsManager.lang.addDefault("Name.12", "+13 ");
        SettingsManager.lang.addDefault("Name.13", "+14 ");
        SettingsManager.lang.addDefault("Name.14", "+15 ");
        SettingsManager.lang.addDefault("Name.15", "I 场");
        SettingsManager.lang.addDefault("Name.16", "II 光");
        SettingsManager.lang.addDefault("Name.17", "III 高");
        SettingsManager.lang.addDefault("Name.18", "IV 有");
        SettingsManager.lang.addDefault("Name.19", "V 同");

        SettingsManager.lang.addDefault("Item.title", "&6你已收集以下道具:");
        SettingsManager.lang.addDefault("Item.listing", "&e%ITEM% &f: &c%COUNT%");
        SettingsManager.lang.addDefault("Item.0", "&6黑石 (武器)");
        SettingsManager.lang.addDefault("Item.1", "&6黑石 (防具)");
        SettingsManager.lang.addDefault("Item.2", "&6凝聚魔力的黑石 (武器)");
        SettingsManager.lang.addDefault("Item.3", "&6凝聚魔力的黑石 (防具)");
        SettingsManager.lang.addDefault("Item.get", "&a你获得了一个%ITEM%.");
        SettingsManager.lang.addDefault("Item.noItem", "&c你没有足够的 &6%STONE%&c来进行本次强化.");
        SettingsManager.lang.addDefault("Item.invalid", "&c你不能强化改道具.");
        SettingsManager.lang.addDefault("Item.use", "&a你使用了一个%ITEM%.");

        SettingsManager.lang.addDefault("Valks.noAdvice", "你不拥有巴尔克斯的忠告.");
        SettingsManager.lang.addDefault("Valks.hasFailstack", "你目前的垫子不为零，为此你不能使用巴尔克斯的忠告.");
        SettingsManager.lang.addDefault("Valks.used", "你使用了巴尔克斯的忠告获得了等级为%LEVEL%的垫子.");
        
        SettingsManager.lang.addDefault("Example.command.add.guide", "enhance add <玩家> <附魔石类型> <数量>");
        SettingsManager.lang.addDefault("Example.command.add.stone", "0 = 黑石 (武器), 1 = 黑石 (防具), 2 = 凝聚魔力的黑石 (武器), 3 = 凝聚魔力的黑石 (防具)");
        SettingsManager.lang.options().copyDefaults(true);
        SettingsManager.saveLang();
    }
}
