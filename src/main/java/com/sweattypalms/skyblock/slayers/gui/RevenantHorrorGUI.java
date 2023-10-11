package com.sweattypalms.skyblock.slayers.gui;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.Slayer;
import com.sweattypalms.skyblock.slayers.SlayerType;
import com.sweattypalms.skyblock.slayers.events.SlayerStartEvent;
import com.sweattypalms.skyblock.slayers.zombie.RevenantHorrorTierI;
import com.sweattypalms.skyblock.ui.BaseGUI;
import com.sweattypalms.skyblock.ui.ClickableItem;
import com.sweattypalms.skyblock.ui.GUIRouter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class RevenantHorrorGUI extends BaseGUI {

    public RevenantHorrorGUI() {
        super(4 * 9, "Revenant Horror");
    }

    @Override
    public void initializeItems(Player player) {
        this.fillBorder(BorderType.ALL);
        this.fillBorder(2, Direction.VERTICAL);
        this.fillBorder(9-1, Direction.VERTICAL);
        this.fillBorder(3, Direction.HORIZONTAL);
        this.setItemAt(4, 4, BACK_BUTTON());
        this.setItemAt(5, 4, CLOSE_GUI());

        this.setNextItem(getTier1(player));
    }

    public ClickableItem getTier1(Player player){
        SlayerTierClickableItem item = new SlayerTierClickableItem.Builder()
                .withName("Revenant Horror")
                .withMaterial(Material.ROTTEN_FLESH)
                .withDifficulty(SlayerTierClickableItem.Difficulty.BEGINNER)
                .withHealth(500)
                .withDamage(15)
                .withLore(List.of("$7The $lRevenant Horror $7is", "$7a scary mob!"))
                .withReward(5)
                .withRewardType("Zombie Slayer")
                .withCostToStart(2000)
                .withOnClick(clickPlayer -> {
                    Slayer slayer = new Slayer(SlayerType.REVENANT_HORROR,1, RevenantHorrorTierI.ID, 150);
                    SlayerStartEvent event = new SlayerStartEvent(SkyblockPlayer.getSkyblockPlayer(clickPlayer), slayer);
                    Bukkit.getPluginManager().callEvent(event);
                    GUIRouter.closeCurrentGUI(clickPlayer);
                })
                .build();

        return item.forPlayer(player);
    }
}
