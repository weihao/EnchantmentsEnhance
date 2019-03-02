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

package org.pixeltime.enchantmentsenhance.manager.modular;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.interfaces.PlaySound;
import com.lgou2w.ldk.bukkit.compatibility.Sounds;

public class PlaySound_Safe implements PlaySound {
    public void playSound(Player p, String type) {
        Types playingType = Types.valueOf(type.toUpperCase());

        switch (playingType) {
            case SUCCESS:
                p.playSound(p.getLocation(), Sounds.NOTE_PLING.toBukkit(),
                        1.0F, 2.0F);
                break;
            case FAILED:
                p.playSound(p.getLocation(), Sounds.ANVIL_BREAK.toBukkit(),
                        1.0F, 2.0F);
                break;
            case DOWNGRADED:
                p.playSound(p.getLocation(), Sounds.EXPLODE.toBukkit(), 1.0F,
                        2.0F);
                break;
            default:
                p.playSound(p.getLocation(), Sounds.ANVIL_USE.toBukkit(),
                        1.0F, 2.0F);
        }
    }


    public enum Types {
        SUCCESS, FAILED, DOWNGRADED
    }
}
