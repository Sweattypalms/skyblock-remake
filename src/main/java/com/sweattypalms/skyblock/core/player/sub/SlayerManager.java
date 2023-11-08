package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.api.sequence.Sequence;
import com.sweattypalms.skyblock.api.sequence.SequenceAction;
import com.sweattypalms.skyblock.core.mobs.builder.MobManager;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.PlayerManager;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.ISlayerMob;
import com.sweattypalms.skyblock.slayers.Slayer;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;

@Getter
public class SlayerManager extends PlayerManager {
    private Slayer activeSlayer;
    @Setter
    private int gatheredXp;
    @Setter
    private long lastMaddoxBatphoneUse = 0L;
    @Setter
    private int failedBatphoneAttempts = 0;

    @Getter
    @Setter
    private EntityLiving boss;

    public SlayerManager(SkyblockPlayer skyblockPlayer) {
        super(skyblockPlayer);
    }

    public void addGatheredXp(int xp, LivingEntity deadEntity) {
        this.gatheredXp += xp;

        if (boss != null) return;
        if (this.gatheredXp < this.activeSlayer.xpRequiredToSpawn()) return;
        this.startSlayer(deadEntity);
    }

    public void startSlayer(LivingEntity deadEntity) {
        String id = this.activeSlayer.bossId();
        SkyblockMob skyblockMob = MobManager.getInstance(id);

        // first show the particle at eye location, of like cloud and end particles,
        // concentrated at one location
        // and then after 1 second,
        // show explosion particles everywhere, and after like 5 ticks, spawn a mob.

        Location tLocation = deadEntity.getEyeLocation();

        Sequence particleSequence = new Sequence();

        SequenceAction cloudParticleSequence = new SequenceAction(
                () -> {
                    World world = deadEntity.getWorld();
                    world.spawnParticle(Particle.END_ROD, tLocation, 10);
                    world.spawnParticle(Particle.SPELL_WITCH, tLocation, 10);

                    world.playSound(tLocation, Sound.ENTITY_CAT_HISS, 0.1f, 1);
                }
                , 3);

        for (int i = 0; i++ < 20 / 3; ) {
            particleSequence.add(cloudParticleSequence);
        }

        SequenceAction explosionParticleSequence = new SequenceAction(
                () -> {

                    World world = deadEntity.getWorld();
                    world.spawnParticle(Particle.EXPLOSION_HUGE, tLocation, 1);
                    world.playSound(tLocation, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                }
                , 3);

        particleSequence.add(explosionParticleSequence);

        SequenceAction spawnMobSequence = new SequenceAction(
                () -> {
                    skyblockMob.spawn(deadEntity.getLocation());

                    ISlayerMob slayerMob = (ISlayerMob) ((CraftLivingEntity) skyblockMob.getEntityInstance()).getHandle();
                    slayerMob.setOwnerPlayer(this.skyblockPlayer);


                    this.boss = ((CraftLivingEntity) skyblockMob.getEntityInstance()).getHandle();
                }
                , 3);

        particleSequence.add(spawnMobSequence);

        particleSequence.start();
    }

    public void addFailedBatphoneAttempt() {
        this.failedBatphoneAttempts++;
    }

    public void setActiveSlayer(Slayer slayer) {
        this.activeSlayer = slayer;
    }

    public void cancelSlayer() {
        this.activeSlayer = null;
    }

    public void finishSlayer() {
        this.activeSlayer = null;
        this.gatheredXp = 0;
        this.boss = null;
    }

    public long getStartTime() {
        return ((ISlayerMob) this.boss).getStartTime();
    }
}
