package com.walls.energetic_plus.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static com.walls.energetic_plus.EnergeticPlus.MOD_ID;

public class ModItems {

    public static final Item ENERGETIC_PLUS = register("energetic_plus", Item::new, new Item.Settings().maxCount(1));

    // 注册单个物品的方法
    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    // 注册所有自定义物品的方法，并将它们添加到指定的物品组中
    public static void registerModItems() {

    }
}
