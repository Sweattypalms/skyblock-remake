package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.core.enchants.EnchantManager;
import com.sweattypalms.skyblock.core.events.def.SkyblockDeathEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockXpEvent;
import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.MobAttributes;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.skillz.Skill;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkyblockDeathListener implements Listener {

    /**
     * Forwarded event from EntityDeathEvent
     * @param event SkyblockDeathEvent
     */
    @EventHandler
    public void skyblockDeathListener(SkyblockDeathEvent event){
        if (event.getDeadEntity() instanceof Player player){
            player.sendMessage("You died!");
            return;
        }
        SkyblockMob skyblockMob = SkyblockMob.getSkyblockMob(event.getDeadEntity());

        if (skyblockMob == null) return;

        if (event.getDamager() != null){
            // Play ding sound
            Location location = event.getDeadEntity().getLocation();
            assert location.getWorld() != null;
            location.getWorld().playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        }
//        if(skyblockMob != null){
//            System.out.println("Skyblock mob died " + skyblockMob.getCustomName());
//        }else{
//            System.out.println("random mob died");
//        }
//        System.out.println("Cause: " + event.getCause());
    }

    @EventHandler
    public void skyblockDeath4Slayer(SkyblockDeathEvent event){
        if(!(event.getDamager() instanceof Player player)) {
            return;
        }

        LivingEntity deadEntity = event.getDeadEntity();
        EntityLiving entityLiving = ((CraftLivingEntity) deadEntity).getHandle();

        if(!(entityLiving instanceof ISkyblockMob iSkyblockMob)) return;

        SkyblockMob skyblockMob = iSkyblockMob.getSkyblockMob();

        double xpToGain = skyblockMob.getAttribute(MobAttributes.COMBAT_XP);

        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        SkyblockXpEvent skyblockXpEvent = new SkyblockXpEvent(xpToGain, Skill.SkillType.COMBAT, skyblockPlayer, event);
        Bukkit.getPluginManager().callEvent(skyblockXpEvent);
    }

    @EventHandler
    public void skyblockDeath4Enchant(SkyblockDeathEvent event) {
        if(!(event.getDamager() instanceof Player player)) {
            return;
        }

        EnchantManager.run(player, event);
    }
}
