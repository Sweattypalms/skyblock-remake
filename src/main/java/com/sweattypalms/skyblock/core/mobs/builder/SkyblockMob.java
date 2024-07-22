package com.sweattypalms.skyblock.core.mobs.builder;

import com.sweattypalms.skyblock.SkyBlock;
import com.sweattypalms.skyblock.core.events.def.SkyblockPlayerDamageEntityEvent;
import com.sweattypalms.skyblock.core.helpers.PlaceholderFormatter;
import com.sweattypalms.skyblock.core.items.builder.abilities.types.IHasAbilityDamage;
import com.sweattypalms.skyblock.core.player.SkyblockPlayer;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.protocol.game.PacketPlayOutEntityStatus;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SkyblockMob {

    @Getter
    public final String id;
    private final Class<? extends ISkyblockMob> nmsClass;
    private final Queue<ArmorStand> damageIndicators = new ConcurrentLinkedQueue<>();

    private final Map<MobAttributes, Object> attributes = MobAttributes.getDefault();
    private final Map<NameAttributes, Object> nameAttributes = NameAttributes.getDefault();
    Runnable onSpawn;
    @Getter
    @Setter
    private MobLoot mobLoot;
    @Getter
    @Setter
    private LivingEntity entityInstance;
    @Getter
    private SkyblockPlayer lastDamager;

    public SkyblockMob(String id, Class<? extends ISkyblockMob> nmsClass) {
        this.id = id;
        this.nmsClass = nmsClass;
    }

    public static SkyblockMob getSkyblockMob(LivingEntity livingEntity) {
        EntityLiving entityLiving = ((CraftLivingEntity) livingEntity).getHandle();
        if (entityLiving instanceof ISkyblockMob skyblockMob) {
            return skyblockMob.getSkyblockMob();
        }
        return null;
    }

    public void onSpawn(Runnable onSpawn) {
        this.onSpawn = onSpawn;
    }

    public void spawn(Location location) {
        if (entityInstance != null) return;

        if (nmsClass == null) return;
        try {
            Constructor<? extends ISkyblockMob> constructor = nmsClass.getConstructor(Location.class, SkyblockMob.class);
            ISkyblockMob _skyblockMob = constructor.newInstance(location, this);
            EntityLiving _entityLiving = _skyblockMob.getEntityInstance();

            ((CraftWorld) location.getWorld()).getHandle().addEntity(_entityLiving);
            _entityLiving.setPosition(location.getX(), location.getY(), location.getZ());

            this.entityInstance = (LivingEntity) _entityLiving.getBukkitEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initMob();
        heartbeat();

        if (onSpawn != null) onSpawn.run();
    }

    private void initMob() {
        LivingEntity entity = this.entityInstance;
        entity.setCustomNameVisible(getNameAttribute(NameAttributes.CUSTOM_NAME_VISIBLE));
        entity.setRemoveWhenFarAway(false);
        entity.setCanPickupItems(false);
        entity.setAI(getAttribute(MobAttributes.AI_ENABLED));
        entity.setMaximumNoDamageTicks(0);
        entity.setNoDamageTicks(0);
        setEntityInstanceMaxHealth(getAttribute(MobAttributes.MAX_HEALTH));
    }

    private void heartbeat() {
        new BukkitRunnable() {
            final SkyblockMob capture = SkyblockMob.this;

            @Override
            public void run() {
                if (entityInstance == null || entityInstance.isDead() || entityInstance.getHealth() < 1) {
                    capture.despawn();
                    cancel();
                    return;
                }
                AttributeInstance healthAttribute = entityInstance.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                assert healthAttribute != null;
                healthAttribute.setBaseValue(getAttribute(MobAttributes.MAX_HEALTH));


                entityInstance.setAI(getAttribute(MobAttributes.AI_ENABLED));
                if (getAttribute(MobAttributes.FROZEN)) {
                    PotionEffect _frozenPotionEffect = new PotionEffect(PotionEffectType.SLOW, 15, 255, false, false);
                    entityInstance.addPotionEffect(_frozenPotionEffect);
                }

                AttributeInstance speedAttribute = entityInstance.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                if (speedAttribute != null)
                    speedAttribute.setBaseValue((int) getAttribute(MobAttributes.SPEED) / 500f);

                refreshName();
            }
        }.runTaskTimer(SkyBlock.getInstance(), 0, 10);
    }

    private void refreshName() {
        String levelComponent = this.getNameAttribute(NameAttributes.SHOW_LEVEL) ? "$8[$7Lv" + getAttribute(MobAttributes.LEVEL) + "$8] " : "";

        double maxHealth = getAttribute(MobAttributes.MAX_HEALTH);
        String healthColour =
                entityInstance.getHealth() > maxHealth * 0.66 ? "$a" :
                        entityInstance.getHealth() > maxHealth * 0.33 ? "$e" :
                                "$c";


        String formattedHp = healthColour + PlaceholderFormatter.compactNumber((int) entityInstance.getHealth()) + "$c❤";
        String noneFormatted =
                healthColour + String.format("%.0f", Math.floor(entityInstance.getHealth())) + "$f/$a" + String.format("%.0f", maxHealth) + "$c❤";

        String customName =
                this.getNameAttribute(NameAttributes.SHOW_HP) ?
                        this.getNameAttribute(NameAttributes.FORMATTED) ? formattedHp : noneFormatted
                        :
                        "";
        customName = levelComponent + getNameAttribute(NameAttributes.CUSTOM_NAME) + " " + customName;

        customName = PlaceholderFormatter.format(customName);

        if (this.getNameAttribute(NameAttributes.CUSTOM_NAME_VISIBLE))
            entityInstance.setCustomName(customName);
    }

    public void despawn() {
        if (entityInstance == null) return;
        if (!entityInstance.isDead()) {
            entityInstance.setHealth(0);
        }
        entityInstance = null;
    }

    /**
     * This is called after the damage calculation is done. Don't call the event again.
     *
     * @param event The event that was called.
     */
    public void damageEntityWithCause(SkyblockPlayerDamageEntityEvent event) {
        if (entityInstance == null) return;
        if (entityInstance.isDead()) return;

        SkyblockPlayer skyblockPlayer = event.getSkyblockPlayer();
        double damage = event.getDamage();

        // Add checks. Maybe if a specific mob is immune to a damage type. (Use interfaces on the nms classes like IAbilityInvulnerable)

        this.lastDamager = skyblockPlayer;

        if (entityInstance == null) return;

        entityInstance.setLastDamageCause(new EntityDamageEvent(entityInstance, EntityDamageEvent.DamageCause.ENTITY_ATTACK, 0.1));

        // To set the last damager
        ((CraftLivingEntity) entityInstance).getHandle().bc = ((CraftPlayer) event.getPlayer()).getHandle();

        this.refreshName();
        boolean showCritEffect = event.isCrit();
        if (event.getDamageType() == SkyblockPlayerDamageEntityEvent.DamageType.ABILITY && event.getAbility() instanceof IHasAbilityDamage)
            showCritEffect = false;
        showDamageIndicator(damage, showCritEffect);

        double newHealth = entityInstance.getHealth() - damage;
        if (newHealth <= 0) {
            // This will kill the entity, triggering the EntityDeathEvent. which is forwards to the SkyblockDeathEvent.
            entityInstance.setHealth(0);
        } else {
            entityInstance.setHealth(newHealth);
            showDamageEffect(skyblockPlayer);
        }

        boolean amIKnockbackResistant = getAttribute(MobAttributes.KNOCKBACK_RESISTANT);

        if (amIKnockbackResistant) {
            Bukkit.getScheduler().runTaskLater(SkyBlock.getInstance(), () -> {
                entityInstance.setVelocity(new Vector(0, 0, 0));
            }, 1);
        }
    }

    public void showDamageIndicator(double damage, boolean showCritEffect) {
        double random = Math.random(); // This is for random spawn location

        Location spawnLocation = entityInstance.getLocation().add(entityInstance.getLocation().getDirection().multiply(random)).add(0, 1, 0);

        if (spawnLocation.getWorld() == null) return;

        assert spawnLocation.getWorld() != null;

        ArmorStand as = spawnLocation.getWorld().spawn(spawnLocation, ArmorStand.class, armorStand -> configureArmorStand(armorStand, damage, showCritEffect));
        int maxAmount = 1;
        if (damageIndicators.size() >= maxAmount) {
            ArmorStand oldest = damageIndicators.poll();
            if (oldest != null && !oldest.isDead()) {
                oldest.remove();
            }
        }
        damageIndicators.add(as);

        new BukkitRunnable() {
            public void run() {
                if (!as.isDead()) {
                    as.remove();
                }
                damageIndicators.remove(as);
            }
        }.runTaskLater(SkyBlock.getInstance(), 10L);
    }

    public void showDamageEffect(SkyblockPlayer player) {
        PacketPlayOutEntityStatus entityStatusPacket = new PacketPlayOutEntityStatus(((CraftLivingEntity) entityInstance).getHandle(), (byte) 2);
        ((CraftPlayer) player.getPlayer()).getHandle().b.sendPacket(entityStatusPacket);
    }

    private void configureArmorStand(ArmorStand armorStand, double damage, boolean showCritEffect) {
        armorStand.setInvisible(true);
        String actualDmg = formatDamageString(damage);
        String star = "§f ✧ ";
        String customName = star + actualDmg + ChatColor.WHITE + " ✧";
        if (!showCritEffect) {
            customName = "§7" + (int) Math.floor(damage);
        }
        armorStand.setCustomName(customName);
        armorStand.setCustomNameVisible(true);
        armorStand.setArms(false);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setSmall(true);
        armorStand.setMarker(true);
        armorStand.setCollidable(false);
    }

    private String formatDamageString(double damage) {
        String dmg = String.valueOf((int) Math.floor(damage));
        StringBuilder actualDmg = new StringBuilder();
        for (int i = 0; i < dmg.length(); i++) {
            char c = dmg.charAt(i);
            switch (i % 4) {
                case 0 -> actualDmg.append("§f");
                case 1 -> actualDmg.append("§e");
                case 2 -> actualDmg.append("§6");
                case 3 -> actualDmg.append("§c");
            }
            actualDmg.append(c);
        }
        return actualDmg.toString();
    }

    public SkyblockMob setCustomName(String customName, boolean formatted) {
        this.setCustomName(customName);
        this.setNameAttribute(NameAttributes.FORMATTED, formatted);

        return this;
    }

    public SkyblockMob setCustomName(String customName) {
        customName = PlaceholderFormatter.format(customName);
        this.setNameAttribute(NameAttributes.CUSTOM_NAME, customName);
        if (entityInstance != null) {
            entityInstance.setCustomName(customName);
            entityInstance.setCustomNameVisible(true);
        }
        return this;
    }

    public SkyblockMob setCustomNameVisible(boolean customNameVisible) {
        this.setNameAttribute(NameAttributes.CUSTOM_NAME_VISIBLE, customNameVisible);
        if (entityInstance != null)
            entityInstance.setCustomNameVisible(customNameVisible);
        return this;
    }

    public SkyblockMob setDamage(double damage) {
        this.setAttribute(MobAttributes.DAMAGE, damage);
        return this;
    }

    public SkyblockMob setDefense(int defense) {
        this.setAttribute(MobAttributes.DEFENSE, defense);
        return this;
    }

    public SkyblockMob setSpeed(int speed) {
        this.setAttribute(MobAttributes.SPEED, speed);
        return this;
    }

    public SkyblockMob setLevel(int level) {
        this.setAttribute(MobAttributes.LEVEL, level);
        return this;
    }

    public SkyblockMob setMaxHealth(double health) {
        this.setAttribute(MobAttributes.MAX_HEALTH, health);
        if (entityInstance != null)
            setEntityInstanceMaxHealth(health);
        return this;
    }

    private void setEntityInstanceMaxHealth(double health) {
        AttributeInstance attributeInstance = entityInstance.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attributeInstance != null;
        attributeInstance.setBaseValue(health);
        entityInstance.setHealth(health);
    }

    public SkyblockMob setDefense(double defense) {
        this.setAttribute(MobAttributes.DEFENSE, defense);
        return this;
    }

    public SkyblockMob setSpeed(double speed) {
        this.setAttribute(MobAttributes.SPEED, speed);
        return this;
    }

    public <T> SkyblockMob setAttribute(MobAttributes key, T value) {
        if (key.getAttribute().getType().isInstance(value)) {
            attributes.put(key, value);
        } else {
            throw new IllegalArgumentException("Value type doesn't match the expected type for the attribute.");
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(MobAttributes key) {
        return (T) attributes.get(key);
    }

    public <T> SkyblockMob setNameAttribute(NameAttributes key, T value) {
        if (key.getAttribute().getType().isInstance(value)) {
            nameAttributes.put(key, value);
        } else {
            throw new IllegalArgumentException("Value type doesn't match the expected type for the attribute.");
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getNameAttribute(NameAttributes key) {
        return (T) nameAttributes.get(key);
    }

    public SkyblockMob setLoot(MobLoot mobLoot) {
        this.mobLoot = mobLoot;
        return this;
    }
}
