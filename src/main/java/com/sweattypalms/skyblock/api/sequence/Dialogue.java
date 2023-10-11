package com.sweattypalms.skyblock.api.sequence;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

import static com.sweattypalms.skyblock.SkyBlock.getInstance;

public class Dialogue implements SequencedAction {
    private final String text;
    private final Player[] receivers;
    private int delay = 40;
    public Dialogue(String text, Player... receivers) {
        this.text = text;
        this.receivers = receivers;
    }
    public Dialogue(String text, int delay, Player... receivers) {
        this.text = text;
        this.receivers = receivers;
        this.delay = delay;
    }

    @Override
    public void perform(Consumer<SequencedAction> nextAction) {
        for (Player player : receivers) {
            player.sendMessage(text);
        }
        Bukkit.getScheduler().runTaskLater(getInstance(), () -> nextAction.accept(this), delay);
    }
}
