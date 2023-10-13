package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.mobs.builder.MobManager;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.ISlayerMob;
import com.sweattypalms.skyblock.slayers.Slayer;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;

@Getter
public class SlayerManager {
    private final SkyblockPlayer skyblockPlayer;
    private Slayer activeSlayer;
    @Setter
    private int gatheredXp;
    @Setter
    private long lastMaddoxBatphoneUse = 0L;
    @Setter
    private int failedBatphoneAttempts = 0;

    @Getter @Setter
    private EntityLiving boss;

    public SlayerManager(SkyblockPlayer skyblockPlayer) {
        this.skyblockPlayer = skyblockPlayer;
    }

    public void addGatheredXp(int xp) {
        this.gatheredXp += xp;

        if(boss != null) return;
        if (this.gatheredXp < this.activeSlayer.xpRequiredToSpawn()) return;
        this.startSlayer();
    }

    public void startSlayer() {
        String id = this.activeSlayer.bossId();
        SkyblockMob skyblockMob = MobManager.getInstance(id);
        skyblockMob.spawn(this.skyblockPlayer.getPlayer().getLocation());

        ISlayerMob slayerMob = (ISlayerMob) ((CraftLivingEntity) skyblockMob.getEntityInstance()).getHandle();
        slayerMob.setOwnerPlayer(this.skyblockPlayer);


        String message = " $5$lSLAYER BOSS SPAWNED!";
        this.skyblockPlayer.sendMessage(message);

        this.boss = ((CraftLivingEntity)skyblockMob.getEntityInstance()).getHandle();
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
    public long getStartTime(){
        return ((ISlayerMob) this.boss).getStartTime();
    }
}
