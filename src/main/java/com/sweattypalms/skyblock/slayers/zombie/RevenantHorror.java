package com.sweattypalms.skyblock.slayers.zombie;

import com.sweattypalms.skyblock.core.helpers.EntityHelper;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.types.slayer.zombie.items.BeheadedHorror;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.NameAttributes;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.slayers.ISlayerMob;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.inventory.ItemStack;

public abstract class RevenantHorror extends EntityZombie implements ISkyblockMob, ISlayerMob {

    private final SkyblockMob skyblockMob;
    public RevenantHorror(Location location, SkyblockMob skyblockMob) {
        super(EntityTypes.be, ((CraftWorld) location.getWorld()).getHandle());
        this.skyblockMob = skyblockMob;

        equipArmor();
        setStats();

        this.getSkyblockMob()
                .setNameAttribute(NameAttributes.FORMATTED, true)
                .setNameAttribute(NameAttributes.SHOW_LEVEL, false);
    }

    public void equipArmor(){
        SkyblockItem beheadedHorror = SkyblockItem.get(BeheadedHorror.ID);
        EntityHelper.equipItem(this, SkyblockItemType.HELMET, beheadedHorror.toItemStack());

        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        EntityHelper.equipItem(this, SkyblockItemType.CHESTPLATE, chestplate);

        ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        leggings.addUnsafeEnchantment(org.bukkit.enchantments.Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        EntityHelper.equipItem(this, SkyblockItemType.LEGGINGS, leggings);

        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        EntityHelper.equipItem(this, SkyblockItemType.BOOTS, boots);
    }

    @Override
    public SkyblockMob getSkyblockMob() {
        return this.skyblockMob;
    }

    @Override
    public EntityLiving getEntityInstance() {
        return this;
    }

    public abstract void setStats();
}
