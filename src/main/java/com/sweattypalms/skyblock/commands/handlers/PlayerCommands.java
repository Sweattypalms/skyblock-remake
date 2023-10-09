package com.sweattypalms.skyblock.commands.handlers;

import com.sweattypalms.skyblock.commands.Command;
import com.sweattypalms.skyblock.slayers.gui.SlayerSelectorGUI;
import com.sweattypalms.skyblock.ui.GUIRouter;
import org.bukkit.entity.Player;

public class PlayerCommands {
    public static String BATPHONE_COMMAND = "???The_reason_for_it_being_so_big_is_because_I_have_enough_space_to_fit_the_entire_bee_movie_script_in_here_also_so_that_you_can't_type_it_in_chat_sorry_little_one_joy_emoji_probably_idfk_lmfao_this_is_so_long_why_amI_doing_this_tf_just_quit_bro";
    @Command(name = "???The_reason_for_it_being_so_big_is_because_I_have_enough_space_to_fit_the_entire_bee_movie_script_in_here_also_so_that_you_can't_type_it_in_chat_sorry_little_one_joy_emoji_probably_idfk_lmfao_this_is_so_long_why_amI_doing_this_tf_just_quit_bro", description = "Opens the slayer menu")
    public void slayerCommand(Player player, String[] args) {
        SlayerSelectorGUI slayerSelectorGUI = new SlayerSelectorGUI();
        GUIRouter.openGUI(player, slayerSelectorGUI);
    }
}
