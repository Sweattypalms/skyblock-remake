package com.sweattypalms.skyblock.core.items.types.end.items;

import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerableAbility;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.event.Event;

import java.util.HashMap;
import java.util.List;

public class SummoningEye extends SkyblockItem implements IHasAbility, IHeadHelmet {

    public static final String ID = "summoning_eye";

    public SummoningEye() {
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
    public List<Ability> getAbilities() {
        return List.of(
                new SummoningEyeAbility()
        );
    }

    @Override
    public String getTexture() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFhOGZjOGRlNjQxN2I0OGQ0OGM4MGI0NDNjZjUzMjZlM2Q5ZGE0ZGJlOWIyNWZjZDQ5NTQ5ZDk2MTY4ZmMwIn19fQ==";
    }

    public static class SummoningEyeAbility implements ITriggerableAbility {

        @Override
        public String getName() {
            return "null";
        }

        @Override
        public boolean nameVisible() {
            return false;
        }

        @Override
        public List<String> getDescription() {
            return List.of();
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.RIGHT_CLICK;
        }

        @Override
        public boolean trigger(Event event) {
            return event instanceof SkyblockInteractEvent skyblockInteractEvent && skyblockInteractEvent.getInteractType() == TriggerType.RIGHT_CLICK
                    && skyblockInteractEvent.getInteractedBlock() != null && skyblockInteractEvent.getInteractedBlock().getType() == Material.END_PORTAL_FRAME;
        }

        @Override
        public void apply(Event event) {
            if (!(event instanceof SkyblockInteractEvent skyblockInteractEvent)) return;

            Block block = skyblockInteractEvent.getInteractedBlock();

            skyblockInteractEvent.setCancelled(true);

            EndPortalFrame endPortalFrame = (EndPortalFrame) block.getBlockData();
            if (endPortalFrame.hasEye()){
                return;
            }

            DragonManager.getInstance().addSummoningEye(skyblockInteractEvent.getSkyblockPlayer(), block.getLocation());
        }
    }
}
