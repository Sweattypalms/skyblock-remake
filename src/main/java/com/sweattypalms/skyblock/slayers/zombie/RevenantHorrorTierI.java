package com.sweattypalms.skyblock.slayers.zombie;

import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.SlayerDrop;
import org.bukkit.Location;

import java.util.List;

public class RevenantHorrorTierI extends RevenantHorror {

    public static final String ID = "revenant_horror_tier_1+slayer";

    public RevenantHorrorTierI(Location location, SkyblockMob skyblockMob) {
        super(location, skyblockMob);
    }

    @Override
    public void setStats() {
        this.getSkyblockMob()
                .setMaxHealth(500)
                .setDamage(15)
                .setCustomName("$c☠ $fRevenant Horror");
    }

    @Override
    public List<SkyblockItem> getConfirmedDrops() {
        return null;
    }

    @Override
    public List<SlayerDrop> getRngDrops() {
        return null;
    }

    @Override
    public int getSlayerXpReward() {
        return 0;
    }

    @Override
    public boolean requirementsMet(SkyblockPlayer skyblockPlayer) {
        return true;
    }
}
