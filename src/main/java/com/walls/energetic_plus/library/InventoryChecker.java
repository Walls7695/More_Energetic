package com.walls.energetic_plus.library;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class InventoryChecker {
    // 检查玩家是否拥有特定物品
    public static boolean hasItem(PlayerEntity player, Item targetItem) {
        if(targetItem == null){
            return false;
        }

        // 检查主手物品
        Item mainHandItem = player.getMainHandStack().getItem();
        ItemStack mainHandStack = player.getMainHandStack();
        if (isMatchingItem(mainHandItem, targetItem)) {
            targetItemStackResult = mainHandStack;
            return true;
        }

        // 检查副手物品
        Item offHandItem = player.getOffHandStack().getItem();
        ItemStack offHandStack = player.getOffHandStack();
        if (isMatchingItem(offHandItem, targetItem)) {
            targetItemStackResult = offHandStack;
            return true;
        }

        // 检查玩家背包格子(0-35)
        for (int i = 0; i < player.getInventory().size(); i++) {
            Item item = player.getInventory().getStack(i).getItem();
            ItemStack stack = player.getInventory().getStack(i);
            if (isMatchingItem(item, targetItem)) {
                targetItemStackResult = stack;
                return true;
            }
        }

        return false;
    }

    // 检查两个物品是否匹配
    private static boolean isMatchingItem(Item stack, Item targetItem) {
        if(stack == targetItem){
            return true;
        }
        return false;
    }

    public static ItemStack targetItemStackResult = null;
}
