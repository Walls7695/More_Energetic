package com.walls.energetic_plus.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
            StatusEffectInstance newEffect = new StatusEffectInstance(ModEffects.BLOOD_POWER_REPAYMENT, 20*10, 2);
            entity.addStatusEffect(newEffect);
        }
        if(entity.isDead()){
            PlayerEntity attacker = entity.getAttackingPlayer();
            StatusEffectInstance effect = entity.getStatusEffect(ModEffects.BLOOD_POWER_REPAYMENT);
            if(attacker instanceof PlayerEntity){
                if(!attacker.getCommandTags().contains("added blood power attacker")){
                    if(attacker.hasStatusEffect(ModEffects.BLOOD_POWER_ATTACKER)){
                        attacker.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOD_POWER_ATTACKER, 20*40 + attacker.getStatusEffect(ModEffects.BLOOD_POWER_ATTACKER).getDuration(), effect.getAmplifier() * 2 + attacker.getStatusEffect(ModEffects.BLOOD_POWER_ATTACKER).getAmplifier()));
                        attacker.sendMessage(Text.of(String.valueOf(effect.getAmplifier() + attacker.getStatusEffect(ModEffects.BLOOD_POWER_ATTACKER).getAmplifier())), false);
                        attacker.addCommandTag("added blood power attacker");
                    } else {
                        attacker.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOD_POWER_ATTACKER, 20*40, effect.getAmplifier() * 2));
                        attacker.sendMessage(Text.of(String.valueOf(effect.getAmplifier())), false);
                        attacker.addCommandTag("added blood power attacker");
                    }
                }
            }
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }
}
