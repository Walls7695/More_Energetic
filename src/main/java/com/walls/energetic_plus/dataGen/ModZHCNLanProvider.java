package com.walls.energetic_plus.dataGen;

import com.walls.energetic_plus.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
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
        translationBuilder.add(ModItems.ENERGY_CONVERTER_BLOOD_POWER_REPAYMENT_TYPE,"能量转换器(血能偿还型)");

        translationBuilder.add("effect."+MOD_ID+".wet","潮湿");
        translationBuilder.add("effect."+MOD_ID+".energy","能量");

        translationBuilder.add("energyItem.tips.level_up", "能量升级");
        translationBuilder.add("energyItem.tips.have_the_maximum", "已达上限");
        translationBuilder.add("energyItem.tips.already_added", "已充能");

        translationBuilder.add("itemGroup.energetic_plus_group", "能量+");
    }
}
