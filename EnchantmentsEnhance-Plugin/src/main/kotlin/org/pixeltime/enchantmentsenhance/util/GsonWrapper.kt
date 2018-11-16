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

import com.google.gson.Gson

object GsonWrapper {

    private val gson = Gson()

    fun getString(obj: Any): String {
        return gson.toJson(obj)
    }

    fun <T> deserializeJson(json: String?, type: Class<T>): T? {
        try {
            if (json == null) {
                return null
            }

            val obj = gson.fromJson(json, type)
            return type.cast(obj)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }

    }
}
