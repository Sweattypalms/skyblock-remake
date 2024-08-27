package com.sweattypalms.skyblock.commands.handlers;

import com.sweattypalms.skyblock.commands.Command;
import com.sweattypalms.skyblock.commands.TabCompleter;
import com.sweattypalms.skyblock.core.enchants.EnchantManager;
import com.sweattypalms.skyblock.core.enchants.builder.Enchantment;
import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.reforges.Reforge;
import com.sweattypalms.skyblock.core.items.builder.reforges.ReforgeManager;
import com.sweattypalms.skyblock.core.items.types.end.armor.SuperiorChestplate;
import com.sweattypalms.skyblock.core.mobs.builder.MobManager;
import com.sweattypalms.skyblock.core.mobs.builder.NameAttributes;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.loot.DragonDropItemEntity;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import com.sweattypalms.skyblock.core.player.sub.stats.StatsManager;
import com.sweattypalms.skyblock.slayers.ISlayerMob;
import com.sweattypalms.skyblock.ui.guis.ItemsGUI;
import net.minecraft.world.item.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftZombie;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class AdminCommands {
    @Command(name = "mob", description = "Mob command", op = true)
    public void mobCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /mob id");
            return;
        }
        String id = args[0].toLowerCase();

        SkyblockMob skyblockMob = getAndSpawnMob(id, player);

        if (skyblockMob == null) return;

        player.sendMessage(ChatColor.RED + "Successfully spawned: " + skyblockMob.getNameAttribute(NameAttributes.CUSTOM_NAME));
    }
    @TabCompleter(command = "mob")
    public List<String> mobTabCompleter(Player player, String[] args) {
        if (args.length > 1) {
            return List.of();
        }

        if (args.length == 0) {
            return MobManager.MOBS_LIST.keySet().stream().toList();
        }

        String id = args[0].toLowerCase();
        return MobManager.MOBS_LIST.keySet().stream().filter(s -> s.startsWith(id)).toList();
    }

    @Command(name = "sitem", description = "Item command", op = true)
    public void itemCommand(Player player, String[] args) {
        if (args.length == 0) {
            String message = ChatColor.YELLOW + "If you want to get an item of a specific id, do:";
            message += "\n" + ChatColor.YELLOW + "/item <id> [optional: amount]";
            player.sendMessage(message);

            ItemsGUI gui = new ItemsGUI();
            gui.open(player);

            return;
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

    @TabCompleter(command = "sitem")
    public List<String> itemTabCompleter(Player player, String[] args) {
        // Smart tab completer to only serve the correct part :)
        if (args.length > 1) {
            return List.of();
        }

        if (args.length == 0) {
            return ItemManager.ITEMS_LIST.keySet().stream().toList();
        }

        String id = args[0].toLowerCase();
        return ItemManager.ITEMS_LIST.keySet().stream().filter(s -> s.startsWith(id)).toList();
    }


    @Command(name = "stat", description = "Stat command", op = true)
    public void statCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /stat stat value");
            return;
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

    @TabCompleter(command = "stat")
    public List<String> statTabCompleter(Player player, String[] args) {
        if (args.length > 1) {
            return List.of();
        }

        if (args.length == 0) {
            return Arrays.stream(Stats.values()).map(Enum::name).toList();
        }

        String id = args[0].toUpperCase();
        return Arrays.stream(Stats.values()).map(Enum::name).filter(s -> s.contains(id)).toList();
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
        ItemStack updatedItemStack = SkyblockItem.updateItemStack(SkyblockPlayer.getSkyblockPlayer(player));
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

        boolean canReforge = reforge.getReforgeableItemTypes().contains(skyblockItem.getItemType());
        boolean shouldForce = args.length == 2 && args[1].equalsIgnoreCase("force");

        if (!canReforge && !shouldForce) {
            player.sendMessage(ChatColor.RED + "This item cannot be reforged with " + reforge.getName() + "!");
            player.sendMessage(ChatColor.RED + "If you want to force reforge, do /reforge <reforge> force");
            return;
        }

        PDCHelper.setString(item, "reforge", reforge.getName().toLowerCase());
        ItemStack updatedItemStack = SkyblockItem.updateItemStack(SkyblockPlayer.getSkyblockPlayer(player));
        player.getInventory().setItemInMainHand(updatedItemStack);

        player.sendMessage(ChatColor.GREEN + "Successfully reforged item!");
    }

    @TabCompleter(command = "reforge")
    public List<String> reforgeTabCompleter(Player player, String[] args) {

        if (args.length == 2) {
            return List.of("force");
        }
        if (args.length > 1) {
            return List.of();
        }

        if (args.length == 0) {
            return ReforgeManager.REFORGES_LIST.keySet().stream().toList();
        }

        String id = args[0].toLowerCase();
        return ReforgeManager.REFORGES_LIST.keySet().stream().filter(s -> s.startsWith(id)).toList();
    }

    @Command(name = "dragloot", description = "Drag loot command", op = true)
    public void dragLootCommand(Player player, String[] args) {
        Location location = player.getLocation().getBlock().getLocation();
        location.add(3, 0, 0);
        while (location.getBlock().getType() != Material.AIR) {
            location.subtract(0, 1, 0);
        }
        DragonDropItemEntity dragonDropItemEntity = new
                DragonDropItemEntity(player, location, SkyblockItem.get(SuperiorChestplate.ID));
        //player.sendMessage(ChatColor.RED + "Spawned dragon loot.");
    }

    @Command(name = "slayer_id", description = "Slayer command", op = true)
    public void slayerCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /slayer id");
            return;
        }
        String id = args[0].toLowerCase();

        SkyblockMob skyblockMob = getAndSpawnMob(id, player);

        if (skyblockMob == null) return;

        ISlayerMob slayerMob = (ISlayerMob) ((CraftZombie) skyblockMob.getEntityInstance()).getHandle();
        slayerMob.setOwnerPlayer(SkyblockPlayer.getSkyblockPlayer(player));

        player.sendMessage(ChatColor.RED + "Successfully spawned: " + skyblockMob.getNameAttribute(NameAttributes.CUSTOM_NAME));
    }

    @TabCompleter(command = "slayer_id")
    public List<String> slayerTabCompleter(Player player, String[] args) {
        List<String> slayerMobs = MobManager.MOBS_LIST.keySet().stream().filter(s -> s.contains("+slayer")).toList();

        if (args.length > 1) {
            return List.of();
        }

        if (args.length == 0) {
            return slayerMobs;
        }

        String id = args[0].toLowerCase();
        return slayerMobs.stream().filter(s -> s.startsWith(id)).toList();
    }

    @Command(name = "?cancel_slayer", description = "Cancel slayer command", op = true)
    public void cancelSlayerCommand(Player player, String[] args) {
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        skyblockPlayer.getSlayerManager().cancelSlayer();
        skyblockPlayer.sendMessage(" $c$lSlayer quest cancelled!");
    }

    private SkyblockMob getAndSpawnMob(String id, Player player) {
        SkyblockMob skyblockMob;
        try {
            skyblockMob = MobManager.getInstance(id);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + e.getMessage());
            return null;
        }

        skyblockMob.spawn(player.getLocation());

        return skyblockMob;
    }

    @Command(name = "sbrl", description = "Skyblock reload command", op = true)
    public void sbrlCommand(Player player, String[] args) {
        player.sendMessage(ChatColor.YELLOW + "Reloading Skyblock...");

        if (!Bukkit.getPluginManager().isPluginEnabled("PluginManager")) {
            player.sendMessage(ChatColor.RED + "PluginManager is not available!");
            return;
        }


        player.performCommand("pm reload skyblock");
    }

    @Command(name = "powerup", description = "Give yourself lots of stats", op = true)
    public void powerupCommand(Player player, String[] args) {
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        StatsManager m = skyblockPlayer.getStatsManager();
        m.setBaseStat(Stats.HEALTH, 100_000);
        m.setBaseStat(Stats.STRENGTH, 10_000);
        m.setBaseStat(Stats.DEFENSE, 1_000);
        m.setBaseStat(Stats.SPEED, 500);
        m.setBaseStat(Stats.CRIT_CHANCE, 1000);
        m.setBaseStat(Stats.CRIT_DAMAGE, 1000);
        m.setBaseStat(Stats.INTELLIGENCE, 1_000_000);
        m.setBaseStat(Stats.MANA_REGEN, 1_000_000);

        skyblockPlayer.sendMessage(" $c$lYou have been powered up!");
    }

    @Command(name = "heal", description = "Give yourself complete healing", op = true)
    public void healCommand(Player player, String[] args) {
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        double maxHealth = skyblockPlayer.getStatsManager().getMaxStats().get(Stats.HEALTH);

        double healedFor = maxHealth - player.getHealth();

        String healedForStr = PlaceholderFormatter.formatDecimalCSV(healedFor);

        skyblockPlayer.sendMessage(" $a$lYou have been healed for " + healedForStr + " health!");

        player.setHealth(maxHealth);
    }

    @Command(name = "currency", description = "Give yourself money / bits", op = true)
    public void currencyCommand(Player player, String[] args) {
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);

        if (args.length == 0) {
            skyblockPlayer.sendMessage("$c$lUsage: /currency <coins|bits|bank> <amount>");
        }

        byte type;

        switch (args[0].toLowerCase()) {
            case "coins" -> type = 0;
            case "bits" -> type = 1;
            case "bank" -> type = 2;
            default -> {
                skyblockPlayer.sendMessage("$c$lInvalid currency type!");
                return;
            }
        }

        double amount;

        try {
            amount = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            skyblockPlayer.sendMessage("$c$lInvalid number or amount!");
            return;
        }


        switch (type) {
            case 0 -> skyblockPlayer.getCurrencyManager().addPurseBalance(amount);
            case 1 -> skyblockPlayer.getCurrencyManager().addBits(amount);
            case 2 -> skyblockPlayer.getCurrencyManager().addBankBalance(amount);
        }

        skyblockPlayer.sendMessage("$a$lYou have been given " + PlaceholderFormatter.formatDecimalCSV(amount) + " " + args[0].toLowerCase() + "!");
    }

    @Command(name = "enchant", description = "Enchant command", op = true)
    public void enchantCommand(Player player, String[] args) {

        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /enchant <id> <optional:level>");
            return;
        }

        String id = args[0].toLowerCase();
        int level = args.length == 2 ? Integer.parseInt(args[1]) : 1;

        try {
            EnchantManager.getEnchantment(id);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + e.getMessage());
            return;
        }

        ItemStack item = skyblockPlayer.getInventoryManager().getItemInHand();

        if (skyblockPlayer.getInventoryManager().getSkyblockItemInHand() == null) {
            player.sendMessage(ChatColor.RED + "You must be holding a skyblock item! (not really but plz do :3)");
            return;
        }

        EnchantManager.addEnchantment(item, id, level);

        SkyblockItem.updateItemStack(skyblockPlayer);

        player.sendMessage(ChatColor.GREEN + "Successfully enchanted item!");
    }
}
