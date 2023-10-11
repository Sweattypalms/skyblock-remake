package com.sweattypalms.skyblock.slayers.gui;

import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.ui.BaseGUI;
import com.sweattypalms.skyblock.ui.ClickableItem;
import com.sweattypalms.skyblock.ui.GUIRouter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

public class SlayerSelectorGUI extends BaseGUI {
    private static final int SIZE = 4 * 9;
    public SlayerSelectorGUI() {
        super(SIZE, "Slayer");
    }

    private static final ClickableItem REVENANT_HORROR = getSlayerItem(
            Material.ROTTEN_FLESH,
//            what is yellow color code in minecraft? it is §e
            "$c☠ $eRevenant Horror",
            List.of(
                    "$7Abhorrent Zombie stuck",
                    "$7between life and death for",
                    "$7an eternity.",
                    "$7",
                    "$eClick to view boss!"
            ),
            player -> {
                RevenantHorrorGUI revenantHorrorGUI = new RevenantHorrorGUI();
                GUIRouter.openGUI(player, revenantHorrorGUI);
            }
    );

    @Override
    public void initializeItems(Player player) {
        this.fillBorder(BorderType.ALL);
        this.fillBorder(3, Direction.HORIZONTAL);
        this.setNextItem(REVENANT_HORROR);
        this.setItemAt(5, 4, CLOSE_GUI());
    }

    public static ClickableItem getSlayerItem(Material material, String name, List<String> lore, Consumer<Player> onClick) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(PlaceholderFormatter.format(name));
        meta.setLore(PlaceholderFormatter.format(lore));
        item.setItemMeta(meta);
        return new ClickableItem(item, onClick);
    }
}
