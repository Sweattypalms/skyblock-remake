package com.sweattypalms.skyblock.dungeons.physical;

import com.sweattypalms.skyblock.dungeons.generator.DungeonGenerator;

public class DungeonPhysical  {
    private final DungeonGenerator dungeonGenerator;

    public DungeonPhysical(DungeonGenerator generator) {
        this.dungeonGenerator = generator;
        System.out.println("DungeonPhysical created with generator hash: " + generator.hashCode());
    }
}
