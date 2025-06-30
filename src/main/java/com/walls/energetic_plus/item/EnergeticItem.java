package com.walls.energetic_plus.item;

import com.walls.energetic_plus.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.walls.energetic_plus.EnergeticPlus.MOD_ID;

public class EnergeticItem extends Item {
    public EnergeticItem(Settings settings) {
        super(settings);
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final int COOLDOWN_DURATION = 20 * 1;

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            handleItemUsage(world, user, hand);
        }
        return super.use(world, user, hand);
    }

    private void handleItemUsage(World world, PlayerEntity user, Hand hand) {
        ItemStack heldItemStack = user.getStackInHand(hand);
        //ENERGETIC_PLUS
        if (heldItemStack.getItem() == ModItems.ENERGETIC_PLUS) {
            if (!isItemOnCooldown(user, heldItemStack)) {
                int Amplifier = Optional.ofNullable(user.getStatusEffect(ModEffects.WET))
                        .map(effect -> effect.getAmplifier() + 1)
                        .orElse(-1);
                if(!(Amplifier >= 10)){
                    if (isItemMaxDamaged(heldItemStack)) {
                        resetItemAndRemove(heldItemStack, user, hand);
                    } else {
                        damageItemAndSetCooldown(heldItemStack, user);
                    }
                }
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if(user.hasStatusEffect(ModEffects.WET)){
                    if(!(Amplifier >10)){
                        StatusEffectInstance wetEffect = new StatusEffectInstance(ModEffects.WET, 20 * 10 * Amplifier, Amplifier+1);
                        user.removeStatusEffect(ModEffects.WET);
                        user.addStatusEffect(wetEffect);
                        user.sendMessage(Text.translatable("等级+1"), true);
                    }else {
                        user.sendMessage(Text.translatable("已达上限"), true);
                    }

                }else {
                    StatusEffectInstance wetEffect = new StatusEffectInstance(ModEffects.WET, 20*10, 0);
                    user.addStatusEffect(wetEffect);
                    user.sendMessage(Text.translatable("已应用"), true);
                }
            } else {
                user.sendMessage(Text.of("ssss"), false);
            }
        }
        //待定

        //and more~~
    }

//    private boolean isValidItem(ItemStack itemStack) {
//        return itemStack.getItem() == ModItems.ENERGETIC_PLUS;
//    }

    private boolean isItemOnCooldown(PlayerEntity player, ItemStack itemStack) {
        return player.getItemCooldownManager().isCoolingDown(itemStack);
    }

    private boolean isItemMaxDamaged(ItemStack itemStack) {
        return itemStack.getDamage() + 1 >= itemStack.getMaxDamage();
    }

    private void resetItemAndRemove(ItemStack itemStack, PlayerEntity player, Hand hand) {
        itemStack.setDamage(0);
        player.setStackInHand(hand, ItemStack.EMPTY);
    }

    private void damageItemAndSetCooldown(ItemStack itemStack, PlayerEntity player) {
        itemStack.damage(1, player);
        player.getItemCooldownManager().set(itemStack, 20*5);
    }
}