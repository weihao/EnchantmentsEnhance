package org.pixeltime.enchantmentsenhance.manager.language;

import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Language_en_us {
    /**
     * Adds English localization file to the plugin.
     */
    public static void addLang() {
        SettingsManager.lang.addDefault("Config.pluginTag", "&7[&3Enchantments&cEnhance&7] ");
        SettingsManager.lang.addDefault("Config.checkingVersion", "&aYou are using EnchantmentsEnhance v%version%.");
        SettingsManager.lang.addDefault("Config.onEnable", "EnchantmentsEnhance is enabled!");
        SettingsManager.lang.addDefault("Config.onDisable", "EnchantmentsEnhance is disabled!");
        SettingsManager.lang.addDefault("Config.onLoadingInventory", "Loading player data...");
        SettingsManager.lang.addDefault("Config.consoleCommand", "Console cannot use this!");
        SettingsManager.lang.addDefault("Config.reload", "&aEnchantmentsEnhance is reloaded!");
        SettingsManager.lang.addDefault("Config.welcome",
                "&3Welcome, Adventurer &c%player%&3! Use &6/enhance help&3 to view enhancing guides!");
        SettingsManager.lang.addDefault("Config.invalidCommand", "&cInvalid command! use &6/enhance help&c to get helps!");
        SettingsManager.lang.addDefault("Config.noPerm", "&aYou don't have permissions!");
        SettingsManager.lang.addDefault("Config.playerNotFound", "&cOnline player not found!");
        SettingsManager.lang.addDefault("Config.invalidNumber", "&cInvalid Number!");

        SettingsManager.lang.addDefault("Annoucer.success", "&6Enhance Success:");
        SettingsManager.lang.addDefault("Annoucer.failed", "&6Enhance Failed:");
        SettingsManager.lang.addDefault("Annoucer.got", "&6got");
        SettingsManager.lang.addDefault("Annoucer.lost", "&6failed");

        SettingsManager.lang.addDefault("Enhance.successRate", "&bSuccess rate is %chance%%.");
        SettingsManager.lang.addDefault("Enhance.itemInvalid", "&cThis item cannot be enhanced!");
        SettingsManager.lang.addDefault("Enhance.itemMax", "&6Maximum enhancement level reached.");
        SettingsManager.lang.addDefault("Enhance.enhanceSuccess", "&6Enhancement was successful!");
        SettingsManager.lang.addDefault("Enhance.forceEnhanceSuccess", "&6Forcing enhancement was successful!");
        SettingsManager.lang.addDefault("Enhance.enhanceFailed", "&cEnhancement failed!");
        SettingsManager.lang.addDefault("Enhance.downgraded", "&4Item has downgraded!");
        SettingsManager.lang.addDefault("Enhance.currentFailstack", "&bFailstack: ");

        SettingsManager.lang.addDefault("Lore.untradeableLore", "&8[&6Keep-On-Death&8]&8[&4Character Bound&8]&f");
        SettingsManager.lang.addDefault("Lore.tradeableLore", "&8[&6Keep-on-death&8]&8[&2Trade Available&8]&f");

        SettingsManager.lang.addDefault("Messages.noItemInHand", "&4No item in hand!");
        SettingsManager.lang.addDefault("Messages.alreadyUntradeable", "&4Already character bound!");
        SettingsManager.lang.addDefault("Messages.alreadyTradeable", "&4Already trade available!");
        SettingsManager.lang.addDefault("Messages.alreadyUnbound", "&4Already unbound!");
        SettingsManager.lang.addDefault("Messages.madeUntradeable", "&2It is now character bound!");
        SettingsManager.lang.addDefault("Messages.madeTradeable", "&2It is now trade available!");
        SettingsManager.lang.addDefault("Messages.madeUnbound", "&2It is now unbound!");
        SettingsManager.lang.addDefault("Messages.noDrop", "&4This item cannot be dropped!");
        SettingsManager.lang.addDefault("Messages.noStorage", "&4This item cannot be stored!");

        SettingsManager.lang.addDefault("Save.createFailstack", "&6You created &9Advice of Valks+%failstack%");
        SettingsManager.lang.addDefault("Save.noFailstack", "&cYou don't have any &9Advice of Valks!");
        SettingsManager.lang.addDefault("Save.failstackTitle", "&e-- Saved &9Advice of Valks %page% --");
        SettingsManager.lang.addDefault("Save.listing", "&e%NUMBER% &f- &c%FAILSTACK%");

        SettingsManager.lang.addDefault("Help.help", "&6view help.");
        SettingsManager.lang.addDefault("Help.menu", "&6open enhancement menu.");
        SettingsManager.lang.addDefault("Help.reload", "&6reload plugin.");
        SettingsManager.lang.addDefault("Help.list", "&6view saved &9Advice of Valks.");
        SettingsManager.lang.addDefault("Help.select", "&6select an &9Advice of Valks to use.");
        SettingsManager.lang.addDefault("Help.version", "&6check version.");
        SettingsManager.lang.addDefault("Help.lore", "&6remove/add lore to an item.");
        SettingsManager.lang.addDefault("Help.inventory", "&6see items that you have collected.");
        SettingsManager.lang.addDefault("Help.add", "&6give a player an enhancing items.\n0 = weapon stone, 1 = armor stone, 2 = conc weapon, 3 = conc armor");
        SettingsManager.lang.addDefault("Help.debug", "&6collect debugging information for developer to fix issues.");
        SettingsManager.lang.addDefault("Help.reform", "&6upgrade the grade of the gear.");

        SettingsManager.lang.addDefault("Menu.gui.title", "Enhancement");
        SettingsManager.lang.addDefault("Menu.gui.enhance", "Enhance");
        SettingsManager.lang.addDefault("Menu.gui.force", "Force");
        SettingsManager.lang.addDefault("Menu.gui.stats", "Info");
        SettingsManager.lang.addDefault("Menu.gui.remove", "Deselect-Item");
        SettingsManager.lang.addDefault("Menu.gui.store", "Save Failstack");
        SettingsManager.lang.addDefault("Menu.lore.store1", "&fUse Blacksmith’s Secret Book");
        SettingsManager.lang.addDefault("Menu.lore.store2", "&fto store failstacks by creating &9Advice of Valks");
        SettingsManager.lang.addDefault("Menu.lore.force1", "&cForce guarantees a successful enhancement");
        SettingsManager.lang.addDefault("Menu.lore.force2", "&cNeeded %ITEM% x%COUNT%");
        SettingsManager.lang.addDefault("Menu.lore.remove", "&6Remove current enhancing item");
        SettingsManager.lang.addDefault("Menu.lore.stats1", "&bEnhancing is the act of increasing the stats of your items.");
        SettingsManager.lang.addDefault("Menu.lore.stats2", "&bFailstacks increase the chance of a successful enhancement attempt.");
        SettingsManager.lang.addDefault("Menu.lore.ifFail", "&6Enhancement could &9fail&6");
        SettingsManager.lang.addDefault("Menu.lore.ifSuccess", "&6Enhancement could succeed");
        SettingsManager.lang.addDefault("Menu.lore.ifDowngrade", "&6Item will be &cdowngraded&6 if failed");
        SettingsManager.lang.addDefault("Menu.lore.ifDestroy", "&6Item will be &4destroyed&6 if failed");

        SettingsManager.lang.addDefault("Item.title", "&6You Have Collected Those Items:");
        SettingsManager.lang.addDefault("Item.listing", "&e%ITEM% &f: &c%COUNT%");
        SettingsManager.lang.addDefault("Item.0", "&6Black Stone (&3Weapon&6)");
        SettingsManager.lang.addDefault("Item.1", "&6Black Stone (&7Armor&6)");
        SettingsManager.lang.addDefault("Item.2", "&6Concentrated Magical Black Stone (&3Weapon&6)");
        SettingsManager.lang.addDefault("Item.3", "&6Concentrated Magical Black Stone (&7Armor&6)");
        SettingsManager.lang.addDefault("Item.get", "&aYou got a ");
        SettingsManager.lang.addDefault("Item.noItem", "&cYou don't have enough &6%STONE%&c to perform an enhancement");
        SettingsManager.lang.addDefault("Item.invalid", "&cYou cannot enhance this item.");
        SettingsManager.lang.addDefault("Item.use", "&aYou used a %ITEM%.");

        SettingsManager.lang.addDefault("Valks.noAdvice", "&cYou do not own any &9Advice of Valks&c.");
        SettingsManager.lang.addDefault("Valks.hasFailstack", "&cYou can't use &9Advice of Valks &cif you have failstacks.");
        SettingsManager.lang.addDefault("Valks.used", "&aYou used an &9Advice of Valks &aand Level &d%LEVEL% &afailstacks is applied.");


        SettingsManager.lang.addDefault("Example.command.add.guide", "/enhance add <player> <stone> <number>");
        SettingsManager.lang.addDefault("Example.command.add.stone",
                "0 = weapon stone, 1 = armor stone, 2 = conc weapon, 3 = conc armor");

        SettingsManager.lang.addDefault("Add.successful", "&aYou gave %player% %number% of %stone%.");
        SettingsManager.lang.addDefault("Reform.gui.title", "Item Reform");
        SettingsManager.lang.addDefault("Reform.reform", "Reform Item");

        SettingsManager.lang.addDefault("enchantment.lifesteal", "&7Lifesteal");
        SettingsManager.lang.addDefault("enchantment.block", "&7Block");
        SettingsManager.lang.addDefault("enchantment.assassin", "&7Assassin");
        SettingsManager.lang.addDefault("enchantment.kill_confirm", "&7Kill Confirm");
        SettingsManager.lang.addDefault("enchantment.hex", "&7Hex");
        SettingsManager.lang.addDefault("enchantment.jump", "&7Jump");
        SettingsManager.lang.addDefault("enchantment.speed", "&7Speed");
        SettingsManager.lang.addDefault("enchantment.zeus", "&7Zeus");
        SettingsManager.lang.addDefault("enchantment.enrage", "&7Enrage");
        SettingsManager.lang.addDefault("enchantment.batvision", "&7Batvision");
        SettingsManager.lang.addDefault("enchantment.demonic_aura", "&7Demonic Aura");
        SettingsManager.lang.addDefault("enchantment.dodge", "&7Dodge");
        SettingsManager.lang.addDefault("enchantment.blessed", "&7Blessed");
        SettingsManager.lang.addDefault("enchantment.arrow_rain", "&7Arrow Rain");
        SettingsManager.lang.addDefault("enchantment.snare", "&7Snare");
        SettingsManager.lang.addDefault("enchantment.curse", "&7Curse");
        SettingsManager.lang.addDefault("enchantment.crushing", "&7Crushing");
        SettingsManager.lang.addDefault("enchantment.execute", "&7Execute");
        SettingsManager.lang.addDefault("enchantment.stealth", "&7Stealth");
        SettingsManager.lang.addDefault("enchantment.aegis", "&7Aegis");
        SettingsManager.lang.addDefault("enchantment.platemail", "&7Platemail");
        SettingsManager.lang.addDefault("enchantment.purge", "&7Purge");
        SettingsManager.lang.addDefault("enchantment.divine", "&7Divine");
        SettingsManager.lang.addDefault("enchantment.entangle", "&7Entangle");
        SettingsManager.lang.addDefault("enchantment.pyromaniac", "&7Pyromaniac");
        SettingsManager.lang.addDefault("enchantment.flame_cloak", "&7Flame Cloak");
        SettingsManager.lang.addDefault("enchantment.battlecry", "&7Battlecry");
        SettingsManager.lang.addDefault("enchantment.corruption", "&7Corruption");
        SettingsManager.lang.addDefault("enchantment.turmoil", "&7Turmoil");
        SettingsManager.lang.addDefault("enchantment.holy_smite", "&7Holy Smite");
        SettingsManager.lang.addDefault("enchantment.riftslayer", "&7Riftslayer");
        SettingsManager.lang.addDefault("enchantment.petrify", "&7Petrify");
        SettingsManager.lang.addDefault("enchantment.eyepatch", "&7Eyepatch");
        SettingsManager.lang.addDefault("enchantment.plunder", "&7Plunder");
        SettingsManager.lang.addDefault("enchantment.mischief", "&7Mischief");
        SettingsManager.lang.addDefault("enchantment.shadowstep", "&7Shadowstep");
        SettingsManager.lang.addDefault("enchantment.demon_siphon", "&7Demon Siphon");
        SettingsManager.lang.addDefault("enchantment.animal_tamer", "&7Animal Tamer");
        SettingsManager.lang.addDefault("enchantment.wolves", "&7Wolves");
        SettingsManager.lang.addDefault("enchantment.disarm", "&7Disarm");
        SettingsManager.lang.addDefault("enchantment.superman_punch", "&7Superman Punch");
        SettingsManager.lang.addDefault("enchantment.spiked", "&7Spiked");
        SettingsManager.lang.addDefault("enchantment.medic", "&7Medic");
        SettingsManager.lang.addDefault("enchantment.swimmer", "&7Swimmer");
        SettingsManager.lang.addDefault("enchantment.thief", "&7Thief");
        SettingsManager.lang.addDefault("enchantment.health_boost", "&7Health Boost");
        SettingsManager.lang.addDefault("enchantment.reborn", "&7Reborn");
        SettingsManager.lang.addDefault("enchantment.endless", "&7Endless");
        SettingsManager.lang.addDefault("enchantment.molten", "&7Molten");
        SettingsManager.lang.addDefault("enchantment.immolation", "&7Immolation");
        SettingsManager.lang.addDefault("enchantment.strength", "&7Strength");
        SettingsManager.lang.addDefault("enchantment.horse_rider", "&7Horse Rider");
        SettingsManager.lang.addDefault("enchantment.web_trap", "&7Web Trap");
        SettingsManager.lang.addDefault("enchantment.paralyze", "&7Paralyze");
        SettingsManager.lang.addDefault("enchantment.soft_touch", "&7Soft Touch");
        SettingsManager.lang.addDefault("enchantment.tnt_shooter", "&7Tnt Shooter");
        SettingsManager.lang.addDefault("enchantment.rekt", "&7Rekt");
        SettingsManager.lang.addDefault("enchantment.reversal", "&7Reversal");
        SettingsManager.lang.addDefault("enchantment.reinforced", "&7Reinforced");
        SettingsManager.lang.addDefault("enchantment.wild_mark", "&7Wild Mark");
        SettingsManager.lang.addDefault("enchantment.shearer", "&7Shearer");
        SettingsManager.lang.addDefault("enchantment.farmer", "&7Farmer");
        SettingsManager.lang.addDefault("enchantment.haste", "&7Haste");
        SettingsManager.lang.addDefault("enchantment.explosive", "&7Explosive");
        SettingsManager.lang.addDefault("enchantment.lumberjack", "&7Lumberjack");
        SettingsManager.lang.addDefault("enchantment.plow", "&7Plow");
        SettingsManager.lang.addDefault("enchantment.smelt", "&7Smelt");
        SettingsManager.lang.addDefault("enchantment.auto_block", "&7Auto Block");
        SettingsManager.lang.addDefault("enchantment.shooter", "&7Shooter");
        SettingsManager.lang.addDefault("enchantment.feather_fall", "&7Feather Fall");
        SettingsManager.lang.addDefault("enchantment.suicide", "&7Suicide");
        SettingsManager.lang.addDefault("enchantment.angry_birds", "&7Angry Birds");

        SettingsManager.lang.options().copyDefaults(true);
        SettingsManager.saveLang();
    }
}
