package org.pixeltime.enchantmentsenhance.mysql;

import com.sk89q.worldedit.entity.Player;
import org.pixeltime.enchantmentsenhance.model.PlayerStat;

import java.util.ArrayList;

public class DatabaseManager {
    private static ArrayList<PlayerStat> playerStats = new ArrayList<>();


    public void addPlayerStat(Player player) {
        if (getPlayerStat(player.getName()) != null) {
            DatabaseManager.removePlayer(player.getName());
        }
        PlayerStat playerStat = new PlayerStat();
        playerStat.setPlayername(player.getName());
        DataStorage.get().loadStats(playerStat);
        playerStats.add(playerStat);
    }


    public static ArrayList<PlayerStat> getPlayerStatsList() {
        return playerStats;
    }

    public static PlayerStat getPlayerStat(final String playername) {
        for (final PlayerStat pData : playerStats) {
            if (pData.getPlayername().equals(playername)) {
                return pData;
            }
        }
        return null;
    }

    public static void removePlayer(String playername) {
        PlayerStat ps = getPlayerStat(playername);
        if (ps != null) {
            playerStats.remove(ps);
        }
    }

}
