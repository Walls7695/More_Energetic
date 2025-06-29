package com.walls.energetic_plus.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.walls.energetic_plus.EnergeticPlus.MOD_ID;

public class EnergeticItem extends Item {
    public EnergeticItem(Settings settings) {
        super(settings);
    }

    //test
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    //处理使用操作
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()){
            DealWithItemObject(world, user, hand);
        }
        return super.use(world, user, hand);
    }

    //处理物品对象
    private void DealWithItemObject(World world, PlayerEntity user, Hand hand) {
        if(user.getStackInHand(hand).getItem() == ModItems.ENERGETIC_PLUS){
            if(!user.getItemCooldownManager().isCoolingDown(user.getStackInHand(hand))){
                if (!(user.getStackInHand(hand).getDamage() ==1)){
                    user.getStackInHand(hand).setDamage(user.getStackInHand(hand).getMaxDamage()-1);
                }else{
                    user.getStackInHand(hand).setDamage(0);
                }
                user.getItemCooldownManager().set(user.getStackInHand(hand), 20*10);
            }else{
                LOGGER.info("lengquezhong~~~");
            }
        }
    }
}
