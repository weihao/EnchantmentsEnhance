package org.pixeltime.enchantmentsenhance.event.animation;

import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.util.Sounds;

public class OpenChest extends BukkitRunnable {
    public int counter;
    public Location loc;
    public ArmorStand open;
    public int taskId;
    public Location l;
    Main pl;
    Player p;


    public OpenChest(
            final ArmorStand open,
            final Player p,
            final Main pl,
            final Location l) {
        this.counter = 1;
        this.open = open;
        this.p = p;
        this.pl = pl;
        this.l = l;
    }

    public static Location lookAt(Location q, final Location lookat) {
        q = q.clone();
        final double dx = lookat.getX() - q.getX();
        final double dz = lookat.getZ() - q.getZ();
        if (dx != 0.0) {
            if (dx < 0.0) {
                q.setYaw(4.712389f);
            } else {
                q.setYaw(1.5707964f);
            }
            q.setYaw(q.getYaw() - (float) Math.atan(dz / dx));
        } else if (dz < 0.0) {
            q.setYaw(3.1415927f);
        }
        q.setYaw(-q.getYaw() * 180.0f / 3.1415927f);
        return q;
    }

    public void run() {
        if (this.counter == 1) {
            this.loc = this.open.getLocation();
            this.loc.setYaw(Math.abs(this.p.getLocation().getYaw() + 90.0f));
            this.open.teleport(this.loc);
        }
        ++this.counter;
        if (this.counter == 51) {
            this.open.setCustomNameVisible(false);
        }
        if (this.counter <= 50) {
            this.open.setCustomNameVisible(true);
            this.loc.setYaw(this.loc.getYaw() + 10.955f);
            this.loc.setY(this.loc.getY() + 0.05);
            this.open.teleport(this.loc);
            this.p.getWorld().playEffect(this.open.getEyeLocation().add(-0.0,
                    -0.3, -0.0), Effect.HAPPY_VILLAGER, 10);
        }
        if (this.counter > 46 && this.counter <= 50) {
            this.p.getWorld().playEffect(this.open.getEyeLocation().add(0.0,
                    0.0, 0.0), Effect.ENDER_SIGNAL, 50);
        }
        if (this.counter >= 50) {
            if (this.counter == 50) {
                this.open.teleport(lookAt(this.loc, this.p.getLocation()));
            }
            if (this.counter >= 51) {
                this.open.teleport(lookAt(this.loc, this.p.getLocation()));
            }
        }
        if (this.counter >= 90 && this.counter <= 95) {
            if (this.counter == 90) {
                this.p.getWorld().playSound(this.open.getEyeLocation(),
                        Sounds.EXPLODE.bukkitSound(), 1.0f, 1.0f);
            }
            Particles.playParticle(EnumParticle.EXPLOSION_HUGE, this.open
                    .getEyeLocation(), 0.0f, 0.0f, 0.0f, 0.25f, 5);
        }
        if (this.counter == 130) {
            this.p.getWorld().playSound(this.p.getLocation(),
                    Sounds.CHICKEN_EGG_POP.bukkitSound(), 1.0f, 1.0f);
            this.open.setCustomNameVisible(true);
        }
        if (this.counter == 225) {
            this.open.remove();
            Bukkit.getScheduler().cancelTask(this.taskId);
        }
    }
}
