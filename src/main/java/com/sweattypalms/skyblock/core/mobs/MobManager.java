package com.sweattypalms.skyblock.core.mobs;

public class MobManager {
    public static SkyblockMob getMob(String id) throws IllegalArgumentException{
        try {
            return SkyblockMobTypes.valueOf(id.toUpperCase()).getInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unknown mob id: " + id);
        }
    }
}