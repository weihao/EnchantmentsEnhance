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

package org.pixeltime.enchantmentsenhance.util.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.util.enums.ArmorType;

public class ArmorEquipEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers;

    static {
        handlers = new HandlerList();
    }

    private final EquipMethod equipType;
    private final ArmorType type;
    private boolean cancel;
    private ItemStack oldArmorPiece;
    private ItemStack newArmorPiece;

    public ArmorEquipEvent(final Player player, final EquipMethod equipType, final ArmorType type, final ItemStack oldArmorPiece, final ItemStack newArmorPiece) {
        super(player);
        this.cancel = false;
        this.equipType = equipType;
        this.type = type;
        this.oldArmorPiece = oldArmorPiece;
        this.newArmorPiece = newArmorPiece;
    }

    public static final HandlerList getHandlerList() {
        return ArmorEquipEvent.handlers;
    }

    public final HandlerList getHandlers() {
        return ArmorEquipEvent.handlers;
    }

    public final boolean isCancelled() {
        return this.cancel;
    }

    public final void setCancelled(final boolean cancel) {
        this.cancel = cancel;
    }

    public final ArmorType getType() {
        return this.type;
    }

    public final ItemStack getOldArmorPiece() {
        return this.oldArmorPiece;
    }

    public final void setOldArmorPiece(final ItemStack oldArmorPiece) {
        this.oldArmorPiece = oldArmorPiece;
    }

    public final ItemStack getNewArmorPiece() {
        return this.newArmorPiece;
    }

    public final void setNewArmorPiece(final ItemStack newArmorPiece) {
        this.newArmorPiece = newArmorPiece;
    }

    public EquipMethod getMethod() {
        return this.equipType;
    }

    public enum EquipMethod {
        SHIFT_CLICK("SHIFT_CLICK", 0),
        DRAG("DRAG", 1),
        HOTBAR("HOTBAR", 2),
        HOTBAR_SWAP("HOTBAR_SWAP", 3),
        DISPENSER("DISPENSER", 4),
        BROKE("BROKE", 5),
        DEATH("DEATH", 6);

        private EquipMethod(final String s, final int n) {
        }
    }
}