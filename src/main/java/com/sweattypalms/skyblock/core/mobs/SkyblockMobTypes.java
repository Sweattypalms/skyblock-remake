package com.sweattypalms.skyblock.core.mobs;

import com.sweattypalms.skyblock.core.mobs.regions.graveyard.GraveyardZombie;
import com.sweattypalms.skyblock.core.mobs.regions.graveyard.ZombieVillager;
import com.sweattypalms.skyblock.core.mobs.regions.test.TestEntity;

public enum SkyblockMobTypes {
    GRAVEYARD_ZOMBIE(GraveyardZombie.class),
    ZOMBIE_VILLAGER(ZombieVillager.class),
    TEST_ENTITY(TestEntity.class);

    private final Class<? extends ISkyblockMob> clazz;

    SkyblockMobTypes(Class<? extends ISkyblockMob> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends ISkyblockMob> getClazz() {
        return clazz;
    }

    public SkyblockMob getInstance(){
        try {
            return new SkyblockMob(this.name().toLowerCase(), this.clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
