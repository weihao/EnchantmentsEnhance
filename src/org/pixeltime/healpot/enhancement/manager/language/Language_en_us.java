package org.pixeltime.healpot.enhancement.manager.language;

import org.pixeltime.healpot.enhancement.manager.SettingsManager;

public class Language_en_us {
    /**
     * Adds English localization file to the plugin.
     */
    public static void addLang() {
        SettingsManager.lang.addDefault("Config.pluginTag", "&f[&6EnchantmentsEnhance&f] ");
        SettingsManager.lang.addDefault("Config.checkingVersion", "&aYou are using EnchantmentsEnhance v%version%.");
        SettingsManager.lang.addDefault("Config.onEnable", "EnchantmentsEnhance is enabled!");
        SettingsManager.lang.addDefault("Config.onDisable", "EnchantmentsEnhance is disabled!");
        SettingsManager.lang.addDefault("Config.consoleCommand", "Console cannot use this!");
        SettingsManager.lang.addDefault("Config.reload", "&aEnchantmentsEnhance is reloaded!");
        SettingsManager.lang.addDefault("Config.welcome",
                "&aWelcome, Adventurer %player%! Use &6/enhance help&a to view enhancing guides!");
        SettingsManager.lang.addDefault("Config.invalidCommand", "&aInvalid commands! use &6/enhance help&a to get helps!");
        SettingsManager.lang.addDefault("Config.noPerm", "&aYou don't have permissions!");
        SettingsManager.lang.addDefault("Config.playerNotFound", "&cOnline player not found!");
        SettingsManager.lang.addDefault("Config.invalidNumber", "&cInvalid Number!");

        SettingsManager.lang.addDefault("Annoucer.success", "&6Enhance Success: ");
        SettingsManager.lang.addDefault("Annoucer.failed", "&6Enhance Failed: ");
        SettingsManager.lang.addDefault("Annoucer.got", " &6got ");
        SettingsManager.lang.addDefault("Annoucer.lost", " &6failed ");

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
        SettingsManager.lang.addDefault("Messages.moStorage", "&4This item cannot be stored!");

        SettingsManager.lang.addDefault("Save.createFailstack", "&6You created &cAdvice Of Valks+%failstack%");
        SettingsManager.lang.addDefault("Save.noFailstack", "&cYou don't have any Advice of Valks!");
        SettingsManager.lang.addDefault("Save.failstackTitle", "&e-- Saved Advice of Valks %page% --");
        SettingsManager.lang.addDefault("Save.listing", "&e%NUMBER% &f- &c%FAILSTACK%");

        SettingsManager.lang.addDefault("Help.help", "&6view help.");
        SettingsManager.lang.addDefault("Help.menu", "&6open enhancement menu.");
        SettingsManager.lang.addDefault("Help.reload", "&6reload plugin.");
        SettingsManager.lang.addDefault("Help.list", "&6View saved Advice of Valks.");
        SettingsManager.lang.addDefault("Help.select", "&6/enhance select {n} to use an Advice of Valks ");
        SettingsManager.lang.addDefault("Help.version", "&6check version.");
        SettingsManager.lang.addDefault("Help.lore", "&6remove/add lore to an item.");
        SettingsManager.lang.addDefault("Help.inventory", "&6see items that you have collected.");
        SettingsManager.lang.addDefault("Help.add", "&6Give a player an enhancing items.");

        SettingsManager.lang.addDefault("Menu.gui.title", "Enhancement");
        SettingsManager.lang.addDefault("Menu.gui.enhance", "Enhance");
        SettingsManager.lang.addDefault("Menu.gui.force", "Force");
        SettingsManager.lang.addDefault("Menu.gui.stats", "Info");
        SettingsManager.lang.addDefault("Menu.gui.remove", "Deselect-Item");
        SettingsManager.lang.addDefault("Menu.gui.store", "Save Failstack");
        SettingsManager.lang.addDefault("Menu.lore.store1", "Use Blacksmith’s Secret Book");
        SettingsManager.lang.addDefault("Menu.lore.store2", "to store failstacks by creating Advice of Valks");
        SettingsManager.lang.addDefault("Menu.lore.force1", "&cForce guarantees a successful enhancement");
        SettingsManager.lang.addDefault("Menu.lore.force2", "&cNeeded %ITEM% x%COUNT%");
        SettingsManager.lang.addDefault("Menu.lore.remove", "&6Remove current enhancing item");
        SettingsManager.lang.addDefault("Menu.lore.stats1", "&bEnhancing is the act of increasing the stats of your items.");
        SettingsManager.lang.addDefault("Menu.lore.stats2", "&bFailstacks increase the chance of a successful enhancement attempt.");
        SettingsManager.lang.addDefault("Menu.lore.ifFail", "&6Enhancement could &9fail&6");
        SettingsManager.lang.addDefault("Menu.lore.ifSuccess", "&6Enhancement could succeed");
        SettingsManager.lang.addDefault("Menu.lore.ifDowngrade", "&6Item will be &cdowngraded&6 if failed");
        SettingsManager.lang.addDefault("Menu.lore.ifDestroy", "&6Item will be &4destroyed&6 if failed");

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
        SettingsManager.lang.addDefault("Name.15", "I PRI");
        SettingsManager.lang.addDefault("Name.16", "II DUO");
        SettingsManager.lang.addDefault("Name.17", "III TRI");
        SettingsManager.lang.addDefault("Name.18", "IV TET");
        SettingsManager.lang.addDefault("Name.19", "V PEN");

        SettingsManager.lang.addDefault("Item.title", "&6You Have Collected Those Items:");
        SettingsManager.lang.addDefault("Item.listing", "&e%ITEM% &f: &c%COUNT%");
        SettingsManager.lang.addDefault("Item.0", "&6Black Stone (Weapon)");
        SettingsManager.lang.addDefault("Item.1", "&6Black Stone (Armor)");
        SettingsManager.lang.addDefault("Item.2", "&6Concentrated Magical Black Stone (Weapon)");
        SettingsManager.lang.addDefault("Item.3", "&6Concentrated Magical Black Stone (Armor)");
        SettingsManager.lang.addDefault("Item.get", "&aYou got a ");
        SettingsManager.lang.addDefault("Item.noItem", "&cYou don't have enough &6%STONE%&c to perform an enhancement");
        SettingsManager.lang.addDefault("Item.invalid", "&cYou cannot enhance this item.");
        SettingsManager.lang.addDefault("Item.use", "&aYou used a %ITEM%.");
        
        SettingsManager.lang.addDefault("Valks.noAdvice", "You do not own any Advice of Valks.");
        SettingsManager.lang.addDefault("Valks.hasFailstack", "You can't use Advice of Valks if you have failstacks.");
        SettingsManager.lang.addDefault("Valks.used", "You used an Advice of Valks and Level %LEVEL% failstacks is applied.");

        
        SettingsManager.lang.addDefault("Example.command.add.guide", "/enhance add <player> <stone> <number>");
        SettingsManager.lang.addDefault("Example.command.add.stone",
                "0 = weapon stone, 1 = armor stone, 2 = conc weapon, 3 = conc armor");
        
        SettingsManager.lang.addDefault("Add.successful", "You gave %player% %number% of %stone%.");
        SettingsManager.lang.options().copyDefaults(true);
        SettingsManager.saveLang();
    }
}
