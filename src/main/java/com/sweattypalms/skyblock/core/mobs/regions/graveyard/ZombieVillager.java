package com.sweattypalms.skyblock.core.mobs.regions.graveyard;

import com.sweattypalms.skyblock.core.helpers.EntityHelper;
import com.sweattypalms.skyblock.core.mobs.builder.IRegionEntity;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.MobAttributes;
import com.sweattypalms.skyblock.core.regions.Regions;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombieVillager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class ZombieVillager extends EntityZombieVillager implements ISkyblockMob, IRegionEntity {

    public static final String ID = "zombie_villager";

    private final SkyblockMob skyblockMob;

    public ZombieVillager(Location location, SkyblockMob skyblockMob) {
        super(EntityTypes.bg, ((CraftWorld) location.getWorld()).getHandle());
        this.skyblockMob = skyblockMob;
        this.skyblockMob
                .setMaxHealth(120)
                .setDamage(24)
                .setCustomName("$cZombie Villager")
                .setLevel(1)
                .setAttribute(MobAttributes.COMBAT_XP, 20d)
        ;

        EntityHelper.equipAllArmor(this, Material.LEATHER);
    }

    @Override
    public Regions getRegion() {
        return Regions.GRAVEYARD;
    }


    @Override
    public SkyblockMob getSkyblockMob() {
        return skyblockMob;
    }

    @Override
    public EntityLiving getEntityInstance() {
        return this;
    }
}
