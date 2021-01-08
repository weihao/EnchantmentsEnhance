# Changelog

## [8.0.0] - 2020-10-19 - WIP

### Fixed

- Fixed the bug that attacker's level was used instead of victim's level

### Changes

- Separated soulbound to soulbound and tradeable
- Updated to kotlin 1.14.

## [7.9.15] - 2020-5-5

### Fixed

- Fixed an AnvilInventory error occured when repairing an item
- Fixed flying in spectator
- Fixed furnace duplication

## [7.9.14] - 2020-4-23

### Fixed

- Fixed an error when opening enhance inventory
- Fixed an error when opening enhance backpack

### Removed

- Removed reward for animal breeding **temporarily**

## [7.9.13] - 2020-4-21

### Added

- Language support for Russian
- Added anvil item renaming

## [7.9.12] - 2020-4-10

### Fixed

- Fixed animal breeding

## [7.9.11] - 2020-4-7

### Fixed

- Fixed smelting loot table
- Fixed a black stone duplication glitching in animal breeding

## [7.9.10] - 2020-2-17

### Fixed

- Fixed an error caused by clicking enhance icons too fast

## [7.9.9] - 2020-2-16

### Removed

- **Temporarily removed badly implemented enchantments to fix plugin performance**

## [7.9.8] - 2020-2-1

### Added

- Added getter methods to Api that get the level of player's weapon, armor, and tool

### Changed

- Separated data models
- Abstracted EE API

## [7.9.7] - 2020-1-26

### Fixed

- Fixed an issue caused player to lose max health
- Fixed an issue caused player to lose player data when mysql is enabled

## [7.9.6] - 2020-1-19

### Changed

- **Changed mysql schema to not use id column**
- **Changed charset from latin1 to utf8**

### Fixed

- Fixed a mysql exception

## [7.9.5] - 2020-1-6

### Fixed

- Fixed all the enchantments in the package does not initalize correctly with a path containing unicodes.

## [7.9.4] - 2019-12-28

### Added

- Added support for 1.15.1

### Fixed

- Fixed an issue with Util toColor IllegalArgumentException and kotlin compile
- Fixed NBT operational problems with null or air item, and if not supported, return the source item
- Fixed a glitch that shift right click enchantment table causes issue

## [7.9.3] - 2019-11-18

### Fixed

- Fixed an UnsupportedOperationException

## [7.9.2] - 2019-11-9

### Fixed

- To color an empty string throws an error

## [7.9.1] - 2019-10-31

### Fixed

- Config backup to an unexpected directory

## [7.9.0] - 2019-10-28

### Fixed

- Fixed offhand item duplication
- Fixed skipping disabled enchantments in enhancements

## [7.8.0] - 2019-8-13

### Added

- Added an option to disable update notification

### Changed

- **Moved the player data files to a different directory**

## [7.7.0] - 2019-6-22

### Fixed

- Fixed an error on startup

### Changed

- Updated bukkit to 1.14.3
- Updated kotlin to 1.3.40

## [7.6.0] - 2019-5-21

### Added

- Added Random Enhancement

### Fixed

- Fixed item disappears after set name
- WorldGuard soft depend

### Changed

- Updated dependencies

## [7.5.0] - 2019-5-6

### Added

- Support for 1.14

### Fixed

- Fixed a security issue

### Changed

- Refactored NBTAPI to LDK-NBT

## [7.4.0] - 2019-4-22

### Fixed

- Config regenerates deleted enchantment levels
- Renamed jarfile crashes the plugin
- Version check freezes the server
- Reflection optimization

## [7.3.0] - 2019-3-17

### Fixed

- Build failing due to issues with dependencies
- Glow does not register after reload
- Advice does not display properly
- Plugin failed to work in offline mode

## [7.2.0] - 2019-3-2

### Added

- Preview enhancement when enhancing
- Item engraving

### Fixed

- Menu async problem
- Glow enchantment error

### Changed

- Changed dependency to LDK compatibility

## [7.1.0] - 2019-2-28

### Added

- Grinding can be disabled
- Reblath failstacking can be disabled
- Sword can be enhanced as a tool
- The shovel can be enhanced as a tool
- Item set command can now support tool
- Option to only accept EE renamed item
- The fishing rod can be enhanced as a tool

### Fixed

- Issue displaying enchantments chance
- Items can be glitched into the menu
- Overflow error

### Changed

- The plugin is now compatible with `1.8`-`1.13.2`
- Configuration file changed to adapt the knife and shovel
- Jar files now contain markdown files

## [7.0.0] - 2019-2-25

### Added

- Breeding includes turtle and llama
- 1.12.2 compatibility

### Fixed

- Force enhancement
- Compatibility with craftbukkit

### Removed

