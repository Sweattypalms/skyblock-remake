package com.sweattypalms.skyblock.core.mobs.regions.end;

import com.sweattypalms.skyblock.core.mobs.builder.ISkyblockMob;
import com.sweattypalms.skyblock.core.mobs.builder.MobAttributes;
import com.sweattypalms.skyblock.core.mobs.builder.SkyblockMob;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.EnumDirection;
import net.minecraft.core.particles.Particles;
import net.minecraft.nbt.GameProfileSerializer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.level.WorldServer;
import net.minecraft.sounds.SoundEffect;
import net.minecraft.sounds.SoundEffects;
import net.minecraft.tags.TagsBlock;
import net.minecraft.tags.TagsFluid;
import net.minecraft.util.MathHelper;
import net.minecraft.util.TimeRange;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSourceIndirect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeProvider;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalUniversalAngerReset;
import net.minecraft.world.entity.ai.targeting.PathfinderTargetCondition;
import net.minecraft.world.entity.monster.EntityEnderman;
import net.minecraft.world.entity.monster.EntityEndermite;
import net.minecraft.world.entity.monster.EntityMonster;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.IMaterial;
import net.minecraft.world.level.RayTrace;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.AxisAlignedBB;
import net.minecraft.world.phys.MovingObjectPositionBlock;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;


public class VoidlingFanatic extends EntityMonster implements IEntityAngerable, ISkyblockMob {
    private static final UUID c = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A1");
    private static final AttributeModifier d;
    private static final int e = 400;
    private static final int bT = 600;
    private static final DataWatcherObject<Optional<IBlockData>> bU;
    private static final DataWatcherObject<Boolean> bV;
    private static final DataWatcherObject<Boolean> bW;
    private int bX = Integer.MIN_VALUE;
    private int bY;
    private static final UniformInt bZ;
    private int ca;
    private UUID cb;

    public static final String ID = "voidling_fanatic";
    private final SkyblockMob skyblockMob;


    static {
        d = new AttributeModifier(c, "Attacking speed boost", 0.15000000596046448, AttributeModifier.Operation.a);
        bU = DataWatcher.a(net.minecraft.world.entity.monster.EntityEnderman.class, DataWatcherRegistry.h);
        bV = DataWatcher.a(net.minecraft.world.entity.monster.EntityEnderman.class, DataWatcherRegistry.i);
        bW = DataWatcher.a(net.minecraft.world.entity.monster.EntityEnderman.class, DataWatcherRegistry.i);
        bZ = TimeRange.a(20, 39);
    }

    /*public VoidlingFanatic(EntityTypes<? extends net.minecraft.world.entity.monster.EntityEnderman> entitytypes, World world) {
        super(entitytypes, world);
        this.O = 1.0F;
        this.a(PathType.i, -1.0F);
    }*/

    public VoidlingFanatic(Location location, SkyblockMob skyblockMob) {
        super(EntityTypes.w, ((CraftWorld) location.getWorld()).getHandle());
        this.skyblockMob = skyblockMob;
        this.skyblockMob
                .setMaxHealth(750_000)
                .setDamage(3_500)
                .setCustomName("$cVoidling Fanatic")
                .setAttribute(MobAttributes.KNOCKBACK_RESISTANT, true)
                .setLevel(85);
    }

