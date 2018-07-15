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
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class LM {
    companion object {
        @JvmStatic
        val lang = LangType.valueOf(SettingsManager.config.getString("language").toUpperCase()).id

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
            addLang("Config.checkingVersion", arrayOf("&aYou are using EnchantmentsEnhance v%version%", "&a您正在使用的插件版本是v%version%"))
            addLang("Config.onEnable", arrayOf("EnchantmentsEnhance is enabled!", "强化插件已开启!"))
            addLang("Config.onDisable", arrayOf("EnchantmentsEnhance is disabled!", "强化插件已禁用!"))
            addLang("Config.onLoadingInventory", arrayOf("Loading player data...", "正在加载玩家数据..."))
            addLang("Config.consoleCommand", arrayOf("Console cannot use this!", "控制台不可以使用这个指令!"))
            addLang("Config.reloading", arrayOf("&aReloading server..!", "&a服务器重载中..!"))
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
            addLang("Enhance.destroyed", arrayOf("&4Item has destroyed!", "&4您的物品炸裂了!"))
            addLang("Enhance.currentFailstack", arrayOf("&bFailstack: ", "&b您目前的垫子是: "))
            addLang("Lore.untradeableLore", arrayOf("&7[&6Keep-On-Death&7]&7[&4Character Bound&7]&f", "&7[&6死亡不掉落&7]&7[&4不可交易&7]&f"))
            addLang("Lore.tradeableLore", arrayOf("&8[&6Keep-on-death&8]&8[&2Trade Available&8]&f", "&8[&6死亡不掉落&8]&8[&2可交易&8]&f"))
            addLang("Messages.noItemInHand", arrayOf("&4No item in hand!", "&4手中物品不符合强化标准!"))
            addLang("Messages.alreadyuntradeable", arrayOf("&4Already character bound!", "&4已是不可交易物品!"))
            addLang("Messages.alreadytradeable", arrayOf("&4Already trade available!", "&4已是可交易物品!"))
            addLang("Messages.alreadyunbound", arrayOf("&4Already unbound!", "&4已是解绑物品!"))
            addLang("Messages.madeuntradeable", arrayOf("&2It is now character bound!", "&2现是不可交易物品!"))
            addLang("Messages.madetradeable", arrayOf("&2It is now trade available!", "&现是可交易物品!"))
            addLang("Messages.madeunbound", arrayOf("&2It is now unbound!", "&2现是解绑物品!"))
            addLang("Messages.noDrop", arrayOf("&4This item cannot be dropped!", "&4这个物品不可被丢弃!"))
            addLang("Messages.noStorage", arrayOf("&4This item cannot be stored!", "&4这个物品不可被储存!"))
            addLang("Save.createFailstack", arrayOf("&6You created &9Advice of Valks+%failstack%", "&6你创造了&9巴尔克斯的忠告+%failstack%"))
            addLang("Save.noFailstack", arrayOf("&cYou don't have any &9Advice of Valks!", "&c你不存有任何&9巴尔克斯的忠告!"))
            addLang("Save.failstackTitle", arrayOf("&e-- Saved &9Advice of Valks&e %page% --", "&e-- 拥有的&9巴尔克斯的忠告&e  第%page%页 --"))
            addLang("Save.listing", arrayOf("&e%NUMBER% &f- &c%FAILSTACK%", "&e顺序&e%NUMBER% &f- 等级&c%FAILSTACK%"))
            addLang("Help.help", arrayOf("&aview help.", "&a查看插件命令帮助."))
            addLang("Help.menu", arrayOf("&aopen enhancement menu.", "&a突破物品潜力界面."))
            addLang("Help.reload", arrayOf("&areload plugin.", "&a重新载入插件配置文件."))
            addLang("Help.version", arrayOf("&acheck version.", "&a检查当前插件版本."))
            addLang("Help.inventory", arrayOf("&asee items that you have collected.", "&a查看你已收集的道具."))
            addLang("Help.add", arrayOf("&agive a player an enhancing items.\n0 = &6Black Stone (&3Weapon&6)&a, 1 = &6Black Stone (&7Armor&6)&a, 2 = &6Concentrated Magical Black Stone (&3Weapon&6)&a, 3 = &6Concentrated Magical Black Stone (&7Armor&6)", "&a给予一个玩家指定黑石.\n 0 = &6黑石 (&3武器&6)&a, 1 = &6黑石 (&7防具&6)&a, 2 = &6凝聚魔力的黑石 (&3武器&6)&a, 3 = &6凝聚魔力的黑石 (&7防具&6)"))
            addLang("Help.debug", arrayOf("&acollect debugging information for developer to fix issues.", "&a收集数据以帮助开发者解决问题."))
            addLang("Help.item", arrayOf("&aedit an item in hand.", "&a编辑手中物品."))
            addLang("Help.enchantment", arrayOf("&aedit the enchantments of an item in hand.", "&a编辑手中物品的附魔."))
            addLang("Menu.gui.title", arrayOf("&6Enhancement", "&6潜力突破界面"))
            addLang("Menu.gui.enhance", arrayOf("&6Enhance", "&6强化"))
            addLang("Menu.gui.force", arrayOf("&cForce", "&c强突"))
            addLang("Menu.gui.stats", arrayOf("&bInfo", "&b信息"))
            addLang("Menu.gui.remove", arrayOf("&7Deselect", "&7取消"))
            addLang("Menu.gui.store", arrayOf("&fSave Failstack", "&f保存垫子"))
            addLang("Menu.gui.back", arrayOf("&9Go Back", "&9返回"))
            addLang("Menu.gui.next", arrayOf("&9Go Next", "&9前进"))
            addLang("Menu.lore.store1", arrayOf("&fUse Blacksmith’s Secret Book", "&f使用铁匠的秘笈创造巴尔克斯的忠告"))
            addLang("Menu.lore.store2", arrayOf("&fto store failstacks by creating &9Advice of Valks", "&f以保留当前的垫子"))
            addLang("Menu.lore.force1", arrayOf("&cForce guarantees a successful enhancement", "&c强制突破百分百成功突破物品的潜力"))
            addLang("Menu.lore.force2", arrayOf("&cNeeded %ITEM% x%COUNT%", "&c需要%COUNT%个%ITEM%"))
            addLang("Menu.lore.remove", arrayOf("&7Remove current enhancing item", "&7取消选择当前强化物品"))
            addLang("Menu.lore.stats1", arrayOf("&bEnhancing is the act of increasing the stats of your items.", "&b潜力突破会强化你的装备"))
            addLang("Menu.lore.stats2", arrayOf("&bFailstacks increase the chance of a successful enhancement attempt.", "&b玩家突破失败的次数会增加下次潜力突破的成功机率"))
            addLang("Menu.lore.ifFail", arrayOf("&6Enhancement could &9fail&6", "&6潜力突破可能会&9失败"))
            addLang("Menu.lore.ifSuccess", arrayOf("&6Enhancement could succeed", "&6潜力突破可能会成功"))
            addLang("Menu.lore.ifDowngrade", arrayOf("&6Item will be &cdowngraded&6 if failed", "&6潜力突破失败会使物品&c降级"))
            addLang("Menu.lore.ifDestroy", arrayOf("&6Item will be &4destroyed&6 if failed", "&6潜力突破失败会使物品&4炸裂"))
            addLang("Menu.lore.back", arrayOf("&9Click to go back to previous page.", "&9点击返回上一页"))
            addLang("Menu.lore.next", arrayOf("&9Click to go to next page.", "&9点击进入到下一页"))
            addLang("Item.gui", arrayOf("&6Your collections of black stones", "&6你拥有的黑石"))
            addLang("Item.gui1", arrayOf("&6Click me to open your backpack", "&6点击我打开你的背包"))
            addLang("Item.title", arrayOf("&3You Have Collected:", "&3你已收集:"))
            addLang("Item.listing", arrayOf("&e%ITEM% &f: &c%COUNT%", "&e%ITEM% &f: &c%COUNT%"))
            addLang("Item.0", arrayOf("&6Black Stone (&3Weapon&6)", "&6黑石 (&3武器&6)"))
            addLang("Item.1", arrayOf("&6Black Stone (&7Armor&6)", "&6黑石 (&7防具&6)"))
            addLang("Item.2", arrayOf("&6Concentrated Magical Black Stone (&3Weapon&6)", "&6凝聚魔力的黑石 (&3武器&6)"))
            addLang("Item.3", arrayOf("&6Concentrated Magical Black Stone (&7Armor&6)", "&6凝聚魔力的黑石 (&7防具&6)"))
            addLang("Item.4", arrayOf("&6Cron Stone", "&6科伦石"))
            addLang("Item.valks", arrayOf("&9Advice of Valks", "&9巴尔克斯的忠告"))
            addLang("Item.get", arrayOf("&aYou got a %ITEM%", "&a你获得了一个%ITEM%."))
            addLang("Item.noItem", arrayOf("&cYou don't have enough &6%STONE%&c to perform an enhancement", "&c你没有足够的 &6%STONE%&c来进行本次强化."))
            addLang("Item.invalid", arrayOf("&cYou cannot enhance this item.", "&c你不能强化改道具."))
            addLang("Item.use", arrayOf("&aYou used a %ITEM%.", "&a你使用了一个%ITEM%."))
            addLang("Valks.gui", arrayOf("&9Owned Advice of Valks", "&9拥有的巴尔克斯的忠告"))
            addLang("Valks.noAdvice", arrayOf("&cYou do not own any &9Advice of Valks&c.", "&c你不拥有&9巴尔克斯的忠告&c."))
            addLang("Valks.hasFailstack", arrayOf("&cYou can't use &9Advice of Valks &cif you have failstacks.", "&c你目前的垫子不为零，为此你不能使用巴尔克斯的忠告."))
            addLang("Valks.used", arrayOf("&aYou used an &9Advice of Valks &aand Level &d%LEVEL% &afailstacks is applied.", "&a你使用了巴尔克斯的忠告获得了等级为%LEVEL%的垫子."))
            addLang("Example.command.add.guide", arrayOf("/enhance add <player> <stone> <number>", "/enhance add <玩家> <黑石类型> <数量>"))
            addLang("Example.command.add.stone", arrayOf("0 = &6Black Stone (&3Weapon&6), 1 = &6Black Stone (&7Armor&6), 2 = &6Concentrated Magical Black Stone (&3Weapon&6), 3 = &6Concentrated Magical Black Stone (&7Armor&6)", "0 = &6黑石 (&3武器), 1 = &6黑石 (&7防具), 2 = &6凝聚魔力的黑石 (&3武器&6), 3 = &6凝聚魔力的黑石 (&7防具&6)"))
            addLang("Add.successful", arrayOf("&aYou gave %player% %number% of %stone%.", "你给%player%了%number%个%stone%."))
            addLang("Reform.gui.title", arrayOf("Item Reform", "道具改良"))
            addLang("Reform.reform", arrayOf("Reform Item", "改良道具"))
            SettingsManager.lang.options().copyDefaults(true)
            SettingsManager.saveLang()
        }
    }
}