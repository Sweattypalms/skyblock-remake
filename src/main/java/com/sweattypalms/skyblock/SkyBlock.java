package com.sweattypalms.skyblock;

import com.sweattypalms.skyblock.commands.MainCommandHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SkyBlock extends JavaPlugin {

    private static SkyBlock instance;
    

    @Override
    public void onEnable() {
        instance = this;
        long start = System.currentTimeMillis();
         registerListeners();
         registerCommands();
         long end = System.currentTimeMillis() - start;
         getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Skyblock has been enabled! This took " + ChatColor.YELLOW + end + "ms");
    }

    @Override
    public void onDisable() {

    }

    public void registerListeners(){

    }

    public void registerCommands(){
        Objects.requireNonNull(getCommand("test")).setExecutor(new MainCommandHandler());
    }

    public static SkyBlock getInstance() {
        return instance;
    }


}
