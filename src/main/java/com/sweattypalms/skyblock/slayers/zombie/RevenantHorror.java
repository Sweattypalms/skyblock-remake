package com.sweattypalms.skyblock.slayers.zombie;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.helpers.EntityHelper;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.types.slayer.zombie.items.BeheadedHorror;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.NameAttributes;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.ISlayerMob;
import com.sweattypalms.skyblock.slayers.SlayerTimer;
import com.sweattypalms.skyblock.slayers.events.SlayerFailEvent;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntityZombie;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.inventory.ItemStack;

public abstract class RevenantHorror extends EntityZombie implements ISkyblockMob, ISlayerMob {

    protected final SkyblockMob skyblockMob;
    protected final SlayerTimer slayerTimer;
    protected final long startTime;

    protected SkyblockPlayer ownerPlayer;

    public RevenantHorror(Location location, SkyblockMob skyblockMob) {
        super(EntityTypes.be, ((CraftWorld) location.getWorld()).getHandle());
        this.skyblockMob = skyblockMob;

        equipArmor();
        setStats();

        this.getSkyblockMob()
                .setNameAttribute(NameAttributes.FORMATTED, true)
                .setNameAttribute(NameAttributes.SHOW_LEVEL, false);

        this.slayerTimer = new SlayerTimer(this.skyblockMob);
        this.startTime = System.currentTimeMillis();
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


    int maxTime = 15; // => 4 minutes
    long ticks = 0;
    @Override
    public void tick() {
        super.tick();
        ticks++;

        if(!valid())
            return;

        if (ticks % 20 == 0) {
            long timeLeft = maxTime - (System.currentTimeMillis() - getStartTime()) / 1000;
            if (timeLeft <= 0) {
                Bukkit.getScheduler().runTask(SkyBlock.getInstance(), () -> {
                    if(valid()) {
                        this.getSkyblockMob().getEntityInstance().setHealth(0);
                        SlayerFailEvent failEvent = new SlayerFailEvent(
                                this,
                                this.getOwnerPlayer(),
                                SlayerFailEvent.SlayerFailReason.TIME_PASSED
                        );
                        Bukkit.getPluginManager().callEvent(failEvent);
                    }
                });
            } else {
                slayerTimer.updateTimer(timeLeft);
            }
        }
    }

    private boolean valid() {
        return this.skyblockMob.getEntityInstance() != null && !this.skyblockMob.getEntityInstance().isDead();
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

    @Override
    public long getStartTime() {
        return this.startTime;
    }

    @Override
    public SkyblockPlayer getOwnerPlayer() {
        return this.ownerPlayer;
    }

    @Override
    public void setOwnerPlayer(SkyblockPlayer skyblockPlayer) {
        this.ownerPlayer = skyblockPlayer;
    }
}
