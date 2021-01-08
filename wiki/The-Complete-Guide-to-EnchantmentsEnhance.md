## **░ Table of contents** <A NAME="table_contents">

### Getting started
* <A HREF="#getting">Getting started</A>
* <A HREF="#vanilla">Understanding Vanilla Enchantments</A>
* <A HREF="#version">Version Compatibility</A>
* <A HREF="#server">Server Compatibility</A>
* <A HREF="#plugins">Plugin Compatibility</A>

## **░ Getting started** <A NAME="getting">
Hi. This is the official reference of the EnchantmentsEnhance Plugin.

#### **Understanding Vanilla Enchantments** <A NAME="vanilla">
Vanilla enchantment sharpness is calculated as `y = 1.25 * x`
Enchantment level is represented by `x` and the final damage boost by the sharpness is `y`. This means the sharpness is uncapped, and damage increases as the enchantments level increases.

Vanilla enchantment protection is is calculated as `y = 287 / 1000 * x + 80` when `0 < x < 5` . If `x > 5`, then `y = 94.35`.  `y` is the damage reduction of the protection enchantment. This means the protection is capped, and protection Lv1000 is same as protection Lv5 because they have same effect.
***

#### **Version Compatibility** <A NAME="version">
-  [x] `1.8 - 1.15`

#### **Server Compatibility** <A NAME="server">
- [x] [Bukkit](https://bukkit.org)
- [x] [Spigot](https://spigotmc.org)
- [x] [PaperSpigot](https://ci.destroystokyo.com/view/All/job/PaperSpigot/)


## **░ Change Language**
    language: EN_US
Supported language: ZH_CN, EN_US, RU_RU.

## **░ Join Message**
    enableWelcomeMessage: true
Set false to disable on join message.
    
## **░ Enhancing Stacked Item**
    enableStackedItem: false
Set false to disallow stacked item to be enhanced.

## **░ Open Enhance Menu**
    enableTableEnchant: true
    openMethod: RIGHT_CLICK
- Set `enableTableEnchant` to false to only use commands to open enhancement menu.
- LEFT_CLICK - the player won't be able to destroy an enchantment table if you bind it to left click.
- RIGHT_CLICK - the player won't be able to open vanilla enchantment table if you bind it to right click.
- SHIFT_AND_LEFT_CLICK  - the player needs to crouch and left clicking at the same time.
- SHIFT_AND_RIGHT_CLICK - the player needs to crouch and right clicking at the same time.

## **░ Anvil Rename**
    enableAnvilFix: true
Fixes color codes when player edits an enhanced item on an anvil.
## **░ Firework damage**
    enablePreventFireworkDamage: true
Allow plugin to block firework damage.
## **░ Fancy Announcer**
    enableFancyAnnouncer: true
- 1.8 uses ActionBar. No dependency required.
- 1.9 and higher use BossBar. No dependency required.
- Set false to disable fancy announcer, and it will use chat.
## **░ Fancy Notify**
    enableFancyNotify: true
Set true to use Title Bar. No dependency required.
## **░ Economy**
    enableEconomy: false
- Set true to enable some features that are related to $.
- Requires Vault dependency.
## **░ Disable Enchantments**
    disabledEnchantments:
      - Wings
      - Thief
- Add to the list to disable some special enchantments that may cause an issue on your server.
## **░ Database**
      enabled: false
      host: 127.0.0.1
      port: 3306
      database: 'mydatabase'
      table: 'enchantmentsenhance'
      user: 'root'
      password: 'by_healpot_with_love'
- If disabled, the plugin will use local YAML files.
- `Host` is MySQL server address.
- `port` is MySQL server port (default 3306).
- `database` is the database name (NOTE! You need to create the database, then the plugin will create the tables).
- `table` is the table name (the plugin will auto create them).
- `user` is the MySQL user name.
- `password` is the MySQL user password.
## **░ Death Tags**
    enableLore: true
    lore:
      bound: tradeable
      sendBoundingMessage: true
- Set `enableLore` false to disable keep item on death.
- Set `sendBoundingMessage` to false to disable notification when item becomes bounded.
- Supported `bound` type: tradeable, untradeable, disabled.
  - tradeable: keep-item-on-death, personal trading is allowed.
  - untradeable: keep-item-on-death, cannot be stored in the chests, cannot be dropped.
  - disabled: disable automatically applying lores.

## **░ Item Rename**
    enableRename: true
    renamingIncludes:
      prefix: true
      suffix: true
 - Set `enableRename` false to disable rename mechanics.
 
## **░ Life Skill**    
    enableLifeskill: true
- Set `enableLifeskill` false to disable life skills.
- Life Skills consist of various tasks. Black stones will be rewarded by performing any of these tasks.
    * mining
    * chopping
    * fishing
    * killing
    * breeding
    * smelting
## **░ Material**
    
- [Material Reference](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)

## **░ Enchantments**
    
- [Vanilla Enchantments Reference](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/enchantments/Enchantment.html)
- [Custom Enchantments Reference](https://github.com/25/EnchantmentsEnhance/wiki/Enchantments-en)

## **░ Enhancement Configuration**
      0:
        baseChance: 100
        chanceIncreasePerFailstack: 0
        maximumFailstackApplied: -1
        failstackGainedPerFail: 1
        costToForceEnchant: -1
        downgradeIfFail: false
        destroyIfFail: false
        requireConcentratedStones: false
        broadcastEnhance: false
        fireworkIfSuccessful: true
        fireworkRounds: 1
        prefix: ""
        suffix: ""
        lore: []
        enchantments:
          WEAPON: []
          ARMOR: []
          PICKAXE: []
          AXE: []
          HOE: []
          BOW: []
- `baseChance` The base success rate of item at the level.
- `chanceIncreasePerFailstack` Chance increase per failstack you have.
- `maximumFailstackApplied` The maximum failstack applied to the enhancement.
- `failstackGainedPerFail` Failstack gained from failing an enhancement at this level.
- `costToForceEnchant` Cost of black stone to enhance the item.
- `downgradeIfFail` Downgrade the item if the enhance failed.
- `destroyIfFail` Destroy the item if the enhance failed.
- `requireConcentratedStones` Enable to require concentrated magical stone to enhance the item.
- `broadcastEnhance` Broadcast enhancement results.
- `fireworkIfSuccessful` Enable or disable firework.
- `fireworkRounds` Rounds of firework.
- `prefix` Prefix of the item will be applied upon a successful enhancement.
- `suffix` Suffix of the item will be applied upon a successful enhancement.
- `lore` Additional lore can be applied to the item upon a successful enhancement.
- `enchantments`
  - `^` Exclusion, does not have.
  - `!` Condition, a conditional check for an existing enchantment.
  - `?` Chance, a chance to apply an enchantment.
  - `:` Level, enchantment level.
  - `-` Range, a range of enchantment levels.
  - `enchantment:level` This is a basic format of enchantment.
    - `Protection:1` It means a level 1 protection enchantment will have 100% chance applied to the item upon a successful enhancement.
  - `condition!chance?enchantment:level-level` This is an advanced format.
    - `Durability!42.3?Damage_All:1-10` It reads from left to right: If the item has Durability enchantment, it will have 42.3% chance to apply a Damage_All enchantment between level 1 to 10.
    - `^Durability!Damage_All:1-2` If the item does not have Durability enchantment, it will have 100% chance to apply a Damage_All enchantment between level 1 to 2.