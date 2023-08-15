package com.sweattypalms.skyblock.commands;

import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.mobs.MobManager;
import com.sweattypalms.skyblock.core.mobs.SkyblockMob;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

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
            case "item":
                itemCommand(player, command, s, args);
                break;
            case "mob":
                mobCommand(player, command, s, args);
                break;
            default:
                player.sendMessage(ChatColor.RED + "Unknown command!");
                break;
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
            skyblockMob = MobManager.getMob(id);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + e.getMessage());
            return;
        }

        assert skyblockMob != null;

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
        }

        SkyblockItem skyblockItem = ItemManager.ITEMS_LIST.get(id);
        ItemStack itemStack = skyblockItem.toItemStack();

        player.getInventory().addItem(itemStack);
    }

}
