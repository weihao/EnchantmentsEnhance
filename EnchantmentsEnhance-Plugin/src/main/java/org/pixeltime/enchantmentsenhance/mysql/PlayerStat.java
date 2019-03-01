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

package org.pixeltime.enchantmentsenhance.mysql;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerStat {

    private static ArrayList<PlayerStat> players = new ArrayList<>();
    private String playername;
    private int failstack;
    private int[] items;
    private List<Integer> valks;
    private int grind;

    public PlayerStat(final Player player) {
        this.playername = player.getName();
        DataStorage.get().loadStats(this);
    }

    public static ArrayList<PlayerStat> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<PlayerStat> players) {
        PlayerStat.players = players;
    }

    public static PlayerStat getPlayerStats(final String playername) {
        for (final PlayerStat pData : getPlayers()) {
            if (pData.getPlayername().equals(playername)) {
                return pData;
            }
        }
        return null;
    }

    public static void removePlayer(String playername) {
        PlayerStat ps = getPlayerStats(playername);
        if (ps != null) {
            players.remove(ps);
        }
    }

    public int getGrind() {
        return grind;
    }

    public void setGrind(int grind) {
        this.grind = grind;
    }

    public List<Integer> getValks() {
        return valks;
    }

    public void setValks(List<Integer> valks) {
        this.valks = valks;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public int getFailstack() {
        return failstack;
    }

    public void setFailstack(int failstack) {
        this.failstack = failstack;
    }
}
