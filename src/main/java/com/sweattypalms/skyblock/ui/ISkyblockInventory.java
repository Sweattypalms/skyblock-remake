package com.sweattypalms.skyblock.ui;

import org.bukkit.inventory.InventoryHolder;

public interface ISkyblockInventory extends InventoryHolder {
    ClickableItem getItemAt(int slot);
}
