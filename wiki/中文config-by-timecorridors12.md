```
############################################################
# +------------------------------------------------------+ #
# |                       Notes                          | #
# +------------------------------------------------------+ #
############################################################
# If you have encountered any problems, use: https://github.com/25/EnchantmentsEnhance/issues
# For config reference guide, use: https://github.com/25/EnchantmentsEnhance/blob/master/EnchantmentsEnhance-Core/src/resources/config.yml
guide: https://github.com/25/EnchantmentsEnhance/blob/master/EnchantmentsEnhance-Core/src/resources/config.yml
# Key Concepts:
# Enhancing is the act of increasing the stats of your items.
# Failstack is used to signify the number of failed attempts a player has made at enhancing. Failstacks increase the chance of a successful enhancement attempt.
# Advice of Valks is a special item that can give you failstack depending on the level of the advice.
# Blacksmith’s Secret Book allows you to store failstack by creating Advice of Valks.
# Lifeskilling consist of various tasks. Black stones will be rewarded by performing any of these tasks.
############################################################
# +------------------------------------------------------+ #
# |                  EnchantmentsEnhance                 | #
# +------------------------------------------------------+ #
############################################################
# Supported language: ZH_CN, EN_US.
language: ZH_CN
# 设置为false以禁用玩家加入欢迎消息.
enableWelcomeMessage: false
# 设置false以禁用更新检查器和更新通知.
enableUpdateChecker: true
# 设置为false以禁止堆叠物品进行增强.
enableStackedItem: false
# 这个插件默认玩家可以通过潜行打开超级附魔界面设置为false则只能通过menu指令打开界面.
enableTableEnchant: false
# LEFT_CLICK - 左击打开：如果你将其绑定到左键，玩家将无法销毁附魔台.
# RIGHT_CLICK - 右击打开：如果你将它绑定到右键，玩家将无法打开原版附魔台
# SHIFT_AND_LEFT_CLICK - shift键+左击：玩家需要同时蹲下并左键单击.
# SHIFT_AND_RIGHT_CLICK - shift键+右击：玩家需要同时蹲下并右键单击.
openMethod: RIGHT_CLICK
# 当玩家在铁砧上编辑增强过的道具时修复颜色代码
enableAnvilFix: true
# 允许插件禁止烟花造成的伤害
enablePreventFireworkDamage: true
#1.8使用ActionBar。不需要前置插件。
#1.9及以上使用BossBar。不需要前置插件。
#设置为false以禁用花式播音员，它将使用聊天栏
enableFancyAnnouncer: true
# 设置为true以使用标题栏。不需要依赖.
enableFancyNotify: true
# 设置为true以启用与金币相关的一些功能需要Vault前置.
enableEconomy: false
# 设置为假是不允许玩家从界面取出强化石实物.
enableItemMaterialization: true
# 设置false禁用铁砧改名???.
enableAnvil: true
# 设置为false以禁用使用铁砧重命名项.
enableAnvilRename: true
# 设置为false以禁用使用铁砧修复耐久???.
enableAnvilRepair: true
# 设置为false，在使用出现烟花时禁用烟花声音??.
enableExplosionSound: true
# 设置为false以禁用从reblath获取失败堆栈的玩家.
enableReblathFailstacking: true
# 设置为false，以禁用玩家从磨矿中获得黑石.
enableGrinding: true
# 将true设置为只允许强化改名物品被强化.
enableEERenamedItemOnly: false
# 添加到禁用一些特殊的附魔，这些附魔可能会导致服务器上的问题，也可能不会导致服务器上的问题.
disabledEnchantments:
  # 插件将控制玩家的飞行权限.
  - Wings
  # 插件将使用economic Plugin(何方插件???)
  - Thief
# SQL ready!以下无需翻译不懂的不懂，懂的不需要翻译
mysql:
  # If disabled, the plugin will use local YAML files.
  enabled: false
  # MySQL server address
  host: 127.0.0.1
  # MySQL server port (default 3306)
  port: 3306
  # Database name (NOTE! You need to create the database, then the plugin will create the tables.)
  database: "mydatabase"
  # Table name (the plugin will auto create them)
  table: "enchantmentsenhance"
  # User name
  user: "root"
  # User password
  password: "by_healpot_with_love"
# Set false to disable keep item on death这个是停用lore增加属性？.
enableLore: true
# Lore是添加到增强物品上的附加属性.
lore:
  # 支持绑定类型:可交易、不可交易、禁用.
  # tradeable: 死亡不掉可交易.
  # untradeable: 不可交易，无法放入箱子....enmmmm无法移动出背包.
  # disabled:禁用自动应用lores.
  bound: tradeable
  # 你想在物品被绑定时通知玩家吗?
  sendBoundingMessage: true
# 设置为false以禁用插件自身重命名机制.
enableRename: true
# 重命名的....不需要解释前缀后缀.
renamingIncludes:
  prefix: true
  suffix: true
# 设置false禁用生活技能.
enableLifeskill: true
# 生活技能包括各种各样的任务。黑石将被奖励执行任何这些任务
lifeskill:
  # 这是挖矿的识别工具？
  mining:
    - DIAMOND_ORE
    - EMERALD_ORE
    - GOLD_ORE
    - IRON_ORE
    - LAPIS_ORE
    - QUARTZ_ORE
    - REDSTONE_ORE
  # 这是砍树的识别工具？
  chopping:
    - ACACIA_LOG
    - BIRCH_LOG
    - DARK_OAK_LOG
    - JUNGLE_LOG
    - OAK_LOG
    - SPRUCE_LOG
# Stones ID
# 0 = 武器黑石
# 1 = 装备黑石
# 2 = 高级武器黑石
# 3 = 高级装备黑石
# 设置0.01的概念就是100次中只有一次的概率
reward:
  mining:#挖矿
    chance: 0.01
    drops:
      - 0
      - 1
  chopping:#砍树
    chance: 0.02
    drops:
      - 0
      - 1
  fishing:#钓鱼
    chance: 0.02
    drops:
      - 0
      - 1
  killing:#杀戮,应该包括PVP和PVE
    chance: 0.01
    drops:
      - 0
      - 1
  breeding:#养殖,包含农作物?
    chance: 0.01
    drops:
      - 0
      - 1
  smelting:#烧矿石
    chance: 0.01
    drops:
      - 0
      - 1
# When the enhancement is succeeded, it controls the firework delay launch.
fireworkDelay: 0
# How many fireworks will be launched.
fireworkCount: 1
# Material Reference: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html
material:
  # stoneTypes is the ID of the item in-game.
  stoneTypes:
    # 0 weapon stone
    - GHAST_TEAR
    # 1 armor stone
    - GOLD_NUGGET
    # 2 concentrated weapon stone
    - SUGAR
    # 3 concentrated armor stone
    - GLOWSTONE_DUST
    # 4 cron stone
    - SLIME_BALL
  # List of Armor.
  armor:
    - DIAMOND_HELMET
    - DIAMOND_CHESTPLATE
    - DIAMOND_LEGGINGS
    - DIAMOND_BOOTS
    - IRON_HELMET
    - IRON_CHESTPLATE
    - IRON_LEGGINGS
    - IRON_BOOTS
    - GOLD_HELMET
    - GOLD_CHESTPLATE
    - GOLD_LEGGINGS
    - GOLD_BOOTS
    - LEATHER_HELMET
    - LEATHER_CHESTPLATE
    - LEATHER_LEGGINGS
    - LEATHER_BOOTS
    - CHAINMAIL_HELMET
    - CHAINMAIL_CHESTPLATE
    - CHAINMAIL_LEGGINGS
    - CHAINMAIL_BOOTS
  # List of weapon.
  weapon:
    - DIAMOND_SWORD
    - GOLD_SWORD
    - STONE_SWORD
    - WOOD_SWORD
    - IRON_SWORD
    - DIAMOND_AXE
    - GOLD_AXE
    - STONE_AXE
    - WOOD_AXE
    - IRON_AXE
  # List of AXE.
  axe:
    - DIAMOND_AXE
    - IRON_AXE
    - WOODEN_AXE
    - STONE_AXE
    - GOLDEN_AXE
  pickaxe:
    - DIAMOND_PICKAXE
    - IRON_PICKAXE
    - GOLDEN_PICKAXE
    - STONE_PICKAXE
    - WOODEN_PICKAXE
  hoe:
    - DIAMOND_HOE
    - IRON_HOE
    - GOLDEN_HOE
    - STONE_HOE
    - WOODEN_HOE
  shovel:
    - DIAMOND_SHOVEL
    - IRON_SHOVEL
    - GOLDEN_SHOVEL
    - STONE_SHOVEL
    - WOODEN_SHOVEL
  knife:
    - DIAMOND_SWORD
    - GOLD_SWORD
    - STONE_SWORD
    - WOOD_SWORD
    - IRON_SWORD
  bow:
    - BOW
  rod:
    - FISHING_ROD
slots:
  # Armor includes armor contents and item in hand.
  enableArmor: true
  # [Working In Progress]
  enableAcessory: false
# Accessory slots.
# Player will place their items to those position in order to use their accessories.
# Player Inventory Slot Reference: [img]https://github.com/25/EnchantmentsEnhance/blob/master/.images/player_inventory.png[/img]
accessory:
  # Set accessory to -1 to disable an slot
  # left_ring:-1
  left_ring: 9
  right_ring: 10
  left_earring: 18
  right_earring: 19
  necklace: 27
  belt: 28
##################################################################
#使用入门
#你好。这是附魔强化插件的官方参考。
#了解原版附魔
#原版锋利附魔的伤害计算为“y = 1.25 * x” 附魔等级用“x”表示，锋利的最终伤害提升为“y”。
#这意味着锋利没有上限，并且随着附魔等级的增加，伤害也会增加。
#当“0<x<5”时，原版保护附魔计算为“y = 287/1000 * x + 80”。
#如果x>5，那么y = 94.35。 y是保护附魔的伤害减免。这意味着防御的伤害是有限的，
#且保护Lv1000与保护Lv5相同，因为它们具有相同的效果
##################################################################
# Vanilla Enchantments Reference: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/enchantments/Enchantment.html
# Custom Enchantments Reference: https://github.com/25/EnchantmentsEnhance/wiki/Enchantments-en
enhance:
  "0":
    # 该级别道具的基本成功率.
    baseChance: 100
    # 每次失败增加下一次的成功概率.
    chanceIncreasePerFailstack: 0
    # 应用于增强的最大垫子.
    maximumFailstackApplied: -1
    # 因此级别的增强功能失败而获得的垫子数量.
    failstackGainedPerFail: 1
    # 增强道具使用的黑石数量.
    costToForceEnchant: -1
    # 如果增强失败，有0%的几率降级物品.
    downgradeChanceIfFail: 0.0
    # 如果增强失败，有0%的几率摧毁物品.
    destroyChanceIfFail: 0.0
    # 设置需要凝聚的黑石石来增强物品.
    requireConcentratedStones: false
    # 聊天公告增强结果.
    broadcastEnhance: false
    # 启用或禁用烟花效果.
    fireworkIfSuccessful: true
    # 放几轮烟花
    fireworkRounds: 1
    # 道具的前缀将在成功增强后应用字符
    prefix: ""
    # 道具的后缀将在成功增强后应用字符
    suffix: ""
    # Applying enchantment.
    #enchantments
    #^排除，没有包含。
    #!条件，检测现有的附魔。
    #?机率，有机会添加附魔。
    #:等级，附魔等级。
    #-范围，附魔等级范围。
    #附魔:等级这是附魔的基本形式。
    #Protection:1这意味着成功增强后，1级保护附魔将有100％的机率应用于该物品。
    #条件!机率?附魔:等级下限-等级上限这是一种高级格式。
    #Durability!42.3?Damage_All:1-10从左到右：如果物品具有耐久附魔，它将有42.3％的机率在获得锋利附魔，1级到10级之间。
    #^Durability!Damage_All:1-2如果物品没有耐久附魔，它将有100％的机率在获得锋利附魔，1级到2级之间。
    enchantments:
      WEAPON: []
      ARMOR: []
      PICKAXE: []
      AXE: []
      HOE: []
      SHOEVEL: []
      BOW: []
      KNIFE: []
      ROD: []
  "1":
    baseChance: 100
    chanceIncreasePerFailstack: 0
    maximumFailstackApplied: -1
    failstackGainedPerFail: 1
    costToForceEnchant: -1
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: false
    fireworkRounds: 1
    prefix: ""
    suffix: "&7+1"
    enchantments:
      WEAPON:
        - Endless:1
        - Random:1
      ARMOR:
        - Endless:1
        - Random:1
      PICKAXE:
        - Endless:1
      AXE:
        - Endless:1
      HOE:
        - Endless:1
      SHOEVEL:
        - Endless:1
      BOW:
        - Endless:1
      KNIFE:
        - Endless:1
      Rod:
        - Endless:1
#附魔id网页:https://github.com/weihao/EnchantmentsEnhance/wiki/%E8%87%AA%E5%AE%9A%E4%B9%89%E9%99%84%E9%AD%94
#PAPI变量网页:https://github.com/weihao/EnchantmentsEnhance/wiki/PAPI%E5%8F%98%E9%87%8F
  "2":
    baseChance: 100
    chanceIncreasePerFailstack: 0
    maximumFailstackApplied: -1
    failstackGainedPerFail: 1
    costToForceEnchant: -1
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: false
    fireworkRounds: 1
    prefix: ""
    suffix: "&7+2"
    enchantments:
      WEAPON:
        - ^Damage_All!Stealth:1
        - Damage_All!Damage_All:1
      ARMOR:
        - Pyromaniac:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Fire:1
      KNIFE:
        - Fire_Aspect:1
      ROD:
        - Lure:1
  "3":
    baseChance: 100
    chanceIncreasePerFailstack: 0
    maximumFailstackApplied: -1
    failstackGainedPerFail: 1
    costToForceEnchant: -1
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: false
    fireworkRounds: 1
    prefix: ""
    suffix: "&7+3"
    enchantments:
      WEAPON:
        - ^Damage_All!Stealth:1
        - ^Damage_All!Execute:1
        - Damage_All!Damage_All:1
      ARMOR:
        - Pyromaniac:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Fire:1
      KNIFE:
        - Fire_Aspect:1
      ROD:
        - Lure:1
  "4":
    baseChance: 100
    chanceIncreasePerFailstack: 0
    maximumFailstackApplied: -1
    failstackGainedPerFail: 1
    costToForceEnchant: -1
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: false
    fireworkRounds: 1
    prefix: ""
    suffix: "&7+4"
    enchantments:
      WEAPON:
        - ^Damage_All!Stealth:1
        - ^Damage_All!Execute:1
        - ^Damage_All!Corruption:1
        - Damage_All!Damage_All:1
      ARMOR:
        - Pyromaniac:1
        - Haste:1
        - Swimmer:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Infinite:1
      KNIFE:
        - Damage_Arthropods:1
      ROD:
        - Lure:1
  "5":
    baseChance: 100
    chanceIncreasePerFailstack: 0
    maximumFailstackApplied: -1
    failstackGainedPerFail: 1
    costToForceEnchant: -1
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: false
    fireworkRounds: 1
    prefix: ""
    suffix: "&7+5"
    enchantments:
      WEAPON:
        - ^Damage_All!Stealth:1
        - ^Damage_All!Execute:1
        - ^Damage_All!Corruption:1
        - ^Damage_All!Rekt:1
        - Damage_All!Damage_All:1
      ARMOR:
        - Pyromaniac:1
        - Haste:1
        - Swimmer:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_Arthropods:1
      ROD:
        - Lure:1
  "6":
    baseChance: 100
    chanceIncreasePerFailstack: 0
    maximumFailstackApplied: -1
    failstackGainedPerFail: 1
    costToForceEnchant: -1
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkRounds: 1
    prefix: ""
    suffix: "&a+6"
    enchantments:
      WEAPON:
        - ^Damage_All!Execute:1
        - ^Damage_All!Corruption:1
        - ^Damage_All!Rekt:1
        - Damage_All!Damage_All:1
      ARMOR:
        - Pyromaniac:1
        - Haste:1
        - Swimmer:1
        - Jump:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_Arthropods:1
      ROD:
        - Lure:1
  "7":
    baseChance: 100
    chanceIncreasePerFailstack: 0
    maximumFailstackApplied: -1
    failstackGainedPerFail: 1
    costToForceEnchant: -1
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: false
    fireworkRounds: 1
    prefix: ""
    suffix: "&a+7"
    enchantments:
      WEAPON:
        - ^Damage_All!Execute:1
        - ^Damage_All!Corruption:1
        - ^Damage_All!Plunder:1
        - Damage_All!Damage_All:1
      ARMOR:
        - Jump:1
        - Batvision:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_Arthropods:1
      ROD:
        - Luck:1
  "8":
    baseChance: 20
    chanceIncreasePerFailstack: 2.5
    maximumFailstackApplied: 13
    failstackGainedPerFail: 1
    costToForceEnchant: 3
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: ""
    suffix: "&a+8"
    enchantments:
      WEAPON:
        - ^Damage_All!Corruption:1
        - ^Damage_All!Launch:5
        - Damage_All!Damage_All:1
      ARMOR:
        - Flame:3
      PICKAXE:
        - Smelt:1
      AXE:
        - Lumberjack:1
      HOE:
        -Plow:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_Arthropods:1
      ROD:
        - Luck:1
  "9":
    baseChance: 17.5
    chanceIncreasePerFailstack: 2.0
    maximumFailstackApplied: 14
    failstackGainedPerFail: 1
    costToForceEnchant: 4
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: ""
    suffix: "&a+9"
    enchantments:
      WEAPON:
        - ^Damage_All!Reborn:3
        - ^Damage_All!Mischief:3
        - Damage_All!Damage_All:1
      ARMOR:
        - Aegis:1
        - Eyepatch:1
      PICKAXE:
        - Explosive:1
      AXE:
        - Lumberjack:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_Undead:1
      ROD:
        - Luck:1
  "10":
    baseChance: 15
    chanceIncreasePerFailstack: 1.5
    maximumFailstackApplied: 15
    failstackGainedPerFail: 1
    costToForceEnchant: 5
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: ""
    suffix: "&b+10"
    enchantments:
      WEAPON:
        - ^Damage_All!Crits:1
        - ^Damage_All!Riftslayer:1
        - Damage_All!Damage_All:1
      ARMOR:
        - Aegis:1
        - Blessed:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_Undead:1
      ROD:
        - Luck:1
  "11":
    baseChance: 12.5
    chanceIncreasePerFailstack: 1.25
    maximumFailstackApplied: 16
    failstackGainedPerFail: 1
    costToForceEnchant: 7
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: ""
    suffix: "&b+11"
    enchantments:
      WEAPON:
        - ^Damage_All!Hex:3
        - ^Damage_All!Assassin:3
        - Damage_All!Damage_All:1
      ARMOR:
        - Aegis:1
        - Blessed:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_Undead:1
      ROD:
        - Luck:1
  "12":
    baseChance: 10
    chanceIncreasePerFailstack: 0.75
    maximumFailstackApplied: 18
    failstackGainedPerFail: 1
    costToForceEnchant: 9
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: ""
    suffix: "&b+12"
    enchantments:
      WEAPON:
        - ^Damage_All!Curse:2
        - ^Damage_All!Strength:1
        - Damage_All!Damage_All:1
      ARMOR:
        - Aegis:1
        - Blessed:1
        - Demonic:5
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_Undead:1
      ROD:
        - Luck:1
  "13":
    baseChance: 7.5
    chanceIncreasePerFailstack: 0.63
    maximumFailstackApplied: 20
    failstackGainedPerFail: 1
    costToForceEnchant: 12
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: ""
    suffix: "&b+13"
    enchantments:
      WEAPON:
        - ^Damage_All!Curse:2
        - Damage_All!Damage_All:1
      ARMOR:
        - Aegis:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_Undead:1
      ROD:
        - Luck:1
  "14":
    baseChance: 5
    chanceIncreasePerFailstack: 0.5
    maximumFailstackApplied: 25
    failstackGainedPerFail: 1
    costToForceEnchant: 15
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: ""
    suffix: "&b+14"
    enchantments:
      WEAPON:
        - ^Damage_All!Speed:1
        - Damage_All!Damage_All:1
      ARMOR:
        - Shield:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Loot_Bonus_Mobs:1
      ROD:
        - Luck:1
  "15":
    baseChance: 2.5
    chanceIncreasePerFailstack: 0.5
    maximumFailstackApplied: 25
    failstackGainedPerFail: 1
    costToForceEnchant: 18
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: false
    broadcastEnhance: false
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: ""
    suffix: "&d+15"
    enchantments:
      WEAPON:
        - ^Damage_All!Siphon:3
        - Damage_All!Damage_All:1
      ARMOR:
        - Saturation:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Dig_Speed:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Loot_Bonus_Mobs:1
  "16":
    baseChance: 15
    chanceIncreasePerFailstack: 1.5
    maximumFailstackApplied: 25
    failstackGainedPerFail: 1
    costToForceEnchant: 22
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: true
    broadcastEnhance: false
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: "&d&l史诗级"
    suffix: ""
    enchantments:
      WEAPON:
        - ^Damage_All!Petrify:3
        - ^Damage_All!Crushing:3
        - ^Damage_All!Lifesteal:1
        - Damage_All!Damage_All:1-4
      ARMOR:
        - Dodge:5
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Explosive:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Loot_Bonus_Mobs:1
      ROD:
        - Luck:1
  "17":
    baseChance: 7.5
    chanceIncreasePerFailstack: 0.75
    maximumFailstackApplied: 35
    failstackGainedPerFail: 2
    costToForceEnchant: 27
    downgradeChanceIfFail: 0.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: true
    broadcastEnhance: false
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: "&d&l进化"
    suffix: ""
    enchantments:
      WEAPON:
        - ^Damage_All!Smite:5
        - ^Damage_All!Turmoil:3
        - ^Damage_All!Crushing:3
        - ^Damage_All!Lifesteal:1
        - Damage_All!Damage_All:1-4
      ARMOR:
        - Repel:3
        - Molten:3
        - Battlecry:3
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Explosive:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Loot_Bonus_Mobs:1
      ROD:
        - Knockback:5
  "18":
    baseChance: 5.0
    chanceIncreasePerFailstack: 0.5
    maximumFailstackApplied: 44
    failstackGainedPerFail: 3
    costToForceEnchant: 45
    downgradeChanceIfFail: 100.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: true
    broadcastEnhance: true
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: "&6&l进化+1"
    suffix: ""
    enchantments:
      WEAPON:
        - ^Damage_All!Lifesteal:1
        - ^Damage_All!Paralyze:2
        - Damage_All!Damage_All:1
      ARMOR:
        - Reinforced:1
        - Suicide:1
        - Frosty:3
        - Divine:3
        - Shield:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Explosive:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Loot_Bonus_Mobs:1
      ROD:
        - Luck:10
  "19":
    baseChance: 2.0
    chanceIncreasePerFailstack: 0.25
    maximumFailstackApplied: 90
    failstackGainedPerFail: 4
    costToForceEnchant: -1
    downgradeChanceIfFail: 100.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: true
    broadcastEnhance: true
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: "&e&l进化+2"
    suffix: ""
    enchantments:
      WEAPON:
        - ^Damage_All!Purge:4
        - Damage_All!Damage_All:1-5
      ARMOR:
        - Spiked:3
        - Vitality:1
        - Cure:1
        - Immolation:1
        - Reversal:1
        - Shield:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Explosive:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_All:1-5
      ROD:
        - Lure:10
  "20":
    baseChance: 1.5
    chanceIncreasePerFailstack: 0.25
    maximumFailstackApplied: 124
    failstackGainedPerFail: 5
    costToForceEnchant: -1
    downgradeChanceIfFail: 100.0
    destroyChanceIfFail: 0.0
    requireConcentratedStones: true
    broadcastEnhance: true
    fireworkIfSuccessful: true
    fireworkRounds: 1
    prefix: "&c&l进化+3"
    suffix: ""
    enchantments:
      WEAPON:
        - ^Damage_All!Strength:2
        - Damage_All!Damage_All:5-10
      ARMOR:
        - Vitality:2
        - Cure:2
        - Immolation:4
        - Reversal:2
        - Shield:1
      PICKAXE:
        - Dig_Speed:1
      AXE:
        - Dig_Speed:1
      HOE:
        - Dig_Speed:1
      SHOVEL:
        - Explosive:1
      BOW:
        - Arrow_Damage:1
      KNIFE:
        - Damage_All:1-5
      ROD:
        - Damage_All:1-20
```