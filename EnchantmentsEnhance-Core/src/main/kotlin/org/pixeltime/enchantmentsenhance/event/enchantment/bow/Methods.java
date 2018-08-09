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

package org.pixeltime.enchantmentsenhance.event.enchantment.bow;


import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.List;
import java.util.Random;

public class Methods {
    public static List<Entity> getNearbyEntitiess(Location loc, double radius, Entity ent) {
        return ent.getNearbyEntities(radius, radius, radius);
    }
    public static boolean randomPicker(int max) {
        Random number = new Random();
        if(max <= 0) {
            return true;
        }
        int chance = 1 + number.nextInt(max);
        return chance == 1;
    }

    public static void explode(Entity player, Entity arrow) {
        ParticleEffect.FLAME.display(0, 0, 0, 1, 200, arrow.getLocation(), 100);
        ParticleEffect.CLOUD.display(.4F, .5F, .4F, 1, 30, arrow.getLocation(), 100);
        ParticleEffect.EXPLOSION_HUGE.display(0, 0, 0, 0, 2, arrow.getLocation(), 100);
        for (Entity e : Methods.getNearbyEntitiess(arrow.getLocation(), 3D, arrow)) {
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
                return;
            }
            if (e.getType() == EntityType.DROPPED_ITEM) {
                e.remove();
            } else {
                if (e instanceof LivingEntity) {
                    LivingEntity en = (LivingEntity) e;
                    if (!player.getName().equalsIgnoreCase(e.getName())) {
                        en.damage(5D);
                        en.setVelocity(en.getLocation().toVector().subtract(arrow.getLocation().toVector()).normalize().multiply(1).setY(.5));
                    }
                }
            }
        }
    }
}