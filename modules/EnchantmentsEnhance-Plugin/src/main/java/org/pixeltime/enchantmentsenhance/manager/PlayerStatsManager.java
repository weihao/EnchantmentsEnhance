package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.model.PlayerStat;
import org.pixeltime.enchantmentsenhance.mysql.DataStorage;

import java.util.ArrayList;

public class PlayerStatsManager {
    private static ArrayList<PlayerStat> playerStatArrayList = new ArrayList<>();


    public static PlayerStat getOne(final Player player) {
        PlayerStat playerStat = new PlayerStat();
        playerStat.setPlayername(player.getName());
        DataStorage.get().loadStats(playerStat);
        return playerStat;
    }

    public static void loadStats(PlayerStat stats) {
        DataStorage.get().loadStats(stats);
    }

    public static ArrayList<PlayerStat> getPlayerStatsList() {
        return playerStatArrayList;
    }

    public static PlayerStat getPlayerStats(final String playername) {
        for (final PlayerStat pData : getPlayerStatsList()) {
            if (pData.getPlayername().equals(playername)) {
                return pData;
            }
        }
        return null;
    }

    public static void removePlayerStats(String playername) {
        PlayerStat ps = getPlayerStats(playername);
        if (ps != null) {
            playerStatArrayList.remove(ps);
        }
    }
}