- Fuzzy name compares

### Changed

- Tools can now be force enhanced

## [6.9.0] - 2019-2-6

### Added

- enhance set permission

### Fixed

- Fixed enhance item duplication bug
- Fixed color codes error

### Changed

- Updated to Spigot 1.13.2
- Updated to Kotlin 1.3

## [6.8.0] - 2019-1-1

### Fixed

- Fixed an issue with consuming item in 1.8
- Fixed counting issue with the black stone bundle

## [6.7.0] - 2018-12-21

### Added

- Added an option to disable anvil repairs
- Added an option to change explosive pickaxe radius

### Fixed

- Fixed a compatibility issue

### Changed

- Updated to Kotlin 1.3.11
- Plugin recodes in progress

All notable changes to this project will be documented in this file

## [6.6.0] - 2018-09-17

### Fixed

- Material list error at startup

## [6.5.0] - 2018-09-16

### Fixed

- Fixed an ArrayIndexOutOfBoundsException in the config
- Fixed enhancement error
- Fixed force enhancing while enhancing in progress
- Fixed unable to open menu after reload
- Fixed force enhancement at the maximum level

## [6.4.0] - 2018-09-3

### Added

- Support for MVdWPlaceholderAPI

## [6.3.0] - 2018-09-2

### Added

- Strictly lore compare

### Fixed

- Version error
- Lumberjack overflow
- Lifeskilling item drops

### Removed

- Fuzzy lore compare

### Changed

- Changed chopping material list

## [6.2.0] - 2018-08-31

### Fixed

- Fixed enhance
- Addressed spawner issue

## [6.1.1] - 2018-08-30

### Fixed

- Fixed touch spawner

## [6.1.0] - 2018-08-29

### Added

- Disableable virtual inventory and player trading

### Fixed

- ITEMS DUPLICATION
- Unable to apply enchantments using commands
- Smelt gives ink sac when lapis is mined
- Touch enhancement error
- Skip animation can overuse items

## [6.0.4] - 2018-08-18

### Added

- Enchantments descriptions in help command

### Fixed

- Permanent Potion removal

## [6.0.3] - 2018-08-18

### Added

- Periodic timer on version information

### Fixed

- A soul bound item could be glitched
- Reload command

## [6.0.2] - 2018-08-16

### Added

- Support for Placeholders

## [6.0.1] - 2018-08-15

### Added

- Aliases for "Wings"

### Fixed

- Fly issue

### Changed

- downgradeIfFail changed to downgradeChanceIfFail
- destroyIfFail changed to destroyChanceIfFail

## [6.0.0] - 2018-08-14

### Added

- Bow enhancement
- Conditional operator
- Range operator
- Exclusion operator
- Possibility operator

### Fixed

- Fixed an error that prevents gui from opening

### Changed

- Renamed `enableFireworkDamage: false` to `enablePreventFireworkDamage: true`

## [5.7.0] - 2018-08-13

### Added

- Tools enhancement
- Pickaxe enhancement
- Axe enhancement
- Hoe enhancement

### Changed

- Lang prefix changed to lower case to follow rules of naming convention
- Configuration file structure change

## [5.6.0] - 2018-08-12

### Added

- Firework damage can be disabled
- Enhancing animation #1

### Fixed

- Grinding probability issue

## [5.5.0] - 2018-08-11

### Added

- Support for TitleBar
- Support for the fancy chat
- Notification
- Announcement
- Random applies enchantments
- Random level enchantments

### Fixed

- An issue with the downgraded item
- The error that enchantments might stack
- NBT tags might not apply properly

### Changed

- Random enchanted enchantments will be erased if downgraded
- All the message can be disabled

### Removed

- Unused item sorting

## [5.4.0] - 2018-08-10

### Added

- Phoenix Enchantment for gear
- Pumpking Enchantment for axe
- Configuration iteration for enchantment file
- Support for ActionBarAPI
- Support for BossBarAPI

### Fixed

- Permanent potion removal
- Enchantment issue with renamed items
- Duplication issue with smelted items in the furnace
- Enchantment command failed and success notification
- LumberJack Enchantment
- Explosive Enchantment drop issue
- Shearer Enchantment with shearing sheep
- Enchantment add command throwing errors

## [5.3.0] - 2018-08-08

### Added

- Changelog
- Explosive Enchantment for tools
- Touch Enchantment for tools
- Ability to disable welcome message

### Fixed

- An issue with survival player flying
- Players could potentially take out items from gui
- Errors with commands
- Yield probability with grinding
- Explosive from enchantments damages the WorldGuard protected regions
- Downgrade upon failing
- Destroy upon failing

### Changed

- Enchantment "Wing" renamed to "Wings"

### Removed

- Support for StackMob
