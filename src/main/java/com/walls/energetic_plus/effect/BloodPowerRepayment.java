package com.walls.energetic_plus.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class BloodPowerRepayment extends StatusEffect {
    protected BloodPowerRepayment() {
        super(StatusEffectCategory.BENEFICIAL, 0xff8000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if(entity.getStatusEffect(ModEffects.BLOOD_POWER_REPAYMENT).getDuration() == 1){
            StatusEffectInstance newEffect = new StatusEffectInstance(ModEffects.BLOOD_POWER_REPAYMENT, 10, 2);
            entity.addStatusEffect(newEffect);
        }
        if(entity.isDead()){
            if(entity.getAttacker() instanceof PlayerEntity||entity.getAttacker().hasStatusEffect(ModEffects.BLOOD_POWER_ATTACKER)){
                entity.getAttacker().addStatusEffect(new StatusEffectInstance(ModEffects.BLOOD_POWER_ATTACKER, 20 * 10 + entity.getAttacker().getStatusEffect(ModEffects.BLOOD_POWER_ATTACKER).getDuration(), entity.getStatusEffect(ModEffects.BLOOD_POWER_REPAYMENT).getAmplifier() * 5 + entity.getAttacker().getStatusEffect(ModEffects.BLOOD_POWER_ATTACKER).getAmplifier()));
            } else if (entity.getAttacker() instanceof PlayerEntity) {
                entity.getAttacker().addStatusEffect(new StatusEffectInstance(ModEffects.BLOOD_POWER_ATTACKER, 20 * 10, entity.getStatusEffect(ModEffects.BLOOD_POWER_REPAYMENT).getAmplifier() * 5));
            }
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
