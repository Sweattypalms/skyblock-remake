package com.sweattypalms.skyblock.ui.guis;

import com.sweattypalms.skyblock.ui.BaseGUI;
import com.sweattypalms.skyblock.ui.ClickableItem;
import com.sweattypalms.skyblock.ui.GUIRouter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class BinGUI extends BaseGUI {

    private static final int SIZE = 9 * 6; // 6 rows of 9 slots

    // empty slots
    private static final int[] NON_EMPTY_SLOTS = {
        0, 1, 2, 3, 4, 5, 6, 7, 8,
        9,                              17,
        18,                             26,
        27,                             35,
        36,                             44,
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    public BinGUI() {
        super(SIZE, "Trash");
    }

    @Override
    public void initializeItems(Player player) {
        this.fillBorder(BorderType.ALL);
        this.setItemAt(5,6, getClearTrashButton());
    }

    private ClickableItem getClearTrashButton() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RED + "Clear Trash");

        item.setItemMeta(meta);

        return new ClickableItem(item, (p) -> {
            this.clearInventory();
        });
    }

    private void clearInventory() {
        Set<Integer> nonEmptySlotsSet = new HashSet<>();
        for (int slot : NON_EMPTY_SLOTS) {
            nonEmptySlotsSet.add(slot);
        }

        for (int i = 0; i < SIZE; i++) {
            if (!nonEmptySlotsSet.contains(i)) {
                this.getInventory().clear(i);
            }
        }
    }

}
