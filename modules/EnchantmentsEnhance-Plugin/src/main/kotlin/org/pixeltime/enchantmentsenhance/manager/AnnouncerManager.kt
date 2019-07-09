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