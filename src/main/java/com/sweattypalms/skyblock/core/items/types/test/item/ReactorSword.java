package com.sweattypalms.skyblock.core.items.types.test.item;

import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IPersonalizedDescription;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * A sword that shows the players' stats in the lore
 */
public class ReactorSword extends SkyblockItem implements IHasAbility, IPersonalizedDescription {
    public static final String ID = "reactor_sword";
    private static final Map<Stats, Double> stats = new HashMap<>(Map.of(
            Stats.HEALTH, 336699d
    ));

    public ReactorSword() {
        super(
                ID,
                "Reactor Sword",
                Material.DIAMOND_SWORD,
                null,
                stats,
                Rarity.SPECIAL,
                SkyblockItemType.SWORD
        );
    }

    @Override
    public List<Ability> getAbilities() {
        return Collections.emptyList();
    }

    @Override
    public List<String> getDescription(SkyblockPlayer skyblockPlayer, ItemStack executor) {
        SkyblockItem item = SkyblockItem.get(PDCHelper.getString(executor, "id"));
        String itemIdentifier = String.format("%s", item.f_getDisplayName());

        List<String> lore = new ArrayList<>(
                List.of("$cYou're holding $9" + itemIdentifier + "$c!",
                        "",
                        "$7Look at yo $cstats!",
                        ""
                ));

        Arrays.stream(Stats.values()).toList().forEach(stat -> {
            lore.add(String.format("%s %s: %s", stat.getSymbol(), stat.getName(), PlaceholderFormatter.formatDecimalCSV(skyblockPlayer.getStatsManager().getMaxStat(stat))));
        });

        lore.addAll(List.of(
                "$6",
                "$6That's all I know!"
        ));

        return lore;
    }
}
