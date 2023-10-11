package com.sweattypalms.skyblock.api.sequence;

import org.bukkit.Bukkit;

import java.util.function.Consumer;

import static com.sweattypalms.skyblock.SkyBlock.getInstance;

public class SequenceAction implements SequencedAction {
    private final Runnable action;
    private int thisDelay = 0;
    private int nextDelay = 0;

    public SequenceAction(Runnable action) {
        this.action = action;
    }

    public SequenceAction(Runnable action, int delay) {
        this.action = action;
        this.thisDelay = delay;
    }

    public SequenceAction(Runnable action, int thisDelay, int nextDelay) {
        this.action = action;
        this.thisDelay = thisDelay;
        this.nextDelay = nextDelay;
    }

    @Override
    public void perform(Consumer<SequencedAction> nextAction) {
        Bukkit.getScheduler().runTaskLater(getInstance(), action, thisDelay);

        Bukkit.getScheduler().runTaskLater(getInstance(), () -> nextAction.accept(this), thisDelay + nextDelay);
    }
}
