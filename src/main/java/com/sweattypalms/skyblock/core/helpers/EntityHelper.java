package com.sweattypalms.skyblock.core.helpers;

import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EntityHelper {

    public static void equipArmor(EntityLiving entityLiving, SkyblockItemType[] armorSlots, Material armor) {
        LivingEntity entity = (LivingEntity) entityLiving.getBukkitEntity();
        EntityEquipment equipment = entity.getEquipment();
        assert equipment != null;

        for (SkyblockItemType armorSlot : armorSlots) {
            Material specificArmorMaterial = getArmorMaterial(armor, armorSlot);
            ItemStack armorPiece = new ItemStack(specificArmorMaterial);

            switch (armorSlot) {
                case HELMET -> equipment.setHelmet(armorPiece);
                case CHESTPLATE -> equipment.setChestplate(armorPiece);
                case LEGGINGS -> equipment.setLeggings(armorPiece);
                case BOOTS -> equipment.setBoots(armorPiece);
            }
        }
    }

    public static void equipAllArmor(EntityLiving entityLiving, Material armor) {
        equipArmor(entityLiving, new SkyblockItemType[]{SkyblockItemType.HELMET, SkyblockItemType.CHESTPLATE, SkyblockItemType.LEGGINGS, SkyblockItemType.BOOTS}, armor);
    }

    public static Material getArmorMaterial(Material baseMaterial, SkyblockItemType armorType) {
        String baseMaterialName = baseMaterial.name().split("_")[0];
        return Material.valueOf(baseMaterialName + "_" + armorType.name());
    }

    public static void equipItem(LivingEntity entity, SkyblockItemType armorSlot, ItemStack item) {
        EntityEquipment equipment = entity.getEquipment();
        assert equipment != null;

        switch (armorSlot) {
            case HELMET -> equipment.setHelmet(item);
            case CHESTPLATE -> equipment.setChestplate(item);
            case LEGGINGS -> equipment.setLeggings(item);
            case BOOTS -> equipment.setBoots(item);
            default -> equipment.setItemInMainHand(item);
        }
    }

    public static void equipItem(EntityLiving entity, SkyblockItemType armorSlot, ItemStack item) {
        equipItem((LivingEntity) entity.getBukkitEntity(), armorSlot, item);
    }

    /**
     * <h3>Make sure to null check.</h3>
     *
     * @param livingEnt The entity for which u want the closest player.
     * @return The closest player or null if no player was found.
     */
    public static Player getClosestPlayer(Entity livingEnt) {
        final double[] distance = {Double.MAX_VALUE};
        Player[] target = {null};
        List<Player> possibleTarget = livingEnt.getWorld()
                .getEntitiesByClass(Player.class).stream().toList();

        possibleTarget.forEach(player -> {
            if (livingEnt.getLocation().distance(player.getLocation()) < distance[0]) { // player.getGameMode().equals(GameMode.SURVIVAL) &&
                distance[0] = livingEnt.getLocation().distance(player.getLocation());
                target[0] = player;
            }
        });
        return target[0];
    }
}
