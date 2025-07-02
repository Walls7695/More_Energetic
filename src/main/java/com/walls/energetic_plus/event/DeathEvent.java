package com.walls.energetic_plus.event;

import com.walls.energetic_plus.effect.ModEffects;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;

public class DeathEvent {
    public static void register(){
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
//            if (entity.hasStatusEffect(ModEffects.BLOOD_POWER_REPAYMENT) || source.getAttacker() instanceof PlayerEntity) {
//                PlayerEntity player = (PlayerEntity) source.getAttacker();
//                if (player != null) {
//                    player.addStatusEffect(new StatusEffectInstance(ModEffects.BLOOD_POWER_ATTACKER, 20 * 10, entity.getStatusEffect(ModEffects.BLOOD_POWER_REPAYMENT).getAmplifier() * 5));
//                }
//            }
        });
    }
}
