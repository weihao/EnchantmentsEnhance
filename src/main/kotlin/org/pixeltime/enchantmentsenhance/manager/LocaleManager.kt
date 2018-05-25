/*
 *     Copyright (C) 2017-Present HealPotion
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.manager

import org.pixeltime.enchantmentsenhance.enums.LangType

class LM {
    companion object {
        @JvmStatic
        val lang = LangType.valueOf(SettingsManager.config.getString("language")).id

        @JvmStatic
        fun addLang(path: String, locale: Array<String>) {
            SettingsManager.lang.addDefault(path, locale[0])
            if (lang != 0) {
                SettingsManager.lang.addDefault(path, locale[lang])
            }
        }

        @JvmStatic
        fun addLocale() {
            LM.addLang("Config.pluginTag", arrayOf("&7[&3Enchantments&cEnhance&7] ", "&f[&6强化插件&f] "))
            LM.addLang("Config.checkingVersion", arrayOf("&aYou are using EnchantmentsEnhance v%version%.", "&a您正在使用的插件版本是v%version%."))
            LM.addLang("Config.onEnable", arrayOf("EnchantmentsEnhance is enabled!", "强化插件已开启!"))
            LM.addLang("Config.onDisable", arrayOf("EnchantmentsEnhance is disabled!", "强化插件已禁用!"))
            LM.addLang("Config.onLoadingInventory", arrayOf("Loading player data...", "Loading player data..."))
            LM.addLang("Config.consoleCommand", arrayOf("Console cannot use this!", "服务器不可以使用这个指令哟!"))
            LM.addLang("Config.reload", arrayOf("&aEnchantmentsEnhance is reloaded!", "&a插件重载成功!"))
            LM.addLang("Config.welcome", arrayOf("&3Welcome, Adventurer &c%player%&3! Use &6/enhance help&3 to view enhancing guides!", "&a欢迎您,%player%勇士!使用&6/enhance help&a查看武器装备强化的指南!"))
            LM.addLang("Config.invalidCommand", arrayOf("&cInvalid command! use &6/enhance help&c to get helps!", "&a您输入的指令无效!使用&6/enhance help&a查看帮助!"))
            LM.addLang("Config.noPerm", arrayOf("&aYou don't have permissions!", "&c你没有权限这么做!"))
            LM.addLang("Config.playerNotFound", arrayOf("&cOnline player not found!", "&c无此在线玩家!"))
            LM.addLang("Config.invalidNumber", arrayOf("&cInvalid Number!", "&c无效数字"))
            LM.addLang("Annoucer.success", arrayOf("&6Enhance Success:", "&6强化成功: "))
            LM.addLang("Annoucer.failed", arrayOf("&6Enhance Failed:", "&6强化失败: "))
            LM.addLang("Annoucer.got", arrayOf("&6got", "&6获得了 "))
            LM.addLang("Annoucer.lost", arrayOf("&6failed", "&6潜力突破失败了 "))
            LM.addLang("Enhance.successRate", arrayOf("&bSuccess rate is %chance%%.", "&b物品的成功率为%chance%%."))
            LM.addLang("Enhance.itemInvalid", arrayOf("&cThis item cannot be enhanced!", "&c不可以强化!"))
            LM.addLang("Enhance.itemMax", arrayOf("&6Maximum enhancement level reached.", "&6物品已是最高级"))
            LM.addLang("Enhance.enhanceSuccess", arrayOf("&6Enhancement was successful!", "&6强化成功!"))
            LM.addLang("Enhance.forceEnhanceSuccess", arrayOf("&6Forcing enhancement was successful!", "&6强制突破成功!"))
            LM.addLang("Enhance.enhanceFailed", arrayOf("&cEnhancement failed!", "&c强化失败!"))
            LM.addLang("Enhance.downgraded", arrayOf("&4Item has downgraded!", "&4您的物品降级了!"))
            LM.addLang("Enhance.currentFailstack", arrayOf("&bFailstack: ", "&b您目前的垫子是: "))
            LM.addLang("Lore.untradeableLore", arrayOf("&8[&6Keep-On-Death&8]&8[&4Character Bound&8]&f", "&8[&6死亡不掉落&8]&8[&4不可交易&8]&f"))
            LM.addLang("Lore.tradeableLore", arrayOf("&8[&6Keep-on-death&8]&8[&2Trade Available&8]&f", "&8[&6死亡不掉落&8]&8[&2可交易&8]&f"))
            LM.addLang("Messages.noItemInHand", arrayOf("&4No item in hand!", "&4手中物品不符合强化标准!"))
            LM.addLang("Messages.alreadyUntradeable", arrayOf("&4Already character bound!", "&4已是不可交易物品!"))
            LM.addLang("Messages.alreadyTradeable", arrayOf("&4Already trade available!", "&4已是可交易物品!"))
            LM.addLang("Messages.alreadyUnbound", arrayOf("&4Already unbound!", "&4已是解绑物品!"))
            LM.addLang("Messages.madeUntradeable", arrayOf("&2It is now character bound!", "&2现是不可交易物品!"))
            LM.addLang("Messages.madeTradeable", arrayOf("&2It is now trade available!", "&现是可交易物品!"))
            LM.addLang("Messages.madeUnbound", arrayOf("&2It is now unbound!", "&2现是解绑物品!"))
            LM.addLang("Messages.noDrop", arrayOf("&4This item cannot be dropped!", "&4这个物品不可被丢弃!"))
            LM.addLang("Messages.noStorage", arrayOf("&4This item cannot be stored!", "&4这个物品不可被储存!"))
            LM.addLang("Save.createFailstack", arrayOf("&6You created &9Advice of Valks+%failstack%", "&6你创造了&c巴尔克斯的忠告+%failstack%"))
            LM.addLang("Save.noFailstack", arrayOf("&cYou don't have any &9Advice of Valks!", "&c你不存有任何巴尔克斯的忠告."))
            LM.addLang("Save.failstackTitle", arrayOf("&e-- Saved &9Advice of Valks %page% --", "&e-- 拥有的巴尔克斯的忠告  第%page%页 --"))
            LM.addLang("Save.listing", arrayOf("&e%NUMBER% &f- &c%FAILSTACK%", "顺序&e%NUMBER% &f- 等级&c%FAILSTACK%"))
            LM.addLang("Help.help", arrayOf("&6view help.", "&6查看插件命令帮助."))
            LM.addLang("Help.menu", arrayOf("&6open enhancement menu.", "&6突破物品潜力界面."))
            LM.addLang("Help.reload", arrayOf("&6reload plugin.", "&6重新载入插件配置文件."))
            LM.addLang("Help.list", arrayOf("&6view saved &9Advice of Valks.", "&6查看存储的巴尔克斯的忠告."))
            LM.addLang("Help.select", arrayOf("&6select an &9Advice of Valks to use.", "&6检测当前文件版本."))
            LM.addLang("Help.version", arrayOf("&6check version.", "&6删除或添加道具绑定标签."))
            LM.addLang("Help.lore", arrayOf("&6remove/add lore to an item.", "&6查看你已收集的道具."))
            LM.addLang("Help.inventory", arrayOf("&6see items that you have collected.", "&6使用巴尔克斯的忠告."))
            LM.addLang("Help.add", arrayOf("&6give a player an enhancing items.\n0 = weapon stone, 1 = armor stone, 2 = conc weapon, 3 = conc armor", "&6给予一个玩家指定黑石."))
            LM.addLang("Help.debug", arrayOf("&6collect debugging information for developer to fix issues.", "&6收集数据以帮助开发者解决问题."))
            LM.addLang("Help.reform", arrayOf("&6upgrade the grade of the gear.", "&6打开改良道具的面板."))
            LM.addLang("Menu.gui.title", arrayOf("Enhancement", "潜力突破界面"))
            LM.addLang("Menu.gui.enhance", arrayOf("Enhance", "强化"))
            LM.addLang("Menu.gui.force", arrayOf("Force", "强突"))
            LM.addLang("Menu.gui.stats", arrayOf("Info", "信息"))
            LM.addLang("Menu.gui.remove", arrayOf("Deselect-Item", "取消"))
            LM.addLang("Menu.gui.store", arrayOf("Save Failstack", "保存垫子"))
            LM.addLang("Menu.lore.store1", arrayOf("&fUse Blacksmith’s Secret Book", "使用铁匠的秘笈创造巴尔克斯的忠告"))
            LM.addLang("Menu.lore.store2", arrayOf("&fto store failstacks by creating &9Advice of Valks", "以保留当前的垫子"))
            LM.addLang("Menu.lore.force1", arrayOf("&cForce guarantees a successful enhancement", "&c强制突破百分百成功突破物品的潜力"))
            LM.addLang("Menu.lore.force2", arrayOf("&cNeeded %ITEM% x%COUNT%", "&c需要%COUNT%个%ITEM%"))
            LM.addLang("Menu.lore.remove", arrayOf("&6Remove current enhancing item", "&6取消选择当前强化物品"))
            LM.addLang("Menu.lore.stats1", arrayOf("&bEnhancing is the act of increasing the stats of your items.", "&b潜力突破会强化你的装备"))
            LM.addLang("Menu.lore.stats2", arrayOf("&bFailstacks increase the chance of a successful enhancement attempt.", "&b玩家突破失败的次数会增加下次潜力突破的成功机率"))
            LM.addLang("Menu.lore.ifFail", arrayOf("&6Enhancement could &9fail&6", "&6潜力突破可能会&9失败"))
            LM.addLang("Menu.lore.ifSuccess", arrayOf("&6Enhancement could succeed", "&6潜力突破可能会成功"))
            LM.addLang("Menu.lore.ifDowngrade", arrayOf("&6Item will be &cdowngraded&6 if failed", "&6潜力突破失败会使物品&c降级"))
            LM.addLang("Menu.lore.ifDestroy", arrayOf("&6Item will be &4destroyed&6 if failed", "&6潜力突破失败会使物品&4炸裂"))
            LM.addLang("Item.title", arrayOf("&6You Have Collected Those Items:", "&6你已收集以下道具:"))
            LM.addLang("Item.listing", arrayOf("&e%ITEM% &f: &c%COUNT%", "&e%ITEM% &f: &c%COUNT%"))
            LM.addLang("Item.0", arrayOf("&6Black Stone (&3Weapon&6)", "&6黑石 (武器)"))
            LM.addLang("Item.1", arrayOf("&6Black Stone (&7Armor&6)", "&6黑石 (防具)"))
            LM.addLang("Item.2", arrayOf("&6Concentrated Magical Black Stone (&3Weapon&6)", "&6凝聚魔力的黑石 (武器)"))
            LM.addLang("Item.3", arrayOf("&6Concentrated Magical Black Stone (&7Armor&6)", "&6凝聚魔力的黑石 (防具)"))
            LM.addLang("Item.get", arrayOf("&aYou got a ", "&a你获得了一个%ITEM%."))
            LM.addLang("Item.noItem", arrayOf("&cYou don't have enough &6%STONE%&c to perform an enhancement", "&c你没有足够的 &6%STONE%&c来进行本次强化."))
            LM.addLang("Item.invalid", arrayOf("&cYou cannot enhance this item.", "&c你不能强化改道具."))
            LM.addLang("Item.use", arrayOf("&aYou used a %ITEM%.", "&a你使用了一个%ITEM%."))
            LM.addLang("Valks.noAdvice", arrayOf("&cYou do not own any &9Advice of Valks&c.", "你不拥有巴尔克斯的忠告."))
            LM.addLang("Valks.hasFailstack", arrayOf("&cYou can't use &9Advice of Valks &cif you have failstacks.", "你目前的垫子不为零，为此你不能使用巴尔克斯的忠告."))
            LM.addLang("Valks.used", arrayOf("&aYou used an &9Advice of Valks &aand Level &d%LEVEL% &afailstacks is applied.", "你使用了巴尔克斯的忠告获得了等级为%LEVEL%的垫子."))
            LM.addLang("Example.command.add.guide", arrayOf("/enhance add <player> <stone> <number>", "enhance add <玩家> <附魔石类型> <数量>"))
            LM.addLang("Example.command.add.stone", arrayOf("0 = weapon stone, 1 = armor stone, 2 = conc weapon, 3 = conc armor", "0 = 黑石 (武器)"))
            LM.addLang("Add.successful", arrayOf("&aYou gave %player% %number% of %stone%.", "你给%player%了%number%个%stone%."))
            LM.addLang("Reform.gui.title", arrayOf("Item Reform", "道具改良"))
            LM.addLang("Reform.reform", arrayOf("Reform Item", "改良道具"))
            LM.addLang("enchantment.lifesteal", arrayOf("&7Lifesteal", "&7吸血"))
            LM.addLang("enchantment.assassin", arrayOf("&7Assassin", "&7暗杀"))
            LM.addLang("enchantment.hex", arrayOf("&7Hex", "&7巫术"))
            LM.addLang("enchantment.jump", arrayOf("&7Jump", "&7跳跃"))
            LM.addLang("enchantment.speed", arrayOf("&7Speed", "&7神速"))
            LM.addLang("enchantment.zeus", arrayOf("&7Zeus", "&7雷鸣"))
            LM.addLang("enchantment.batvision", arrayOf("&7Batvision", "&7天眼"))
            LM.addLang("enchantment.demonic_aura", arrayOf("&7Demonic Aura", "&7恶灵"))
            LM.addLang("enchantment.dodge", arrayOf("&7Dodge", "&7闪避"))
            LM.addLang("enchantment.blessed", arrayOf("&7Blessed", "&7祝福"))
            LM.addLang("enchantment.curse", arrayOf("&7Curse", "&7诅咒"))
            LM.addLang("enchantment.crushing", arrayOf("&7Crushing", "&7暴击"))
            LM.addLang("enchantment.execute", arrayOf("&7Execute", "&7背刺"))
            LM.addLang("enchantment.stealth", arrayOf("&7Stealth", "&7潜伏"))
            LM.addLang("enchantment.aegis", arrayOf("&7Aegis", "&7庇护"))
            LM.addLang("enchantment.platemail", arrayOf("&7Platemail", "&7坚韧"))
            LM.addLang("enchantment.purge", arrayOf("&7Purge", "&7净化"))
            LM.addLang("enchantment.divine", arrayOf("&7Divine", "&7神圣"))
            LM.addLang("enchantment.entangle", arrayOf("&7Entangle", "&7绞杀"))
            LM.addLang("enchantment.pyromaniac", arrayOf("&7Pyromaniac", "&7火焰"))
            LM.addLang("enchantment.flame_cloak", arrayOf("&7Flame Cloak", "&7燃烧"))
            LM.addLang("enchantment.battlecry", arrayOf("&7Battlecry", "&7战吼"))
            LM.addLang("enchantment.corruption", arrayOf("&7Corruption", "&7腐败"))
            LM.addLang("enchantment.turmoil", arrayOf("&7Turmoil", "&7风暴"))
            LM.addLang("enchantment.holy_smite", arrayOf("&7Holy Smite", "&7圣水"))
            LM.addLang("enchantment.riftslayer", arrayOf("&7Riftslayer", "&7溅射"))
            LM.addLang("enchantment.petrify", arrayOf("&7Petrify", "&7石化"))
            LM.addLang("enchantment.eyepatch", arrayOf("&7Eyepatch", "&7天眼"))
            LM.addLang("enchantment.plunder", arrayOf("&7Plunder", "&7掠夺"))
            LM.addLang("enchantment.mischief", arrayOf("&7Mischief", "&7戏耍"))
            LM.addLang("enchantment.shadowstep", arrayOf("&7Shadowstep", "&7暗影步"))
            LM.addLang("enchantment.demon_siphon", arrayOf("&7Demon Siphon", "&7虹吸"))
            LM.addLang("enchantment.tamer", arrayOf("&7Tamer", "&7驯兽"))
            LM.addLang("enchantment.wolves", arrayOf("&7Wolves", "&7野狼"))
            LM.addLang("enchantment.disarm", arrayOf("&7Disarm", "&7缴械"))
            LM.addLang("enchantment.repel", arrayOf("&7Repel", "&7超人"))
            LM.addLang("enchantment.spiked", arrayOf("&7Spiked", "&7尖刺"))
            LM.addLang("enchantment.medic", arrayOf("&7Medic", "&7军医"))
            LM.addLang("enchantment.swimmer", arrayOf("&7Swimmer", "&7亲水"))
            LM.addLang("enchantment.thief", arrayOf("&7Thief", "&7盗窃"))
            LM.addLang("enchantment.health_boost", arrayOf("&7Health Boost", "&7生命护盾"))
            LM.addLang("enchantment.reborn", arrayOf("&7Reborn", "&7重生"))
            LM.addLang("enchantment.endless", arrayOf("&7Endless", "&7无尽"))
            LM.addLang("enchantment.molten", arrayOf("&7Molten", "&7炉融"))
            LM.addLang("enchantment.immolation", arrayOf("&7Immolation", "&7自焚"))
            LM.addLang("enchantment.strength", arrayOf("&7Strength", "&7力量"))
            LM.addLang("enchantment.horse_rider", arrayOf("&7Horse Rider", "&7骑御"))
            LM.addLang("enchantment.web_trap", arrayOf("&7Web Trap", "&7蛛网"))
            LM.addLang("enchantment.paralyze", arrayOf("&7Paralyze", "&7麻痹"))
            LM.addLang("enchantment.tnt_shooter", arrayOf("&7Tnt Shooter", "&7炮手"))
            LM.addLang("enchantment.rekt", arrayOf("&7Rekt", "&7碾压"))
            LM.addLang("enchantment.reversal", arrayOf("&7Reversal", "&7倒转"))
            LM.addLang("enchantment.reinforced", arrayOf("&7Reinforced", "&7钢筋"))
            LM.addLang("enchantment.wild_mark", arrayOf("&7Wild Mark", "&7野性印记"))
            LM.addLang("enchantment.shearer", arrayOf("&7Shearer", "&7剪裁"))
            LM.addLang("enchantment.farmer", arrayOf("&7Farmer", "&7农场"))
            LM.addLang("enchantment.haste", arrayOf("&7Haste", "&7急迫"))
            LM.addLang("enchantment.explosive", arrayOf("&7Explosive", "&7爆炸"))
            LM.addLang("enchantment.lumberjack", arrayOf("&7Lumberjack", "&7伐木"))
            LM.addLang("enchantment.plow", arrayOf("&7Plow", "&7耕种"))
            LM.addLang("enchantment.smelt", arrayOf("&7Smelt", "&7冶炼"))
            LM.addLang("enchantment.auto_block", arrayOf("&7Auto Block", "&7自动"))
            LM.addLang("enchantment.shooter", arrayOf("&7Shooter", "&7射手"))
            LM.addLang("enchantment.feather_fall", arrayOf("&7Feather Fall", "&7羽落"))
            LM.addLang("enchantment.suicide", arrayOf("&7Suicide", "&帝陨"))
            LM.addLang("enchantment.launch", arrayOf("&7Launch", "&7弹射"))

            SettingsManager.lang.options().copyDefaults(true);
            SettingsManager.saveLang();
        }
    }
}