package com.sweattypalms.skyblock;

import com.sweattypalms.skyblock.commands.MainCommandHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Skyblock extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("skyblock")).setExecutor(new MainCommandHandler());
    }

    @Override
    public void onDisable() {
    }
}
