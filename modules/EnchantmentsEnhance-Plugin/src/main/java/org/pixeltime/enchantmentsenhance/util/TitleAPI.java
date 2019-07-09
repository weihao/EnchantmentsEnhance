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

package org.pixeltime.enchantmentsenhance.util;

import com.lgou2w.ldk.bukkit.chat.ChatFactory;
import com.lgou2w.ldk.chat.ChatComponent;
import com.lgou2w.ldk.chat.ChatSerializer;
import org.bukkit.entity.Player;

public class TitleAPI {

    @Deprecated
    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, message, null);
    }

    @Deprecated
    public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, null, message);
    }

    @Deprecated
    public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subTitle) {
        ChatComponent title0 = ChatSerializer.fromRawOrNull(title);
        ChatComponent subTitle0 = ChatSerializer.fromRawOrNull(subTitle);
        ChatFactory.sendTitle(player, title0, subTitle0, fadeIn, stay, fadeOut);
    }

    public static void clearTitle(Player player) {
        ChatFactory.sendTitleClear(player);
    }

    public static void sendTabTitle(Player player, String header, String footer) {
        ChatComponent header0 = ChatSerializer.fromRawOrNull(header);
        ChatComponent footer0 = ChatSerializer.fromRawOrNull(footer);
        ChatFactory.sendPlayerListHeaderFooter(player, header0, footer0);
    }
}
