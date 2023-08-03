package com.sweattypalms.skyblock.core.items.builder;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArmorSet {
    private List<SkyblockArmor> armorPieces;

    public ArmorSet(List<SkyblockArmor> armorPieces){
        this.armorPieces = armorPieces;
    }

    public boolean isFullSet(Set<SkyblockArmor> equippedArmor){
        return equippedArmor.containsAll(this.armorPieces);
    }

    public List<Ability> getAbilities() {
        return armorPieces.stream().flatMap(p -> p.getAbilities().stream()).collect(Collectors.toList());
    }
}
