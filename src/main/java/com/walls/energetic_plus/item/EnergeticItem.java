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
        ItemStack heldItemStack = user.getStackInHand(hand);
        //ENERGETIC_PLUS
        if (heldItemStack.getItem() == ModItems.ENERGETIC_PLUS) {
            if (!isItemOnCooldown(user, heldItemStack)) {
                int Amplifier = Optional.ofNullable(user.getStatusEffect(ModEffects.ENERGY))
                        .map(StatusEffectInstance::getAmplifier)
                        .orElse(-1);

                if(!(Amplifier >= 10)){
                    if (isItemMaxDamaged(heldItemStack)) {
                        resetItemAndRemove(heldItemStack, user, hand);
                    } else {
                        damageItemAndSetCooldown(heldItemStack, user);
                    }
                }
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if(user.hasStatusEffect(ModEffects.ENERGY)){
                    if(!(Amplifier >10)){
                        StatusEffectInstance energyEffect = new StatusEffectInstance(ModEffects.ENERGY, user.getStatusEffect(ModEffects.ENERGY).getDuration()+20*20, Amplifier+1);
                        user.removeStatusEffect(ModEffects.ENERGY);
                        user.addStatusEffect(energyEffect);
                        user.sendMessage(Text.translatable("energyItem.tips.level_up"), true);
                    }else {
                        user.sendMessage(Text.translatable("energyItem.tips.have_the_maximum"), true);
                    }
                }else {
                    StatusEffectInstance energyEffect = new StatusEffectInstance(ModEffects.ENERGY, 20*10, 0);
                    user.addStatusEffect(energyEffect);
                    user.sendMessage(Text.translatable("energyItem.tips.already_added"), true);
                }
            }
        }
        //待定

        //and more~~
    }

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
        player.getItemCooldownManager().set(itemStack, COOLDOWN_DURATION);
    }
}