    protected void initPathfinder() {
        this.bP.a(0, new PathfinderGoalFloat(this));
        this.bP.a(1, new VoidlingFanatic.a(this));
        this.bP.a(2, new PathfinderGoalMeleeAttack(this, 1.0, false));
        this.bP.a(7, new PathfinderGoalRandomStrollLand(this, 1.0, 0.0F));
        this.bP.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.bP.a(8, new PathfinderGoalRandomLookaround(this));
        this.bP.a(10, new PathfinderGoalEndermanPlaceBlock(this));
        this.bP.a(11, new PathfinderGoalEndermanPickupBlock(this));
        this.bQ.a(1, new PathfinderGoalPlayerWhoLookedAtTarget(this, this::a_));
        this.bQ.a(2, new PathfinderGoalHurtByTarget(this, new Class[0]));
        this.bQ.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityEndermite.class, true, false));
        this.bQ.a(4, new PathfinderGoalUniversalAngerReset(this, false));
    }

    public static AttributeProvider.Builder n() {
        return EntityMonster.fB().a(GenericAttributes.a, 40.0).a(GenericAttributes.d, 0.30000001192092896).a(GenericAttributes.f, 7.0).a(GenericAttributes.b, 64.0);
    }

    public void setGoalTarget(@Nullable EntityLiving entityliving) {
        this.setGoalTarget(entityliving, EntityTargetEvent.TargetReason.UNKNOWN, true);
    }

    public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
        if (!super.setGoalTarget(entityliving, reason, fireEvent)) {
            return false;
        } else {
            entityliving = this.getGoalTarget();
            AttributeModifiable attributemodifiable = this.getAttributeInstance(GenericAttributes.d);
            if (entityliving == null) {
                this.bY = 0;
                this.Y.set(bV, false);
                this.Y.set(bW, false);
                attributemodifiable.removeModifier(d);
            } else {
                this.bY = this.R;
                this.Y.set(bV, true);
                if (!attributemodifiable.a(d)) {
                    attributemodifiable.b(d);
                }
            }

            return true;
        }
    }

    protected void initDatawatcher() {
        super.initDatawatcher();
        this.Y.register(bU, Optional.empty());
        this.Y.register(bV, false);
        this.Y.register(bW, false);
    }

    public void anger() {
        this.setAnger(bZ.a(this.Q));
    }

    public void setAnger(int i) {
        this.ca = i;
    }

    public int getAnger() {
        return this.ca;
    }

    public void setAngerTarget(@Nullable UUID uuid) {
        this.cb = uuid;
    }

    public UUID getAngerTarget() {
        return this.cb;
    }

    public void p() {
        if (this.R >= this.bX + 400) {
            this.bX = this.R;
            if (!this.isSilent()) {
                this.t.a(this.locX(), this.getHeadY(), this.locZ(), SoundEffects.fv, this.getSoundCategory(), 2.5F, 1.0F, false);
            }
        }

    }

    public void a(DataWatcherObject<?> datawatcherobject) {
        if (bV.equals(datawatcherobject) && this.fy() && this.t.y) {
            this.p();
        }

        super.a(datawatcherobject);
    }

    public void saveData(NBTTagCompound nbttagcompound) {
        super.saveData(nbttagcompound);
        IBlockData iblockdata = this.getCarried();
        if (iblockdata != null) {
            nbttagcompound.set("carriedBlockState", GameProfileSerializer.a(iblockdata));
        }

        this.c(nbttagcompound);
    }

    public void loadData(NBTTagCompound nbttagcompound) {
        super.loadData(nbttagcompound);
        IBlockData iblockdata = null;
        if (nbttagcompound.hasKeyOfType("carriedBlockState", 10)) {
            iblockdata = GameProfileSerializer.c(nbttagcompound.getCompound("carriedBlockState"));
            if (iblockdata.isAir()) {
                iblockdata = null;
            }
        }

        this.setCarried(iblockdata);
        this.a(this.t, nbttagcompound);
    }

    boolean g(EntityHuman entityhuman) {
        ItemStack itemstack = (ItemStack)entityhuman.getInventory().i.get(3);
        if (itemstack.a(Blocks.dc.getItem())) {
            return false;
        } else {
            Vec3D vec3d = entityhuman.e(1.0F).d();
            Vec3D vec3d1 = new Vec3D(this.locX() - entityhuman.locX(), this.getHeadY() - entityhuman.getHeadY(), this.locZ() - entityhuman.locZ());
            double d0 = vec3d1.f();
            vec3d1 = vec3d1.d();
            double d1 = vec3d.b(vec3d1);
            return d1 > 1.0 - 0.025 / d0 ? entityhuman.hasLineOfSight(this) : false;
        }
    }

    protected float b(EntityPose entitypose, EntitySize entitysize) {
        return 2.55F;
    }

    public void movementTick() {
        if (this.t.y) {
            for(int i = 0; i < 2; ++i) {
                this.t.addParticle(Particles.T, this.d(0.5), this.da() - 0.25, this.g(0.5), (this.Q.nextDouble() - 0.5) * 2.0, -this.Q.nextDouble(), (this.Q.nextDouble() - 0.5) * 2.0);
            }
        }

        this.bn = false;
        if (!this.t.y) {
            this.a((WorldServer)this.t, true);
        }

        super.movementTick();
    }

    public boolean ex() {
        return true;
    }

    protected void mobTick() {
        if (this.t.isDay() && this.R >= this.bY + 600) {
            float f = this.aY();
            if (f > 0.5F && this.t.g(this.getChunkCoordinates()) && this.Q.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.setGoalTarget((EntityLiving)null);
                this.t();
            }
        }

        super.mobTick();
    }

    protected boolean t() {
        if (!this.t.isClientSide() && this.isAlive()) {
            double d0 = this.locX() + (this.Q.nextDouble() - 0.5) * 64.0;
            double d1 = this.locY() + (double)(this.Q.nextInt(64) - 32);
            double d2 = this.locZ() + (this.Q.nextDouble() - 0.5) * 64.0;
            return this.q(d0, d1, d2);
        } else {
            return false;
        }
    }

    boolean a(Entity entity) {
        Vec3D vec3d = new Vec3D(this.locX() - entity.locX(), this.e(0.5) - entity.getHeadY(), this.locZ() - entity.locZ());
        vec3d = vec3d.d();
        double d0 = 16.0;
        double d1 = this.locX() + (this.Q.nextDouble() - 0.5) * 8.0 - vec3d.b * 16.0;
        double d2 = this.locY() + (double)(this.Q.nextInt(16) - 8) - vec3d.c * 16.0;
        double d3 = this.locZ() + (this.Q.nextDouble() - 0.5) * 8.0 - vec3d.d * 16.0;
        return this.q(d1, d2, d3);
    }

    private boolean q(double d0, double d1, double d2) {
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(d0, d1, d2);

        while(blockposition_mutableblockposition.getY() > this.t.getMinBuildHeight() && !this.t.getType(blockposition_mutableblockposition).getMaterial().isSolid()) {
            blockposition_mutableblockposition.c(EnumDirection.a);
        }

        IBlockData iblockdata = this.t.getType(blockposition_mutableblockposition);
        boolean flag = iblockdata.getMaterial().isSolid();
        boolean flag1 = iblockdata.getFluid().a(TagsFluid.b);
        if (flag && !flag1) {
            boolean flag2 = this.a(d0, d1, d2, true);
            if (flag2 && !this.isSilent()) {
                this.t.playSound((EntityHuman)null, this.u, this.v, this.w, SoundEffects.fw, this.getSoundCategory(), 1.0F, 1.0F);
                this.playSound(SoundEffects.fw, 1.0F, 1.0F);
            }

            return flag2;
        } else {
            return false;
        }
    }

    protected SoundEffect getSoundAmbient() {
        return this.fx() ? SoundEffects.fu : SoundEffects.fr;
    }

    protected SoundEffect getSoundHurt(DamageSource damagesource) {
        return SoundEffects.ft;
    }

    protected SoundEffect getSoundDeath() {
        return SoundEffects.fs;
    }

    protected void dropDeathLoot(DamageSource damagesource, int i, boolean flag) {
        super.dropDeathLoot(damagesource, i, flag);
        IBlockData iblockdata = this.getCarried();
        if (iblockdata != null) {
            this.a((IMaterial)iblockdata.getBlock());
        }

    }

    public void setCarried(@Nullable IBlockData iblockdata) {
        this.Y.set(bU, Optional.ofNullable(iblockdata));
    }

    public @Nullable IBlockData getCarried() {
        return (IBlockData)((Optional)this.Y.get(bU)).orElse((Object)null);
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        Bukkit.broadcastMessage("Damage source: " + damagesource);

        if (this.isInvulnerable(damagesource)) {
            return false;
        } else {
            /*boolean flag = super.damageEntity(damagesource, f);
            Bukkit.broadcastMessage("Would've teleported normally, but we're not doing that right now");

            return flag;*/

            boolean flag = super.damageEntity(damagesource, f);

            // with obfuscation:
            // hurt time (aK):
            this.aK = 0;
            // hurt duration (aL):
            this.aL = 0;

            // Ensure the entity stays in place
            this.setMot(0, 0, 0);


            // Force an update to clients
            this.getWorld().broadcastEntityEffect(this, (byte) 2);

            Bukkit.broadcastMessage("Damage dealt, preventing movement and animation");

            // target the entity that hurt us
            Entity entity = damagesource.getEntity();
            if (entity != null ){
                // persistent target
                this.cb = entity.getUniqueID();
                // remaining anger
                this.ca = 400;
            }


            return flag;
        }
    }

    public boolean fx() {
        return (Boolean)this.Y.get(bV);
    }

    public boolean fy() {
        return (Boolean)this.Y.get(bW);
    }

    public void fz() {
        this.Y.set(bW, true);
    }

    public boolean isSpecialPersistence() {
        return super.isSpecialPersistence() || this.getCarried() != null;
    }

    private static class PathfinderGoalEndermanPickupBlock extends PathfinderGoal {
        private final VoidlingFanatic a;

        public PathfinderGoalEndermanPickupBlock(VoidlingFanatic entityenderman) {
            this.a = entityenderman;
        }

        public boolean a() {
            return this.a.getCarried() != null ? false : (!this.a.t.getGameRules().getBoolean(GameRules.c) ? false : this.a.getRandom().nextInt(20) == 0);
        }

        public void e() {
            Random random = this.a.getRandom();
            World world = this.a.t;
            int i = MathHelper.floor(this.a.locX() - 2.0 + random.nextDouble() * 4.0);
            int j = MathHelper.floor(this.a.locY() + random.nextDouble() * 3.0);
            int k = MathHelper.floor(this.a.locZ() - 2.0 + random.nextDouble() * 4.0);
            BlockPosition blockposition = new BlockPosition(i, j, k);
            IBlockData iblockdata = world.getType(blockposition);
            Vec3D vec3d = new Vec3D((double)this.a.cW() + 0.5, (double)j + 0.5, (double)this.a.dc() + 0.5);
            Vec3D vec3d1 = new Vec3D((double)i + 0.5, (double)j + 0.5, (double)k + 0.5);
            MovingObjectPositionBlock movingobjectpositionblock = world.rayTrace(new RayTrace(vec3d, vec3d1, RayTrace.BlockCollisionOption.b, RayTrace.FluidCollisionOption.a, this.a));
            boolean flag = movingobjectpositionblock.getBlockPosition().equals(blockposition);
            if (iblockdata.a(TagsBlock.ac) && flag && !CraftEventFactory.callEntityChangeBlockEvent(this.a, blockposition, Blocks.a.getBlockData()).isCancelled()) {
                world.a(blockposition, false);
                world.a(this.a, GameEvent.d, blockposition);
                this.a.setCarried(iblockdata.getBlock().getBlockData());
            }

        }
    }

    private static class PathfinderGoalEndermanPlaceBlock extends PathfinderGoal {
        private final VoidlingFanatic a;

        public PathfinderGoalEndermanPlaceBlock(VoidlingFanatic entityenderman) {
            this.a = entityenderman;
        }

        public boolean a() {
            return this.a.getCarried() == null ? false : (!this.a.t.getGameRules().getBoolean(GameRules.c) ? false : this.a.getRandom().nextInt(2000) == 0);
        }

        public void e() {
            Random random = this.a.getRandom();
            World world = this.a.t;
            int i = MathHelper.floor(this.a.locX() - 1.0 + random.nextDouble() * 2.0);
            int j = MathHelper.floor(this.a.locY() + random.nextDouble() * 2.0);
            int k = MathHelper.floor(this.a.locZ() - 1.0 + random.nextDouble() * 2.0);
            BlockPosition blockposition = new BlockPosition(i, j, k);
            IBlockData iblockdata = world.getType(blockposition);
            BlockPosition blockposition1 = blockposition.down();
            IBlockData iblockdata1 = world.getType(blockposition1);
            IBlockData iblockdata2 = this.a.getCarried();
            if (iblockdata2 != null) {
                iblockdata2 = Block.b(iblockdata2, this.a.t, blockposition);
                if (this.a(world, blockposition, iblockdata2, iblockdata, iblockdata1, blockposition1) && !CraftEventFactory.callEntityChangeBlockEvent(this.a, blockposition, iblockdata2).isCancelled()) {
                    world.setTypeAndData(blockposition, iblockdata2, 3);
                    world.a(this.a, GameEvent.g, blockposition);
                    this.a.setCarried((IBlockData)null);
                }
            }

        }

        private boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, IBlockData iblockdata1, IBlockData iblockdata2, BlockPosition blockposition1) {
            return iblockdata1.isAir() && !iblockdata2.isAir() && !iblockdata2.a(Blocks.z) && iblockdata2.r(world, blockposition1) && iblockdata.canPlace(world, blockposition) && world.getEntities(this.a, AxisAlignedBB.a(Vec3D.b(blockposition))).isEmpty();
        }
    }

    private static class PathfinderGoalPlayerWhoLookedAtTarget extends PathfinderGoalNearestAttackableTarget<EntityHuman> {
        private final VoidlingFanatic i;
        private EntityHuman j;
        private int k;
        private int l;
        private final PathfinderTargetCondition m;
        private final PathfinderTargetCondition n = PathfinderTargetCondition.a().d();

        public PathfinderGoalPlayerWhoLookedAtTarget(VoidlingFanatic entityenderman, @Nullable Predicate<EntityLiving> predicate) {
            super(entityenderman, EntityHuman.class, 10, false, false, predicate);
            this.i = entityenderman;
            this.m = PathfinderTargetCondition.a().a(this.k()).a((entityliving) -> {
                return entityenderman.g((EntityHuman)entityliving);
            });
        }

        public boolean a() {
            this.j = this.i.t.a(this.m, this.i);
            return this.j != null;
        }

        public void c() {
            this.k = 5;
            this.l = 0;
            this.i.fz();
        }

        public void d() {
            this.j = null;
            super.d();
        }

        public boolean b() {
            if (this.j != null) {
                if (!this.i.g(this.j)) {
                    return false;
                } else {
                    this.i.a(this.j, 10.0F, 10.0F);
                    return true;
                }
            } else {
                return this.c != null && this.n.a(this.i, this.c) ? true : super.b();
            }
        }

        public void e() {
            if (this.i.getGoalTarget() == null) {
                super.a((EntityLiving)null);
            }

            if (this.j != null) {
                if (--this.k <= 0) {
                    this.c = this.j;
                    this.j = null;
                    super.c();
                }
            } else {
                if (this.c != null && !this.i.isPassenger()) {
                    if (this.i.g((EntityHuman)this.c)) {
                        if (this.c.f(this.i) < 16.0) {
                            this.i.t();
                        }

                        this.l = 0;
                    } else if (this.c.f(this.i) > 256.0 && this.l++ >= 30 && this.i.a((Entity)this.c)) {
                        this.l = 0;
                    }
                }

                super.e();
            }

        }
    }

    private static class a extends PathfinderGoal {
        private final VoidlingFanatic a;
        private EntityLiving b;

        public a(VoidlingFanatic entityenderman) {
            this.a = entityenderman;
            this.a(EnumSet.of(Type.c, Type.a));
        }

        public boolean a() {
            this.b = this.a.getGoalTarget();
            if (!(this.b instanceof EntityHuman)) {
                return false;
            } else {
                double d0 = this.b.f(this.a);
                return !(d0 > 256.0) && this.a.g((EntityHuman) this.b);
            }
        }

        public void c() {
            this.a.getNavigation().o();
        }

        public void e() {
            this.a.getControllerLook().a(this.b.locX(), this.b.getHeadY(), this.b.locZ());
        }
    }


    @Override
    public SkyblockMob getSkyblockMob() {
        return skyblockMob;
    }

    @Override
    public EntityLiving getEntityInstance() {
        return this;
    }

}
