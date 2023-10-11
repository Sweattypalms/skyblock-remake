package com.sweattypalms.skyblock.ui.guis;

import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.ui.BaseGUI;
import com.sweattypalms.skyblock.ui.ClickableItem;
import org.bukkit.entity.Player;

import java.util.*;

import static com.sweattypalms.skyblock.core.items.ItemManager.ITEMS_LIST;

public class ItemsGUI extends BaseGUI {

    private static final int SIZE = 6 * 9; // 6 rows of 9 slots

    public ItemsGUI() {
        super(SIZE, "Items GUI");
    }

    @Override
    public void initializeItems(Player player) {
        List<SkyblockItem> sortedItemList = getSkyblockItems();

        for (SkyblockItem item : sortedItemList) {
            setNextItem(new ClickableItem(item.toItemStack(), (p) -> {
                p.sendMessage("You clicked the " + item.__getDisplayName() + "!");
                p.getInventory().addItem(item.toItemStack());
            }));
        }
    }

    private static List<SkyblockItem> getSkyblockItems() {
        Comparator<SkyblockItem> itemComparator = (item1, item2) -> {
            String setName1 = getFirstWord(item1.getDisplayName());
            String setName2 = getFirstWord(item2.getDisplayName());

            // Compare by armor set name
            int setNameComparison = setName1.compareTo(setName2);
            if (setNameComparison != 0) {
                return setNameComparison;
            }

            // If they are from the same armor set, compare by type ordinal
            int typeComparison = item1.getItemType().ordinal() - item2.getItemType().ordinal();
            if (typeComparison != 0) {
                return typeComparison;
            }
            // If types are the same, compare alphabetically by full name
            return item1.getDisplayName().compareTo(item2.getDisplayName());
        };


        List<SkyblockItem> sortedItemList = new ArrayList<>(ITEMS_LIST.values());
        sortedItemList.sort(itemComparator);
        return sortedItemList;
    }

    public static String getFirstWord(String itemName) {
        String[] parts = itemName.split(" ");

        return parts.length > 0 ? parts[0] : itemName;
    }
}
