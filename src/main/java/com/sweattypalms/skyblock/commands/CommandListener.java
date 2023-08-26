package com.sweattypalms.skyblock.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandListener implements Listener {
    private final CommandRegistry registry;

    public CommandListener() {
        this.registry = CommandRegistry.getInstance();
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String[] split = event.getMessage().split(" ");
        String command = split[0].substring(1);  // Remove the leading "/"
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        if (registry.executeCommand(event.getPlayer(), command, args)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onTabCompleteEvent(TabCompleteEvent event){
        String buffer = event.getBuffer(); // Returns complete string. ex) "/test 1 2 3"

        String baseCommand = buffer.split(" ")[0].substring(1); // Returns the base command. ex) "test"
        // check if the end is " " for the tab completion thing you know what i mean
        boolean isEndSpace = buffer.endsWith(" ");
        String[] args = buffer.split(" "); // Returns the arguments. ex) ["/test", "1", "2", "3"]
        args = Arrays.copyOfRange(args, 1, args.length); // Remove the base command from the arguments. ex) ["1", "2", "3"]
        List<String> modified = new ArrayList<>(Arrays.asList(args));
        if (isEndSpace) {
            modified.add("");
        }
        List<String> completions = registry.handleTabCompletion(event.getSender(), baseCommand, modified.toArray(new String[args.length + (isEndSpace ? 1 : 0)]));
        event.setCompletions(completions);
    }

}
