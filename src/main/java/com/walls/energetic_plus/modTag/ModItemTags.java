package com.walls.energetic_plus.modTag;

import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> SWORD = of("sword");

    private static  TagKey<Item> of(String id){
        return TagKey.of(RegistryKeys.ITEM, Identifier.ofVanilla(id));
    }

    public static void registerModItemTags() {
    }
}
