package org.pixeltime.enchantmentsenhance.manager;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredListener;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.event.enchantment.*;
import org.pixeltime.enchantmentsenhance.listener.ArmorHandler;
import org.pixeltime.enchantmentsenhance.listener.EnchantmentHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class EnchantmentsManager {
    public static final Material[] armour;
    public static final Material[] weapon;
    public static final Material[] sword;
    public static final Material[] axe;
    public static final Material[] helmet;
    public static final Material[] boot;
    public static final Material[] chestplate;
    public static final Material[] pick;
    public static final Material[] hoe;
    public static ArrayList<UUID> spartan;
    public static HashMap<ItemStack, Integer> rate;
    public static ArrayList<String> enchants;
    public static ArrayList<String> edited;
    public static Economy econ;
    public static HashMap<String, Integer> enchantments;
    public static ArrayList<String> speed;
    public static ArrayList<String> jump;
    public static ArrayList<String> bat;
    public static ArrayList<String> swimmer;
    public static ArrayList<String> health;
    public static ArrayList<String> molten;
    public static ArrayList<String> strength;
    public static ArrayList<String> haste;
    public static ArrayList<String> platemail;
    public static ArrayList<Material> armours;
    public static ArrayList<Material> weapons;
    public static ArrayList<Material> swords;
    public static ArrayList<Material> axes;
    public static ArrayList<Material> helmets;
    public static ArrayList<Material> boots;
    public static ArrayList<Material> chestplates;
    public static ArrayList<Material> picks;
    public static HashMap<String, String> type;
    public static ArrayList<String> vanilla;
    public static HashMap<ItemStack, String> rune;
    public static ArrayList<Material> hoes;
    public static ArrayList<UUID> uuid;

    static {
        EnchantmentsManager.spartan = new ArrayList<UUID>();
        EnchantmentsManager.rate = new HashMap<ItemStack, Integer>();
        EnchantmentsManager.enchants = new ArrayList<String>();
        EnchantmentsManager.edited = new ArrayList<String>();
        EnchantmentsManager.econ = null;
        EnchantmentsManager.enchantments = new HashMap<String, Integer>();
        EnchantmentsManager.speed = new ArrayList<String>();
        EnchantmentsManager.jump = new ArrayList<String>();
        EnchantmentsManager.bat = new ArrayList<String>();
        EnchantmentsManager.swimmer = new ArrayList<String>();
        EnchantmentsManager.health = new ArrayList<String>();
        EnchantmentsManager.molten = new ArrayList<String>();
        EnchantmentsManager.strength = new ArrayList<String>();
        EnchantmentsManager.haste = new ArrayList<String>();
        EnchantmentsManager.platemail = new ArrayList<String>();
        EnchantmentsManager.armours = new ArrayList<Material>();
        EnchantmentsManager.weapons = new ArrayList<Material>();
        EnchantmentsManager.swords = new ArrayList<Material>();
        EnchantmentsManager.axes = new ArrayList<Material>();
        EnchantmentsManager.helmets = new ArrayList<Material>();
        EnchantmentsManager.boots = new ArrayList<Material>();
        EnchantmentsManager.chestplates = new ArrayList<Material>();
        EnchantmentsManager.picks = new ArrayList<Material>();
        EnchantmentsManager.type = new HashMap<String, String>();
        EnchantmentsManager.vanilla = new ArrayList<String>();
        EnchantmentsManager.rune = new HashMap<ItemStack, String>();
        EnchantmentsManager.hoes = new ArrayList<Material>();
        EnchantmentsManager.uuid = new ArrayList<UUID>();
        armour = new Material[]{Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS};
        weapon = new Material[]{Material.DIAMOND_SWORD, Material.GOLD_SWORD, Material.STONE_SWORD, Material.WOOD_SWORD, Material.DIAMOND_AXE, Material.GOLD_AXE, Material.STONE_AXE, Material.WOOD_AXE, Material.IRON_SWORD, Material.IRON_AXE, Material.WOOD_AXE};
        sword = new Material[]{Material.DIAMOND_SWORD, Material.GOLD_SWORD, Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD};
        axe = new Material[]{Material.DIAMOND_AXE, Material.IRON_AXE, Material.WOOD_AXE, Material.STONE_AXE, Material.GOLD_AXE};
        helmet = new Material[]{Material.DIAMOND_HELMET, Material.GOLD_HELMET, Material.IRON_HELMET, Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET};
        boot = new Material[]{Material.DIAMOND_BOOTS, Material.IRON_BOOTS, Material.GOLD_BOOTS, Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS};
        chestplate = new Material[]{Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE};
        pick = new Material[]{Material.DIAMOND_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.STONE_PICKAXE, Material.WOOD_PICKAXE};
        hoe = new Material[]{Material.DIAMOND_HOE, Material.IRON_HOE, Material.GOLD_HOE, Material.STONE_HOE, Material.WOOD_HOE};
    }

    public void setUp() {

        for (int length = armour.length, i = 0; i < length; ++i) {
            EnchantmentsManager.armours.add(armour[i]);
        }
        for (int length2 = weapon.length, j = 0; j < length2; ++j) {
            EnchantmentsManager.weapons.add(weapon[j]);
        }
        for (int length3 = sword.length, k = 0; k < length3; ++k) {
            EnchantmentsManager.swords.add(sword[k]);
        }
        for (int length4 = axe.length, l = 0; l < length4; ++l) {
            EnchantmentsManager.axes.add(axe[l]);
        }
        for (int length5 = helmet.length, n = 0; n < length5; ++n) {
            EnchantmentsManager.helmets.add(helmet[n]);
        }
        for (int length6 = boot.length, n2 = 0; n2 < length6; ++n2) {
            EnchantmentsManager.boots.add(boot[n2]);
        }

        for (int length7 = chestplate.length, n3 = 0; n3 < length7; ++n3) {
            EnchantmentsManager.chestplates.add(chestplate[n3]);
        }

        for (int length8 = pick.length, n4 = 0; n4 < length8; ++n4) {
            EnchantmentsManager.picks.add(pick[n4]);
        }

        for (int length9 = hoe.length, n5 = 0; n5 < length9; ++n5) {
            EnchantmentsManager.hoes.add(hoe[n5]);
        }
        this.loadEnchants();
        this.applyEnchantments();
        this.register();
        this.register("reversal", "weapon", 3);
        this.register("reinforced", "chestplate", 5);
        this.register("wild_mark", "bow", 1);
        this.register("shearer", "shear", 1);
        this.register("farmer", "armour", 1);
        this.register("haste", "pickaxe", 3);
        this.register("explosive", "pickaxe", 5);
        this.register("lumberjack", "axe", 1);
        this.register("magical", "any", 1);
        this.register("plow", "hoe", 5);
        this.register("veinminer", "pickaxe", 3);
        this.register("lucky_mining", "pickaxe", 5);
        this.register("smelt", "pickaxe", 1);
        this.register("auto_block", "pickaxe", 1);
        this.register("shooter", "pickaxe", 1);
        this.register("feather_fall", "armour", 1);
        this.register("cursed_stone", "any", 1);
        this.register("magic_dust", "runes", 1);
        this.register("suicide", "chestplate", 1);
        this.register("launch", "sword", 5);
        this.register("rekt", "axe", 1);

        Main.getMain().getServer().getPluginManager().registerEvents(new EnchantmentHandler(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new ArmorHandler(null), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Assassin(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Batvision(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Blessed(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Demonic_Aura(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Dodge(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Hex(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Jump(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Kill_Confirm(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Lifesteal(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Speed(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Zeus(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Arrow_Rain(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Snare(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Execute(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Crushing(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Aegis(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Stealth(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Purge(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Divine(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Platemail(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Turmoil(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Spiked(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Flame_Cloak(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Entangle(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Corruption(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Battlecry(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Pyromaniac(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Holy_Smite(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Riftslayer(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Curse(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Eyepatch(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Mischief(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Plunder(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Shadowstep(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Tamer(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Wolves(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Repel(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Swimmer(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Medic(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Reborn(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Health_Boost(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Endless(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Molten(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Immolation(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Strength(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Horse_Rider(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Petrify(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Thief(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Demon_Siphon(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Web_Trap(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Paralyze(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Soft_Touch(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Tnt_Shooter(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Rekt(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Reinforced(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Wild_Mark(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Shearer(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Farmer(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Haste(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Lumberjack(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Plow(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Smelt(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Auto_Block(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Shooter(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Feather_Fall(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Suicide(), Main.getMain());
        Main.getMain().getServer().getPluginManager().registerEvents(new Launch(), Main.getMain());
        Main.getMain().saveDefaultConfig();
        for (final String s : SettingsManager.enchant.getStringList("disabled")) {
            EnchantmentsManager.type.remove(s);
            EnchantmentsManager.enchants.remove(s);
            EnchantmentsManager.enchantments.remove(s);
            for (final RegisteredListener registeredListener : HandlerList.getRegisteredListeners(Main.getMain())) {
                if (registeredListener.getListener().getClass().getSimpleName().equalsIgnoreCase(s)) {
                    HandlerList.unregisterAll(registeredListener.getListener());
                }
            }
        }
    }

    public void register() {
        EnchantmentsManager.type.put("aegis", "armour");
        EnchantmentsManager.type.put("tamer", "armour");
        EnchantmentsManager.type.put("arrow_rain", "bow");
        EnchantmentsManager.type.put("assassin", "sword");
        EnchantmentsManager.type.put("batvision", "helmet");
        EnchantmentsManager.type.put("battlecry", "axe");
        EnchantmentsManager.type.put("blessed", "armour");
        EnchantmentsManager.type.put("corruption", "axe");
        EnchantmentsManager.type.put("crushing", "weapon");
        EnchantmentsManager.type.put("curse", "weapon");
        EnchantmentsManager.type.put("demon_siphon", "sword");
        EnchantmentsManager.type.put("demonic_aura", "armour");
        EnchantmentsManager.type.put("disarm", "weapon");
        EnchantmentsManager.type.put("divine", "weapon");
        EnchantmentsManager.type.put("dodge", "armour");
        EnchantmentsManager.type.put("entangle", "bow");
        EnchantmentsManager.type.put("execute", "weapon");
        EnchantmentsManager.type.put("eyepatch", "helmet");
        EnchantmentsManager.type.put("flame_cloak", "armour");
        EnchantmentsManager.type.put("hex", "sword");
        EnchantmentsManager.type.put("holy_smite", "axe");
        EnchantmentsManager.type.put("jump", "boot");
        EnchantmentsManager.type.put("kill_confirm", "sword");
        EnchantmentsManager.type.put("lifesteal", "sword");
        EnchantmentsManager.type.put("mischief", "weapon");
        EnchantmentsManager.type.put("petrify", "axe");
        EnchantmentsManager.type.put("platemail", "armour");
        EnchantmentsManager.type.put("plunder", "weapon");
        EnchantmentsManager.type.put("purge", "weapon");
        EnchantmentsManager.type.put("pyromaniac", "armour");
        EnchantmentsManager.type.put("riftslayer", "sword");
        EnchantmentsManager.type.put("shadowstep", "armour");
        EnchantmentsManager.type.put("snare", "bow");
        EnchantmentsManager.type.put("speed", "boot");
        EnchantmentsManager.type.put("spiked", "armour");
        EnchantmentsManager.type.put("stealth", "armour");
        EnchantmentsManager.type.put("repel", "armour");
        EnchantmentsManager.type.put("turmoil", "axe");
        EnchantmentsManager.type.put("wolves", "armour");
        EnchantmentsManager.type.put("zeus", "bow");
        EnchantmentsManager.type.put("swimmer", "helmet");
        EnchantmentsManager.type.put("medic", "bow");
        EnchantmentsManager.type.put("thief", "weapon");
        EnchantmentsManager.type.put("health_boost", "chestplate");
        EnchantmentsManager.type.put("reborn", "weapon");
        EnchantmentsManager.type.put("endless", "any");
        EnchantmentsManager.type.put("molten", "chestplate");
        EnchantmentsManager.type.put("immolation", "chestplate");
        EnchantmentsManager.type.put("strength", "chestplate");
        EnchantmentsManager.type.put("horse_rider", "armour");
        EnchantmentsManager.type.put("protection", "any");
        EnchantmentsManager.type.put("web_trap", "weapon");
        EnchantmentsManager.type.put("paralyze", "armour");
        EnchantmentsManager.type.put("soft_touch", "pickaxe");
        EnchantmentsManager.type.put("tnt_shooter", "bow");
    }

    public void loadEnchants() {
        EnchantmentsManager.enchants.add("lifesteal");
        EnchantmentsManager.enchants.add("assassin");
        EnchantmentsManager.enchants.add("kill_confirm");
        EnchantmentsManager.enchants.add("hex");
        EnchantmentsManager.enchants.add("jump");
        EnchantmentsManager.enchants.add("speed");
        EnchantmentsManager.enchants.add("zeus");
        EnchantmentsManager.enchants.add("batvision");
        EnchantmentsManager.enchants.add("demonic_aura");
        EnchantmentsManager.enchants.add("dodge");
        EnchantmentsManager.enchants.add("blessed");
        EnchantmentsManager.enchants.add("arrow_rain");
        EnchantmentsManager.enchants.add("snare");
        EnchantmentsManager.enchants.add("curse");
        EnchantmentsManager.enchants.add("crushing");
        EnchantmentsManager.enchants.add("execute");
        EnchantmentsManager.enchants.add("stealth");
        EnchantmentsManager.enchants.add("aegis");
        EnchantmentsManager.enchants.add("platemail");
        EnchantmentsManager.enchants.add("purge");
        EnchantmentsManager.enchants.add("divine");
        EnchantmentsManager.enchants.add("entangle");
        EnchantmentsManager.enchants.add("pyromaniac");
        EnchantmentsManager.enchants.add("flame_cloak");
        EnchantmentsManager.enchants.add("battlecry");
        EnchantmentsManager.enchants.add("corruption");
        EnchantmentsManager.enchants.add("enrage");
        EnchantmentsManager.enchants.add("turmoil");
        EnchantmentsManager.enchants.add("spiked");
        EnchantmentsManager.enchants.add("riftslayer");
        EnchantmentsManager.enchants.add("petrify");
        EnchantmentsManager.enchants.add("eyepatch");
        EnchantmentsManager.enchants.add("plunder");
        EnchantmentsManager.enchants.add("mischief");
        EnchantmentsManager.enchants.add("protection");
        EnchantmentsManager.enchants.add("holy_smite");
        EnchantmentsManager.enchants.add("shadowstep");
        EnchantmentsManager.enchants.add("demon_siphon");
        EnchantmentsManager.enchants.add("tamer");
        EnchantmentsManager.enchants.add("wolves");
        EnchantmentsManager.enchants.add("repel");
        EnchantmentsManager.enchants.add("disarm");
        EnchantmentsManager.enchants.add("thief");
        EnchantmentsManager.enchants.add("medic");
        EnchantmentsManager.enchants.add("swimmer");
        EnchantmentsManager.enchants.add("health_boost");
        EnchantmentsManager.enchants.add("reborn");
        EnchantmentsManager.enchants.add("endless");
        EnchantmentsManager.enchants.add("molten");
        EnchantmentsManager.enchants.add("immolation");
        EnchantmentsManager.enchants.add("strength");
        EnchantmentsManager.enchants.add("horse_rider");
        EnchantmentsManager.enchants.add("web_trap");
        EnchantmentsManager.enchants.add("paralyze");
        EnchantmentsManager.enchants.add("soft_touch");
        EnchantmentsManager.enchants.add("tnt_shooter");
    }

    public void applyEnchantments() {
        EnchantmentsManager.enchantments.put("lifesteal", 3);
        EnchantmentsManager.enchantments.put("assassin", 3);
        EnchantmentsManager.enchantments.put("kill_confirm", 3);
        EnchantmentsManager.enchantments.put("hex", 3);
        EnchantmentsManager.enchantments.put("jump", 3);
        EnchantmentsManager.enchantments.put("speed", 3);
        EnchantmentsManager.enchantments.put("zeus", 3);
        EnchantmentsManager.enchantments.put("batvision", 1);
        EnchantmentsManager.enchantments.put("demonic_aura", 5);
        EnchantmentsManager.enchantments.put("dodge", 5);
        EnchantmentsManager.enchantments.put("blessed", 3);
        EnchantmentsManager.enchantments.put("arrow_rain", 5);
        EnchantmentsManager.enchantments.put("snare", 4);
        EnchantmentsManager.enchantments.put("curse", 4);
        EnchantmentsManager.enchantments.put("crushing", 5);
        EnchantmentsManager.enchantments.put("execute", 5);
        EnchantmentsManager.enchantments.put("stealth", 4);
        EnchantmentsManager.enchantments.put("aegis", 5);
        EnchantmentsManager.enchantments.put("platemail", 1);
        EnchantmentsManager.enchantments.put("purge", 4);
        EnchantmentsManager.enchantments.put("divine", 3);
        EnchantmentsManager.enchantments.put("entangle", 4);
        EnchantmentsManager.enchantments.put("pyromaniac", 5);
        EnchantmentsManager.enchantments.put("flame_cloak", 3);
        EnchantmentsManager.enchantments.put("battlecry", 3);
        EnchantmentsManager.enchantments.put("corruption", 5);
        EnchantmentsManager.enchantments.put("turmoil", 3);
        EnchantmentsManager.enchantments.put("spiked", 2);
        EnchantmentsManager.enchantments.put("holy_smite", 5);
        EnchantmentsManager.enchantments.put("riftslayer", 1);
        EnchantmentsManager.enchantments.put("petrify", 3);
        EnchantmentsManager.enchantments.put("eyepatch", 1);
        EnchantmentsManager.enchantments.put("plunder", 1);
        EnchantmentsManager.enchantments.put("mischief", 3);
        EnchantmentsManager.enchantments.put("protection", 1);
        EnchantmentsManager.enchantments.put("shadowstep", 3);
        EnchantmentsManager.enchantments.put("demon_siphon", 3);
        EnchantmentsManager.enchantments.put("tamer", 1);
        EnchantmentsManager.enchantments.put("wolves", 5);
        EnchantmentsManager.enchantments.put("repel", 3);
        EnchantmentsManager.enchantments.put("disarm", 3);
        EnchantmentsManager.enchantments.put("thief", 3);
        EnchantmentsManager.enchantments.put("swimmer", 3);
        EnchantmentsManager.enchantments.put("medic", 1);
        EnchantmentsManager.enchantments.put("health_boost", 3);
        EnchantmentsManager.enchantments.put("reborn", 3);
        EnchantmentsManager.enchantments.put("endless", 1);
        EnchantmentsManager.enchantments.put("molten", 1);
        EnchantmentsManager.enchantments.put("immolation", 5);
        EnchantmentsManager.enchantments.put("strength", 3);
        EnchantmentsManager.enchantments.put("horse_rider", 1);
        EnchantmentsManager.enchantments.put("web_trap", 3);
        EnchantmentsManager.enchantments.put("paralyze", 2);
        EnchantmentsManager.enchantments.put("soft_touch", 1);
        EnchantmentsManager.enchantments.put("tnt_shooter", 1);
    }

    public void register(final String s, final String s2, final int n) {
        EnchantmentsManager.type.put(s, s2);
        EnchantmentsManager.enchants.add(s);
        EnchantmentsManager.enchantments.put(s, n);
    }

}
