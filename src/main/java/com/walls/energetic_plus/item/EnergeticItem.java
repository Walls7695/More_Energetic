package com.walls.energetic_plus.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EnergeticItem extends Item {
    public EnergeticItem(Settings settings) {
        super(settings);
    }

    @Override
    //处理使用操作
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()){
            Item(world, user, hand);
        }
        return super.use(world, user, hand);
    }

    //处理物品对象
    private void Item(World world, PlayerEntity user, Hand hand) {
        if(user.getStackInHand(hand) == ModItems.ENERGETIC_PLUS.getDefaultStack()){
            user.speed=10;
        }
    }
}
