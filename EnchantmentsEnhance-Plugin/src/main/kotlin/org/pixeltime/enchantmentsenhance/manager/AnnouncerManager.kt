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

package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.scheduler.BukkitRunnable
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.chat.Announcement
import org.pixeltime.enchantmentsenhance.chat.Announcer
import java.util.concurrent.ConcurrentLinkedDeque


class AnnouncerManager(val announcer: Announcer) {

    fun call(announcement: Announcement) {
        // If queue is empty and inactive.
        if (queued.isEmpty() && !active) {
            queued.add(announcement)
            active = display()
        } else {
            queued.add(announcement)
        }
    }

    fun display(): Boolean {
        if (!queued.isEmpty()) {
            val announcement = queued.pop()
            announcer.announce(announcement.msg, announcement.type)
            object : BukkitRunnable() {
                override fun run() {
                    active = display()
                }
            }.runTaskLater(Main.getMain(), DELAY)
            return true
        }
        return false
    }

    companion object {
        val queued = ConcurrentLinkedDeque<Announcement>()
        var active = false
        const val DELAY = 3 * 20L
    }
}