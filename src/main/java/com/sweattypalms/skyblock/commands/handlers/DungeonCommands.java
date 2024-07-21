package com.sweattypalms.skyblock.commands.handlers;

import com.sweattypalms.skyblock.commands.Command;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.dungeons.generator.DungeonGenerator;
import com.sweattypalms.skyblock.dungeons.generator.DungeonMapRenderer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import java.util.Random;

public class DungeonCommands {
    @Command(name = "dungeon", description = "Dungeon command", op = true)
    public void dungeonCommand(Player player, String[] args) {
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);

        skyblockPlayer.sendMessage("$cGenerating dungeon map");

        DungeonGenerator generator = new DungeonGenerator();
        generator.generate(new Random().nextLong());

        MapView view = Bukkit.createMap(player.getWorld());
        view.getRenderers().clear();


        view.addRenderer(new DungeonMapRenderer(generator));
        view.setScale(MapView.Scale.FARTHEST);
        view.setTrackingPosition(false);
        view.setCenterX(0);
        view.setCenterZ(0);

        ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
        MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();
        assert mapMeta != null;
        mapMeta.setMapView(view);
        mapItem.setItemMeta(mapMeta);

        player.getInventory().addItem(mapItem);
        skyblockPlayer.sendMessage("$aDungeon map generated");
    }
}