/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

package org.pixeltime.enchantmentsenhance.util.reflection;

public class MethodNames {

    private final static MinecraftVersion MINECRAFT_VERSION = MinecraftVersion.getVersion();

    public static String getTileDataMethodName() {
        if (MINECRAFT_VERSION == MinecraftVersion.MC1_8_R3) {
            return "b";
        }
        return "save";
    }

    public static String getTypeMethodName() {
        if (MINECRAFT_VERSION == MinecraftVersion.MC1_8_R3) {
            return "b";
        }
        return "d";
    }

    public static String getEntityNbtGetterMethodName() {
        return "b";
    }

    public static String getEntityNbtSetterMethodName() {
        return "a";
    }

    public static String getRemoveMethodName() {
        if (MINECRAFT_VERSION == MinecraftVersion.MC1_8_R3) {
            return "a";
        }
        return "remove";
    }

}
