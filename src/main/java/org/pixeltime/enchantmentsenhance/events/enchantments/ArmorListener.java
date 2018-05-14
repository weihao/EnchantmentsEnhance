package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dispenser;
import org.pixeltime.enchantmentsenhance.util.enums.ArmorType;

import java.util.Iterator;
import java.util.List;

public class ArmorListener implements Listener {
    private final List<String> blockedMaterials;

    public ArmorListener(final List<String> blockedMaterials) {
        this.blockedMaterials = blockedMaterials;
    }

    @EventHandler
    public final void onInventoryClick(final InventoryClickEvent inventoryClickEvent) {
        try {
            boolean b = false;
            boolean b2 = false;
            if (inventoryClickEvent.isCancelled()) {
                return;
            }
            if (inventoryClickEvent.getClick().equals(ClickType.SHIFT_LEFT) || inventoryClickEvent.getClick().equals(ClickType.SHIFT_RIGHT)) {
                b = true;
            }
            if (inventoryClickEvent.getClick().equals(ClickType.NUMBER_KEY)) {
                b2 = true;
            }
            if ((inventoryClickEvent.getSlotType() != InventoryType.SlotType.ARMOR || inventoryClickEvent.getSlotType() != InventoryType.SlotType.QUICKBAR) && !inventoryClickEvent.getInventory().getType().equals(InventoryType.CRAFTING)) {
                return;
            }
            if (!(inventoryClickEvent.getWhoClicked() instanceof Player)) {
                return;
            }
            if (inventoryClickEvent.getCurrentItem() == null) {
                return;
            }
            ArmorType armorType = ArmorType.matchType(b ? inventoryClickEvent.getCurrentItem() : inventoryClickEvent.getCursor());
            if (!b && armorType != null && inventoryClickEvent.getRawSlot() != armorType.getSlot()) {
                return;
            }
            if (b) {
                final ArmorType matchType = ArmorType.matchType(inventoryClickEvent.getCurrentItem());
                if (matchType != null) {
                    boolean b3 = true;
                    if (inventoryClickEvent.getRawSlot() == matchType.getSlot()) {
                        b3 = false;
                    }
                    Label_0403:
                    {
                        if (matchType.equals(ArmorType.HELMET)) {
                            if (b3) {
                                if (inventoryClickEvent.getWhoClicked().getInventory().getHelmet() == null) {
                                    break Label_0403;
                                }
                            } else if (inventoryClickEvent.getWhoClicked().getInventory().getHelmet() != null) {
                                break Label_0403;
                            }
                        }
                        if (matchType.equals(ArmorType.CHESTPLATE)) {
                            if (b3) {
                                if (inventoryClickEvent.getWhoClicked().getInventory().getChestplate() == null) {
                                    break Label_0403;
                                }
                            } else if (inventoryClickEvent.getWhoClicked().getInventory().getChestplate() != null) {
                                break Label_0403;
                            }
                        }
                        if (matchType.equals(ArmorType.LEGGINGS)) {
                            if (b3) {
                                if (inventoryClickEvent.getWhoClicked().getInventory().getLeggings() == null) {
                                    break Label_0403;
                                }
                            } else if (inventoryClickEvent.getWhoClicked().getInventory().getLeggings() != null) {
                                break Label_0403;
                            }
                        }
                        if (!matchType.equals(ArmorType.BOOTS)) {
                            return;
                        }
                        if (b3) {
                            if (inventoryClickEvent.getWhoClicked().getInventory().getBoots() != null) {
                                return;
                            }
                        } else if (inventoryClickEvent.getWhoClicked().getInventory().getBoots() == null) {
                            return;
                        }
                    }
                    final ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent((Player) inventoryClickEvent.getWhoClicked(), ArmorEquipEvent.EquipMethod.SHIFT_CLICK, matchType, b3 ? null : inventoryClickEvent.getCurrentItem(), b3 ? inventoryClickEvent.getCurrentItem() : null);
                    Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
                    if (armorEquipEvent.isCancelled()) {
                        inventoryClickEvent.setCancelled(true);
                    }
                }
            } else {
                ItemStack cursor = inventoryClickEvent.getCursor();
                ItemStack itemStack = inventoryClickEvent.getCurrentItem();
                if (b2) {
                    if (inventoryClickEvent.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                        final ItemStack item = inventoryClickEvent.getClickedInventory().getItem(inventoryClickEvent.getHotbarButton());
                        if (item != null) {
                            armorType = ArmorType.matchType(item);
                            cursor = item;
                            itemStack = inventoryClickEvent.getClickedInventory().getItem(inventoryClickEvent.getSlot());
                        } else {
                            armorType = ArmorType.matchType((inventoryClickEvent.getCurrentItem() != null && inventoryClickEvent.getCurrentItem().getType() != Material.AIR) ? inventoryClickEvent.getCurrentItem() : inventoryClickEvent.getCursor());
                        }
                    }
                } else {
                    armorType = ArmorType.matchType((inventoryClickEvent.getCurrentItem() != null && inventoryClickEvent.getCurrentItem().getType() != Material.AIR) ? inventoryClickEvent.getCurrentItem() : inventoryClickEvent.getCursor());
                }
                if (armorType != null && inventoryClickEvent.getRawSlot() == armorType.getSlot()) {
                    ArmorEquipEvent.EquipMethod equipMethod = ArmorEquipEvent.EquipMethod.DRAG;
                    if (inventoryClickEvent.getAction().equals(InventoryAction.HOTBAR_SWAP) || b2) {
                        equipMethod = ArmorEquipEvent.EquipMethod.HOTBAR_SWAP;
                    }
                    final ArmorEquipEvent armorEquipEvent2 = new ArmorEquipEvent((Player) inventoryClickEvent.getWhoClicked(), equipMethod, armorType, itemStack, cursor);
                    Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent2);
                    if (armorEquipEvent2.isCancelled()) {
                        inventoryClickEvent.setCancelled(true);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    @EventHandler
    public void playerInteractEvent(final PlayerInteractEvent playerInteractEvent) {
        try {
            if (playerInteractEvent.getPlayer().getItemInHand() == null) {
                return;
            }
            if (playerInteractEvent.getAction() == Action.RIGHT_CLICK_AIR || playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
                final Player player = playerInteractEvent.getPlayer();
                if (playerInteractEvent.getClickedBlock() != null && playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    final Material type = playerInteractEvent.getClickedBlock().getType();
                    final Iterator<String> iterator = this.blockedMaterials.iterator();
                    while (iterator.hasNext()) {
                        if (type.name().equalsIgnoreCase(iterator.next())) {
                            return;
                        }
                    }
                }
                final ArmorType matchType = ArmorType.matchType(playerInteractEvent.getItem());
                if (matchType != null && ((matchType.equals(ArmorType.HELMET) && playerInteractEvent.getPlayer().getInventory().getHelmet() == null) || (matchType.equals(ArmorType.CHESTPLATE) && playerInteractEvent.getPlayer().getInventory().getChestplate() == null) || (matchType.equals(ArmorType.LEGGINGS) && playerInteractEvent.getPlayer().getInventory().getLeggings() == null) || (matchType.equals(ArmorType.BOOTS) && playerInteractEvent.getPlayer().getInventory().getBoots() == null))) {
                    final ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(playerInteractEvent.getPlayer(), ArmorEquipEvent.EquipMethod.HOTBAR, ArmorType.matchType(playerInteractEvent.getItem()), null, playerInteractEvent.getItem());
                    Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
                    if (armorEquipEvent.isCancelled()) {
                        playerInteractEvent.setCancelled(true);
                        player.updateInventory();
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    @EventHandler
    public void dispenserFireEvent(final BlockDispenseEvent blockDispenseEvent) {
        try {
            final ArmorType matchType = ArmorType.matchType(blockDispenseEvent.getItem());
            if (ArmorType.matchType(blockDispenseEvent.getItem()) != null) {
                final Location location = blockDispenseEvent.getBlock().getLocation();
                for (final Player player : location.getWorld().getPlayers()) {
                    if (location.getBlockY() - player.getLocation().getBlockY() >= -1 && location.getBlockY() - player.getLocation().getBlockY() <= 1 && ((player.getInventory().getHelmet() == null && matchType.equals(ArmorType.HELMET)) || (player.getInventory().getChestplate() == null && matchType.equals(ArmorType.CHESTPLATE)) || (player.getInventory().getLeggings() == null && matchType.equals(ArmorType.LEGGINGS)) || (player.getInventory().getBoots() == null && matchType.equals(ArmorType.BOOTS)))) {
                        final BlockFace facing = ((Dispenser) blockDispenseEvent.getBlock().getState().getData()).getFacing();
                        if ((facing == BlockFace.EAST && player.getLocation().getBlockX() != location.getBlockX() && player.getLocation().getX() <= location.getX() + 2.3 && player.getLocation().getX() >= location.getX()) || (facing == BlockFace.WEST && player.getLocation().getX() >= location.getX() - 1.3 && player.getLocation().getX() <= location.getX()) || (facing == BlockFace.SOUTH && player.getLocation().getBlockZ() != location.getBlockZ() && player.getLocation().getZ() <= location.getZ() + 2.3 && player.getLocation().getZ() >= location.getZ()) || (facing == BlockFace.NORTH && player.getLocation().getZ() >= location.getZ() - 1.3 && player.getLocation().getZ() <= location.getZ())) {
                            final ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(player, ArmorEquipEvent.EquipMethod.DISPENSER, ArmorType.matchType(blockDispenseEvent.getItem()), null, blockDispenseEvent.getItem());
                            Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
                            if (armorEquipEvent.isCancelled()) {
                                blockDispenseEvent.setCancelled(true);
                            }
                            return;
                        }
                        continue;
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    @EventHandler
    public void itemBreakEvent(final PlayerItemBreakEvent playerItemBreakEvent) {
        try {
            final ArmorType matchType = ArmorType.matchType(playerItemBreakEvent.getBrokenItem());
            if (matchType != null) {
                final Player player = playerItemBreakEvent.getPlayer();
                final ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(player, ArmorEquipEvent.EquipMethod.BROKE, matchType, playerItemBreakEvent.getBrokenItem(), null);
                Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
                if (armorEquipEvent.isCancelled()) {
                    final ItemStack clone = playerItemBreakEvent.getBrokenItem().clone();
                    clone.setAmount(1);
                    clone.setDurability((short) (clone.getDurability() - 1));
                    if (matchType.equals(ArmorType.HELMET)) {
                        player.getInventory().setHelmet(clone);
                    } else if (matchType.equals(ArmorType.CHESTPLATE)) {
                        player.getInventory().setChestplate(clone);
                    } else if (matchType.equals(ArmorType.LEGGINGS)) {
                        player.getInventory().setLeggings(clone);
                    } else if (matchType.equals(ArmorType.BOOTS)) {
                        player.getInventory().setBoots(clone);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    @EventHandler
    public void playerDeathEvent(final PlayerDeathEvent playerDeathEvent) {
        try {
            final Player entity = playerDeathEvent.getEntity();
            ItemStack[] armorContents = entity.getInventory().getArmorContents();
            for (int length = armorContents.length, i = 0; i < length; ++i) {
                final ItemStack itemStack = armorContents[i];
                if (itemStack != null && !itemStack.getType().equals(Material.AIR)) {
                    Bukkit.getServer().getPluginManager().callEvent(new ArmorEquipEvent(entity, ArmorEquipEvent.EquipMethod.DEATH, ArmorType.matchType(itemStack), itemStack, null));
                }
            }
        } catch (Exception ex) {
        }
    }
}
