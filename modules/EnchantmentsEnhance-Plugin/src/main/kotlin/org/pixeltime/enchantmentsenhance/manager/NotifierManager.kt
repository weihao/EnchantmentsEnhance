package org.pixeltime.enchantmentsenhance.manager

import org.pixeltime.enchantmentsenhance.chat.Notification
import org.pixeltime.enchantmentsenhance.chat.Notifier

class NotifierManager(val notifier: Notifier) {

    fun call(notification: Notification) {
        notifier.sendMessage(notification.player, notification.msg as Array<String>)
    }
}