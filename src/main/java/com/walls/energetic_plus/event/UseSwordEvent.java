package com.walls.energetic_plus.event;

import com.walls.energetic_plus.effect.ModEffects;
import com.walls.energetic_plus.modTag.ModItemTags;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Optional;
import java.util.logging.Logger;

public class UseSwordEvent {
    public static void register(){
        ServerLivingEntityEvents.AFTER_DAMAGE.register((LivingEntity, DamageSource, Stack, Float, Boolean) -> {
            if (DamageSource.getAttacker() instanceof PlayerEntity) {
                if (((PlayerEntity) DamageSource.getAttacker()).getMainHandStack().isIn(ItemTags.SWORDS)) {
                    int Amplifier = Optional.ofNullable(DamageSource.getAttacker()) // 先检查攻击者是否存在
                            .filter(entity -> entity instanceof PlayerEntity) // 过滤玩家
                            .map(entity -> (PlayerEntity) entity) // 转换为 PlayerEntity
                            .map(player -> player.getStatusEffect(ModEffects.ENERGY)) // 获取效果
                            .map(effect -> effect.getAmplifier() + 2) // 存在效果时返回 amplifier + 2
                            .orElse(1); // 攻击者为空、非玩家或无效果时返回 1
                    LivingEntity.setHealth(LivingEntity.getHealth() - Stack * Amplifier);
                }
            }
        });
    }
}
