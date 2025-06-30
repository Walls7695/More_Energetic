package com.walls.energetic_plus.dataGen;

import com.walls.energetic_plus.effect.ModEffects;
import com.walls.energetic_plus.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static com.walls.energetic_plus.EnergeticPlus.MOD_ID;

public class ModZHCNLanProvider extends FabricLanguageProvider {
    public ModZHCNLanProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput,"zh_cn", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.ENERGETIC_PLUS,"能量+");
        translationBuilder.add("effect."+MOD_ID+".wet","潮湿");
        translationBuilder.add("effect."+MOD_ID+".energy","能量");

        translationBuilder.add("itemGroup.energetic_plus_group", "能量+");
    }
}
