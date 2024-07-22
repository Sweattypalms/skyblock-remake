package com.sweattypalms.skyblock.core.items.builder.abilities.types;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;

/**
 * Unlike {@link DamageAbility}, this ability is triggered when player receives damage.
 */
public interface RecvDamageAbility extends Ability, ITriggerableAbility {
    /**
     * @return If ability will be executed before damage calculation or after.
     */
    boolean preCalc();
    default TriggerType getTriggerType(){
        return TriggerType.NONE;
    }
}
