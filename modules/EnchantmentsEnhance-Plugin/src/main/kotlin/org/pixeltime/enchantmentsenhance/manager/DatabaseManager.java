package org.pixeltime.enchantmentsenhance.manager;

import com.sk89q.worldedit.entity.Player;
import org.pixeltime.enchantmentsenhance.model.PlayerStat;
import org.pixeltime.enchantmentsenhance.mysql.DataStorage;

import java.util.ArrayList;

public class DatabaseManager {
    private static ArrayList<PlayerStat> playerStats = new ArrayList<>();


    public void addPlayerStat(Player player) {
        DatabaseManager.removePlayerStat(player.getName());
        PlayerStat playerStat = new PlayerStat();
        playerStat.setPlayername(player.getName());
        DataStorage.get().loadStats(playerStat);
        playerStats.add(playerStat);
    }


    public static PlayerStat getPlayerStat(final String playername) {
        for (final PlayerStat pData : playerStats) {
            if (pData.getPlayername().equals(playername)) {
                return pData;
            }
        }
        return null;
    }

    public static void removePlayerStat(String playername) {
        PlayerStat ps = getPlayerStat(playername);
        if (ps != null) {
            playerStats.remove(ps);
        }
    }

}
