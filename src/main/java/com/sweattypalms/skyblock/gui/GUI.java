package com.sweattypalms.skyblock.gui;

import com.sweattypalms.skyblock.SkyBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;


public abstract class GUI implements Listener{
    public static final ItemStack BLACK_STAINED_GLASS_PANE = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    public static final ItemStack RED_STAINED_GLASS_PANE = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    public static final ItemStack LIME_STAINED_GLASS_PANE = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
    public static final Map<UUID, GUI> GUI_MAP = new HashMap<>();



    protected String title;
    protected int size;
    protected List<GUIItem> items;

    public GUI(String title, int size) {
        this.title = title;
        this.size = size;
        this.items = new ArrayList<>();
    }

    public GUI(String title) {
        this(title, 27);
    }

    public void set(GUIItem item) {
        items.removeIf(i -> i.getSlot() == item.getSlot());
        items.add(item);
    }

    public void set(int slot, ItemStack stack, boolean pickup) {
        if (stack == null) {
            items.removeIf(i -> i.getSlot() == slot);
            return;
        }
        set(new GUIItem() {
            @Override
            public int getSlot() {
                return slot;
            }

            @Override
            public ItemStack getItem() {
                return stack;
            }

            @Override
            public boolean canPickup() {
                return pickup;
            }

            @Override
            public void run(InventoryClickEvent e) {
            }
        });
    }

    public void set(int slot, ItemStack stack) {
        set(slot, stack, false);
    }

    public GUIItem get(int slot) {
        for (GUIItem item : items) {
            if (item.getSlot() == slot)
                return item;
        }
        return null;
    }
    public String getTitle(){
        return title;
    }


    public void fill(ItemStack stack, int cornerSlot, int cornerSlot2, boolean overwrite, boolean pickup) {
        int topLeft = Math.min(cornerSlot, cornerSlot2);
        int bottomRight = Math.max(cornerSlot, cornerSlot2);
        int topRight;
        for (topRight = bottomRight; topRight > topLeft; topRight -= 9) ;
        int bottomLeft;
        for (bottomLeft = topLeft; bottomLeft < bottomRight; bottomLeft += 9) ;
        topRight += 9;
        bottomLeft -= 9;
        for (int y = topLeft; y <= bottomLeft; y += 9) {
            for (int x = y; x <= topRight - topLeft + y; x++) {
                int f = x;
                if (items.stream().filter(item -> item.getSlot() == f).toArray().length != 0 && !overwrite) continue;
                set(x, stack, pickup);
            }
        }
    }

    public void fill(ItemStack stack, int cornerSlot, int cornerSlot2, boolean pickup) {
        fill(stack, cornerSlot, cornerSlot2, true, pickup);
    }

    public void fill(ItemStack stack, int cornerSlot, int cornerSlot2) {
        fill(stack, cornerSlot, cornerSlot2, false);
    }

    public void fill(ItemStack stack) {
        fill(stack, 0, size - 1);
    }

    public void fill(Material material) {
        fill(new ItemStack(material));
    }

    public void border(ItemStack stack, int cornerSlot, int cornerSlot2, boolean overwrite, boolean pickup) {
        if (cornerSlot < 0 || cornerSlot > size)
            throw new IllegalArgumentException("Corner 1 of the border described is out of bounds");
        if (cornerSlot2 < 0 || cornerSlot2 > size)
            throw new IllegalArgumentException("Corner 2 of the border described is out of bounds");
        int topLeft = Math.min(cornerSlot, cornerSlot2);
        int bottomRight = Math.max(cornerSlot, cornerSlot2);
        int topRight;
        for (topRight = bottomRight; topRight > topLeft; topRight -= 9) ;
        int bottomLeft;
        for (bottomLeft = topLeft; bottomLeft < bottomRight; bottomLeft += 9) ;
        topRight += 9;
        bottomLeft -= 9;
        for (int y = topLeft; y <= bottomLeft; y += 9) {
            for (int x = y; x <= topRight - topLeft + y; x++) {
                int f = x;
                if (items.stream().filter(item -> item.getSlot() == f).toArray().length != 0 && !overwrite) continue;
                if (y == topLeft || y == bottomLeft || x == topLeft || x == topRight) {
                    set(x, stack, pickup);
                }
            }
        }
    }

    public void border(ItemStack stack, int cornerSlot, int cornerSlot2, boolean pickup) {
        border(stack, cornerSlot, cornerSlot2, true, pickup);
    }

    public void border(ItemStack stack, int cornerSlot, int cornerSlot2) {
        border(stack, cornerSlot, cornerSlot2, false);
    }

    public void border(Material material, int cornerSlot, int cornerSlot2, boolean overwrite, boolean pickup) {
        border(new ItemStack(material), cornerSlot, cornerSlot2, overwrite, pickup);
    }

    public void border(Material material, int cornerSlot, int cornerSlot2, boolean pickup) {
        border(material, cornerSlot, cornerSlot2, false, pickup);
    }

    public void border(Material material, int cornerSlot, int cornerSlot2) {
        border(material, cornerSlot, cornerSlot2, false);
    }

    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(player, size, ChatColor.translateAlternateColorCodes('&', title));
        GUIOpenEvent openEvent = new GUIOpenEvent(player, this, inventory);
        SkyBlock.getInstance().getServer().getPluginManager().callEvent(openEvent);
        for (GUIItem item : items) {
            inventory.setItem(item.getSlot(), item.getItem());
        }
        player.openInventory(inventory);
        GUI_MAP.remove(player.getUniqueId());
        GUI_MAP.put(player.getUniqueId(), this);
    }
    public void onOpen(GUIOpenEvent e) {
    }

    public void close(Player player) {
        GUI_MAP.remove(player.getUniqueId());
        player.closeInventory();
    }


    public interface GUIItem {
        int getSlot();

        ItemStack getItem();

        boolean canPickup();

        void run(InventoryClickEvent e);
    }
}


