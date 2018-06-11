- [Chinese](README-zh.md)
- [Japanese](README-ja.md)

<p align="center">
<img src=".images/logo.jpg" alt="EnchantmentsEnhance" />
</p>

<p align="center">
<a href="https://travis-ci.org/HealPotion/EnchantmentsEnhance"><img src="https://travis-ci.org/25/EnchantmentsEnhance.svg?branch=master" alt="Travis CI" /></a>
<a href="https://codebeat.co/projects/github-com-healpotion-enchantmentsenhance-master"><img alt="codebeat badge" src="https://codebeat.co/badges/2ef380b7-5479-4ac6-89d9-fd1fb673511c" /></a>
<a href="https://github.com/healpotion/WeatherAPI"><img src="https://badges.frapsoft.com/os/v1/open-source.svg?v=102" alt="OpenSource" /></a>
<a href="http://www.gnu.org/licenses/gpl-3.0"><img src="https://badges.frapsoft.com/os/gpl/gpl.svg?v=102" alt="License" /></a>

</p>

## Introduction
[Wiki](https://github.com/25/EnchantmentsEnhance/wiki)

## Life Cycle
###### Alpha (1.0 - 4.0)
#####  (4.0.1 - [current](https://github.com/25/EnchantmentsEnhance/blob/master/pom.xml))
This plugin is under active development at the stage of beta phase.
Completion of the plugin is currently projected at 30%, meaning the general functionality and availability are incomplete.
You can find latest stable build of pre-release on Spigot.

## Compatibility
Version: `1.8.x ~ 1.12.x`
- [x] [Bukkit](https://bukkit.org)
- [x] [Spigot](https://spigotmc.org)
- [x] [PaperSpigot](https://ci.destroystokyo.com/view/All/job/PaperSpigot/)

## Install
1. Download plugin jarfile
2. Stop the server
3. Place jar file in the `plugins` folder
4. Start the server
5. Configure the plugin
6. Restart the server

## Building

EnchantmentsEnhance uses `Maven` to manage project dependencies.

#### Requirements

- JDK 8
- Kotlin 1.2.x
- Maven 3.5.x
- Git


> Build of Craftbukkit and Bukkit for local maven repository. See [BuildTools](https://www.spigotmc.org/wiki/buildtools/) Tools for more information.

Then run:

```sh
git clone https://github.com/25/EnchantmentsEnhance.git
cd EnchantmentsEnhance
mvn clean install
```

You can find the output file in the `target` directory of the corresponding module.

## Problems
Please use: https://github.com/HealPotion/EnchantmentsEnhance/issues
