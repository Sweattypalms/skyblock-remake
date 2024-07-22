package com.sweattypalms.skyblock.core.player.sub;

import com.sweattypalms.skyblock.core.enchants.EnchantManager;
import com.sweattypalms.skyblock.core.enchants.builder.Enchantment;
import com.sweattypalms.skyblock.core.enchants.builder.PassiveEnchantment;
import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.FullSetBonus;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IPersonalizedDescription;
import com.sweattypalms.skyblock.core.player.PlayerManager;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class InventoryManager extends PlayerManager {
    PlayerInventory playerInventory;

    public InventoryManager(SkyblockPlayer player) {
        super(player);
        this.playerInventory = player.getPlayer().getInventory();
    }

    public void tick() {
        getInventoryItems().forEach((skyblockItemType, itemStack) -> {
            String id = PDCHelper.getString(itemStack, "id");
            /** Check if the item is an IPersonalizedDescription */
            if (SkyblockItem.get(id) instanceof IPersonalizedDescription) {
                SkyblockItem.updateItemStack(this.skyblockPlayer, itemStack);
            }

            /** Check if the item has any enchants that are PassiveEnchantments */
            EnchantManager.getEnchantments(itemStack).forEach(enchantmentIntegerTuple -> {
                Enchantment enchantment = enchantmentIntegerTuple.a();

                if (enchantment instanceof PassiveEnchantment passiveEnchantment) {
                    passiveEnchantment.onTick(this.skyblockPlayer);
                }
            });
        });
    }

    public SkyblockItem getSkyblockItemInHand() {
        ItemStack itemStack = this.skyblockPlayer.getPlayer().getInventory().getItemInMainHand();
        String id = PDCHelper.getString(itemStack, "id");
        return ItemManager.ITEMS_LIST.get(id);
    }

    public ItemStack getItemInHand() {
        return this.playerInventory.getItemInMainHand();
    }

    /**
     * Get all the items in the players inventory
     *
     * @return items that are valid skyblock items, but as ItemStacks
     */
    public HashMap<SkyblockItemType, ItemStack> getInventoryItems() {
        HashMap<SkyblockItemType, ItemStack> items = new HashMap<>();
        final boolean[] handSlotVisited = {false};
        Arrays.stream(SkyblockItemType.values()).toList().forEach(skyblockItemType -> {
            if (skyblockItemType.getSlot() == null) return;
            if (skyblockItemType.getSlot() == EquipmentSlot.HAND && handSlotVisited[0]) return;
            handSlotVisited[0] = true;
            ItemStack itemStack = this.playerInventory.getItem(skyblockItemType.getSlot());
            SkyblockItem skyblockItemFromItemstack = SkyblockItem.fromItemStack(itemStack);
            List<SkyblockItemType> armorTypes = new ArrayList<>(List.of(
                    SkyblockItemType.HELMET,
                    SkyblockItemType.CHESTPLATE,
                    SkyblockItemType.LEGGINGS,
                    SkyblockItemType.BOOTS
            ));
            if (PDCHelper.hasString(itemStack, "id"))
                items.put(skyblockItemType, itemStack);
        });
        return items;
    }

    public HashMap<SkyblockItemType, SkyblockItem> getInventorySkyblockItems() {
        HashMap<SkyblockItemType, SkyblockItem> items = new HashMap<>();
//        final boolean[] handSlotVisited = {false};
        AtomicBoolean handSlotVisited = new AtomicBoolean(false);
        Arrays.stream(SkyblockItemType.values()).toList().forEach(skyblockItemType -> {
            if (skyblockItemType.getSlot() == null) return;
            if (skyblockItemType.getSlot() == EquipmentSlot.HAND && handSlotVisited.getAndSet(true)) return;

            ItemStack itemStack = this.playerInventory.getItem(skyblockItemType.getSlot());
            SkyblockItem skyblockItem = SkyblockItem.fromItemStack(itemStack);
            if (skyblockItem != null)
                items.put(skyblockItemType, skyblockItem);
        });
        return items;
    }

    @Nullable
    public FullSetBonus getEquippedFullSetBonus() {
        for (SkyblockItem skyblockItem : getInventorySkyblockItems().values()) {
            if (!(skyblockItem instanceof IHasAbility)) {
                continue;
            }
            for (Ability ability : ((IHasAbility) skyblockItem).getAbilities()) {
                if (!(ability instanceof FullSetBonus fullSetBonus)) {
                    continue;
                }
                if (!fullSetBonus.isFullSetEquipped(skyblockPlayer)) {
                    continue;
                }

                return fullSetBonus;
            }
        }
        return null;
    }

    public void addItem(SkyblockItem skyblockItem) {
        this.skyblockPlayer.getPlayer().getInventory().addItem(skyblockItem.toItemStack());
    }

    public int getHeldItemSlot() {
        return this.playerInventory.getHeldItemSlot();
    }

    public void updateItemInSlot(int slot, SkyblockItem skyblockItem) {
        this.playerInventory.setItem(slot, skyblockItem.toItemStack());
    }
    public void updateItemInSlot(int slot, ItemStack skyblockItem) {
        this.playerInventory.setItem(slot, skyblockItem);
    }

    public void updateItemInHand(SkyblockItem skyblockItem) {
        this.playerInventory.setItemInMainHand(skyblockItem.toItemStack());
    }
    public void updateItemInHand(ItemStack skyblockItem) {
        this.playerInventory.setItem(this.getHeldItemSlot(), skyblockItem);
    }

    public void refreshInventory() {
        this.skyblockPlayer.getPlayer().updateInventory();
    }
}