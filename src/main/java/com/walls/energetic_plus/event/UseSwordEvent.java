package com.walls.energetic_plus.event;

import com.walls.energetic_plus.effect.ModEffects;
import com.walls.energetic_plus.library.TextDisplay;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class UseSwordEvent {
    public static void register(){
        // 注册一个事件，当实体受到伤害后触发
        ServerLivingEntityEvents.AFTER_DAMAGE.register((LivingEntity, DamageSource, Stack, Float, Boolean) -> {
            if (!LivingEntity.getWorld().isClient){
                // 检查伤害来源是否为玩家
                if (DamageSource.getAttacker() instanceof PlayerEntity) {
                    // 检查玩家是否手持剑
                    if (((PlayerEntity) DamageSource.getAttacker()).getMainHandStack().isIn(ItemTags.SWORDS)) {
                        // 检查玩家是否拥有能量效果
                        if(((PlayerEntity) DamageSource.getAttacker()).getStatusEffect(ModEffects.ENERGY) != null){
                            // 获取攻击者的能量效果的放大器值
                            int Amplifier = Optional.ofNullable(DamageSource.getAttacker()) // 先检查攻击者是否存在
                                    .filter(entity -> entity instanceof PlayerEntity) // 过滤玩家
                                    .map(entity -> (PlayerEntity) entity) // 转换为 PlayerEntity
                                    .map(player -> player.getStatusEffect(ModEffects.ENERGY)) // 获取效果
                                    .map(effect -> effect.getAmplifier() + 2) // 存在效果时返回 amplifier + 2
                                    .orElse(1); // 攻击者为空、非玩家或无效果时返回 1
                            // 检查实体是否存活
                            if(LivingEntity.isAlive()){
                                // 检查玩家是否还有能量效果
                                if(((PlayerEntity) DamageSource.getAttacker()).getStatusEffect(ModEffects.ENERGY).getAmplifier() != 0){
                                    // 创建一个新的能量效果实例
                                    StatusEffectInstance energyEffect = new StatusEffectInstance(ModEffects.ENERGY, ((PlayerEntity)DamageSource.getAttacker()).getStatusEffect(ModEffects.ENERGY).getDuration(), ((PlayerEntity)DamageSource.getAttacker()).getStatusEffect(ModEffects.ENERGY).getAmplifier()-1);
                                    // 移除攻击者的能量效果
                                    ((PlayerEntity) DamageSource.getAttacker()).removeStatusEffect(ModEffects.ENERGY);
                                    // 给攻击者添加新的能量效果
                                    ((PlayerEntity) DamageSource.getAttacker()).addStatusEffect(energyEffect);
                                    // 伤害实体
                                    LivingEntity.damage((ServerWorld) LivingEntity.getWorld(), DamageSource, Stack* Amplifier);
                                    ((ServerWorld) LivingEntity.getWorld()).spawnParticles(ParticleTypes.ENCHANTED_HIT, LivingEntity.getX(), LivingEntity.getY(), LivingEntity.getZ(), 1, 0, 0, 0, 0);
                                    // 发送消息给攻击者
                                    ((PlayerEntity) DamageSource.getAttacker()).sendMessage(Text.of(String.valueOf(Stack*Amplifier)), true);
                                }else {
                                    // 移除攻击者的能量效果
                                    ((PlayerEntity) DamageSource.getAttacker()).removeStatusEffect(ModEffects.ENERGY);
                                    // 伤害实体
                                    LivingEntity.damage((ServerWorld) LivingEntity.getWorld(), DamageSource, Stack* Amplifier);
                                    ((ServerWorld) LivingEntity.getWorld()).spawnParticles(ParticleTypes.ENCHANTED_HIT, LivingEntity.getX(), LivingEntity.getY(), LivingEntity.getZ(), 1, 0, 0, 0, 0);
                                    // 发送消息给攻击者
                                    ((PlayerEntity) DamageSource.getAttacker()).sendMessage(Text.of("你耗尽了能量！"), true);
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
