package com.sweattypalms.skyblock.core.mobs.builder.dragons;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.api.Point;
import com.sweattypalms.skyblock.api.sequence.Sequence;
import com.sweattypalms.skyblock.api.sequence.SequenceAction;
import com.sweattypalms.skyblock.core.helpers.MozangStuff;
import com.sweattypalms.skyblock.core.helpers.PDCHelper;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.ItemManager;
import com.sweattypalms.skyblock.core.items.types.end.items.RemnantOfTheEye;
import com.sweattypalms.skyblock.core.items.types.end.items.SummoningEye;
import com.sweattypalms.skyblock.core.items.types.end.items.UsedSummoningEye;
import com.sweattypalms.skyblock.core.mobs.builder.MobManager;
import com.sweattypalms.skyblock.core.mobs.builder.NameAttributes;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import com.sweattypalms.skyblock.core.mobs.regions.end.dragons.StrongDragon;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

/**
 * Singleton class that manages all dragons in the end.
 */
public class DragonManager {
    // (relative to 0,0)
    private final static List<Point> altarPoints = List.of(
            new Point(2, 1),
            new Point(2, -1),

            new Point(1, 2),
            new Point(1, -2),

            new Point(-1, 2),
            new Point(-1, -2),

            new Point(-2, 1),
            new Point(-2, -1)
    );
    public static Map<Location, Material> temp_save_backup = new HashMap<>();
    private static World endWorld; // TODO: implement better system
    private static DragonManager instance;
    private final Map<Block, UUID> altarBlocks = new HashMap<>();

    private int summoningEyes = 0;
    private SkyblockMob dragon;
    private final Map<UUID, Double> playerDamage = new HashMap<>();

    public double getPlayerDamage(UUID playerUUID) {
        return this.playerDamage.getOrDefault(playerUUID, 0d);
    }

    public void addPlayerDamage(UUID playerUUID, double damage) {
        double before = this.playerDamage.getOrDefault(playerUUID, 0.0);
        this.playerDamage.put(playerUUID, before + damage);
    }

    public DragonManager() {
        endWorld = Bukkit.getWorld("skyblock_end");
        instance = this;
    }

