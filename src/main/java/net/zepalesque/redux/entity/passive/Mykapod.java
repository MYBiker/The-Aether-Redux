
package net.zepalesque.redux.entity.passive;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.entity.passive.AetherAnimal;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolActions;
import net.zepalesque.redux.client.audio.ReduxSoundEvents;
import net.zepalesque.redux.entity.ReduxEntityTypes;
import net.zepalesque.redux.entity.ai.goal.*;
import net.zepalesque.redux.entity.dataserializer.ReduxDataSerializers;
import net.zepalesque.redux.item.ReduxItems;
import net.zepalesque.redux.misc.ReduxTags;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class Mykapod extends AetherAnimal implements GeoEntity {

    private AnimatableInstanceCache cache;
    @OnlyIn(Dist.CLIENT)
    private int clientAnimTickCount = 0;
    private int sheddingTicker = 0;

    public Mykapod(EntityType<? extends Mykapod> entityType, Level level) {
        super(entityType, level);
        this.cache = GeckoLibUtil.createInstanceCache(this);
        this.refreshDimensions();
    }
    private static final EntityDataAccessor<Float> HURT_ANGLE = SynchedEntityData.defineId(Mykapod.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> HURT_ANGLE_X = SynchedEntityData.defineId(Mykapod.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> HURT_ANGLE_Z = SynchedEntityData.defineId(Mykapod.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<HideStatus> IS_HIDING = SynchedEntityData.defineId(Mykapod.class, ReduxDataSerializers.HIDE_STATUS.get());

    private static final EntityDataAccessor<Boolean> HAS_SHELL = SynchedEntityData.defineId(Mykapod.class, EntityDataSerializers.BOOLEAN);

    public int hideCounter = 0;
    public int hideCooldown = 0;
    public int timeSinceShed = 0;

    @OnlyIn(Dist.CLIENT)
    public State anim;



    private @OnlyIn(Dist.CLIENT) enum State {
        NONE, FEAR, HIDE, UNHIDE, INTERRUPT, SHED;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MykapodPanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new MykapodBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new MykapodTemptGoal(this, 1.25D, Ingredient.of(ReduxTags.Items.MYKAPOD_FOLLOW_ITEMS), false));
        this.goalSelector.addGoal(5, new MykapodWanderGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new MykapodStareGoal(this, Player.class, 6.0F) );
        this.goalSelector.addGoal(7, new MykapodLookGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.1F).add(Attributes.KNOCKBACK_RESISTANCE, 0.75F);
    }

/*    @Override
    public void tick() {
        super.tick();
    }*/

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(IS_HIDING, HideStatus.OUT);
        this.getEntityData().define(HAS_SHELL, true);
        this.getEntityData().define(HURT_ANGLE, 0.0F);
        this.getEntityData().define(HURT_ANGLE_X, 0.0F);
        this.getEntityData().define(HURT_ANGLE_Z, 0.0F);
    }

    public boolean hasShell() {
        return this.getEntityData().get(HAS_SHELL);
    }
    public void setShell(boolean shell) {
        this.getEntityData().set(HAS_SHELL, shell);
    }

    public boolean isHiding() {
        return this.getEntityData().get(IS_HIDING).isHidden();
    }
    public HideStatus hideStatus() {
        return this.getEntityData().get(IS_HIDING);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.clientAnimTickCount++;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide()) {
            if (this.hideCooldown > 0) {
                this.hideCooldown--;
            }

            if (this.sheddingTicker > 0) {
                this.sheddingTicker--;
            }
            if (this.sheddingTicker == 1) {
                this.shedShell();
            }
            if (this.sheddingTicker == 15) {
                this.shedAnim();
            }
            if (this.isHiding() && this.hideCounter > 1) {
                this.hideCounter--;
            } else {
                this.hideCounter = 0;
            }
            if (this.isHiding() && this.hideCounter == 1 && this.random.nextInt(10) == 0) {
                this.setHiding(HideStatus.OUT);
                this.hideCounter = 0;
            }
            if (!this.hasShell()) {
                this.timeSinceShed++;
                if (this.timeSinceShed > 3000 && this.random.nextInt(this.timeSinceShed - 3000) > 150) {
                    this.setShell(true);
                    // TODO: Regrow sound?
                }
            }
        }
    }

    public void setHiding(HideStatus hiding) {
        this.getEntityData().set(IS_HIDING, hiding);
    }

    public enum HideStatus implements StringRepresentable {
        SCARED(true, "scared"), HIDING(true, "hiding"), OUT(false, "out"), INTERRUPTED(false, "interrupted");

        private final boolean hidden;
        private final String id;
        HideStatus(boolean b, String id) {
            this.hidden = b;
            this.id = id;
        }

        public boolean isHidden() {
            return this.hidden;
        }

        @Override
        public String getSerializedName() {
            return this.id;
        }
    }


    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (key == IS_HIDING) {
            this.refreshDimensions();
            if (this.level().isClientSide() && this.clientAnimTickCount > 10) {
                if (this.hideStatus() == HideStatus.OUT) {
                    this.anim = State.UNHIDE;
                } else if (this.hideStatus() == HideStatus.INTERRUPTED) {
                    this.anim = State.INTERRUPT;
                } else if (this.hideStatus() == HideStatus.SCARED) {
                    this.anim = State.FEAR;
                } else if (this.hideStatus() == HideStatus.HIDING) {
                    this.anim = State.HIDE;
                }
            }
        }
    }


    public float getHurtAngleX() {
        return this.getEntityData().get(HURT_ANGLE_X);
    }

    public void setHurtAngleX(float hurtAngleX) {
        this.getEntityData().set(HURT_ANGLE_X, hurtAngleX);
    }

    public float getHurtAngleZ() {
        return this.getEntityData().get(HURT_ANGLE_Z);
    }

    public void setHurtAngleZ(float hurtAngleZ) {
        this.getEntityData().set(HURT_ANGLE_Z, hurtAngleZ);
    }

    public float getHurtAngle() {
        return this.getEntityData().get(HURT_ANGLE);
    }

    public void setHurtAngle(float hurtAngle) {
        this.getEntityData().set(HURT_ANGLE, hurtAngle);
    }



