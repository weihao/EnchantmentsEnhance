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
