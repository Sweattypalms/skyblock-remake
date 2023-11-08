package com.sweattypalms.skyblock.core.items.builder.abilities.types;

import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerEvent;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.event.Event;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface FullSetBonus extends Ability, ITriggerableAbility {
    default TriggerType getTriggerType(){
        return TriggerType.NONE;
    }
    default boolean isFullSetEquipped(SkyblockPlayer player){
        return getSimilarAbilitySize(player) == 4;
    }
    default int getSimilarAbilitySize(SkyblockPlayer player){
        List<IHasAbility> armor = new java.util.ArrayList<>(Arrays.stream(Objects.requireNonNull(player.getPlayer().getEquipment()).getArmorContents()).map(_item -> {
            if (_item == null) return null;
            SkyblockItem skyblockItem = SkyblockItem.fromItemStack(_item);
            if (skyblockItem == null) return null;
            if (!(skyblockItem instanceof IHasAbility abilityItem))return null;
            return abilityItem;
        }).toList());

        armor.removeIf(Objects::isNull);

        if (armor.isEmpty()) return 0;

        return armor.stream().filter(abilityItem -> {
            List<Ability> abilities = abilityItem.getAbilities();
            for(Ability ability : abilities) {
                if(ability instanceof FullSetBonus) {
                    return (ability).getName().equals(this.getName());
                }
            }
            return false;
        }).toList().size();
    }

    @Override
    default boolean trigger(Event event){
        if (!(event instanceof SkyblockPlayerEvent skyblockPlayerEvent)) return false;
        return isFullSetEquipped(skyblockPlayerEvent.getSkyblockPlayer());
    }

}
