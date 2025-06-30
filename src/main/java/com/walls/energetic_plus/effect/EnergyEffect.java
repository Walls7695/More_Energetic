package com.walls.energetic_plus.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class EnergyEffect extends StatusEffect {
    protected EnergyEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xff8000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if(entity.getStatusEffect(ModEffects.ENERGY).getDuration() == 1){
            world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0F, 1.0F);
        };

        return super.applyUpdateEffect(world, entity, amplifier);
    }


}
