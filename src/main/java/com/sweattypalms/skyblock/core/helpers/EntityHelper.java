package com.sweattypalms.skyblock.core.helpers;

import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

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
        equipArmor(entityLiving, new SkyblockItemType[]{ SkyblockItemType.HELMET, SkyblockItemType.CHESTPLATE,  SkyblockItemType.LEGGINGS, SkyblockItemType.BOOTS }, armor);
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
            default ->  equipment.setItemInMainHand(item);
        }
    }



}
