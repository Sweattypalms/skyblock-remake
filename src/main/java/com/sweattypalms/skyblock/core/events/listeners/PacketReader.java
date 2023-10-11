package com.sweattypalms.skyblock.core.events.listeners;

import io.netty.channel.*;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Used to read packets both IN and OUT.
 */
public class PacketReader implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        injectPlayer(e.getPlayer());
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent e) {
        unInjectPlayer(e.getPlayer());
    }

    public void unInjectPlayer(Player p) {
        Channel channel = ((CraftPlayer) p).getHandle().b.a.k;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(p.getName());
            return null;
        });
    }

    public void injectPlayer(Player p) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                super.channelRead(channelHandlerContext, packet);
            }

            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                super.write(ctx, msg, promise);
            }

        };
        ChannelPipeline pipeline = ((CraftPlayer) p).getHandle().b.a.k.pipeline();
        pipeline.addBefore("packet_handler", p.getName(), channelDuplexHandler);
    }
}
