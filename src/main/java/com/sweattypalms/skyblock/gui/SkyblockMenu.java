package com.sweattypalms.skyblock.gui;

import com.sweattypalms.skyblock.utils.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SkyblockMenu extends GUI{


    public SkyblockMenu() {
        super("SkyBlock Menu", 54);

    }

    @Override
    public void onOpen(GUIOpenEvent e) {
        fill(BLACK_STAINED_GLASS_PANE);
        Player player = e.getPlayer();
         set(new GUIItem() {
             @Override
             public int getSlot() {
                 return 13;
             }

             @Override
             public ItemStack getItem() {
                 return ItemUtil.getSkullStack(ChatColor.GREEN + "Your SkyBlock Profile", player.getName(), 1,
                         ChatColor.RED + "  ❤ Health " + ChatColor.WHITE + 100 + " HP",
                         ChatColor.GREEN + "  ❈ Defense " + ChatColor.WHITE + 0,
                         ChatColor.RED + "  ❁ Strength " + ChatColor.WHITE + 0,
                         ChatColor.WHITE + "  ✦ Speed " + 100,
                         ChatColor.BLUE + "  ☣ Crit Chance " + ChatColor.WHITE + 30 + "%",
                         ChatColor.BLUE + "  ☠ Crit Damage " + ChatColor.WHITE + 50 + "%",
                         ChatColor.AQUA + "  ✎ Intelligence " + ChatColor.WHITE + 100,
                         " ",
                         ChatColor.YELLOW + "Click to view your profile!");
             }


             @Override
             public boolean canPickup() {
                 return false;
             }

             @Override
             public void run(InventoryClickEvent e) {
                 // open profile gui

             }
         });
         set(new GUIItem() {
             @Override
             public int getSlot() {
                 return 19;
             }

             @Override
             public ItemStack getItem() {
                     return ItemUtil.getStack(ChatColor.GREEN + "Your Skills", Material.DIAMOND_SWORD, (short) 0, 1,
                             ChatColor.GRAY + "View your Skill progression and",
                             ChatColor.GRAY + "rewards.",
                             " ",
                             ChatColor.YELLOW + "Click to view!");
                 }


             @Override
             public boolean canPickup() {
                 return false;
             }

             @Override
             public void run(InventoryClickEvent e) {

             }
         });

       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 20;
           }

           @Override
           public ItemStack getItem() {
               return ItemUtil.getStack(ChatColor.GREEN + "Collection", Material.PAINTING, (short) 0, 1,
                       ChatColor.GRAY + "View all of the items available",
                       ChatColor.GRAY + "in SkyBlock. Collect more of an",
                       ChatColor.GRAY + "item to unlock rewards on your",
                       ChatColor.GRAY + "way to becoming a master of",
                       ChatColor.GRAY + "SkyBlock!",
                       " ",
                       ChatColor.YELLOW + "Click to view!");
           }

           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {

           }
       });

       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 21;
           }

           @Override
           public ItemStack getItem() {
               return ItemUtil.getStack(ChatColor.GREEN + "Recipe Book", Material.BOOK, (short) 0, 1,
                       ChatColor.GRAY + "Through your adventure, you will",
                       ChatColor.GRAY + "unlock recipes for all kinds of",
                       ChatColor.GRAY + "special items! You can view how",
                       ChatColor.GRAY + "to craft these items here",
                       "",
                       ChatColor.YELLOW + "Click to view!");
           }

           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {

           }
       });

       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 22;
           }

           @Override
           public ItemStack getItem() {
               return ItemUtil.getStack(ChatColor.GREEN + "Trade", Material.EMERALD, (short) 0, 1,
                       "",
                       ChatColor.YELLOW + "Click to view!");
           }

           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {

           }
       });

       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 23;
           }

           @Override
           public ItemStack getItem() {
               return ItemUtil.getStack(ChatColor.GREEN + "Quest Log", Material.WRITTEN_BOOK, (short) 0, 1,
                       ChatColor.GRAY + "View your active quests,",
                       ChatColor.GRAY + "progress, and rewards.",
                       "",
                       ChatColor.YELLOW + "Click to view!");
           };


           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {

           }
       });

       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 24;
           }

           @Override
           public ItemStack getItem() {
               return ItemUtil.getStack(ChatColor.GREEN + "Calendar and Event", Material.CLOCK, (short) 0, 1,
                       ChatColor.GRAY + "View the SkyBlock Calendar,",
                       ChatColor.GRAY + "upcoming events, and event",
                       ChatColor.GRAY + "rewards!",
                       "",
                       ChatColor.GRAY + "Next Event: " + ChatColor.YELLOW + "N/A",
                       ChatColor.GRAY + "Starting in: " + ChatColor.YELLOW + "N/A",
                       "",
                       ChatColor.YELLOW + "Click to view!");
           }

           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {

           }
       });

       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 25;
           }

           @Override
           public ItemStack getItem() {
               return ItemUtil.getStack(ChatColor.GREEN + "Ender Chest", Material.ENDER_CHEST,
                       (short) 0, 1,
                       ChatColor.GRAY + "Store global items that you want",
                       ChatColor.GRAY + "to access at any time from",
                       ChatColor.GRAY + "anywhere here.",
                       " ",
                       ChatColor.YELLOW + "Click to open!");
           }

           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {
               e.getWhoClicked().openInventory(e.getWhoClicked().getEnderChest());

           }
       });

       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 29;
           }

           @Override
           public ItemStack getItem() {
               return ItemUtil.getStack(ChatColor.GREEN + "Active Effects", Material.POTION, (short) 0, 1,
                       ChatColor.GRAY + "View and manage all of your",
                       ChatColor.GRAY + "active potion effects.",
                       " ",
                       ChatColor.GRAY + "Drink Potions or splash them",
                       ChatColor.GRAY + "on the ground to buff yourself!",
                       " ",
                       ChatColor.GRAY + "Currently Active: " + ChatColor.YELLOW + "null");
           }


           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {

           }
       });

       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 30;
           }

           @Override
           public ItemStack getItem() {
               return ItemUtil.getStack(ChatColor.GREEN + "Pets", Material.BONE, (short) 0, 1,
                       ChatColor.GRAY + "View and manage all of your",
                       ChatColor.GRAY + "Pets.",
                       " ",
                       ChatColor.GRAY + "Level up your pets faster by",
                       ChatColor.GRAY + "gaining XP in their favorite",
                       ChatColor.GRAY + "skill!",
                       " ",
                       ChatColor.GRAY + "Selected pet: " + "None",
                       " ",
                       ChatColor.YELLOW + "Click to view!");
           }

           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {

           }
       });

       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 31;
           }

           @Override
           public ItemStack getItem() {
              return ItemUtil.getStack(ChatColor.GREEN + "Crafting Table", Material.CRAFTING_TABLE, (short) 0, 1,
                       ChatColor.GRAY + "Opens the crafting grid.",
                       " ",
                       ChatColor.YELLOW + "Click to open!");
           }

           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {
               e.getWhoClicked().openWorkbench(null, true);

           }
       });


       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 32;
           }

           @Override
           public ItemStack getItem() {
               return ItemUtil.getStack(ChatColor.GREEN + "Wardrobe", Material.LEATHER_CHESTPLATE, (short) 0, 1,
                       ChatColor.GRAY + "Store armor sets and quickly",
                       ChatColor.GRAY + "swap between them!",
                       " ",
                       ChatColor.YELLOW + "Click to open!");
           }

           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {

           }
       });

       set(new GUIItem() {
           @Override
           public int getSlot() {
               return 33;
           }

           @Override
           public ItemStack getItem() {
               return ItemUtil.getStack(ChatColor.GREEN + "Personal Bank", Material.PLAYER_HEAD ,(short) 0, 1,
                       ChatColor.GRAY + "Contact your banker from",
                       ChatColor.GRAY + "anywhere.",
                       ChatColor.GRAY + "Cooldown:" + ChatColor.YELLOW + " Time",
                       " ",
                       ChatColor.GRAY + "Banker Status:",
                       ChatColor.GREEN + "Availabe");
           }


           @Override
           public boolean canPickup() {
               return false;
           }

           @Override
           public void run(InventoryClickEvent e) {

           }
       });



    }
}
