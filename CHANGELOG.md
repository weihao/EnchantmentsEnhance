# Changelog
All notable changes to this project will be documented in this file.
## [6.4.0] - 2018-09-3
### Added
- Support for MVdWPlaceholderAPI.

## [6.3.0] - 2018-09-2
### Added
- Strictly lore compare.
### Fixed
- Version error.
- Lumberjack overflow.
- Lifeskilling item drops.
### Removed
- Fuzzy lore compare.
### Changed
- Changed chopping material list.

## [6.2.0] - 2018-08-31
### Fixed
- Fixed enhance.
- Addressed spawner issue.

## [6.1.1] - 2018-08-30
### Fixed
- Fixed touch spawner.

## [6.1.0] - 2018-08-29
### Added
- Disableable virtual inventory and player trading.
### Fixed
- ITEMS DUPLICATION.
- Unable to apply enchantments using commands.
- Smelt gives ink sac when lapis is mined.
- Touch enhancement error.
- Skip animation can overuse items.

## [6.0.4] - 2018-08-18
### Added
- Enchantments descriptions in help command.
### Fixed
- Permanent Potion removal.


## [6.0.3] - 2018-08-18
### Added
- Periodic timer on version information.
### Fixed
- A soul bound item could be glitched.
- Reload command.

## [6.0.2] - 2018-08-16
### Added
- Support for Placeholders.

## [6.0.1] - 2018-08-15
### Added
- Aliases for "Wings".

### Fixed
- Fly issue.

### Changed
- downgradeIfFail changed to downgradeChanceIfFail.
- destroyIfFail changed to destroyChanceIfFail.

## [6.0.0] - 2018-08-14
### Added
- Bow enhancement.
- Conditional operator.
- Range operator.
- Exclusion operator.
- Possibility operator.
### Fixed
- Fixed an error that prevents gui from opening.
### Changed
- Renamed `enableFireworkDamage: false` to `enablePreventFireworkDamage: true`.

## [5.7.0] - 2018-08-13
### Added
- Tools enhancement.
- Pickaxe enhancement.
- Axe enhancement.
- Hoe enhancement.
### Changed
- Lang prefix changed to lower case to follow rules of naming convention.
- Configuration file structure change.

## [5.6.0] - 2018-08-12
### Added
- Firework damage can be disabled.
- Enhancing animation #1.
### Fixed
- Grinding probability issue.

## [5.5.0] - 2018-08-11
### Added
- Support for TitleBar.
- Support for fancy chat.
- Notification.
- Announcement.
- Random applies enchantments.
- Random level enchantments.

### Fixed
- An issue with the downgraded item.
- The error that enchantments might stack.
- NBT tags might not apply properly.

### Changed
- Random enchanted enchantments will be erased if downgraded.
- All the message can be disabled.

### Removed
- Unused item sorting.

## [5.4.0] - 2018-08-10
### Added
- Phoenix Enchantment for gear.
- Pumpking Enchantment for axe.
- Configuration iteration for enchantment file.
- Support for ActionBarAPI.
- Support for BossBarAPI.

### Fixed
- Permanent potion removal.
- Enchantment issue with renamed items.
- Duplication issue with smelted items in the furnace.
- Enchantment command failed and success notification.
- LumberJack Enchantment.
- Explosive Enchantment drop issue.
- Shearer Enchantment with shearing sheep.
- Enchantment add command throwing errors.

## [5.3.0] - 2018-08-08
### Added
- Changelog.
- Explosive Enchantment for tools.
- Touch Enchantment for tools.
- Ability to disable welcome message.

### Fixed
- An issue with survival player flying.
- Players could potentially take out items from gui.
- Errors with commands.
- Yield probability with grinding.
- Explosive from enchantments damages the WorldGuard protected regions.
- Downgrade upon failing.
- Destroy upon failing.

### Changed
- Enchantment "Wing" renamed to "Wings".

### Removed
- Support for StackMob.
