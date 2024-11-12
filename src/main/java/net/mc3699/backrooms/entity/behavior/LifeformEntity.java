package net.mc3699.backrooms.entity.behavior;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class LifeformEntity extends Monster {

    public final AnimationState idleAnimState = new AnimationState();
    private int idleTimeout = 0;

    @Override
    public void tick() {
        super.tick();
    }

    public LifeformEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    protected void registerGoals() {
        goalSelector.addGoal(0, new StalkGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.MOVEMENT_SPEED, .5D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ARMOR, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 100D);
    }
}
