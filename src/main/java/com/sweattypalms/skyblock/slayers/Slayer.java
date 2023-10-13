package com.sweattypalms.skyblock.slayers;


public record Slayer(SlayerType slayerType, int level, String bossId, int xpRequiredToSpawn) {

    public static final int MAX_TIME = 240;

}
