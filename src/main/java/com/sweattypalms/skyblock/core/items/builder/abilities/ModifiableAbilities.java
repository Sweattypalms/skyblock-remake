package com.sweattypalms.skyblock.core.items.builder.abilities;

import java.util.List;

public interface ModifiableAbilities {
    List<Ability> getPossibleAbilities();
    void addAbility(Ability ability);
}