/*    protected SoundEvent getAmbientSound() {
        return SoundEvents.COW_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.COW_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COW_DEATH;
    }
    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(SoundEvents.COW_STEP, 0.15F, 1.0F);
    }*/


/**
     * Returns the volume for the sounds this mob makes.
     */

    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public boolean canFallInLove() {
        return super.canFallInLove() && !this.isHiding();
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        Entity entitySource = source.getEntity();
        if (entitySource instanceof LivingEntity attacker) {


            if (source.is(ReduxTags.DamageTypes.BYPASS_MYKAPOD)) {
                if (this.isHiding()) {
                    this.setHiding(HideStatus.INTERRUPTED);
                    this.hideCounter = Math.max(this.hideCounter - 5, 0);
                    return super.hurt(source, amount);
                }
            } else if (this.isHiding()) {
                if (canAttackShell(attacker.getMainHandItem()) && this.breakShell()) {
                    this.setHiding(HideStatus.INTERRUPTED);
                    return super.hurt(source, amount);
                } else {
                    this.hideCounter = Math.max(this.hideCounter - 5, 0);
                    this.wobbleAttack(entitySource);
                }
            } else {
                if (!canAttackShell(attacker.getMainHandItem())) {
                    this.setHiding(HideStatus.SCARED);
                    this.hideCooldown = 900;
                }
                return super.hurt(source, amount);
            }
        }
        return super.hurt(source, amount);
    }

    private void wobbleAttack(Entity attacker) {
        double a = Math.abs(this.position().x() - attacker.position().x());

        double c = Math.abs(this.position().z() - attacker.position().z());
        if (a > c) {
            this.setHurtAngleZ(1.0F);
            this.setHurtAngleX(0.0F);
            if (this.position().x() > attacker.position().x()) {
                this.setHurtAngleZ(-1.0F);
            }
        } else {
            this.setHurtAngleX(1.0F);
            this.setHurtAngleZ(0.0F);
            if (this.position().z() > attacker.position().z()) {
                this.setHurtAngleX(-1.0F);
            }
        }
        this.setHurtAngle(0.7F - this.getHealth() / 875.0F);
    }
    private void breakParticles() {
        if (!this.level().isClientSide() && this.level() instanceof ServerLevel sl) {
            for (int j = 0; j < 10; j++) {
                double a = this.getBoundingBox().minX + (this.random.nextFloat() * (this.getBoundingBox().maxX - this.getBoundingBox().minX));
                double b = this.getBoundingBox().minY + (this.random.nextFloat() * (this.getBoundingBox().maxY - this.getBoundingBox().minY));
                double c = this.getBoundingBox().minZ + (this.random.nextFloat() * (this.getBoundingBox().maxZ - this.getBoundingBox().minZ));
                sl.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(ReduxItems.MYKAPOD_SHELL_CHUNK.get())), a, b, c, 1, 0, 0, 0, 0);
            }
        }
    }

    protected boolean canAttackShell(ItemStack stack) {
        return stack.canPerformAction(ToolActions.PICKAXE_DIG) || stack.is(AetherTags.Items.SLIDER_DAMAGING_ITEMS);
    }




    public boolean shedShell() {
        if (!this.level().isClientSide() && this.hasShell() && !this.isBaby()) {
            this.breakParticles();
            this.setShell(false);
            this.spawnAtLocation(ReduxItems.MYKAPOD_SHELL_CHUNK.get(), 1);
            this.level().playSound(null, this.position().x, this.position().y, this.position().z, ReduxSoundEvents.MYKAPOD_SHELL_SHED.get(), SoundSource.NEUTRAL, 1, 0.8F + (this.random.nextFloat() * 0.4F));
            return true;
        }
        return false;
    }
    public void shedAnim() {
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte) 17);
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);
        if (id == 17 && this.level().isClientSide()) {
            this.anim = State.SHED;
        }
    }

    public boolean breakShell() {
        if (!this.level().isClientSide() && this.hasShell() && !this.isBaby()) {
            this.breakParticles();
            this.setShell(false);
            this.level().playSound(null, this.position().x, this.position().y, this.position().z, ReduxSoundEvents.MYKAPOD_SHELL_BREAK.get(), SoundSource.NEUTRAL, 1, 0.8F + (this.random.nextFloat() * 0.4F));
            return true;
        }
        return false;
    }
    public void crackShell() {
        if (!this.level().isClientSide() && this.hasShell() && !this.isBaby()) {
            this.breakParticles();
            this.level().playSound(null, this.position().x, this.position().y, this.position().z, ReduxSoundEvents.MYKAPOD_SHELL_CRACK.get(), SoundSource.NEUTRAL, 1, 0.8F + (this.random.nextFloat() * 0.4F));
        }
    }


    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsHiding", this.isHiding());
        compound.putBoolean("HasShell", this.hasShell());
        compound.putInt("HideCounter", this.hideCounter);
        compound.putInt("HideCooldown", this.hideCooldown);
        compound.putInt("TimeSinceShed", this.timeSinceShed);
        compound.putInt("SheddingTicker", this.sheddingTicker);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("IsHiding")) {
            this.setHiding(compound.getBoolean("IsHiding") ? HideStatus.SCARED : HideStatus.OUT);
        }
        if (compound.contains("HasShell")) {
            this.setShell(compound.getBoolean("HasShell"));
        }
        if (compound.contains("HideCounter")) {
            this.hideCounter = compound.getInt("HideCounter");
        }
        if (compound.contains("HideCooldown")) {
            this.hideCooldown = compound.getInt("HideCooldown");
        }
        if (compound.contains("TimeSinceShed")) {
            this.timeSinceShed = compound.getInt("TimeSinceShed");
        }
        if (compound.contains("SheddingTicker")) {
            this.sheddingTicker = compound.getInt("SheddingTicker");
        }
    }


    public EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(1.0F, this.isHiding() ? 0.5F : 1.0F);
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob entity) {
        return ReduxEntityTypes.MYKAPOD.get().create(level);
    }
    public boolean isFood(ItemStack stack) {
        return stack.is(ReduxTags.Items.MYKAPOD_TEMPTATION_ITEMS) && !this.isHiding();
    }

    public boolean canUseToShed(ItemStack stack) {
        return stack.is(ReduxTags.Items.MYKAPOD_SHED_FOOD) && !this.isHiding();
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (this.canUseToShed(itemstack)) {
            if (!this.level().isClientSide && !this.isBaby() && this.hasShell()) {
                this.usePlayerItem(player, hand, itemstack);
                this.setHiding(HideStatus.HIDING);
                this.sheddingTicker = 40;
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }



    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar registrar) {
        registrar.add(new AnimationController<>(this, "controller", 3, this::predicate));
        registrar.add(new AnimationController<>(this, "hideController", 3, this::hiding));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state) {

        if (this.isHiding()) {
            state.getController().setAnimation(RawAnimation.begin().then("animations.mykapod.hidden", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        if (state.isMoving()) {
            Vec3 velocity = this.getDeltaMovement();
            float avgVelocity = (float)(Math.abs(velocity.x) + Math.abs(velocity.z)) / 2f;
            state.getController().setAnimation(RawAnimation.begin().then(avgVelocity > 0.01F ? "animations.mykapod.runaway" : "animations.mykapod.move", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        state.getController().setAnimation(RawAnimation.begin().then("animations.mykapod.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;



    }
    private <T extends GeoAnimatable> PlayState hiding(AnimationState<T> state) {

        if (this.anim == State.FEAR) {
            state.getController().setAnimation(RawAnimation.begin().then("animations.mykapod.scared", Animation.LoopType.PLAY_ONCE));
            this.anim = State.NONE;
        } else if (this.anim == State.HIDE) {
            state.getController().setAnimation(RawAnimation.begin().then("animations.mykapod.hide", Animation.LoopType.PLAY_ONCE));
            this.anim = State.NONE;
        } else if (this.anim == State.SHED) {
            state.getController().setAnimation(RawAnimation.begin().then("animations.mykapod.shed", Animation.LoopType.PLAY_ONCE));
            this.anim = State.NONE;
        } else if (this.anim == State.UNHIDE && state.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            state.getController().setAnimation(RawAnimation.begin().then("animations.mykapod.unhide", Animation.LoopType.PLAY_ONCE));
            this.anim = State.NONE;
        } else if (this.anim == State.INTERRUPT && state.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            state.getController().setAnimation(RawAnimation.begin().then("animations.mykapod.force_unhide", Animation.LoopType.PLAY_ONCE));
            this.anim = State.NONE;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
