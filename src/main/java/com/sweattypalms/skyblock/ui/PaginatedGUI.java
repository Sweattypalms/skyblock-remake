package com.sweattypalms.skyblock.ui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public abstract class PaginatedGUI extends BaseGUI {

    protected int currentPage = 0;
    protected List<Inventory> pages = new ArrayList<>();

    public PaginatedGUI(int size, String title) {
        super(size, title);
    }

//    public void initializePages(List<ClickableItem> items) {
//        int totalPages = (int) Math.ceil((double) items.size() / (SIZE - 9));  // Deduct a row for navigation
//
//        for (int i = 0; i < totalPages; i++) {
//            Inventory page = Bukkit.createInventory(this, SIZE, getTitle() + " - Page " + (i + 1));
//
//            int startIndex = i * (SIZE - 9);
//            int endIndex = Math.min(startIndex + (SIZE - 9), items.size());
//            for (int j = startIndex; j < endIndex; j++) {
//                page.setItem(j % (SIZE - 9), items.get(j).getItemStack());
//            }
//
//            // Navigation buttons
//            if (i > 0) {
//                page.setItem(SIZE - 6, BACK_BUTTON().getItemStack());
//            }
//            if (i < totalPages - 1) {
//                page.setItem(SIZE - 4, NEXT_PAGE(player -> {
//                    currentPage++;
//                    open(player);
//                }).getItemStack());
//            }
//
//            pages.add(page);
//        }
//    }

    @Override
    public void open(Player player) {
        player.openInventory(pages.get(currentPage));
    }

    public abstract List<ClickableItem> getPaginatedItems();
}
