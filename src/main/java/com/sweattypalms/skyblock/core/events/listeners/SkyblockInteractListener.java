package com.sweattypalms.skyblock.core.events.listeners;

import com.sweattypalms.skyblock.core.events.SkyblockAbilityUseEvent;
import com.sweattypalms.skyblock.core.events.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerable;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class SkyblockInteractListener implements Listener {
    @EventHandler
    public void onSkyblockInteract(SkyblockInteractEvent event) {
        List<SkyblockItem> items = event.getSkyblockPlayer().getInventoryManager().getInventorySkyblockItems().values().stream().toList();
        items = items.stream().filter(item -> item instanceof IHasAbility).toList();
        List<ITriggerable> abilities =
                items.stream()
                        .map(item -> (IHasAbility) item)
                        .flatMap(iHasAbility -> iHasAbility.getAbilities().stream())
                        .filter(ability -> ability instanceof ITriggerable)
                        .map(ability -> (ITriggerable) ability)
                        .toList();

        abilities.forEach(ability -> {
            if (ability.trigger(event)) {
                SkyblockAbilityUseEvent abilityUseEvent = new SkyblockAbilityUseEvent(event.getSkyblockPlayer(), ability);
                Bukkit.getPluginManager().callEvent(abilityUseEvent);

                if(abilityUseEvent.isCancelled()) return;
                ability.apply(event);
            }
        });
    }
}
