package com.sweattypalms.skyblock.commands;

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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.Arrays;

public class MainCommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(@Nullable CommandSender commandSender,
                             @Nullable Command command,
                             @Nullable String s,
                             @Nullable String[] args) {

        if (commandSender == null || command == null || s == null || args == null) return false;

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }
        player.sendMessage(ChatColor.GREEN + "Hello, " + player.getName() + "!");


        switch (command.getName().toLowerCase()) {
//            case "test":
//                testCommand(player, command, s, args);
//                break;
            case "item" -> itemCommand(player, command, s, args);
            case "mob" -> mobCommand(player, command, s, args);
            case "stat" -> statCommand(player, command, s, args);
            case "upgrade" -> upgradeCommand(player, command, s, args);
            case "reforge" -> reforgeCommand(player, command, s, args);
            case "debug" -> debugCommand(player, command, s, args);
            default -> player.sendMessage(ChatColor.RED + "Unknown command!");
        }


        return true;
    }

    private void mobCommand(Player player, Command command, String s, String[] args) {
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


    private void itemCommand(@Nullable CommandSender commandSender,
                             @Nullable Command command,
                             @Nullable String s,
                             @Nullable String[] args) {

        Player player = (Player) commandSender;

        assert args != null;
        assert player != null;

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
        for(int i = 0; i < amount; i++) {
            PDCHelper.setString(itemStack, "uuid", java.util.UUID.randomUUID().toString());
            player.getInventory().addItem(itemStack);
        }
    }

    private void statCommand(Player player, Command command, String s, String[] args) {
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


    private void upgradeCommand(Player player, Command command, String s, String[] args) {
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

    private void reforgeCommand(Player player, Command command, String s, String[] args) {
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
    private void debugCommand(Player player, Command command, String s, String[] args){
//        log all info about the item in hand with the name etc
        ItemStack item = player.getInventory().getItemInMainHand();
        if (SkyblockItem.fromItemStack(item) == null) {
            player.sendMessage(ChatColor.RED + "You must be holding a skyblock item!");
            return;
        }
        SkyblockItem skyblockItem = SkyblockItem.fromItemStack(item);
        player.sendMessage(ChatColor.GREEN + "Item: " + skyblockItem.getId());
        player.sendMessage(ChatColor.GREEN + "Rarity: " + skyblockItem.getRarity());
        player.sendMessage(ChatColor.GREEN + "Reforge: " + PDCHelper.getString(item, "reforge"));
        player.sendMessage(ChatColor.GREEN + "Stats: " + Arrays.toString(skyblockItem.getStats().entrySet().toArray()));
//        player.sendMessage(ChatColor.GREEN + "Lore: " + Arrays.toString(skyblockItem.getStaticLore().toArray()));
        player.sendMessage(ChatColor.GREEN + "-----------------");
        SkyblockItem.getStatsFromItemStack(item).forEach((stat, value) -> {
            player.sendMessage(ChatColor.GREEN + stat.name() + ": " + value);
        });
    }
}
