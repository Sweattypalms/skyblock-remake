package com.sweattypalms.skyblock.core.mobs.builder.dragons.abilities;

public interface IDragonAbility {
    void start();
    void stop();
    void tick();
    boolean shouldActivate();
}
