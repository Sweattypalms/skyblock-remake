package com.sweattypalms.skyblock.api.sequence;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

public class Sequence {
    @Getter
    private final Queue<SequencedAction> actionQueue = new LinkedList<>();
    private Runnable onFinish = () -> {};
    public void onFinishEvent(Runnable onFinish) {
        this.onFinish = onFinish;
    }
    public Sequence add(SequencedAction action) {
        actionQueue.add(action);
        return this;
    }

    public void start() {
        processNextAction();
    }

    public void stop() {
        actionQueue.clear();
    }

    private void processNextAction() {
        if (!actionQueue.isEmpty()) {
            SequencedAction action = actionQueue.poll();
            action.perform(t -> processNextAction());
        } else {
            onFinish.run();
        }
    }

}