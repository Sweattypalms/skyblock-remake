package com.sweattypalms.skyblock.slayers;

import lombok.Getter;
import org.bukkit.entity.EntityType;

import java.util.Arrays;

@Getter
public enum SlayerType {
    REVENANT_HORROR("Zombie Slayer", "Zombies", EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER),
    TARANTULA_BROODFATHER("Spider Slayer", "Spiders", EntityType.SPIDER, EntityType.CAVE_SPIDER),
    SVEN_PACKMASTER("Sven Slayer", "Svens", EntityType.WOLF),
    VOIDLING("Enderman Slayer", "Endermen", EntityType.ENDERMAN),
    ;

    private final String alternateName;
    private final String mobType;
    private final EntityType[] possibleHarvestableEntities;
    SlayerType(String alternateName, String mobType, EntityType ... entityTypes) {
        this.alternateName = alternateName;
        this.mobType = mobType;
        this.possibleHarvestableEntities = entityTypes;
    }

    public boolean validEntity(EntityType type){
        return Arrays.stream(possibleHarvestableEntities).anyMatch(entityType -> entityType == type);
    }
}
