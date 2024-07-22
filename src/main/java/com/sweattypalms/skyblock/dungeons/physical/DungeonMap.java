package com.sweattypalms.skyblock.dungeons.physical;

import com.sweattypalms.skyblock.dungeons.generator.DungeonGenerator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

public class DungeonMap {
    private final DungeonGenerator dungeonGenerator;

    public DungeonMap(DungeonGenerator dungeonGenerator) {
        this.dungeonGenerator = dungeonGenerator;
    }

    public ItemStack getItem(World world) {
        MapView view = Bukkit.createMap(world);
        view.getRenderers().clear();

        view.addRenderer(new DungeonMapRenderer(dungeonGenerator));
        view.setScale(MapView.Scale.FARTHEST);
        view.setTrackingPosition(false);
        view.setCenterX(0);
        view.setCenterZ(0);

        ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
        MapMeta mapMeta = (MapMeta) mapItem.getItemMeta();
        assert mapMeta != null;
        mapMeta.setMapView(view);
        mapItem.setItemMeta(mapMeta);

        return mapItem;
    }
}
