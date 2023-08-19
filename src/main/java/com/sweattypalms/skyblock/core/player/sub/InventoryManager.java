package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.FullSetBonus;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InventoryManager {
    private final SkyblockPlayer player;

    public InventoryManager(SkyblockPlayer player) {
        this.player = player;
    }


    public SkyblockItem getSkyblockItemInHand() {
        ItemStack itemStack = this.player.getPlayer().getInventory().getItemInMainHand();
        String id = PDCHelper.getString(itemStack, "id");
        return ItemManager.ITEMS_LIST.get(id);
    }

    public HashMap<SkyblockItemType, ItemStack> getInventoryItems() {
        HashMap<SkyblockItemType, ItemStack> items = new HashMap<>();
        final boolean[] handSlotVisited = {false};
        Arrays.stream(SkyblockItemType.values()).toList().forEach(skyblockItemType -> {
            if (skyblockItemType.getSlot() == null) return;
            if (skyblockItemType.getSlot() == EquipmentSlot.HAND && handSlotVisited[0]) return;
            handSlotVisited[0] = true;
            ItemStack itemStack = this.player.getPlayer().getInventory().getItem(skyblockItemType.getSlot());
            SkyblockItem skyblockItemFromItemstack = SkyblockItem.fromItemStack(itemStack);
            List<SkyblockItemType> armorTypes = new ArrayList<>(List.of(
                    SkyblockItemType.HELMET,
                    SkyblockItemType.CHESTPLATE,
                    SkyblockItemType.LEGGINGS,
                    SkyblockItemType.BOOTS
            ));
            if (
                    skyblockItemFromItemstack != null
                            && armorTypes.contains(skyblockItemFromItemstack.getItemType())
                            && skyblockItemType.getSlot().equals(EquipmentSlot.HAND)) return;
            if (PDCHelper.hasString(itemStack, "id"))
                items.put(skyblockItemType, itemStack);
        });
        return items;
    }

    public HashMap<SkyblockItemType, SkyblockItem> getInventorySkyblockItems() {
        HashMap<SkyblockItemType, SkyblockItem> items = new HashMap<>();
        final boolean[] handSlotVisited = {false};
        Arrays.stream(SkyblockItemType.values()).toList().forEach(skyblockItemType -> {
            if (skyblockItemType.getSlot() == null) return;
            if (skyblockItemType.getSlot() == EquipmentSlot.HAND && handSlotVisited[0]) return;
            handSlotVisited[0] = true;
            ItemStack itemStack = this.player.getPlayer().getInventory().getItem(skyblockItemType.getSlot());
            SkyblockItem skyblockItem = SkyblockItem.fromItemStack(itemStack);
            if (skyblockItem != null)
                items.put(skyblockItemType, skyblockItem);
        });
        return items;
    }

    public FullSetBonus getEquippedFullSetBonus() {
        for (SkyblockItem skyblockItem : getInventorySkyblockItems().values()) {
            if (skyblockItem instanceof IHasAbility) {
                for (Ability ability : ((IHasAbility) skyblockItem).getAbilities()) {
                    if (ability instanceof FullSetBonus fullSetBonus) {
                        if (fullSetBonus.isFullSetEquipped(player)) {
                            return fullSetBonus;
                        }
                    }
                }
            }
        }
        return null;
    }

}