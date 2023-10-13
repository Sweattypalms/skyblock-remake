package com.sweattypalms.skyblock.core.items.types.end.items;

import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.event.Event;

import java.util.HashMap;
import java.util.List;

public class UsedSummoningEye extends SkyblockItem implements IHeadHelmet , IHasAbility{

    public static final String ID = "used_summoning_eye";

    public UsedSummoningEye() {
        super(
                ID,
                "Summoning Eye",
                Material.SKELETON_SKULL,
                List.of(
                        "$7Use this at the $5End Altar",
                        "$7in the $5Dragon's Nest $7to",
                        "$7summon Ender Dragons!"
                ),
                new HashMap<>(),
                Rarity.EPIC,
                SkyblockItemType.NONE
        );
    }

    @Override
    public String getTexture() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdjMGQwMTBkZDBlNTEyZmZlYTEwOGQ3YzVmZTY5ZDU3NmMzMWVjMjY2Yzg4NGI1MWVjMGIyOGNjNDU3In19fQ==";
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(
                new UsedSummoningEyeAbility()
        );
    }

    public static class UsedSummoningEyeAbility extends SummoningEye.SummoningEyeAbility{
        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return;
            if (skyblockInteractEvent.getInteractedBlock() == null) return;

            Block block = skyblockInteractEvent.getInteractedBlock();

            if (block.getType() != Material.END_PORTAL_FRAME) return;

            skyblockInteractEvent.setCancelled(true);

            EndPortalFrame  frame = (EndPortalFrame) block.getBlockData();

            if (!frame.hasEye()) return;

            DragonManager.getInstance().removeSummoningEye(skyblockInteractEvent.getSkyblockPlayer(), block.getLocation());
        }
    }
}
