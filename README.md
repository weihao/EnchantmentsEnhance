* [Chinese](README-zh.md) 
* [Japanese](README-ja.md)

# <img src=".images/logo.jpg" alt="Logo" align="right">
[![Travis CI](https://travis-ci.org/25/EnchantmentsEnhance.svg?branch=master)](https://travis-ci.org/25/EnchantmentsEnhance)
[![Codebeat Badge](https://codebeat.co/badges/2ef380b7-5479-4ac6-89d9-fd1fb673511c)](https://codebeat.co/projects/github-com-healpotion-enchantmentsenhance-master)
[![bStats](https://img.shields.io/badge/bStats-deployed-3366ff.svg?style=flat)](https://bstats.org/plugin/bukkit/EnchantmentsEnhance)
[![Open Source](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://github.com/25/EnchantmentsEnhance)
[![License](https://badges.frapsoft.com/os/gpl/gpl.svg?v=102)](http://www.gnu.org/licenses/gpl-3.0)
[![PR](https://img.shields.io/badge/contributing-welcome-FF69B4.svg?style=flat)](https://github.com/25/EnchantmentsEnhance/pulls)
[![Issues](https://img.shields.io/badge/issues-report-E74C3C.svg?style=flat)](https://github.com/25/EnchantmentsEnhance/issues)

# [<img src=".images/jenkins.png" alt="Download" align="left">](http://soulbound.me:8080/job/EnchantmentsEnhance/)
[![Jenkins](https://img.shields.io/badge/download-server_online-27AE60.svg?style=flat)](http://soulbound.me:8080/job/EnchantmentsEnhance/)
[![Jenkins Build Status](http://soulbound.me:8080/job/EnchantmentsEnhance/badge/icon)](http://soulbound.me:8080/job/EnchantmentsEnhance/)
[![Jitpack](https://jitpack.io/v/25/EnchantmentsEnhance.svg)](https://jitpack.io/#25/EnchantmentsEnhance/)
[![YAML](https://img.shields.io/badge/yaml-supported-27AE60.svg?style=flat)](https://github.com/25/EnchantmentsEnhance/blob/master/src/main/java/org/pixeltime/enchantmentsenhance/resources/playerdata.yml)
[![SQL](https://img.shields.io/badge/sql-supported-27AE60.svg?style=flat)](https://github.com/25/EnchantmentsEnhance/blob/master/src/main/java/org/pixeltime/enchantmentsenhance/resources/tables.sql)



## Life Cycle
###### Alpha (1.0 - 4.0)
##### Beta (4.0.1 - [latest](http://www.soulbound.me:8080/job/EnchantmentsEnhance/))
This plugin is under active development at the stage of beta phase.
Completion of the plugin is currently projected at 35%, meaning the general functionality and availability are incomplete.

## Introduction
[Wiki](https://github.com/25/EnchantmentsEnhance/wiki)

## Compatibility
Version: `1.8.x - 1.12.x`
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
