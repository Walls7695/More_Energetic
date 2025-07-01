package com.walls.energetic_plus.item;

import com.walls.energetic_plus.effect.ModEffects;
import com.walls.energetic_plus.modTag.ModItemTags;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Optional;


public class EnergeticItem extends Item {
    public EnergeticItem(Settings settings) {
        super(settings);
    }

    //test
    //public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final int COOLDOWN_DURATION = 20 * 5;

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            handleItemUsage(world, user, hand);
        }
        return super.use(world, user, hand);
    }

    private void handleItemUsage(World world, PlayerEntity user, Hand hand) {
        //获取玩家手持物品
        ItemStack heldItemStack = user.getStackInHand(hand);
        //ENERGETIC_PLUS
        if (heldItemStack.getItem() == ModItems.ENERGETIC_PLUS) {
            //判断物品是否在冷却中
            if (!isItemOnCooldown(user, heldItemStack)) {
                //获取玩家身上的ENERGY效果等级
                int Amplifier = Optional.ofNullable(user.getStatusEffect(ModEffects.ENERGY))
                        .map(StatusEffectInstance::getAmplifier)
                        .orElse(-1);

                //判断ENERGY效果等级是否大于等于10
                if(!(Amplifier >= 10)){
                    //判断物品是否已经损坏
                    if (isItemMaxDamaged(heldItemStack)) {
                        //重置物品并移除
                        resetItemAndRemove(heldItemStack, user, hand);
                    } else {
                        //损坏物品并设置冷却
                        damageItemAndSetCooldown(heldItemStack, user);
                    }
                }
                //播放升级音效
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0F, 1.0F);
                //判断玩家是否已经有ENERGY效果
                if(user.hasStatusEffect(ModEffects.ENERGY)){
                    //判断ENERGY效果等级是否大于10
                    if(!(Amplifier >10)){
                        //创建ENERGY效果实例
                        StatusEffectInstance energyEffect = new StatusEffectInstance(ModEffects.ENERGY, user.getStatusEffect(ModEffects.ENERGY).getDuration()+20*20, Amplifier+1);
                        //移除ENERGY效果
                        user.removeStatusEffect(ModEffects.ENERGY);
                        //添加ENERGY效果
                        user.addStatusEffect(energyEffect);
                        //发送升级提示
                        user.sendMessage(Text.translatable("energyItem.tips.level_up"), true);
                    }else {
                        //发送已经达到最大等级提示
                        user.sendMessage(Text.translatable("energyItem.tips.have_the_maximum"), true);
                    }
                }else {
                    //创建ENERGY效果实例
                    StatusEffectInstance energyEffect = new StatusEffectInstance(ModEffects.ENERGY, 20*10, 0);
                    //添加ENERGY效果
                    user.addStatusEffect(energyEffect);
                    //发送已经添加提示
                    user.sendMessage(Text.translatable("energyItem.tips.already_added"), true);
                }
            }
        }
        //待定

        //and more~~
    }

// 判断玩家是否在冷却中
    private boolean isItemOnCooldown(PlayerEntity player, ItemStack itemStack) {
        // 返回玩家物品冷却管理器中是否正在冷却物品
        return player.getItemCooldownManager().isCoolingDown(itemStack);
    }

// 判断物品是否损坏到最大程度
    private boolean isItemMaxDamaged(ItemStack itemStack) {
        // 获取物品的损坏程度
        return itemStack.getDamage() + 1 >= itemStack.getMaxDamage();
    }

// 重置物品并移除
    private void resetItemAndRemove(ItemStack itemStack, PlayerEntity player, Hand hand) {
        // 将物品的耐久度设置为0
        itemStack.setDamage(0);
        // 将玩家的手上的物品设置为空
        player.setStackInHand(hand, ItemStack.EMPTY);
    }

// 伤害物品并设置冷却时间
    private void damageItemAndSetCooldown(ItemStack itemStack, PlayerEntity player) {
        // 伤害物品
        itemStack.damage(1, player);
        // 设置物品冷却时间
        player.getItemCooldownManager().set(itemStack, COOLDOWN_DURATION);
    }
}