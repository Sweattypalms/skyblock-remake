package com.sweattypalms.skyblock.slayers.events;

import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.slayers.ISlayerMob;
import lombok.Getter;

@Getter
public class SlayerFailEvent extends SlayerEvent {
    private final SlayerFailReason reason;

    public SlayerFailEvent(ISlayerMob slayerMob, SkyblockPlayer skyblockPlayer, SlayerFailReason reason) {
        super(slayerMob, skyblockPlayer);

        this.reason = reason;
    }

    public enum SlayerFailReason {
        WRONG_PLACE("The boss can't be in %s"),
        TIME_PASSED("You couldn't kill the boss in time, noob!"),
        ;

        private final String reason;

        SlayerFailReason(String reason) {
            this.reason = reason;
        }

        @Override
        public String toString() {
            return this.reason;
        }
    }
}
