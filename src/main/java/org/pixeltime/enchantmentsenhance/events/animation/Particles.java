package org.pixeltime.enchantmentsenhance.events.animation;


import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Particles {
    public static void playParticle(final EnumParticle type, final Location loc, final float xOffset, final float yOffset, final float zOffset, final float speed, final int count) {
        final float x = (float) loc.getX();
        final float y = (float) loc.getY();
        final float z = (float) loc.getZ();
        final PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, x, y, z, xOffset, yOffset, zOffset, speed, count, (int[]) null);
        for (final Player player : Bukkit.getServer().getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket((Packet) packet);
        }
    }
}
