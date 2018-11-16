/*
 *     Copyright (C) 2017-Present HealPot
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

package org.pixeltime.enchantmentsenhance.util

import org.bukkit.plugin.java.JavaPlugin
import java.io.IOException
import java.net.URL
import java.util.*
import java.util.jar.JarFile

object ClassGetter {

    fun getClassesForPackage(plugin: JavaPlugin, pkgname: String): ArrayList<Class<*>> {
        val classes = ArrayList<Class<*>>()
        val src = plugin.javaClass.protectionDomain.codeSource
        if (src != null) {
            val resource = src.location
            resource.path
            processJarfile(resource, pkgname, classes)
        }
        return classes
    }

    private fun loadClass(className: String): Class<*>? {
        try {
            return Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Unexpected ClassNotFoundException loading class '$className'")
        } catch (e: NoClassDefFoundError) {
            return null
        }

    }

    private fun processJarfile(resource: URL, pkgname: String, classes: ArrayList<Class<*>>) {
        val relPath = pkgname.replace('.', '/')
        val resPath = resource.path.replace("%20", " ")
        val jarPath = resPath.replaceFirst("[.]jar[!].*".toRegex(), ".jar").replaceFirst("file:".toRegex(), "")
        val jarFile: JarFile
        try {
            jarFile = JarFile(jarPath)
        } catch (e: IOException) {
            throw RuntimeException("Unexpected IOException reading JAR File '$jarPath'. Do you have strange characters in your folders? Such as #?", e)
        }

        val entries = jarFile.entries()
        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()
            val entryName = entry.name
            var className: String? = null
            if (entryName.endsWith(".class") && entryName.startsWith(relPath)
                && entryName.length > relPath.length + "/".length) {
                className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "")
            }
            if (className != null) {
                val c = loadClass(className)
                if (c != null)
                    classes.add(c)
            }
        }
    }
}
