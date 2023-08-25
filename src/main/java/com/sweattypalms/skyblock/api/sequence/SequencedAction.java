package com.sweattypalms.skyblock.api.sequence;

import java.util.function.Consumer;

public interface SequencedAction {
    void perform(Consumer<SequencedAction> nextAction);
}