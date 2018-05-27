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

package org.pixeltime.enchantmentsenhance.locale

import org.pixeltime.enchantmentsenhance.enums.LangType
import org.pixeltime.enchantmentsenhance.manager.MM.Companion.armor
import org.pixeltime.enchantmentsenhance.manager.MM.Companion.weapon
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

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
            addLang("Config.pluginTag", arrayOf("&7[&3Enchantments&cEnhance&7] ", "&f[&6强化插件&f] "))
            addLang("Config.checkingVersion", arrayOf("&aYou are using EnchantmentsEnhance v%version%.", "&a您正在使用的插件版本是v%version%."))
            addLang("Config.onEnable", arrayOf("EnchantmentsEnhance is enabled!", "强化插件已开启!"))
            addLang("Config.onDisable", arrayOf("EnchantmentsEnhance is disabled!", "强化插件已禁用!"))
            addLang("Config.onLoadingInventory", arrayOf("Loading player data...", "正在加载玩家数据..."))
            addLang("Config.consoleCommand", arrayOf("Console cannot use this!", "控制台不可以使用这个指令!"))
            addLang("Config.reload", arrayOf("&aEnchantmentsEnhance is reloaded!", "&a插件重载成功!"))
            addLang("Config.welcome", arrayOf("&3Welcome, Adventurer &c%player%&3! Use &6/enhance help&3 to view enhancing guides!", "&3欢迎您,&c%player%&3勇士!使用&6/enhance help&3查看武器装备强化的指南!"))
            addLang("Config.invalidCommand", arrayOf("&cInvalid command! use &6/enhance help&c to get helps!", "&c您输入的指令无效!使用&6/enhance help&a查看帮助!"))
            addLang("Config.noPerm", arrayOf("&aYou don't have permissions!", "&a你没有权限这么做!"))
            addLang("Config.playerNotFound", arrayOf("&cOnline player not found!", "&c无此在线玩家!"))
            addLang("Config.invalidNumber", arrayOf("&cInvalid Number!", "&c无效数字"))
            addLang("Annoucer.success", arrayOf("&6Enhance Success: %player% got %item%.", "&6强化成功: %player%获得了%item%."))
            addLang("Annoucer.failed", arrayOf("&6Enhance Failed: %player% lost %item%.", "&6强化失败: %player%潜力突破失败了%item%."))
            addLang("Enhance.successRate", arrayOf("&bSuccess rate is %chance%%.", "&b物品的成功率为%chance%%."))
            addLang("Enhance.itemInvalid", arrayOf("&cThis item cannot be enhanced!", "&c不可以强化!"))
            addLang("Enhance.itemMax", arrayOf("&6Maximum enhancement level reached.", "&6物品已是最高级"))
            addLang("Enhance.enhanceSuccess", arrayOf("&6Enhancement was successful!", "&6强化成功!"))
            addLang("Enhance.forceEnhanceSuccess", arrayOf("&6Forcing enhancement was successful!", "&6强制突破成功!"))
            addLang("Enhance.enhanceFailed", arrayOf("&cEnhancement failed!", "&c强化失败!"))
            addLang("Enhance.downgraded", arrayOf("&4Item has downgraded!", "&4您的物品降级了!"))
            addLang("Enhance.currentFailstack", arrayOf("&bFailstack: ", "&b您目前的垫子是: "))
            addLang("Lore.untradeableLore", arrayOf("&7[&6Keep-On-Death&8]&7[&4Character Bound&7]&f", "&7[&6死亡不掉落&7]&8[&4不可交易&7]&f"))
            addLang("Lore.tradeableLore", arrayOf("&8[&6Keep-on-death&8]&8[&2Trade Available&8]&f", "&8[&6死亡不掉落&8]&8[&2可交易&8]&f"))
            addLang("Messages.noItemInHand", arrayOf("&4No item in hand!", "&4手中物品不符合强化标准!"))
            addLang("Messages.alreadyUntradeable", arrayOf("&4Already character bound!", "&4已是不可交易物品!"))
            addLang("Messages.alreadyTradeable", arrayOf("&4Already trade available!", "&4已是可交易物品!"))
            addLang("Messages.alreadyUnbound", arrayOf("&4Already unbound!", "&4已是解绑物品!"))
            addLang("Messages.madeUntradeable", arrayOf("&2It is now character bound!", "&2现是不可交易物品!"))
            addLang("Messages.madeTradeable", arrayOf("&2It is now trade available!", "&现是可交易物品!"))
            addLang("Messages.madeUnbound", arrayOf("&2It is now unbound!", "&2现是解绑物品!"))
            addLang("Messages.noDrop", arrayOf("&4This item cannot be dropped!", "&4这个物品不可被丢弃!"))
            addLang("Messages.noStorage", arrayOf("&4This item cannot be stored!", "&4这个物品不可被储存!"))
            addLang("Save.createFailstack", arrayOf("&6You created &9Advice of Valks+%failstack%", "&6你创造了&9巴尔克斯的忠告+%failstack%"))
            addLang("Save.noFailstack", arrayOf("&cYou don't have any &9Advice of Valks!", "&c你不存有任何&9巴尔克斯的忠告!"))
            addLang("Save.failstackTitle", arrayOf("&e-- Saved &9Advice of Valks&e %page% --", "&e-- 拥有的&9巴尔克斯的忠告&e  第%page%页 --"))
            addLang("Save.listing", arrayOf("&e%NUMBER% &f- &c%FAILSTACK%", "&e顺序&e%NUMBER% &f- 等级&c%FAILSTACK%"))
            addLang("Help.help", arrayOf("&6view help.", "&6查看插件命令帮助."))
            addLang("Help.menu", arrayOf("&6open enhancement menu.", "&6突破物品潜力界面."))
            addLang("Help.reload", arrayOf("&6reload plugin.", "&6重新载入插件配置文件."))
            addLang("Help.list", arrayOf("&6view saved &9Advice of Valks.", "&6查看存储的&9巴尔克斯的忠告."))
            addLang("Help.select", arrayOf("&6select an &9Advice of Valks to use.", "&6选择使用一个&9巴尔克斯的忠告."))
            addLang("Help.version", arrayOf("&6check version.", "&6检查当前插件版本."))
            addLang("Help.lore", arrayOf("&6remove/add lore to an item.", "&6删除或添加道具绑定标签."))
            addLang("Help.inventory", arrayOf("&6see items that you have collected.", "&6查看你已收集的道具."))
            addLang("Help.add", arrayOf("&6give a player an enhancing items.\n0 = &6Black Stone (&3Weapon&6), 1 = &6Black Stone (&7Armor&6), 2 = &6Concentrated Magical Black Stone (&3Weapon&6), 3 = &6Concentrated Magical Black Stone (&7Armor&6)", "&6给予一个玩家指定黑石.\n 0 = &6黑石 (&3武器), 1 = &6黑石 (&7防具), 2 = &6凝聚魔力的黑石 (&3武器&6), 3 = &6凝聚魔力的黑石 (&7防具&6)"))
            addLang("Help.debug", arrayOf("&6collect debugging information for developer to fix issues.", "&6收集数据以帮助开发者解决问题."))
            addLang("Help.reform", arrayOf("&6upgrade the grade of the gear.", "&6打开改良道具的面板."))
            addLang("Help.item", arrayOf("&6edit an item in hand.", "&编辑手中物品."))
            addLang("Menu.gui.title", arrayOf("Enhancement", "潜力突破界面"))
            addLang("Menu.gui.enhance", arrayOf("Enhance", "强化"))
            addLang("Menu.gui.force", arrayOf("Force", "强突"))
            addLang("Menu.gui.stats", arrayOf("Info", "信息"))
            addLang("Menu.gui.remove", arrayOf("Deselect-Item", "取消"))
            addLang("Menu.gui.store", arrayOf("Save Failstack", "保存垫子"))
            addLang("Menu.lore.store1", arrayOf("&fUse Blacksmith’s Secret Book", "&f使用铁匠的秘笈创造巴尔克斯的忠告"))
            addLang("Menu.lore.store2", arrayOf("&fto store failstacks by creating &9Advice of Valks", "&f以保留当前的垫子"))
            addLang("Menu.lore.force1", arrayOf("&cForce guarantees a successful enhancement", "&c强制突破百分百成功突破物品的潜力"))
            addLang("Menu.lore.force2", arrayOf("&cNeeded %ITEM% x%COUNT%", "&c需要%COUNT%个%ITEM%"))
            addLang("Menu.lore.remove", arrayOf("&6Remove current enhancing item", "&6取消选择当前强化物品"))
            addLang("Menu.lore.stats1", arrayOf("&bEnhancing is the act of increasing the stats of your items.", "&b潜力突破会强化你的装备"))
            addLang("Menu.lore.stats2", arrayOf("&bFailstacks increase the chance of a successful enhancement attempt.", "&b玩家突破失败的次数会增加下次潜力突破的成功机率"))
            addLang("Menu.lore.ifFail", arrayOf("&6Enhancement could &9fail&6", "&6潜力突破可能会&9失败"))
            addLang("Menu.lore.ifSuccess", arrayOf("&6Enhancement could succeed", "&6潜力突破可能会成功"))
            addLang("Menu.lore.ifDowngrade", arrayOf("&6Item will be &cdowngraded&6 if failed", "&6潜力突破失败会使物品&c降级"))
            addLang("Menu.lore.ifDestroy", arrayOf("&6Item will be &4destroyed&6 if failed", "&6潜力突破失败会使物品&4炸裂"))
            addLang("Item.title", arrayOf("&6You Have Collected Those Items:", "&6你已收集以下道具:"))
            addLang("Item.listing", arrayOf("&e%ITEM% &f: &c%COUNT%", "&e%ITEM% &f: &c%COUNT%"))
            addLang("Item.0", arrayOf("&6Black Stone (&3Weapon&6)", "&6黑石 (&3武器)"))
            addLang("Item.1", arrayOf("&6Black Stone (&7Armor&6)", "&6黑石 (&7防具)"))
            addLang("Item.2", arrayOf("&6Concentrated Magical Black Stone (&3Weapon&6)", "&6凝聚魔力的黑石 (&3武器&6)"))
            addLang("Item.3", arrayOf("&6Concentrated Magical Black Stone (&7Armor&6)", "&6凝聚魔力的黑石 (&7防具&6)"))
            addLang("Item.get", arrayOf("&aYou got a ", "&a你获得了一个%ITEM%."))
            addLang("Item.noItem", arrayOf("&cYou don't have enough &6%STONE%&c to perform an enhancement", "&c你没有足够的 &6%STONE%&c来进行本次强化."))
            addLang("Item.invalid", arrayOf("&cYou cannot enhance this item.", "&c你不能强化改道具."))
            addLang("Item.use", arrayOf("&aYou used a %ITEM%.", "&a你使用了一个%ITEM%."))
            addLang("Valks.noAdvice", arrayOf("&cYou do not own any &9Advice of Valks&c.", "&c你不拥有巴尔克斯的忠告."))
            addLang("Valks.hasFailstack", arrayOf("&cYou can't use &9Advice of Valks &cif you have failstacks.", "&c你目前的垫子不为零，为此你不能使用巴尔克斯的忠告."))
            addLang("Valks.used", arrayOf("&aYou used an &9Advice of Valks &aand Level &d%LEVEL% &afailstacks is applied.", "&a你使用了巴尔克斯的忠告获得了等级为%LEVEL%的垫子."))
            addLang("Example.command.add.guide", arrayOf("/enhance add <player> <stone> <number>", "/enhance add <玩家> <黑石类型> <数量>"))
            addLang("Example.command.add.stone", arrayOf("0 = &6Black Stone (&3Weapon&6), 1 = &6Black Stone (&7Armor&6), 2 = &6Concentrated Magical Black Stone (&3Weapon&6), 3 = &6Concentrated Magical Black Stone (&7Armor&6)", "0 = &6黑石 (&3武器), 1 = &6黑石 (&7防具), 2 = &6凝聚魔力的黑石 (&3武器&6), 3 = &6凝聚魔力的黑石 (&7防具&6)"))
            addLang("Add.successful", arrayOf("&aYou gave %player% %number% of %stone%.", "你给%player%了%number%个%stone%."))
            addLang("Reform.gui.title", arrayOf("Item Reform", "道具改良"))
            addLang("Reform.reform", arrayOf("Reform Item", "改良道具"))
            addLang("enchantments.aegis", arrayOf("&7Aegis", "&7庇护"))
            addLang("enchantments.assassin", arrayOf("&7Assassin", "&7暗杀"))
            addLang("enchantments.auto_block", arrayOf("&7Auto Block", "&7自动"))
            addLang("enchantments.battlecry", arrayOf("&7Battlecry", "&7战吼"))
            addLang("enchantments.batvision", arrayOf("&7Batvision", "&7天眼"))
            addLang("enchantments.blessed", arrayOf("&7Blessed", "&7祝福"))
            addLang("enchantments.corruption", arrayOf("&7Corruption", "&7腐败"))
            addLang("enchantments.crushing", arrayOf("&7Crushing", "&7暴击"))
            addLang("enchantments.curse", arrayOf("&7Curse", "&7诅咒"))
            addLang("enchantments.demon_siphon", arrayOf("&7Demon Siphon", "&7虹吸"))
            addLang("enchantments.demonic_aura", arrayOf("&7Demonic Aura", "&7恶灵"))
            addLang("enchantments.disarm", arrayOf("&7Disarm", "&7缴械"))
            addLang("enchantments.divine", arrayOf("&7Divine", "&7神圣"))
            addLang("enchantments.dodge", arrayOf("&7Dodge", "&7闪避"))
            addLang("enchantments.endless", arrayOf("&7Endless", "&7无尽"))
            addLang("enchantments.entangle", arrayOf("&7Entangle", "&7绞杀"))
            addLang("enchantments.execute", arrayOf("&7Execute", "&7背刺"))
            addLang("enchantments.explosive", arrayOf("&7Explosive", "&7爆炸"))
            addLang("enchantments.eyepatch", arrayOf("&7Eyepatch", "&7天眼"))
            addLang("enchantments.farmer", arrayOf("&7Farmer", "&7农场"))
            addLang("enchantments.feather_fall", arrayOf("&7Feather Fall", "&7羽落"))
            addLang("enchantments.flame_cloak", arrayOf("&7Flame Cloak", "&7燃烧"))
            addLang("enchantments.haste", arrayOf("&7Haste", "&7急迫"))
            addLang("enchantments.health_boost", arrayOf("&7Health Boost", "&7生命护盾"))
            addLang("enchantments.hex", arrayOf("&7Hex", "&7巫术"))
            addLang("enchantments.holy_smite", arrayOf("&7Holy Smite", "&7圣水"))
            addLang("enchantments.horse_rider", arrayOf("&7Horse Rider", "&7骑御"))
            addLang("enchantments.immolation", arrayOf("&7Immolation", "&7自焚"))
            addLang("enchantments.jump", arrayOf("&7Jump", "&7跳跃"))
            addLang("enchantments.launch", arrayOf("&7Launch", "&7弹射"))
            addLang("enchantments.lifesteal", arrayOf("&7Lifesteal", "&7吸血"))
            addLang("enchantments.lumberjack", arrayOf("&7Lumberjack", "&7伐木"))
            addLang("enchantments.medic", arrayOf("&7Medic", "&7军医"))
            addLang("enchantments.mischief", arrayOf("&7Mischief", "&7戏耍"))
            addLang("enchantments.molten", arrayOf("&7Molten", "&7炉融"))
            addLang("enchantments.paralyze", arrayOf("&7Paralyze", "&7麻痹"))
            addLang("enchantments.petrify", arrayOf("&7Petrify", "&7石化"))
            addLang("enchantments.platemail", arrayOf("&7Platemail", "&7坚韧"))
            addLang("enchantments.plow", arrayOf("&7Plow", "&7耕种"))
            addLang("enchantments.plunder", arrayOf("&7Plunder", "&7掠夺"))
            addLang("enchantments.purge", arrayOf("&7Purge", "&7净化"))
            addLang("enchantments.pyromaniac", arrayOf("&7Pyromaniac", "&7火焰"))
            addLang("enchantments.reborn", arrayOf("&7Reborn", "&7重生"))
            addLang("enchantments.reinforced", arrayOf("&7Reinforced", "&7钢筋"))
            addLang("enchantments.rekt", arrayOf("&7Rekt", "&7碾压"))
            addLang("enchantments.repel", arrayOf("&7Repel", "&7驱散"))
            addLang("enchantments.reversal", arrayOf("&7Reversal", "&7倒转"))
            addLang("enchantments.riftslayer", arrayOf("&7Riftslayer", "&7溅射"))
            addLang("enchantments.shadowstep", arrayOf("&7Shadowstep", "&7暗影步"))
            addLang("enchantments.shearer", arrayOf("&7Shearer", "&7剪裁"))
            addLang("enchantments.smelt", arrayOf("&7Smelt", "&7冶炼"))
            addLang("enchantments.speed", arrayOf("&7Speed", "&7神速"))
            addLang("enchantments.spiked", arrayOf("&7Spiked", "&7尖刺"))
            addLang("enchantments.stealth", arrayOf("&7Stealth", "&7潜伏"))
            addLang("enchantments.strength", arrayOf("&7Strength", "&7力量"))
            addLang("enchantments.suicide", arrayOf("&7Suicide", "&帝陨"))
            addLang("enchantments.swimmer", arrayOf("&7Swimmer", "&7亲水"))
            addLang("enchantments.tamer", arrayOf("&7Tamer", "&7驯兽"))
            addLang("enchantments.thief", arrayOf("&7Thief", "&7盗窃"))
            addLang("enchantments.tnt_shooter", arrayOf("&7Tnt Shooter", "&7炮手"))
            addLang("enchantments.turmoil", arrayOf("&7Turmoil", "&7风暴"))
            addLang("enchantments.web_trap", arrayOf("&7Web Trap", "&7蛛网"))

            SettingsManager.lang.options().copyDefaults(true);
            SettingsManager.saveLang();
        }
    }
}