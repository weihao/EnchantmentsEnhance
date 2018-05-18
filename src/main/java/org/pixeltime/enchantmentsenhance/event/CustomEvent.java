package org.pixeltime.enchantmentsenhance.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class CustomEvent extends BlockBreakEvent {
    public CustomEvent(final Block block, final Player player) {
        super(block, player);
    }
}
