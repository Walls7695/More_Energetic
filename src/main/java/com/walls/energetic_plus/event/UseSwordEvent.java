package com.walls.energetic_plus.event;

import com.walls.energetic_plus.effect.ModEffects;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;

import java.util.Optional;

public class UseSwordEvent {
    public static void register(){
        // 注册一个事件，当实体受到伤害后触发
        ServerLivingEntityEvents.AFTER_DAMAGE.register((LivingEntity, DamageSource, Stack, Float, Boolean) -> {
            if(LivingEntity.getCommandTags().contains("attacked")){
                LivingEntity.removeCommandTag("attacked");
            }else {
                // 如果伤害来源是玩家
                if (DamageSource.getAttacker() instanceof PlayerEntity) {
                    // 如果玩家的主手物品是剑
                    if (((PlayerEntity) DamageSource.getAttacker()).getMainHandStack().isIn(ItemTags.SWORDS)) {
                        // 获取攻击者的能量效果
                        int Amplifier = Optional.ofNullable(DamageSource.getAttacker()) // 先检查攻击者是否存在
                                .filter(entity -> entity instanceof PlayerEntity) // 过滤玩家
                                .map(entity -> (PlayerEntity) entity) // 转换为 PlayerEntity
                                .map(player -> player.getStatusEffect(ModEffects.ENERGY)) // 获取效果
                                .map(effect -> effect.getAmplifier() + 2) // 存在效果时返回 amplifier + 2
                                .orElse(1); // 攻击者为空、非玩家或无效果时返回 1
                        // 伤害实体
                        LivingEntity.addCommandTag("attacked");
                        LivingEntity.damage(DamageSource.getAttacker().getServer().getOverworld(), DamageSource, Stack*Amplifier);
                        ((PlayerEntity) DamageSource.getAttacker()).sendMessage(Text.of("You have attacked with " + Stack*Amplifier + " damage"), false);
                    }
                }
            }
        });
    }
}
