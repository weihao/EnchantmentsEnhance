<p align="center"> <img width="200" height="200" src=".images/thumbnail.png"> </p>

# <img src=".images/logo.jpg" alt="Logo" align="right">
[![Travis CI](https://travis-ci.org/25/EnchantmentsEnhance.svg?branch=master)](https://travis-ci.org/25/EnchantmentsEnhance)
[![Codebeat Badge](https://codebeat.co/badges/2ef380b7-5479-4ac6-89d9-fd1fb673511c)](https://codebeat.co/projects/github-com-healpotion-enchantmentsenhance-master)
[![bStats](https://img.shields.io/badge/bStats-deployed-3366ff.svg?style=flat)](https://bstats.org/plugin/bukkit/EnchantmentsEnhance)
[![Open Source](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://github.com/25/EnchantmentsEnhance)
[![License](https://badges.frapsoft.com/os/gpl/gpl.svg?v=102)](http://www.gnu.org/licenses/gpl-3.0)
[![PR](https://img.shields.io/badge/contributing-welcome-FF69B4.svg?style=flat)](https://github.com/25/EnchantmentsEnhance/pulls)
[![Issues](https://img.shields.io/badge/issues-report-E74C3C.svg?style=flat)](https://github.com/25/EnchantmentsEnhance/issues)

# [<img src=".images/jenkins.png" alt="Download" align="left">](http://soulbound.me/job/EnchantmentsEnhance/)
[![Jenkins](https://img.shields.io/badge/download-server_online-27AE60.svg?style=flat)](http://soulbound.me/job/EnchantmentsEnhance/)
[![Jenkins Build Status](http://www.soulbound.me/job/EnchantmentsEnhance_stable/badge/icon)](http://www.soulbound.me/job/EnchantmentsEnhance-stable/)
[![Jitpack](https://jitpack.io/v/25/EnchantmentsEnhance.svg)](https://jitpack.io/#25/EnchantmentsEnhance/)



## Life Cycle
###### Alpha (1.0 - 4.0)
###### Beta (4.0.1 - 4.9.1)
##### Release (5.0 - [latest](http://www.soulbound.me/job/EnchantmentsEnhance-stable/))
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

1.12: Parent(resolve dependencies) -> Core(build plugin) -> 1.12 Shade(1.8 - 1.12 compatibility)

1.13: Parent(resolve dependencies) -> Core(build plugin) -> 1.13 Shade(1.3 compatibility)

#### Requirements
- IntelliJ IDEA
- JDK 8
- Kotlin 1.2.x
- Maven 3.5.x
- Git



Then run:

```sh
git clone https://github.com/25/EnchantmentsEnhance.git
cd EnchantmentsEnhance
mvn install package
```

You can find the output file in the `target` directory of the corresponding module.
