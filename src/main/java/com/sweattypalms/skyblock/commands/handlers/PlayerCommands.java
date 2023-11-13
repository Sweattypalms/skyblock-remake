package com.sweattypalms.skyblock.commands.handlers;

import com.sweattypalms.skyblock.commands.Command;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.gui.SlayerSelectorGUI;
import com.sweattypalms.skyblock.ui.GUIRouter;
import com.sweattypalms.skyblock.ui.guis.BinGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerCommands {
    public static String BATPHONE_COMMAND = "?slayer_gui";
    @Command(name = "?slayer_gui", description = "Opens the slayer menu")
    public void slayerCommand(Player player, String[] args) {
        SlayerSelectorGUI slayerSelectorGUI = new SlayerSelectorGUI();
        GUIRouter.openGUI(player, slayerSelectorGUI);
    }


    @Command(name = "hub", description = "Hub command")
    public void hubCommand(Player player){
        World world = Bukkit.getWorld("skyblock_hub");
        assert world != null;
        player.teleport(world.getSpawnLocation());
    }

    @Command(name = "warp" , description = "Warp command")
    public void warpCommand(Player player, String[] args) {
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        if (args.length == 0) {
            skyblockPlayer.sendMessage("$cUsage: /warp <island>");
            return;
        }

        String islandName = args[0].toLowerCase();

        switch (islandName) {
            case "hub" -> teleportToIsland(player, "hub");
            case "end" -> teleportToIsland(player, "end");
            default ->  {
                skyblockPlayer.sendMessage("$cThat island does not exist!");
                return;
            }
        }

        skyblockPlayer.sendMessage(String.format("$aSending to server mini%s", islandName.hashCode()));
        skyblockPlayer.sendMessage("$8pretend it's an actual server");
    }

    private void teleportToIsland(Player player, String islandName) {
        World world = Bukkit.getWorld("skyblock_" + islandName);

        if (world == null) {
            SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
            skyblockPlayer.sendMessage("$cThat island does not exist!");

            if(player.isOp()) {
                skyblockPlayer.sendMessage("$cThis is supposed to be an island.\nIt's possible that the world is not loaded or doesn't exist.");
            }

            return;
        }

        Location location = world.getSpawnLocation();
        player.teleport(location);
    }

    @Command(name="credits", description="Credits command")
    public void creditsCommand(Player player) {
        SkyblockPlayer skyblockPlayer = SkyblockPlayer.getSkyblockPlayer(player);
        String obfus = "§c§l§kU";

        String sweattypalms = obfus + "§c§l Sweattypalms " + obfus;

        skyblockPlayer.sendMessage("$aThis server was made by " + sweattypalms + "$a.");
    }

    @Command(name = "bin", description = "Opens the trash menu")
    public void binCommand(Player player) {
        player.sendMessage(ChatColor.RED + "This command is disabled for now!");
         GUIRouter.openGUI(player, new BinGUI());
    }
}
