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

package org.pixeltime.enchantmentsenhance.manager

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.chat.ComponentSerializer
import net.minecraft.server.v1_8_R3.IChatBaseComponent
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaBook
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta


class BM {
    companion object {
        @JvmStatic
        fun open(player: Player) {
            //create the book
            val book = ItemStack(Material.WRITTEN_BOOK)
            val bookMeta = book.itemMeta as BookMeta
            val pages: MutableList<IChatBaseComponent>

            //get the pages
            try {
                pages = (CraftMetaBook::class.java.getDeclaredField("pages").get(bookMeta) as List<IChatBaseComponent>).toMutableList()
            } catch (ex: ReflectiveOperationException) {
                ex.printStackTrace()
                return
            }

            //create a page
            val text = TextComponent("Click me")
            text.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "http://spigotmc.org")
            text.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder("Goto the spigot website!").create())

            //add the page to the list of pages
            val page = IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(text))
            pages.add(page)

            //set the title and author of this book
            bookMeta.title = "Interactive Book"
            bookMeta.author = "gigosaurus"

            //update the ItemStack with this new meta
            book.itemMeta = bookMeta
        }

    }
}