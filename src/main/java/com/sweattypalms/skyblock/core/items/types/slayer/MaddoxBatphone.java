package com.sweattypalms.skyblock.core.items.types.slayer;

import com.sweattypalms.skyblock.api.sequence.Sequence;
import com.sweattypalms.skyblock.api.sequence.SequenceAction;
import com.sweattypalms.skyblock.commands.handlers.PlayerCommands;
import com.sweattypalms.skyblock.core.events.def.SkyblockInteractEvent;
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerEvent;
import com.sweattypalms.skyblock.core.items.builder.Rarity;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItem;
import com.sweattypalms.skyblock.core.items.builder.SkyblockItemType;
import com.sweattypalms.skyblock.core.items.builder.abilities.Ability;
import com.sweattypalms.skyblock.core.items.builder.abilities.IHasAbility;
import com.sweattypalms.skyblock.core.items.builder.abilities.TriggerType;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ICooldown;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.ITriggerableAbility;
import com.sweattypalms.skyblock.core.items.builder.armor.IHeadHelmet;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import com.sweattypalms.skyblock.core.player.sub.stats.Stats;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MaddoxBatphone extends SkyblockItem implements IHeadHelmet, IHasAbility {

    public static final String ID = "maddox_batphone";
    private static final HashMap<Stats, Double> stats = new HashMap<>();

    public MaddoxBatphone() {
        super(
                "maddox_batphone",
                "Maddox Batphone",
                Material.PLAYER_HEAD,
                null,
                new HashMap<>(),
                Rarity.UNCOMMON,
                SkyblockItemType.NONE
        );
    }

    @Override
    public String getTexture() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTMzNmQ3Y2M5NWNiZjY2ODlmNWU4Yzk1NDI5NGVjOGQxZWZjNDk0YTQwMzEzMjViYjQyN2JjODFkNTZhNDg0ZCJ9fX0=";
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(new Whassup());
    }

    public class Whassup implements ITriggerableAbility, ICooldown {

        private static final Random random = new Random();

        @Override
        public String getName() {
            return "Whassup?";
        }

        @Override
        public List<String> getDescription() {
            return List.of(
                    "$7Lets you call $5Maddox$7, when",
                    "$7he's not busy."
            );
        }

        @Override
        public void apply(Event _event) {
            ICooldown.super.apply(_event);
            SkyblockPlayerEvent event = (SkyblockInteractEvent) _event;
            SkyblockPlayer player = event.getSkyblockPlayer();
            Sequence sequence = new Sequence();
            for (int time = 0; time < 3; time++) {
                int finalTime = time;
                sequence.add(new SequenceAction(() -> {
                    String base = "$e✆ ";
                    String ring = "Ring... ";
                    String message = base + ring.repeat(finalTime + 1);
                    event.getSkyblockPlayer().sendMessage(message);

                    Sequence soundSequence = new Sequence();

                    for (AtomicInteger i = new AtomicInteger(0); i.getAndIncrement()<6;) {
                        soundSequence.add(new SequenceAction(() -> player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, i.get()*5), 2));
                    }

                    soundSequence.start();

                }, 0, 20));
            }
            long cooldown = 17 * 1000;
            long lastUse = player.getSlayerManager().getLastMaddoxBatphoneUse();

            String[] possibleMessages = {
                    "✆ Please leave your message after the beep.",
                    "✆ How can you tell if a bee is on the phone? You get a buzzy signal!",
                    "✆ The phone keeps ringing, is it broken?",
                    "✆ The phone picks up but it immediately hangs up!",
                    "✆ What did the cat say on the phone? Can you hear meow?",
                    "✆ No answer.",
                    "✆ Seems like it's not picking up!",
                    "✆ \"Your call is important to us, please stay on the line\", so you hang up."
            };

            if (lastUse + cooldown > System.currentTimeMillis()) {
                player.getSlayerManager().addFailedBatphoneAttempt();

                if (player.getSlayerManager().getFailedBatphoneAttempts() >= 3) {
                    sequence.add(new SequenceAction(() -> player.sendMessage("$c✆ HEY IT'S NOT PICKING UP STOP TRYING!"), 0));
                } else {
                    String selectedMessage = possibleMessages[random.nextInt(possibleMessages.length)];
                    sequence.add(new SequenceAction(() -> player.sendMessage("$c" + selectedMessage), 0));
                }
                sequence.start();
                return;
            }

            String[] possibleGoodMessages = {
                    "✆ Hello? ",
                    "✆ Someone answers! ",
                    "✆ How does a lobster answer? Shello! ",
                    "✆ Hey what do you need? ",
                    "✆ You hear the line pick up... ",
                    "✆ You again? What do you want this time? "
            };

            // minecraft color code: §
            TextComponent textComponent = new TextComponent("§a§l[OPEN MENU]");
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + PlayerCommands.BATPHONE_COMMAND));

            TextComponent goodMessage = new TextComponent("§a" + possibleGoodMessages[random.nextInt(possibleGoodMessages.length)]);

            sequence.add(new SequenceAction(() -> {
                player.getPlayer().spigot().sendMessage(ChatMessageType.CHAT, goodMessage, textComponent);
                player.getSlayerManager().setLastMaddoxBatphoneUse(System.currentTimeMillis());
                player.getSlayerManager().setFailedBatphoneAttempts(0);
            }, 0));

            sequence.start();
        }

        @Override
        public TriggerType getTriggerType() {
            return TriggerType.RIGHT_CLICK;
        }

        @Override
        public boolean trigger(Event event) {
            return event instanceof SkyblockInteractEvent skyblockInteractEvent
                    && !isOnCooldown(skyblockInteractEvent.getSkyblockPlayer());
        }

        @Override
        public long getCooldown(SkyblockPlayer skyblockPlayer) {
            return 5 * 1000; // 0.5 seconds
        }
    }
}
