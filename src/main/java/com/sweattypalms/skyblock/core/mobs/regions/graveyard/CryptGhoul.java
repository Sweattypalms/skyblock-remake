package com.sweattypalms.skyblock.core.mobs.regions.graveyard;

import com.sweattypalms.skyblock.core.helpers.EntityHelper;
import com.sweattypalms.skyblock.core.mobs.builder.IRegionEntity;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.regions.Regions;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class CryptGhoul extends EntityZombie implements ISkyblockMob, IRegionEntity {

    public static final String ID = "crypt_ghoul";

    private final SkyblockMob skyblockMob;

    public CryptGhoul(Location location, SkyblockMob skyblockMob) {
        super(EntityTypes.be, ((CraftWorld) location.getWorld()).getHandle());
        this.skyblockMob = skyblockMob;
        this.skyblockMob
                .setMaxHealth(2000)
                .setDamage(350)
                .setCustomName("$cCrypt Ghoul")
                .setLevel(30)
        ;

        EntityHelper.equipAllArmor(this, Material.CHAINMAIL_CHESTPLATE);
        EntityHelper.equipAllArmor(this, Material.CHAINMAIL_LEGGINGS);
        EntityHelper.equipAllArmor(this, Material.CHAINMAIL_BOOTS);
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
