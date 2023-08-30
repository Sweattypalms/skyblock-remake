package com.sweattypalms.skyblock.commands.handlers;

import com.sweattypalms.skyblock.commands.Command;
import com.sweattypalms.skyblock.commands.TabCompleter;
import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.reforges.Reforge;
import com.sweattypalms.skyblock.core.items.builder.reforges.ReforgeManager;
import com.sweattypalms.skyblock.core.mobs.builder.MobManager;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.Stats;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AdminCommands {
    @Command(name = "mob", description = "Mob command", op = true)
    public void mobCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /mob id");
        }
        String id = args[0].toLowerCase();

        SkyblockMob skyblockMob;
        try {
            skyblockMob = MobManager.getInstance(id);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + e.getMessage());
            return;
        }

        skyblockMob.spawn(player.getLocation());
        player.sendMessage(ChatColor.RED + "Successfully spawned: " + skyblockMob.getId());
    }

    @Command(name = "item", description = "Item command", op = true)
    public void itemCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /item id");
        }
        String id = args[0].toLowerCase();
        if (!ItemManager.ITEMS_LIST.containsKey(id)) {
            player.sendMessage(ChatColor.RED + "Invalid item ID!");
            return;
        }

        SkyblockItem skyblockItem = ItemManager.ITEMS_LIST.get(id);
        ItemStack itemStack = skyblockItem.toItemStack();

        int amount = 1;
        if (args.length > 1) {
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "Invalid amount!");
                return;
            }
        }
        for (int i = 0; i < amount; i++) {
            PDCHelper.setString(itemStack, "uuid", java.util.UUID.randomUUID().toString());
            player.getInventory().addItem(itemStack);
        }
    }

    @TabCompleter(command = "item")
    public List<String> itemTabCompleter(Player player, String[] args){
        // Smart tab completer to only serve the correct part :)
        if(args.length > 1){
            return List.of();
        }

        if(args.length == 0){
            return ItemManager.ITEMS_LIST.keySet().stream().toList();
        }

        String id = args[0].toLowerCase();
        return ItemManager.ITEMS_LIST.keySet().stream().filter(s -> s.startsWith(id)).toList();
    }



    @Command(name = "stat", description = "Stat command", op = true)
    public void statCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /stat stat value");
        }
        Stats stat;
        double value = 0;
        try {
            stat = Stats.valueOf(args[0].toUpperCase());
            value = Double.parseDouble(args[1]);
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "Invalid stat or value!");
            return;
        }

        player.sendMessage(ChatColor.GREEN + "Setting " + stat + " to " + value);

        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        skyblockPlayer.getStatsManager().setBaseStat(stat, value);
    }


    @Command(name = "upgrade", description = "Upgrade command", op = true)
    public void upgradeCommand(Player player, String[] args) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (SkyblockItem.fromItemStack(item) == null) {
            player.sendMessage(ChatColor.RED + "You must be holding a skyblock item!");
            return;
        }

        SkyblockItem skyblockItem = SkyblockItem.fromItemStack(item);

        PDCHelper.setString(item, "rarity", skyblockItem.getRarity().getUpgraded().name());
        ItemStack updatedItemStack = SkyblockItem.updateItemStack(item);
        player.getInventory().setItemInMainHand(updatedItemStack);
    }

    @Command(name = "reforge", description = "Reforge command", op = true)
    public void reforgeCommand(Player player, String[] args) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (SkyblockItem.fromItemStack(item) == null) {
            player.sendMessage(ChatColor.RED + "You must be holding a skyblock item!");
            return;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /reforge reforge");
            return;
        }

        Reforge reforge = ReforgeManager.getReforge(args[0].toLowerCase());
        if (reforge == null) {
            player.sendMessage(ChatColor.RED + "Invalid reforge!");
            return;
        }

        SkyblockItem skyblockItem = SkyblockItem.fromItemStack(item);

        PDCHelper.setString(item, "reforge", reforge.getName().toLowerCase());
        ItemStack updatedItemStack = SkyblockItem.updateItemStack(item);
        player.getInventory().setItemInMainHand(updatedItemStack);

    }

}
