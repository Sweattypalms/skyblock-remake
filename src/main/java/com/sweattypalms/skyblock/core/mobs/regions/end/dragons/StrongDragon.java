package com.sweattypalms.skyblock.core.mobs.regions.end.dragons;

import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonStage;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonType;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.EnderDragon;
import org.bukkit.Location;

import java.util.List;

public class StrongDragon extends EnderDragon implements ISkyblockMob {

    public static final String ID = "strong_dragon";

    public StrongDragon(Location location, SkyblockMob skyblockMob) {
        super(location, skyblockMob);
    }

    @Override
    public DragonType getDragonType() {
        return DragonType.STRONG;
    }

    @Override
    public String getDragonName() {
        return "Strong Dragon";
    }

    @Override
    public double getMaxDragonHealth() {
        return 9_000_000_000d;
    }

    @Override
    public double getDragonDamage() {
        return 1_100;
    }

    @Override
    public List<DragonStage> getDragonStages() {
        return super.getDragonStages();
    }
}
