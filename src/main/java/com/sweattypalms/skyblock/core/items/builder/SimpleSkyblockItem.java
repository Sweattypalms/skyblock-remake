package com.sweattypalms.skyblock.core.items.builder;

import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import lombok.Getter;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;

import static com.sweattypalms.skyblock.core.items.ItemManager.ITEMS_LIST;

@Getter
public class SimpleSkyblockItem extends SkyblockItem implements IHasAbility {
    private final List<Ability> abilities;
    public SimpleSkyblockItem(Builder<?> builder) {
        super(
                builder.id,
                builder.displayName,
                builder.material,
                builder.staticLore,
                builder.stats,
                builder.baseRarity,
                builder.itemType
        );
        this.abilities = builder.abilities == null ? List.of() : builder.abilities;
        ITEMS_LIST.put(this.getId(), this);
    }

    public SkyblockItem addAbility(Ability ability) {
        this.abilities.add(ability);
        return this;
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends Builder<T>>{
        private String id;
        private String displayName;
        private Material material;
        private List<String> staticLore;
        private Map<Stats, Double> stats;
        private Rarity baseRarity;
        private SkyblockItemType itemType;
        private List<Ability> abilities;

        public T id(String id) {
            this.id = id;
            return self();
        }

        public T displayName(String displayName) {
            this.displayName = displayName;
            return self();
        }

        public T material(Material material) {
            this.material = material;
            return self();
        }

        public T staticLore(List<String> staticLore) {
            this.staticLore = staticLore;
            return self();
        }

        public T stats(Map<Stats, Double> stats) {
            this.stats = stats;
            return self();
        }

        public T baseRarity(Rarity baseRarity) {
            this.baseRarity = baseRarity;
            return self();
        }

        public T itemType(SkyblockItemType itemType) {
            this.itemType = itemType;
            return self();
        }

        public T abilities(List<Ability> abilities) {
            this.abilities = abilities;
            return self();
        }

        // this is unchecked, but safe in this context
        @SuppressWarnings("unchecked")
        protected T self() {
            return (T) this;
        }

        public SimpleSkyblockItem build() {
            return new SimpleSkyblockItem(this);
        }
    }
}
