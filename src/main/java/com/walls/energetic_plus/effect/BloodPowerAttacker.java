package com.walls.energetic_plus.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class BloodPowerAttacker extends StatusEffect {
    public BloodPowerAttacker() {
        super(StatusEffectCategory.NEUTRAL, 0xff8000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {

        return super.applyUpdateEffect(world, entity, amplifier);
    }
}

