/*
 *     Copyright (C) 2017-Present HealPotion
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.command.console

import org.bukkit.command.CommandSender
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.api.API
import org.pixeltime.enchantmentsenhance.command.SubConsoleCommand
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.ClassGetter
import java.util.*

class DebugConsoleCommand : SubConsoleCommand() {

    override fun onCommand(sender: CommandSender, args: Array<String>) {
        val sb = StringBuilder()
        when {
            args[0] == "gen" -> {

                // Generate English Enchantments Wiki.
                sb.appendln("Generating English Enchantments Wiki...")
                sb.appendln("| Enchantments ID| Effect| Level|")
                sb.appendln("| :---:| :---------| :---:")
                for (enchClass in ClassGetter.getClassesForPackage(Main.getMain(), "org.pixeltime.enchantmentsenhance.event.enchantment")) {
                    if (EnchantmentListener::class.java.isAssignableFrom(enchClass)) {
                        try {
                            val enchantmentListener = enchClass.newInstance() as EnchantmentListener
                            sb.appendln("|" + enchantmentListener.javaClass.simpleName + "|" + enchantmentListener.desc()[0] + "|" + API.getEnchantmentMaxLevel(enchantmentListener.javaClass.simpleName) + "|")
                        } catch (e: InstantiationException) {
                            e.printStackTrace()
                        } catch (e: IllegalAccessException) {
                            e.printStackTrace()
                        }
                    }
                }


                // Generate Chinese Enchantments Wiki.
                sb.appendln("Generating Chinese Enchantments Wiki...")
                sb.appendln("| 附魔ID| 中文名称| 效果|")
                sb.appendln("| :---:|:---:| :--------------------------:|")
                for (enchClass in ClassGetter.getClassesForPackage(Main.getMain(), "org.pixeltime.enchantmentsenhance.event.enchantment")) {
                    if (EnchantmentListener::class.java.isAssignableFrom(enchClass)) {
                        try {
                            val enchantmentListener = enchClass.newInstance() as EnchantmentListener
                            sb.appendln("|" + enchantmentListener.javaClass.simpleName + "|" + enchantmentListener.lang()[0] + "|" + enchantmentListener.desc()[1] + "|")
                        } catch (e: InstantiationException) {
                            e.printStackTrace()
                        } catch (e: IllegalAccessException) {
                            e.printStackTrace()
                        }

                    }
                }

                // Generate English Commands Wiki.
                sb.appendln("Generating English Commands Wiki...")
                sb.appendln("| Player Command| Permission| Aliases|")
                sb.appendln("| :---|:---|:---|")
                for (command in Main.getMain().commandManager.commands) {
                    try {
                        val sb = StringBuilder()
                        sb.append("|")
                        sb.append(command.usage().replace("|", "&#124;"))
                        sb.append(" - ")
                        sb.append(SettingsManager.lang.getString("Help." + command.name()).replace("|", "&#124;"))
                        sb.append("|")
                        sb.append(command.permission)
                        sb.append("|")
                        sb.append(Arrays.toString(command.aliases()))
                        sb.append("|")
                        sb.appendln(sb.toString().replace("&[a-z0-9]".toRegex(), "").replace("\n", "<br />"))
                    } catch (ex: IllegalStateException) {
                    }
                }
            }
        }
        print(sb.toString())
    }


    override fun name(): String {
        return "debug"
    }


    override fun usage(): String {
        return "/enhance debug"
    }


    override fun aliases(): Array<String> {
        return arrayOf("debug", "tiaoshi", "ts")
    }


    fun log(s: String) {
        Main.getMain().logger.info("DEBUG >>>$s")
    }

}
