package com.sweattypalms.skyblock.commands.handlers;

import com.sweattypalms.skyblock.commands.Command;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.dungeons.generator.DungeonGenerator;
import com.sweattypalms.skyblock.dungeons.physical.DungeonMap;
import com.sweattypalms.skyblock.dungeons.physical.DungeonPhysical;
import org.bukkit.World;
import org.bukkit.entity.Player;


import java.util.Random;

public class DungeonCommands {
    @Command(name = "dungeon", description = "Dungeon command", op = true)
    public void dungeonCommand(Player player, String[] args) {
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);

        skyblockPlayer.sendMessage("$cGenerating dungeon map");

        DungeonGenerator generator = new DungeonGenerator();
        generator.generate(new Random().nextLong());

        DungeonMap dungeonMap = new DungeonMap(generator);
        DungeonPhysical dungeonPhysical = new DungeonPhysical(generator);

        World world = player.getWorld();

        player.getInventory().addItem(dungeonMap.getItem(world));
        skyblockPlayer.sendMessage("$aDungeon map generated");
    }
}