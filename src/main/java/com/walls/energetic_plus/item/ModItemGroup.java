package com.walls.energetic_plus.item;

import com.walls.energetic_plus.EnergeticPlus;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    // 定义并注册自定义物品组
    public static final ItemGroup MAO_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EnergeticPlus.MOD_ID, "mao_group"), // 注册物品组ID
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.energetic_plus_group"))  // 设置物品组名称
                    .icon(() -> new ItemStack(ModItems.ENERGETIC_PLUS)) // 设置物品组图标
                    .entries((displayContext, entries) -> { // 添加物品到物品组中
                        entries.add(ModItems.ENERGETIC_PLUS);
                    }).build());

    // 注册物品组的方法
    public static void registerModItemsGroup() {
    }
}