    public static DragonManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Cannot get instance of a singleton class before it is instantiated");
        }
        return instance;
    }

    public void addSummoningEye(SkyblockPlayer player, Location location) {
        if (summoningEyes >= altarPoints.size()) {
            String message = "$cThe altar is full!";
            player.sendMessage(message);
            return;
        }

        Block altarBlock = location.getBlock();
        EndPortalFrame endPortalFrame = (EndPortalFrame) altarBlock.getBlockData();


        if (endPortalFrame.hasEye()) {
            String message = "$cSorry, there is already an eye here!";
            player.sendMessage(message);
            return;
        }

        if (this.dragon != null) {
            String message = "$cThere is already a dragon in place!";
            player.sendMessage(message);
            return;
        }

        summoningEyes++;

        String defaultMessage = "$5⇒ %s $5placed a Summoning Eye! $7(%s%d$7/$a%d$7)";
        String message = PlaceholderFormatter.format(String.format(defaultMessage, player.getPlayer().getDisplayName(), summoningEyes == altarPoints.size() ? "$a" : "$e", summoningEyes, altarPoints.size()));
        String myMessage = PlaceholderFormatter.format(String.format(defaultMessage, "You", summoningEyes == altarPoints.size() ? "$a" : "$e", summoningEyes, altarPoints.size()));
        assert endWorld != null;
        endWorld.getPlayers().stream().filter(_player -> _player.getUniqueId() != player.getPlayer().getUniqueId()).forEach(_player -> _player.sendMessage(message));
        player.getPlayer().sendMessage(myMessage);

        altarBlocks.put(altarBlock, player.getPlayer().getUniqueId()); // Will be used for taking out eyes.

        endPortalFrame.setEye(true);

        altarBlock.setBlockData(endPortalFrame);

        ItemStack summoningEye = ItemManager.getItemStack(UsedSummoningEye.ID);
        player.getPlayer().getInventory().setItemInMainHand(summoningEye);

        if (summoningEyes == altarPoints.size()) {
            altarBlocks.forEach((block, uuid) -> {
                Player _player = Bukkit.getPlayer(uuid);
                if (_player != null) {
                    for (ItemStack item : _player.getInventory().getContents()) {
                        if (item != null && PDCHelper.getString(item, "id") != null && PDCHelper.getString(item, "id").equals(UsedSummoningEye.ID)) {
                            ItemStack remnantOfTheEye = ItemManager.getItemStack(RemnantOfTheEye.ID);
                            _player.getInventory().remove(item);
                            _player.getInventory().addItem(remnantOfTheEye);
                            break; // Remove only one eye per player
                        }
                    }
                    _player.updateInventory();
                }
            });

            this.startEyeAnimation();
        }
    }

    public void startEyeAnimation() { // Shows the eye animation that goes upwards
        List<ArmorStand> eyes = new ArrayList<>();
        if (this.altarBlocks.size() == 8) {
            for (Block block : this.altarBlocks.keySet()) {
                Location loc = block.getLocation();
                loc = loc.clone();
                if (loc.getWorld() == null) continue;
                ArmorStand as = loc.getWorld().spawn(loc.clone().add(0.5, -1, 0.5), ArmorStand.class);
                as.setVisible(false);
                as.setMarker(true);
                as.getEyeLocation().setPitch(90); // Looking up
                as.setGravity(false);

                EntityEquipment equipment = as.getEquipment();
                assert equipment != null;
                equipment.setHelmet(ItemManager.getItemStack(SummoningEye.ID));
                eyes.add(as);
                EndPortalFrame endPortalFrame = (EndPortalFrame) loc.getBlock().getBlockData();
                endPortalFrame.setEye(false);
            }
            new BukkitRunnable() {

                double y = 0;

                @Override
                public void run() {
                    for (ArmorStand as : eyes) {
                        as.teleport(as.getLocation().add(0, 0.05, 0));
                    }

                    if (y >= 4) {
                        for (ArmorStand as : eyes) {
                            as.remove();
                        }
                        this.cancel();
                        // Now running the block animation and cancelling this task!
                        DragonManager.this.summonDragon();
                        return;
                    }
                    y += 0.05;
                }
            }.runTaskTimer(SkyBlock.getInstance(), 0L, 1L);
        }
    }


    public void removeSummoningEye(SkyblockPlayer player, Location location) {
        if (!altarBlocks.containsKey(location.getBlock())) return;
        if (!altarBlocks.get(location.getBlock()).equals(player.getPlayer().getUniqueId())) {
            String message = "$cYou cannot take out this eye!";
            message = PlaceholderFormatter.format(message);
            player.getPlayer().sendMessage(message);
            return;
        }

        Block altarBlock = location.getBlock();
        EndPortalFrame endPortalFrame = (EndPortalFrame) altarBlock.getBlockData();
        endPortalFrame.setEye(false);
        altarBlock.setBlockData(endPortalFrame);
        summoningEyes--;

        altarBlocks.remove(location.getBlock());

        ItemStack summoningEye = ItemManager.getItemStack(SummoningEye.ID);
        player.getPlayer().getInventory().setItemInMainHand(summoningEye);
    }

    private void summonDragon() {
        assert endWorld != null;

//        -8, 34, -8
//        -8 , 34, 9
//        9, 34, 8
//        9, 34, -8

        // diagonal
        final Location START_LOCATION = new Location(endWorld, -8, 34, -8);
        final Location END_LOCATION = new Location(endWorld, 9, 34, 8);

        Sequence sequence = new Sequence();

        int allStart = 34;
        for (int y = 28; y <= 68; y++) {
            Block blockAtLoc = endWorld.getBlockAt(0, y, 0);
            if (blockAtLoc.getType() == Material.PURPLE_WOOL) {
                break;
            }
            int finalY = y;

            sequence.add(new SequenceAction(() -> {
                for (int x = START_LOCATION.getBlockX(); x <= END_LOCATION.getBlockX(); x++) {
                    for (int z = START_LOCATION.getBlockZ(); z <= END_LOCATION.getBlockZ(); z++) {
                        Location loc = new Location(endWorld, x, finalY, z);
                        if (loc.getBlock().getType() == Material.AIR) continue;
                        if (finalY > allStart) {
                            if (checkSolidBlock(loc.getBlock())) continue;
                            temp_save_backup.put(loc.getBlock().getLocation(), loc.getBlock().getType());
                            endWorld.getBlockAt(loc).setType(Material.AIR);
                        }
                        if (x == 0 && z == 0) {
                            if (checkSolidBlock(loc.getBlock())) continue;
                            temp_save_backup.put(loc.getBlock().getLocation(), Material.PURPLE_STAINED_GLASS);
                            endWorld.getBlockAt(loc).setType(Material.AIR);
                            loc.clone().add(0, 1, 0).getBlock().setType(Material.PURPLE_STAINED_GLASS);
                            if (loc.clone().add(0, 2, 0).getBlock().getType() == Material.PURPLE_WOOL) {
                                break;
                            }
                            loc.clone().add(0, 2, 0).getBlock().setType(Material.SEA_LANTERN);
                        }
                    }
                }
            }, 4));
        }

        sequence.add(new SequenceAction(() -> {
            Location eggCenter = new Location(endWorld, 0, 83, 0);

            List<FallingBlock> blocks = new ArrayList<>();
            for (int y = 68; y < 100; y++) {
                for (int x = START_LOCATION.getBlockX(); x <= END_LOCATION.getBlockX(); x++) {
                    for (int z = START_LOCATION.getBlockZ(); z <= END_LOCATION.getBlockZ(); z++) {
                        Location loc = new Location(endWorld, x, y, z);
                        if (loc.getBlock().getType() == Material.AIR) continue;
                        if (checkSolidBlock(loc.getBlock())) continue;
                        temp_save_backup.put(loc.getBlock().getLocation(), loc.getBlock().getType());
                        blocks.add(convertToFalling(loc, loc.getBlock().getType()));
                    }
                }
            }

            simulateExplosion(blocks, eggCenter);

            TNTPrimed tnt = endWorld.spawn(eggCenter, TNTPrimed.class);
            tnt.setFuseTicks(0); // Explode immediately.
            tnt.setYield(0f); // No block damage.
            tnt.setIsIncendiary(false); // No fire.
        }, 5));

        sequence.add(new SequenceAction(
                () -> {
                    String summonMessage = String.format("$5⇒ $5c$l%s $5Dragon Spawned!", "Strong");
                    summonMessage = PlaceholderFormatter.format(summonMessage);
                    String finalSummonMessage = PlaceholderFormatter.format(summonMessage);
                    endWorld.getPlayers().forEach(player -> player.sendMessage(finalSummonMessage));
                    SkyblockMob dragon = MobManager.getInstance(StrongDragon.ID);
                    dragon.spawn(new Location(endWorld, 0, 85, 0));
                    this.dragon = dragon;
                },
                30
        ));

        sequence.start();
        Location soundLoc = new Location(endWorld, 0, 35, 0);
        endWorld.playSound(soundLoc, Sound.ENTITY_ENDER_DRAGON_DEATH, 8, 1);

    }

    private FallingBlock convertToFalling(Location loc, Material material) {
        FallingBlock fallingBlock = loc.getWorld().spawnFallingBlock(loc, material.createBlockData());
        fallingBlock.setDropItem(false);
        fallingBlock.setHurtEntities(false);
        fallingBlock.setInvulnerable(true);
        loc.getBlock().setType(Material.AIR);
        return fallingBlock;
    }

    private void simulateExplosion(List<FallingBlock> blocks, Location center) {
        for (FallingBlock block : blocks) {
            Vector direction = block.getLocation().subtract(center).toVector().normalize().multiply(2);
            direction.setY(0.5); // A slight upward direction.
            block.setVelocity(direction);
        }
    }

    private boolean checkSolidBlock(Block block) {
        BlockFace[] faces = {BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN};

        for (BlockFace face : faces) {
            if (block.getRelative(face).getType() == Material.AIR) {
                return false;
            }
        }
        return true;
    }

    public void onEnderDragonDeath() {
        this.summoningEyes = 0;
        this.dragonDownMessage(this.dragon.getEntityInstance().getKiller());
        this.dragon.despawn();
        this.dragon = null;
        this.altarBlocks.keySet().forEach(block -> {
            EndPortalFrame endPortalFrame = (EndPortalFrame) block.getBlockData();
            endPortalFrame.setEye(false);
            block.setBlockData(endPortalFrame);
        });
        this.altarBlocks.clear();
        this.playerDamage.clear();

        temp_save_backup.forEach((loc, type) -> loc.getBlock().setType(type));
    }

    public void dragonDownMessage(Player killer) {
        Map<UUID, Double> damage = MozangStuff.sortByValue(this.playerDamage);
        List<UUID> damagers = new ArrayList<>(damage.keySet());

        String green = PlaceholderFormatter.format("$a");
        String yellow = PlaceholderFormatter.format("$e");
        String gold = PlaceholderFormatter.format("$6");
        String bold = PlaceholderFormatter.format("$l");
        String gray = PlaceholderFormatter.format("$7");
        String red = PlaceholderFormatter.format("$c");
        String light_purple = PlaceholderFormatter.format("$d");

        for (Player p : Bukkit.getOnlinePlayers()) {
            int playerRank = damagers.indexOf(p.getUniqueId()) + 1;
            String yourDamage = getDamageString(damage, p.getUniqueId());
            String rankColor = getRankColor(playerRank).toString();

            p.sendMessage(green + "----------------------------------------------------");
            p.sendMessage(gold + "                   " + bold + dragon.getNameAttribute(NameAttributes.CUSTOM_NAME).toString().toUpperCase() + " DOWN!");
            p.sendMessage("");
            p.sendMessage(green + "                " + killer.getName() + gray + " dealt the final blow.");
            p.sendMessage("");

            sendDamagerInfo(p, damagers, damage, 1, yellow);
            sendDamagerInfo(p, damagers, damage, 2, gold);
            sendDamagerInfo(p, damagers, damage, 3, red);

            p.sendMessage("");
            p.sendMessage(yellow + "          Your Damage: " + rankColor + yourDamage + rankColor + " (Position #" + playerRank + ")");
            p.sendMessage(yellow + "               Runecrafting Experience: " + light_purple + "0" + red + " (WIP)");
            p.sendMessage("");
            p.sendMessage(green + "----------------------------------------------------");
        }
    }

    private String getDamageString(Map<UUID, Double> damage, UUID playerId) {
        try {
            return PlaceholderFormatter.formatDecimalCSV(damage.get(playerId));
        } catch (IllegalArgumentException ignored) {
            return "0";
        }
    }

    private ChatColor getRankColor(int rank) {
        return switch (rank) {
            case 1 -> ChatColor.GOLD;
            case 2 -> ChatColor.YELLOW;
            case 3 -> ChatColor.RED;
            default -> ChatColor.GRAY;
        };
    }

    private void sendDamagerInfo(Player p, List<UUID> damagers, Map<UUID, Double> damage, int rank, String color) {
        String rankName = rank == 1 ? "1st" : (rank == 2 ? "2nd" : "3rd");
        if (damagers.size() >= rank) {
            UUID damagerId = damagers.get(rank - 1);
            String damagerName = Bukkit.getOfflinePlayer(damagerId).getName();
            String damageAmount = PlaceholderFormatter.formatDecimalCSV(damage.get(damagerId));
            p.sendMessage(color + "          " + PlaceholderFormatter.format("$l") + rankName + " Damager" + PlaceholderFormatter.format("$7") + " - " + damagerName + PlaceholderFormatter.format("$7") + " - " + PlaceholderFormatter.format("$e") + damageAmount);
        } else {
            p.sendMessage(color + "          " + PlaceholderFormatter.format("$l") + rankName + " Damager" + PlaceholderFormatter.format("$7") + " - N/A");
        }
    }

    private Location pointToLocation(Point point) {
        return new Location(endWorld, point.getX(), 30, point.getZ());
    }

    public boolean isDragonActive() {
        return dragon != null;
    }

    public SkyblockMob getActiveDragon() {
        return dragon;
    }
}
