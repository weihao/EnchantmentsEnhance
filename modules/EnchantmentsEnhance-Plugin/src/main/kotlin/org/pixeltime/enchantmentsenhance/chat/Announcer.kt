package org.pixeltime.enchantmentsenhance.chat

import org.pixeltime.enchantmentsenhance.enums.AnnounceType

interface Announcer {
    fun announce(msg: String, type: AnnounceType)
}