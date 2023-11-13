package com.sweattypalms.skyblock.core.mobs.builder.dragons.abilities;

import com.sweattypalms.skyblock.core.helpers.EntityHelper;
import com.sweattypalms.skyblock.core.helpers.MathHelper;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.DragonStage;
import com.sweattypalms.skyblock.core.mobs.builder.dragons.EnderDragon;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class RushAbility implements IDragonAbility{

    private final EnderDragon dragon;

    public RushAbility(EnderDragon dragon) {
        this.dragon = dragon;
    }

    Player target = null;
    @Override
    public void start() {
        this.dragon.setMoving(false);
        this.target = EntityHelper.getClosestPlayer((LivingEntity) this.dragon.getBukkitEntity());
        rushing = true;
        startRushPosition = this.dragon.getBukkitEntity().getLocation().toVector();
        rushProgress = 0.0d;
    }

    boolean rushing = false;
    Vector startRushPosition = null;
    double rushProgress = 0.0d;
    @Override
    public void stop() {
        dragon.setAbility(null);
        int tickCount = 0;
    }

    @Override
    public void tick() {
        if (this.target == null){
            throw new NullPointerException("Target not found??? cancelling ability");
        }

        Vector nextPosition = MathHelper.linearInterpolation(startRushPosition, target.getLocation().toVector(), rushProgress);
        this.dragon.setPositionRotation(nextPosition.getX(), nextPosition.getY(), nextPosition.getZ(), target.getLocation().getYaw(), target.getLocation().getPitch());

        rushProgress += 0.025;

        if(rushProgress >= 1 || this.dragon.getBukkitEntity().getLocation().distance(target.getLocation()) < 3){
            String message = "$5☬ $c" + dragon.getDragonName() + " $dused $eRush $don you for $c" + PlaceholderFormatter.formatDecimalCSV(dragon.getDragonDamage()) + " damage!";
            message = PlaceholderFormatter.format(message);
            target.sendMessage(message);
            SkyblockPlayer.getSkyblockPlayer(target).damageWithReduction(dragon.getDragonDamage());
            computeReturnPath();
        }
    }

    private void computeReturnPath(){
        Vector endPosition = dragon.getCurrentStage().getPoint(1);
        Vector currentDragonPosition = this.dragon.getBukkitEntity().getLocation().toVector();

        dragon.setCurrentStage(new DragonStage(List.of(currentDragonPosition, endPosition), 0.02));
        dragon.setT(0.0);
        dragon.setMoving(true);
        dragon.setAbility(null);
        rushing = false;
    }

    @Override
    public boolean shouldActivate() {
        return dragon.getRandom().nextInt(1500) == 0; // 1 in 1500 chance or every 75 seconds
    }
}

