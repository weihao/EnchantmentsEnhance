/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

class LocaleManager {
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
            addLang("config.pluginTag", arrayOf("&7[&3Enchantments&cEnhance&7] ", "&f[&6强化插件&f] "))
            addLang("config.checkingVersion", arrayOf("&aYou are using EnchantmentsEnhance v%version%", "&a您正在使用的插件版本是v%version%"))
            addLang("config.onEnable", arrayOf("EnchantmentsEnhance is enabled!", "强化插件已开启!"))
            addLang("config.onDisable", arrayOf("EnchantmentsEnhance is disabled!", "强化插件已禁用!"))
            addLang("config.onLoadingInventory", arrayOf("Loading player data...", "正在加载玩家数据..."))
            addLang("config.consoleCommand", arrayOf("Console cannot use this!", "控制台不可以使用这个指令!"))
            addLang("config.reloading", arrayOf("&aReloading server..!", "&a服务器重载中..!"))
            addLang("config.reload", arrayOf("&aEnchantmentsEnhance is reloaded!", "&a插件重载成功!"))
            addLang("config.welcome", arrayOf("&3Welcome, Adventurer &c%player%&3! Use &6/enhance help&3 to view enhancing guides!", "&3欢迎您,&c%player%&3勇士!使用&6/enhance help&3查看武器装备强化的指南!"))
            addLang("config.invalidCommand", arrayOf("&cInvalid command! use &6/enhance help&c to get helps!", "&c您输入的指令无效!使用&6/enhance help&a查看帮助!"))
            addLang("config.noPerm", arrayOf("&aYou don't have permissions!", "&a你没有权限这么做!"))
            addLang("config.playerNotFound", arrayOf("&cOnline player not found!", "&c无此在线玩家!"))
            addLang("config.invalidNumber", arrayOf("&cInvalid Number!", "&c无效数字"))
            addLang("config.invalidItem", arrayOf("&cInvalid Item!", "&c无效物品"))
            addLang("config.invalidEnchant", arrayOf("&cInvalid Enchantment!", "&c无效附魔"))
            addLang("config.success", arrayOf("&aSuccessfully applied enchantment!", "&a成功添加附魔!"))
            addLang("annoucer.success", arrayOf("&6Enhance Success: %player% got %item%.", "&6强化成功: %player%获得了%item%."))
            addLang("annoucer.failed", arrayOf("&cEnhance Failed: %player% downgraded %item%.", "&c强化失败: %player%潜力突破失败降级了%item%."))
            addLang("annoucer.destroyed", arrayOf("&cEnhance Failed: %player% destroyed %item%.", "&c强化失败: %player%潜力突破失败摧毁了%item%."))
            addLang("enhance.successRate", arrayOf("&bSuccess rate is %chance%%.", "&b物品的成功率为%chance%%."))
            addLang("enhance.itemInvalid", arrayOf("&cThis item cannot be enhanced!", "&c不可以强化!"))
            addLang("enhance.itemMax", arrayOf("&6Maximum enhancement level reached.", "&6物品已是最高级"))
            addLang("enhance.enhanceSuccess", arrayOf("&6Enhancement was successful!", "&6强化成功!"))
            addLang("enhance.forceEnhanceSuccess", arrayOf("&6Forcing enhancement was successful!", "&6强制突破成功!"))
            addLang("enhance.enhanceFailed", arrayOf("&cEnhancement failed!", "&c强化失败!"))
            addLang("enhance.downgraded", arrayOf("&4Item has downgraded!", "&4您的物品降级了!"))
            addLang("enhance.destroyed", arrayOf("&4Item has destroyed!", "&4您的物品炸裂了!"))
            addLang("enhance.currentFailstack", arrayOf("&bFailstack: ", "&b您目前的垫子是: "))
            addLang("lore.untradeableLore", arrayOf("&7[&6Keep-On-Death&7]&7[&4Character Bound&7]&f", "&7[&6死亡不掉落&7]&7[&4不可交易&7]&f"))
            addLang("lore.tradeableLore", arrayOf("&8[&6Keep-on-death&8]&8[&2Trade Available&8]&f", "&8[&6死亡不掉落&8]&8[&2可交易&8]&f"))
            addLang("messages.noItemInHand", arrayOf("&4No item in hand!", "&4手中物品不符合强化标准!"))
            addLang("messages.alreadyuntradeable", arrayOf("&4Already character bound!", "&4已是不可交易物品!"))
            addLang("messages.alreadytradeable", arrayOf("&4Already trade available!", "&4已是可交易物品!"))
            addLang("messages.alreadyunbound", arrayOf("&4Already unbound!", "&4已是解绑物品!"))
            addLang("messages.madeuntradeable", arrayOf("&2It is now character bound!", "&2现是不可交易物品!"))
            addLang("messages.madetradeable", arrayOf("&2It is now trade available!", "&现是可交易物品!"))
            addLang("messages.madeunbound", arrayOf("&2It is now unbound!", "&2现是解绑物品!"))
            addLang("messages.noDrop", arrayOf("&4This item cannot be dropped!", "&4这个物品不可被丢弃!"))
            addLang("messages.noStorage", arrayOf("&4This item cannot be stored!", "&4这个物品不可被储存!"))
            addLang("save.createFailstack", arrayOf("&6You created &9Advice of Valks+%failstack%", "&6你创造了&9巴尔克斯的忠告+%failstack%"))
            addLang("save.noFailstack", arrayOf("&cYou don't have any &9Advice of Valks!", "&c你不存有任何&9巴尔克斯的忠告!"))
            addLang("save.failstackTitle", arrayOf("&e-- Saved &9Advice of Valks&e %page% --", "&e-- 拥有的&9巴尔克斯的忠告&e  第%page%页 --"))
            addLang("save.listing", arrayOf("&e%NUMBER% &f- &c%FAILSTACK%", "&e顺序&e%NUMBER% &f- 等级&c%FAILSTACK%"))
            addLang("help.help", arrayOf("&aview help.", "&a查看插件命令帮助."))
            addLang("help.menu", arrayOf("&aopen enhancement menu.", "&a突破物品潜力界面."))
            addLang("help.reload", arrayOf("&areload plugin.", "&a重新载入插件配置文件."))
            addLang("help.version", arrayOf("&acheck version.", "&a检查当前插件版本."))
            addLang("help.inventory", arrayOf("&asee items that you have collected.", "&a查看你已收集的道具."))
            addLang("help.add", arrayOf("&agive a player an enhancing items.\n0 = &6Black Stone (&3Weapon&6)&a, 1 = &6Black Stone (&7Armor&6)&a, 2 = &6Concentrated Magical Black Stone (&3Weapon&6)&a, 3 = &6Concentrated Magical Black Stone (&7Armor&6)", "&a给予一个玩家指定黑石.\n 0 = &6黑石 (&3武器&6)&a, 1 = &6黑石 (&7防具&6)&a, 2 = &6凝聚魔力的黑石 (&3武器&6)&a, 3 = &6凝聚魔力的黑石 (&7防具&6)"))
            addLang("help.debug", arrayOf("&acollect debugging information for developer to fix issues.", "&a收集数据以帮助开发者解决问题."))
            addLang("help.item", arrayOf("&aedit an item in hand.", "&a编辑手中物品."))
            addLang("help.enchantment", arrayOf("&aedit the enchantments of an item in hand.", "&a编辑手中物品的附魔."))
            addLang("help.set", arrayOf("&aSet personal item grinding multiplier.", "&a编辑物品研磨获得倍数."))
            addLang("menu.gui.title", arrayOf("&6Enhancement", "&6潜力突破界面"))
            addLang("menu.gui.enhance", arrayOf("&6Enhance", "&6强化"))
            addLang("menu.gui.force", arrayOf("&cForce", "&c强突"))
            addLang("menu.gui.stats", arrayOf("&bInfo", "&b信息"))
            addLang("menu.gui.remove", arrayOf("&7Deselect", "&7取消"))
            addLang("menu.gui.store", arrayOf("&fSave Failstack", "&f保存垫子"))
            addLang("menu.gui.back", arrayOf("&9Go Back", "&9返回"))
            addLang("menu.gui.next", arrayOf("&9Go Next", "&9前进"))
            addLang("menu.gui.reblath", arrayOf("&9Reblath Failstacking", "&9叠垫子"))
            addLang("menu.gui.grind", arrayOf("&9Grind", "&9研磨"))
            addLang("menu.gui.cancel", arrayOf("&4CANCEL", "&4取消"))
            addLang("menu.lore.store1", arrayOf("&fUse Blacksmith’s Secret Book", "&f使用铁匠的秘笈创造巴尔克斯的忠告"))
            addLang("menu.lore.store2", arrayOf("&fto store failstacks by creating &9Advice of Valks", "&f以保留当前的垫子"))
            addLang("menu.lore.force1", arrayOf("&cForce guarantees a successful enhancement", "&c强制突破百分百成功突破物品的潜力"))
            addLang("menu.lore.force2", arrayOf("&cNeeded %ITEM% x%COUNT%", "&c需要%COUNT%个%ITEM%"))
            addLang("menu.lore.remove", arrayOf("&7Remove current enhancing item", "&7取消选择当前强化物品"))
            addLang("menu.lore.stats1", arrayOf("&bEnhancing is the act of increasing the stats of your items.", "&b潜力突破会强化你的装备"))
            addLang("menu.lore.stats2", arrayOf("&bFailstacks increase the chance of a successful enhancement attempt.", "&b玩家突破失败的次数会增加下次潜力突破的成功机率"))
            addLang("menu.lore.ifFail", arrayOf("&6Enhancement could &9fail&6", "&6潜力突破可能会&9失败"))
            addLang("menu.lore.ifSuccess", arrayOf("&6Enhancement could succeed", "&6潜力突破可能会成功"))
            addLang("menu.lore.ifDowngrade", arrayOf("&6Item will be &cdowngraded&6 if failed", "&6潜力突破失败会使物品&c降级"))
            addLang("menu.lore.ifDestroy", arrayOf("&6Item will be &4destroyed&6 if failed", "&6潜力突破失败会使物品&4炸裂"))
            addLang("menu.lore.skip", arrayOf("&6Right click to &cskip &6animation.", "&6右击&c跳过&6强化动画"))
            addLang("menu.lore.back", arrayOf("&9Click to go back to previous page.", "&9点击返回上一页"))
            addLang("menu.lore.next", arrayOf("&9Click to go to next page.", "&9点击进入到下一页"))
            addLang("menu.lore.cancel", arrayOf("&4Cancel this enhancement.", "&4取消本次强化"))
            addLang("item.gui", arrayOf("&6Your collections of black stones", "&6你拥有的黑石"))
            addLang("item.gui1", arrayOf("&6Click me to open your backpack", "&6点击我打开你的背包"))
            addLang("item.title", arrayOf("&3You Have Collected:", "&3你已收集:"))
            addLang("item.listing", arrayOf("&e%ITEM% &f: &c%COUNT%", "&e%ITEM% &f: &c%COUNT%"))
            addLang("item.0", arrayOf("&6Black Stone (&3Weapon&6)", "&6黑石 (&3武器&6)"))
            addLang("item.1", arrayOf("&6Black Stone (&7Armor&6)", "&6黑石 (&7防具&6)"))
            addLang("item.2", arrayOf("&6Concentrated Magical Black Stone (&3Weapon&6)", "&6凝聚魔力的黑石 (&3武器&6)"))
            addLang("item.3", arrayOf("&6Concentrated Magical Black Stone (&7Armor&6)", "&6凝聚魔力的黑石 (&7防具&6)"))
            addLang("item.4", arrayOf("&6Cron Stone", "&6科伦石"))
            addLang("item.valks", arrayOf("&9Advice of Valks", "&9巴尔克斯的忠告"))
            addLang("item.get", arrayOf("&aYou got a %ITEM%", "&a你获得了一个%ITEM%."))
            addLang("item.noItem", arrayOf("&cYou don't have enough &6%STONE%&c to perform an enhancement", "&c你没有足够的 &6%STONE%&c来进行本次强化."))
            addLang("item.invalid", arrayOf("&cYou cannot enhance this item.", "&c你不能强化改道具."))
            addLang("item.use", arrayOf("&aYou used a %ITEM%.", "&a你使用了一个%ITEM%."))
            addLang("valks.gui", arrayOf("&9Owned Advice of Valks", "&9拥有的巴尔克斯的忠告"))
            addLang("valks.noAdvice", arrayOf("&cYou do not own any &9Advice of Valks&c.", "&c你不拥有&9巴尔克斯的忠告&c."))
            addLang("valks.hasFailstack", arrayOf("&cYou can't use &9Advice of Valks &cif you have failstacks.", "&c你目前的垫子不为零，为此你不能使用巴尔克斯的忠告."))
            addLang("valks.used", arrayOf("&aYou used an &9Advice of Valks &aand Level &d%LEVEL% &afailstacks is applied.", "&a你使用了巴尔克斯的忠告获得了等级为%LEVEL%的垫子."))
            addLang("example.command.add.guide", arrayOf("/enhance add <player> <stone> <number>", "/enhance add <玩家> <黑石类型> <数量>"))
            addLang("example.command.add.stone", arrayOf("0 = &6Black Stone (&3Weapon&6), 1 = &6Black Stone (&7Armor&6), 2 = &6Concentrated Magical Black Stone (&3Weapon&6), 3 = &6Concentrated Magical Black Stone (&7Armor&6)", "0 = &6黑石 (&3武器), 1 = &6黑石 (&7防具), 2 = &6凝聚魔力的黑石 (&3武器&6), 3 = &6凝聚魔力的黑石 (&7防具&6)"))
            addLang("add.successful", arrayOf("&aYou gave %player% %number% of %stone%.", "你给%player%了%number%个%stone%."))
            addLang("reform.gui.title", arrayOf("Item Reform", "道具改良"))
            addLang("reform.reform", arrayOf("Reform Item", "改良道具"))
            addLang("gui.missingItem", arrayOf("Please select an item to operate", "请选择一个你要操作的物品"))
            addLang("gui.noItem", arrayOf("You do not have sufficient item", "你没有足够的物品"))
            addLang("gui.addFailstack", arrayOf("You got %level% level of failstack. You now have %size% failstacks.", "你获得了%level%层垫子，你总共有%size%层垫子"))
            addLang("gui.resetFailstack", arrayOf("You lost your %level% failstacks.", "你失去了%level%层垫子"))
            addLang("icon.gear1", arrayOf("&9Gear enhancement mode.", "&9装备强化模式"))
            addLang("icon.gear2", arrayOf("&9Click your weapons and armors to begin enhancement.", "&9你可以点击你武器和防具进行强化"))
            addLang("icon.tool1", arrayOf("&9Tool enhancement mode.", "&9工具强化模式"))
            addLang("icon.tool2", arrayOf("&9Click your tools to begin enhancement.", "&9你可以点击你工具进行强化"))
            addLang("set.success", arrayOf("You have set your grind multiplier to x%leverage%.", "你已将研磨的倍数调整到%leverage%倍."))
            addLang("set.failed", arrayOf("Your grind multiplier needs to be x2 or greater.", "你的研磨倍数必须等于或者大于两倍."))
            addLang("grind.failed", arrayOf("You grind your stone and it shattered.", "你的研磨的石头粉碎了."))
            addLang("grind.success", arrayOf("You grind your stone and you got x%amount% stones that you grind.", "你研磨的石头成功分裂成x%amount%个."))
            addLang("grind.info1", arrayOf("&9Grind x1 stone has a chance of yielding x%amount% of same stones.", "&9研磨1x石头有机率获得x%amount%个."))
            addLang("grind.info2", arrayOf("&9Use &6/enhance set { multiplier } &9to set your multiplier", "&9使用&6/enhance set { multiplier} &9更改你的倍率."))
            addLang("reblath.info1", arrayOf("&9Use x1 stone to gain 1 failstack.", "&9使用一个黑石来交换一层垫子."))
            addLang("reblath.info2", arrayOf("&9But you have 17.5% chance to lose your current failstack.", "&9你有17.5%的机率失去你目前的垫子."))
            addLang("materialize.info1", arrayOf("&6Right Click to use this bundle.", "&9右击来使用这个包裹."))
            addLang("materialize.info2", arrayOf("&6You will get x%amount% %item%.", "&9你会获得x%amount%个%item%."))
            addLang("materialize.success", arrayOf("&6You received x%amount% %item% from bundle.", "&9你从包裹里获得了x%amount%个%item%."))
            addLang("materialize.advice1", arrayOf("&6You will get a level %level% Advice of Valks.", "&9你会获得%level%级的巴尔克斯的忠告."))
            addLang("materialize.adviceSucess", arrayOf("&6You received a level %level% Advice of Valks.", "&9你获得了%level%级的巴尔克斯的忠告."))
            addLang("materialize.inventoryFull", arrayOf("&cFailed: Your inventory is full.", "&c操作失败: 你的背包已满"))
            addLang("materialize.notEnoughItem", arrayOf("&cFailed: Not enough items.", "&c操作失败: 你无足够多的物品"))
            addLang("menu.leftInfo", arrayOf("&6Left click an item to select to act.", "&6左击物品来进行更多的操作"))
            addLang("menu.rightInfo", arrayOf("&6Right click an item to take it out from your virtual inventory.", "&6右击物品来从虚拟背包里取出物品"))
            addLang("menu.leftAdviceInfo", arrayOf("&6Left click an advice to use it.", "&6左击来使用道具"))
            addLang("update.outdated", arrayOf("&cEnchantmentsEnhance is OUTDATED!", "&c附魔强化插件需要更新!"))
            addLang("update.updateToDate", arrayOf("&aEnchantments Enhance is UP-TO-DATE!", "&a附魔强化插件已是最新版!"))
            addLang("vendor.gui", arrayOf("&8Night Vendor", "&8神秘商人"))
            addLang("anvil.disabled", arrayOf("&cAnvil is disabled.", "&c铁砧已被禁用"))
            addLang("anvil.repairDisabled", arrayOf("&cAnvil repair is disabled.", "&c铁砧修复已被禁用"))
            addLang("anvil.renameDisabled", arrayOf("&cAnvil rename is disabled.", "&c铁砧重命名已被禁用"))

            SettingsManager.lang.options().copyDefaults(true)
            SettingsManager.saveLang()
        }
    }
}