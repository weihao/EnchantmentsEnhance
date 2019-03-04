<p align="center"> <img width="200" height="200" src=".images/thumbnail.png"> </p>

# <img src=".images/logo.jpg" alt="Logo" align="right">
[![Kotlin](https://img.shields.io/badge/Kotlin-1.3%2b-brightgreen.svg)](https://kotlinlang.org)
[![Java](https://img.shields.io/badge/Java-8%2b-brightgreen.svg)](https://www.java.com/)
[![Travis CI](https://travis-ci.org/25/EnchantmentsEnhance.svg?branch=master)](https://travis-ci.org/25/EnchantmentsEnhance)
[![bStats](https://img.shields.io/badge/bStats-deployed-3366ff.svg?style=flat)](https://bstats.org/plugin/bukkit/EnchantmentsEnhance)
[![License](https://img.shields.io/badge/license-GPL-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)
[![PR](https://img.shields.io/badge/contributing-welcome-FF69B4.svg?style=flat)](https://github.com/25/EnchantmentsEnhance/pulls)
[![Issues](https://img.shields.io/badge/issues-report-E74C3C.svg?style=flat)](https://github.com/25/EnchantmentsEnhance/issues)
[![Language](https://img.shields.io/github/languages/count/25/EnchantmentsEnhance.svg)](https://github.com/25/EnchantmentsEnhance/issues)

# [<img src=".images/jenkins.png" alt="Download" align="left">](http://www.soulbound.me/job/EnchantmentsEnhance_stable/)
[![Download](https://img.shields.io/spiget/downloads/51635.svg)](https://www.spigotmc.org/resources/enchantmentsenhance-gear-progression-mechanics-with-customized-enchantments.51635/)
[![Spiget Stars](https://img.shields.io/spiget/stars/59555.svg)](https://www.spigotmc.org/resources/enchantmentsenhance-gear-progression-mechanics-with-customized-enchantments.51635/)
[![Spiget Rating](https://img.shields.io/spiget/rating/51635.svg)](https://www.spigotmc.org/resources/enchantmentsenhance-gear-progression-mechanics-with-customized-enchantments.51635/)
[![Jenkins](https://img.shields.io/website-up-down-green-red/http/www.soulbound.me.svg)](http://soulbound.me/job/EnchantmentsEnhance_stable/)
[![Jenkins Build Status](http://www.soulbound.me/job/EnchantmentsEnhance_stable/badge/icon)](http://www.soulbound.me/job/EnchantmentsEnhance_stable/)
[![Jitpack](https://jitpack.io/v/25/EnchantmentsEnhance.svg)](https://jitpack.io/#25/EnchantmentsEnhance/)



## Life Cycle
###### Alpha (1.0 - 4.0)
###### Beta (4.0.1 - 4.9.1)
##### Release (5.0 - [latest](http://www.soulbound.me/job/EnchantmentsEnhance_stable/))
This plugin is under active development at the stage of the release phase.
Completion of the plugin is currently projected at `70%`, meaning the general functionality are `mostly complete`.

## Introduction
[Wiki](https://github.com/25/EnchantmentsEnhance/wiki)

[Change Log](https://github.com/25/EnchantmentsEnhance/blob/master/CHANGELOG.md)

## Compatibility
Version: `1.8.x - 1.13.x`
- [x] [Bukkit](https://bukkit.org)
- [x] [Spigot](https://spigotmc.org)
- [x] [PaperSpigot](https://github.com/PaperMC/Paper)

## Install
1. Download plugin jarfile
2. Stop the server
3. Place jar file in the `plugins` folder
4. Start the server
5. Configure the plugin
6. Restart the server

## Building
#### Dependency
> Build of Craftbukkit and Bukkit for local maven repository. See [BuildTools](https://www.spigotmc.org/wiki/buildtools/) Tools for more information.

EnchantmentsEnhance uses `Maven` to manage project dependencies.

#### Requirements
- IntelliJ IDEA
- JDK 8
- Kotlin 1.3.x
- Maven 3.5.x
- Git



Then run:

```sh
git clone https://github.com/25/EnchantmentsEnhance.git
cd EnchantmentsEnhance
mvn install package
```

You can find the output file in the `target` directory of the corresponding module.
