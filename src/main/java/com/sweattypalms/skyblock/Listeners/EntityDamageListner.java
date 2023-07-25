package com.sweattypalms.skyblock.Listeners;


import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.utils.SkyUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.Random;

public class EntityDamageListner implements Listener {
    public static boolean crithit = false;

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        // using dummy stats value as stats system is not implemented yet

        double damage = 0;
        if (event.getDamager() instanceof Player) {
            if (event.getEntity() instanceof Player) return;
            Player player = (Player) event.getDamager();
            try {
                double d = 0;
                d += (5 + 100) + (200 / 5) * (1 + 200 / 100);
                d += 1 + (1 * 0.04);

                double totalCritDmg = 120;

                Random rnd = new Random();
                int critchancernd = rnd.nextInt(100);
                if (critchancernd <= 60) {
                    d *= (1 + totalCritDmg / 100);
                    crithit = true;
                }
                damage = d;
                event.setDamage(d);
            } catch (NullPointerException e) {
                double d = 0;
                d += (5 + (200 / 5)) * (1 + 200 / 100);
                d += 1 + (1 * 0.04);
                Random rnd = new Random();
                int critchancernd = rnd.nextInt(100);
                if (critchancernd <= 60) {
                    d *= (1 + 120 / 100);
                    crithit = true;
                }
                damage = d;
                event.setDamage(d);

            }
        }
        double finalDamage = damage;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (finalDamage > 0) {
                    addIndicator(finalDamage, SkyUtils.getRandomLocation(event.getEntity().getLocation(), 2), event.getCause());
                } else {
                    addIndicator(finalDamage, SkyUtils.getRandomLocation(event.getEntity().getLocation(), 2), event.getCause());
                }
            }
        }.runTaskLater(SkyBlock.getInstance(), 1L);
    }

    public @Nullable String addCritTexture(String str) {
        String new_string = null;
        if (str.length() == 1)
            new_string = "§f✧§f" + str + "§e✧";
        if (str.length() == 2)
            new_string = "§f✧§f" + (str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§c✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if (str.length() == 3)
            new_string = "§f✧§f" + (str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if (str.length() == 4)
            new_string = "§f✧§f" + (str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + "§c✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if (str.length() == 5)
            new_string = "§f✧§f" +(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + "§e✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if (str.length() == 6)
            new_string = "§f✧§f" + (str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + str.charAt(5) + "§e✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if (str.length() == 7)
            new_string = "§f✧§f" + (str.charAt(0)) + "§e" + (str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + str.charAt(5) + str.charAt(6) + "§e✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if(str.length() == 8)
            new_string = "§f✧§f" + (str.charAt(0)) + "§e" + (str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + str.charAt(5) + str.charAt(6) + str.charAt(7) + "§e✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if(str.length() == 9)
            new_string = "§f✧§f" + (str.charAt(0)) + "§e" + (str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + str.charAt(5) + str.charAt(6) + str.charAt(7) + str.charAt(8) + "§e✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if(str.length() == 10)
            new_string = "§f✧§f" + (str.charAt(0)) + "§e" + (str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + str.charAt(5) + str.charAt(6) + str.charAt(7) + str.charAt(8) + "§6" + str.charAt(9) + "§e✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if(str.length() == 11)
            new_string = "§f✧§f" + (str.charAt(0)) + "§e" + (str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + str.charAt(5) + str.charAt(6) + str.charAt(7) + str.charAt(8) + str.charAt(9) + "§e" + str.charAt(10) + "§e✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");
        if(str.length() == 12)
            new_string = "§f✧§f" + (str.charAt(0)) + "§e" + (str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + str.charAt(4) + str.charAt(5) + str.charAt(6) + str.charAt(7) + str.charAt(8) + str.charAt(9) + str.charAt(10) + "§f" + str.charAt(11) + "§e✧".replaceAll(",", ChatColor.DARK_PURPLE + ",");

        return new_string;
    }

    public void addIndicator(double damage, Location loc, EntityDamageEvent.DamageCause cause) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formatted = formatter.format(damage);

        ArmorStand Indicator = (ArmorStand) loc.getWorld().spawnEntity(loc , EntityType.ARMOR_STAND);
        Indicator.setCustomNameVisible(true);
        Indicator.setGravity(false);
        Indicator.setInvisible(true);
        Indicator.setSmall(true);
        Indicator.setSmall(true);
        Indicator.setNoDamageTicks(20);

        if (cause == EntityDamageEvent.DamageCause.FIRE_TICK || cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.LAVA) {
            Indicator.setCustomName("§6" + Math.round(damage));
        } else if(cause == EntityDamageEvent.DamageCause.DROWNING) {
            Indicator.setCustomName("§3" + Math.round(damage));
        } else if(cause == EntityDamageEvent.DamageCause.POISON) {
            Indicator.setCustomName("§2" + Math.round(damage));
        } else {
            if (crithit) {
                Indicator.setCustomName(addCritTexture(formatted));
                crithit = false;
            } else {
                Indicator.setCustomName("§7" + Math.round(damage));
            }
        }


        new BukkitRunnable() {
            @Override
            public void run() {
               Indicator.remove();
            }
        }.runTaskLater(SkyBlock.getInstance(), 10);
    }

}


